
# 一个单独的上传文件服务（one upload file servlet）
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/monsterLin/Pigeon/master/LICENSE)
[![Travis](https://img.shields.io/travis/rust-lang/rust.svg)](http://shields.io/)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)]()
#### 目的

 + 上传文件的方式越来越多样,根据开发和运行环境的不同，把它独立出来也越来越必要.
 + 如果仅仅需要一个上传文件的功能，以前必定是要依赖jar；但在servlet 3.1之后，一个上传文件的功能除了依赖的servlet容器jar外，却不依赖jar.

### 怎么用
 +  环境需要：mvn + jdk
 +  找到配置文件 `upload.properties`
 +  配置本地上传的目录 `location`
 +  配置该文件服务的ip和端口 `hostUrl`
 +  运行`Start.java` main函数并在test.html中进行测试
 
### 具体怎么用
 + 1、下载java,并配置环境变量，[点击这里](https://www.baidu.com/link?url=_aHOakriK2ItSzf8hWSKQfUE6Pj-EY23BFggfjkR-y40HKp9JEQwpw4rs4cAsmYkQzItWs70yR0iradnH8IcdgNIlnMs9gjZInWVOYNyMGe&wd=&eqid=e72f78db00014f4a00000002599a4f73) 查看教程
 
 + 2、下载maven,[点击这里](http://maven.apache.org/download.cgi) 查看下载地址
 + 3、配置maven环境变量,[点击这里](https://www.baidu.com/link?url=WjjhnbNAzgtZ8IdZEh_LWWicMG_diGfwYzBZYZ4TosoibfQKDOCpCAC094wDyimQgh_l0dRrePZOYh42FGunSm31gu4cpgxhWlTr6yeKQd7&wd=&eqid=f3f6a0d3000141f700000002599a4e84)查看教程
 + 4、打开命令窗口，输入`mvn -v`,出现版本说明，则配置成功
 + 5、下载该项目，[点击这里下载](https://github.com/zk-123/upload/archive/master.zip)
 + 6、解压该项目，并配置文件
 
 + 7、配置完成后，打开该项目目录;`shift + 鼠标右键` 从该位置打开命令窗口;输入`mvn clean package`就能打包成`fat jar`. 
 + 8、如果想打包成war：将pom文件中`package`节点改成war,在项目目录下输入`mvn clean package`打包成`war`,文件上传目录需要输入tomcat的webapp新建的一个upload目录的路径
 + 9、打包成`fat jar`后，按照[这里](https://www.baidu.com/link?url=iEtlW6cq3YGYY4RqNAoQvH_Ms1in5tIbk3r_RsO6AF8wjdQBlju6mnLf0kdWTuHMJYCe-U6Vl1YzI21V2XMYp66EHEoaY94eN1Mf3dJ3NC3&wd=&eqid=dd6294760001bfcf00000002599a5284)的教程，配置成windows服务，或者其他服务或者不配置，直接运行jar

### 说明

  + `upload.properties` 中还有其他的属性,包括：上传文件的name属性,上传文件的大小,允许上传的文件格式(nameProperty,maxFileSize,suffixFilter).
  
  + 因为采用的jetty 容器，所以真正的部署时，要用maven打包成**Fat Jar**并配置运行的main类.
  
  + linux下运行**Fat jar**如果省事的话,直接挂起或者创建软链接进而定义成一个服务启动；windows下可以通过[exe4j](http://hnpy.onlinedown.net/down/exe4j_windows-x64_5_0_1.zip)将jar包变成服务启动。

