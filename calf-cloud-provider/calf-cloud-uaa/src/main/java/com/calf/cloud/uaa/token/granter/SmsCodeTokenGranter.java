package com.calf.cloud.uaa.token.granter;


import com.calf.cloud.common.core.constant.Oauth2Constant;
import com.calf.cloud.starter.redis.adpter.RedisAdapter;
import com.calf.cloud.uaa.handle.auth.sms.SmsCodeAuthenticationToken;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;


/**
 * -------------------------------------------------
 * <pre>手机号验证码登录TokenGranter</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/5 23:39
 * --------------------------------------------------
 */
public class SmsCodeTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "sms";

    private final AuthenticationManager authenticationManager;

    private RedisAdapter redisAdapter;

    public SmsCodeTokenGranter(AuthenticationManager authenticationManager,
      AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
      OAuth2RequestFactory requestFactory, RedisAdapter redisAdapter) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.redisAdapter = redisAdapter;
    }

    protected SmsCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
      ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String mobile = parameters.get("mobile");
        String code = parameters.get("code");

        if (StringUtils.isBlank(code)) {
            throw new UserDeniedAuthorizationException("请输入验证码！");
        }

        String codeFromRedis = null;
        // 从Redis里读取存储的验证码信息
        try {
            codeFromRedis = redisAdapter.get(Oauth2Constant.SMS_CODE_KEY + mobile).toString();
        } catch (Exception e) {
            throw new UserDeniedAuthorizationException("验证码不存在！");
        }

        if (codeFromRedis == null) {
            throw new UserDeniedAuthorizationException("验证码已过期！");
        }
        // 比较输入的验证码是否正确
        if (!StringUtils.equalsIgnoreCase(code, codeFromRedis)) {
            throw new UserDeniedAuthorizationException("验证码不正确！");
        }

        redisAdapter.delete(Oauth2Constant.SMS_CODE_KEY + mobile);

        Authentication userAuth = new SmsCodeAuthenticationToken(mobile);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        }
        // If the username/password are wrong the spec says we should send 400/invalid grant

        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + mobile);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
