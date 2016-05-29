# bookSystem
> 系统分析与设计课程项目

## 简介
bookSystem 是一个电影订票系统。

## 所用工具
* ###前端
	* node
	* grunt
	* jQuery
* ###后端
	* mevan
	* spring
	* jdk
    * mysql

	
## start
1. 安装必要的支持库(jdk, node, mysql, mevan)
2. `npm install`
3. `grunt` (生成前端文件， 此时会停止在 watch 任务，所以请打开第二个终端进行以下步骤)
4. `mvn install` (安装必要的 java 包， 比如 sping)
5. `mvn toncat7:run` (编译后端代码并运行 web 服务器)
6. 可以愉快的进行 PY 交易

## 已有的 url
* `localhost:8080/app` ：主页
* `localhost:8080/app/book` 订票页面

## 目录结构
* 前端
    * 源文件：fron/src/
    * build 目录：src/main/webapp
* 后端
    * MVC配置文件：src\main\java\thymeleafexamples\stsm\application
    * Model: src\main\java\thymeleafexamples\stsm\business\entities
    * Controller: src\main\java\thymeleafexamples\stsm\web\controller
    * View: src\main\webapp

## License
 BookSystem is licensed under the MIT license. (http://opensource.org/licenses/MIT)