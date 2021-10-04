# calf-cloud

![SpringBoot](https://img.shields.io/badge/SpringBoot-2.4.6-lightBlue.svg)
![SpringCloud](https://img.shields.io/badge/SpringCloud-2020.0.3-lightBlue.svg)
![MybatusPlus](https://img.shields.io/badge/mybatis--plus-3.4.5-orange)
![Redission](https://img.shields.io/badge/redission-3.16.3-orange)
![JAVA](https://img.shields.io/badge/JAVA-1.8+-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.0+-green.svg)
![badge](https://img.shields.io/badge/buil-passing-green.svg)

# 项目介绍
calf-cloud是自己在工作中总结积累搭建的基于Spring Cloud微服务化开发平台的后端Java服务的开发脚手架。
 代码简洁，架构清晰，适合学习和直接项目中使用。 核心技术采用Spring Boot 2.4.6、Spring Cloud (2020.0.4)以及eureka 相关核心组件,目前在进一步完善中
 后续会推出SpringCloudAlibaba版本
## 项目架构
### 项目结构
--- 更新中

## 1. swagger 
### 1.1 swagger配置
```yml
swagger:
  enable: true
  title: 文档管理-用户模块
  version: V0.0.1
  description: 用户模块-文档管理
  author: fengzijk
  email: guozhifengvip@gmail.com
  requestParameter: true
  termsOfServiceUrl: http//:fengzijk.com
  basePackage: 
   - com.calf.cloud.user.controller

```
### 1.2 swagger效果图
![image](https://user-images.githubusercontent.com/12505138/135797722-add829a4-4a74-49cc-a39c-6625211920d2.png)



# 全局异常及统一返回结果
## 配置



## 异常示例 

```json
{
"code": 404,
"msg": "资源不存在",
"data": "No handler found for GET /api/userinfo/listUserIn",
"timestamp": 1633324501496,
"success": false
}
```
![image](https://user-images.githubusercontent.com/12505138/135798117-0f004b1c-34f6-40a7-a427-0c77707cf14c.png)

## 返回结果示例
```java
    @GetMapping("listUserInfo")
    @ApiOperation(value = "获取用户列表", httpMethod = "GET")
    public String listUserInfo() {
        return "sucess";
    }
```
![image](https://user-images.githubusercontent.com/12505138/135798326-38027b5b-ffe4-41ea-8cf2-e47a5015dd5c.png)

