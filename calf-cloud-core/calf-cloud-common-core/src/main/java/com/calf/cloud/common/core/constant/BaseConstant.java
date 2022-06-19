/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2019-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@gmail.com
 *   @Version    V1.0
 *   @Date:   2022年06月19日 13时33分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Description
 *   -----------------------------------------------------------------------------------
 *  2022-06-19 13:33:39    fengzijk         1.0         Why & What is modified: <修改原因描述>
 *
 *
 */

package com.calf.cloud.common.core.constant;

public class BaseConstant {


    public static final String ALL = "/**";

    public static final String OAUTH_ALL = "/oauth/**";

    public static final String OAUTH_AUTHORIZE = "/oauth/authorize";

    public static final String OAUTH_CHECK_TOKEN = "/oauth/check_token";

    public static final String OAUTH_CONFIRM_ACCESS = "/oauth/confirm_access";

    public static final String OAUTH_TOKEN = "/oauth/token";

    public static final String OAUTH_TOKEN_KEY = "/oauth/token_key";

    public static final String OAUTH_ERROR = "/oauth/error";

    public static final String OAUTH_MOBILE = "/oauth/mobile";

    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * 社交登录，传递的参数名称
     */
    public static final String DEFAULT_PARAMETER_NAME_SOCIAL = "social";

    /**
     * 验证码 key
     */
    public static final String VALIDATE_CODE_KEY = "key";
    /**
     * 验证码 code
     */
    public static final String VALIDATE_CODE_CODE = "code";
    /**
     * 认证类型参数 key
     */
    public static final String GRANT_TYPE = "grant_type";
    /**
     * 登录类型
     */
    public static final String LOGIN_TYPE = "login_type";

    /**
     * 刷新模式
     */
    public static final String REFRESH_TOKEN = "refresh_token";
    /**
     * 授权码模式
     */
    public static final String AUTHORIZATION_CODE = "authorization_code";
    /**
     * 客户端模式
     */
    public static final String CLIENT_CREDENTIALS = "client_credentials";
    /**
     * 密码模式
     */
    public static final String PASSWORD = "password";
    /**
     * 简化模式
     */
    public static final String IMPLICIT = "implicit";

    public static final String SIGN_KEY = "USER";

    public static final String CAPTCHA_KEY = "USER.captcha.";

    public static final String SMS_CODE_KEY = "USER.sms.code.";

    public static final String CAPTCHA_HEADER_KEY = "key";

    public static final String CAPTCHA_HEADER_CODE = "code";


    public static final String HEADER_TOKEN = "USER-Auth";

    /**
     * 自定义client表名
     */
    public static final String CLIENT_TABLE = "base_manager_client";

    public static final String ENCRYPT = "{USER}";

    public static final String CAPTCHA_ERROR = "验证码不正确！";

    public static final String SUPER_ADMIN = "admin";

    /**
     * 基础查询语句
     */
    public static final String CLIENT_BASE = "select client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, " +
            "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity," +
            "refresh_token_validity, additional_information, autoapprove from " + CLIENT_TABLE;

    public static final String FIND_CLIENT_DETAIL_SQL = CLIENT_BASE + " order by client_id";

    public static final String SELECT_CLIENT_DETAIL_SQL = CLIENT_BASE + " where client_id = ?";

    /**
     * 标志
     */
    public static final String FROM = "from";

    /**
     * 内部
     */
    public static final String FROM_IN = "Y";

    /**
     * 权限标识前缀
     */
    public static final String USER_PERMISSION_PREFIX = "USER.permission.";

    /**
     * 用户ID
     */
    public static final String USER_ID = "userId";

    /**
     * 用户名
     */
    public static final String USER_NAME = "userName";

    /**
     * 用户头像
     */
    public static final String USER_AVATAR = "avatar";


}
