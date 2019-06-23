# 文档生成器
## 说明
   生成器基于spring boot、swagger2和肖玉明的swagger-ui。设置好参数后一键生成文档，并打成可运行jar包，只需在命令行中执行java -jar
   container.jar，即可在浏览器中访问:localhost:8080/doc.html
## 技术难点记录
   * idea提供的文档不足，好多api不会用，又查不到
   * 读取jar包中的文件
   * 插件的classpath问题
   * 动态编译
   * 对jar包操作