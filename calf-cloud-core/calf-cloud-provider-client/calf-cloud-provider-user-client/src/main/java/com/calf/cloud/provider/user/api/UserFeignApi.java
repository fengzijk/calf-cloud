/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2022年04月10日 08时18分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2022-04-10 08:18:50    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.provider.user.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
/**
*-------------------------------------------------
* <pre>用户服务Feign</pre>
* @author : guozhifeng
* @date : 10/4/2022 上午8:32
*--------------------------------------------------
*/
@FeignClient(value = "calf-cloud-user",fallback = UserFeignApiHystrix.class )
public interface UserFeignApi {

    @GetMapping(value = "/test/getTest")
    String getTest();


}

