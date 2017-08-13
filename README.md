
### 一个单独的上传文件服务（one upload file servlet）

#### 目的

 + 上传文件的方式越来越多样,根据开发和运行环境的不同，把它独立出来也越来越必要.
 + 如果仅仅需要一个上传文件的功能，以前必定是要依赖jar；但在servlet 3.1之后，一个上传文件的功能除了依赖的servlet容器jar外，却不依赖jar.

### 怎么用
 
 +  找到配置文件 `upload.properties`
 +  配置本地上传的目录 `location`
 +  配置该文件服务的ip和端口 `hostUrl`
 +  运行`Start.java` main函数并在test.html中进行测试
 
### 说明

  + `upload.properties` 中还有其他的属性,包括：上传文件的name属性,上传文件的大小,允许上传的文件格式(nameProperty,maxFileSize,suffixFilter).
  + 因为采用的jetty 容器，所以真正的部署时，要用maven打包成**Fat Jar**并配置运行的main类