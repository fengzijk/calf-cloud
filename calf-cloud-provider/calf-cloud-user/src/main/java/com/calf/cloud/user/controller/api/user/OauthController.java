/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月05日 16时12分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-05 16:12:22    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.user.controller.api.user;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * -------------------------------------------------
 * <pre>Oauth</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/23 12:51
 * --------------------------------------------------
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {



    /**
     * 自定义返Token回格式
     *
     * @param accessToken token
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author : guozhifeng
     * @date : 2021/10/7 11:50
     */

    private Map<String, Object> customJwt( ) {

        return new HashMap<>();
    }
}