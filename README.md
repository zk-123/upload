
### 一个单独的上传文件服务（one upload file servlet）

#### purpose

 + want to deploy to the server individually.
 + Only the interface calls.

### how use

 + test.html file is a  example which you just make a little configuration use it.
 + upload.properties file contains four properties.
    + **uploadDir**   you can upload to which Dir(AbsolutePath).
    + **hostUrl** you can access uploadDir path.
    + **file** you will upload file  property name  default 'file'.
    + **suffixFilter** you must split it by ','.it will intercept file by what you set here.
 
 + log4j.properties also will create a log file  default name 'log4j';

