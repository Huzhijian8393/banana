#DailyReport

##20180709
###1.FINISHED：
```
1)完成http自动跳转https功能
    a)自定义配置tomcat时，注意：2.0.3以上版本的Spring用TomcatServletWebServerFactory
      而以下版本的用TomcatEmbeddedServletContainerFactory。
      以下是核心代码：
      TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
          @Override
          public void postProcessContext(Context context) {
              // 安全约束
              SecurityConstraint securityConstraint = new SecurityConstraint();
              // "confidential"，机密的，表示信任的
              securityConstraint.setUserConstraint("CONFIDENTIAL");
              SecurityCollection securityCollection = new SecurityCollection();
              securityCollection.addPattern("/*");
              securityConstraint.addCollection(securityCollection);
              // context添加安全约束
              context.addConstraint(securityConstraint);
          }
      };
      
      Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
      // 设置请求头为http而不是https
      connector.setScheme("http");
      // Connector监听的http的端口号
      connector.setPort(80);
      // 启用https加密连接时必须降secure设置成false
      connector.setSecure(false);
      // 监听到http的端口号后转向到的https的端口号，必须和tomcat的端口号设置成一致
      connector.setRedirectPort(443);

      tomcat.addAdditionalTomcatConnectors(connector);
2)Facvion的设置
    若要设置自己的facvion只需将facvion.icon文件放置在类路径根目录、
    类路径MATA-INF\resources下、类路径resources下、类路径static或者
    类路径public下。此处将其放置在src\main\resources\static目录下。
```
###2.TODO：
```
1)为了系统兼容性应该新增“是否启用SSL加密”的配置项，当然这在application.yml中有“server.ssl.enable”配置项，
   故在“访问http自动转https”功能代码中应做相应修改，也即：
   a)如果禁用SSL加密连接，当正常访问以http开头的地址时，正常访问，不做https的跳转；
   b)如果禁用SSL加密连接，当访问以https开头的地址时，做http的跳转；
   c)如果启用SSL加密连接，当访问以http开头的地址时，做https的跳转；
   d)如果启用SSL加密连接，当访问以https开头的地址时，正常访问，不做http的跳转。
```

##20180710
###1.FINISHED
```
    
```
###2.TODO
```

```

##20180711
###1.FINISHED
```

```
###2.TODO
```

```
