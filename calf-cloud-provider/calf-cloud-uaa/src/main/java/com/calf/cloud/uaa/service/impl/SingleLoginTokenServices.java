/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 15时50分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 15:50:30    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.uaa.service.impl;

import java.util.Date;
import java.util.UUID;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.transaction.annotation.Transactional;

public class SingleLoginTokenServices extends DefaultTokenServices {

	private TokenStore tokenStore;
	private TokenEnhancer accessTokenEnhancer;

	/**
	 * 是否登录同应用同账号互踢
	 */
	private boolean isSingleLogin;

	public SingleLoginTokenServices(boolean isSingleLogin) {
		this.isSingleLogin = isSingleLogin;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
		OAuth2AccessToken existingAccessToken = tokenStore.getAccessToken(authentication);
		OAuth2RefreshToken refreshToken = null;
		if (existingAccessToken != null) {
			if (isSingleLogin) {
				if (existingAccessToken.getRefreshToken() != null) {
					tokenStore.removeRefreshToken(existingAccessToken.getRefreshToken());
				}
				tokenStore.removeAccessToken(existingAccessToken);
			} else if (existingAccessToken.isExpired()) {
				if (existingAccessToken.getRefreshToken() != null) {
					refreshToken = existingAccessToken.getRefreshToken();
					// The token store could remove the refresh token when the
					// access token is removed, but we want to
					// be sure...
					tokenStore.removeRefreshToken(refreshToken);
				}
				tokenStore.removeAccessToken(existingAccessToken);
			}
			else {
				// Re-store the access token in case the authentication has changed
				tokenStore.storeAccessToken(existingAccessToken, authentication);
				return existingAccessToken;
			}
		}

		// Only create a new refresh token if there wasn't an existing one
		// associated with an expired access token.
		// Clients might be holding existing refresh tokens, so we re-use it in
		// the case that the old access token
		// expired.
		if (refreshToken == null) {
			refreshToken = createRefreshToken(authentication);
		}
		// But the refresh token itself might need to be re-issued if it has
		// expired.
		else if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
			ExpiringOAuth2RefreshToken expiring = (ExpiringOAuth2RefreshToken) refreshToken;
			if (System.currentTimeMillis() > expiring.getExpiration().getTime()) {
				refreshToken = createRefreshToken(authentication);
			}
		}

		OAuth2AccessToken accessToken = createAccessToken(authentication, refreshToken);
		tokenStore.storeAccessToken(accessToken, authentication);
		// In case it was modified
		refreshToken = accessToken.getRefreshToken();
		if (refreshToken != null) {
			tokenStore.storeRefreshToken(refreshToken, authentication);
		}
		return accessToken;

	}

	private OAuth2RefreshToken createRefreshToken(OAuth2Authentication authentication) {
		if (!isSupportRefreshToken(authentication.getOAuth2Request())) {
			return null;
		}
		int validitySeconds = getRefreshTokenValiditySeconds(authentication.getOAuth2Request());
		String value = UUID.randomUUID().toString();
		if (validitySeconds > 0) {
			return new DefaultExpiringOAuth2RefreshToken(value, new Date(System.currentTimeMillis()
					+ (validitySeconds * 1000L)));
		}
		return new DefaultOAuth2RefreshToken(value);
	}

	private OAuth2AccessToken createAccessToken(OAuth2Authentication authentication, OAuth2RefreshToken refreshToken) {
		DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
		int validitySeconds = getAccessTokenValiditySeconds(authentication.getOAuth2Request());
		if (validitySeconds > 0) {
			token.setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
		}
		token.setRefreshToken(refreshToken);
		token.setScope(authentication.getOAuth2Request().getScope());

		return accessTokenEnhancer != null ? accessTokenEnhancer.enhance(token, authentication) : token;
	}

	@Override
	public void setTokenStore(TokenStore tokenStore) {
		this.tokenStore = tokenStore;
		super.setTokenStore(tokenStore);
	}

	@Override
	public void setTokenEnhancer(TokenEnhancer accessTokenEnhancer) {
		this.accessTokenEnhancer = accessTokenEnhancer;
		super.setTokenEnhancer(accessTokenEnhancer);
	}
}