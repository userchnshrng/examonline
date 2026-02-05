# SpringBoot-Vue-OnlineExam

这是一个用于学习 Spring Boot + Vue 的在线考试系统示例工程。前后端分离，后端使用 Spring Boot + MyBatis Plus，前端使用 Vue 2 + Element-UI。

## 项目结构（简要）
- `springboot/`：后端工程（Maven）
  - `src/main/java/com/exam`：后端代码（controller/service/mapper/entity）
  - `src/main/resources/application.properties`：配置
- `exam/`：前端工程（Vue）
  - `src/`：前端源码（components/router/vuex）

## 要求与建议环境
- Java 1.8 (JDK8)
- Maven（项目自带 `mvnw` 可直接使用）
- Node.js 14.x（推荐 `v14.17.0`）
- npm（建议 v6 或 v7）
- MySQL 5.7/8.0

## 快速启动步骤
下面步骤假设你在 Windows 环境，IDEA 已安装，命令在项目根目录执行。

### 1. 导入数据库
1. 在 MySQL 中创建数据库：
```sql
CREATE DATABASE exam DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```
2. 使用提供的 SQL 文件导入表结构与测试数据：
`sql/exam.sql`。

### 2. 配置后端数据库连接
编辑 `springboot/src/main/resources/application.properties`，修改数据库连接信息：
```properties
spring.datasource.username=root
spring.datasource.password=你的密码
spring.datasource.url=jdbc:mysql://localhost:3306/exam?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC
```

### 3. 启动后端（两种方法）
- 在 IntelliJ IDEA 中打开 `springboot` 文件夹为项目，找到 `ExamsystemApplication` 启动类，Run。 
- 或在命令行（项目 `springboot` 或根目录）使用 Maven wrapper：
```powershell
# 在项目根目录
cd springboot
..\mvnw.cmd spring-boot:run
```

后端默认监听端口：`8080`。

### 4. 启动前端
打开命令行进入 `exam` 目录：
```powershell
cd exam
npm install --legacy-peer-deps
npm run dev
```
前端默认 dev 服务器地址：`http://localhost:8088`，前端开发环境中配置了代理 `/api` 指向 `http://localhost:8080`，因此前端请求 `/api/...` 将会被转发到后端。

## 已实现的扩展功能（小结）
- 教务公告系统（`notice` 表）：增删改查接口 + 前端列表与管理页面
- Excel 导出：教师可导出某次考试成绩（后端 `ExportController`，前端考试列表增加“导出成绩”按钮）
  - 导出接口示例：`GET /api/export/score/{examCode}`
- Excel 导入：管理员可批量导入学生（后端 `ImportController`，前端学生管理页使用 `el-upload` 上传）
  - 导入接口示例：`POST /api/import/students`（form field `file`）

## 重要文件说明（你会修改这些文件）
- 登录页：`exam/src/components/common/login.vue`（去掉默认预填账号密码可在这里修改）
- 主页公告：`exam/src/components/common/hello.vue`（已接入公告 API）
- 侧边栏：`exam/src/components/common/mainLeft.vue`（菜单项、唯一展开配置）
- 考试功能介绍：`exam/src/components/teacher/examDescription.vue`
- 导出控制器（后端）：`springboot/src/main/java/com/exam/controller/ExportController.java`
- 导入控制器（后端）：`springboot/src/main/java/com/exam/controller/ImportController.java`
- 学生导入 VO：`springboot/src/main/java/com/exam/vo/StudentImportVO.java`

## 常用命令汇总
- 后端编译并运行（Maven wrapper）：
```powershell
cd springboot
..\mvnw.cmd spring-boot:run
```
- 前端安装依赖并运行：
```powershell
cd exam
npm install --legacy-peer-deps
npm run dev
```

## 常见问题与排查（快速指南）
- npm 报 EPERM / mkdir 权限错误：以管理员身份运行终端或修改 `D:\nodejs` 文件夹权限。
- npm 依赖冲突（ERESOLVE）：使用 `npm install --legacy-peer-deps` 或 `--force`。
- 运行 `webpack-dev-server` 出现 `http_parser` 或 `No such module: http_parser`：请使用 Node 14.x（推荐 `v14.17.0`）。
- 后端无法连接数据库：检查 `application.properties` 中的 URL/用户名/密码，并确认 `exam` 数据库已导入 `sql/exam.sql`。
- 前端请求 404 或 Cannot GET /api/...：确认前端代理 `exam/config/index.js` 中 `proxyTable` 指向 `http://localhost:8080`，并确保后端已启动且接口路径正确。

## 安全与权限注意
- 默认数据库测试账号密码为示例，请上线前修改默认密码和安全设置。

## 示例账号（数据库 `sql/exam.sql` 中的测试数据）
- 管理员：9527 / 123456
- 教师：20081001 / 123456
- 学生：20154084 / 123456

这是一个基于 [原项目名称 (Original Project Name)](https://github.com/OriginalAuthor/OriginalRepo) 改进的项目。
