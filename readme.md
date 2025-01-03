# Lab Management

**首页**
- 获取当前教师课表。课表中包含：课程信息，实验命名，周次等等

**课程管理**
- CRUD
- 增加列表，模态框输入
- 在前端限制用户输入。要求是数字就不不能输入字母

**预约**
- 加载当前教师的全部课程，仅加载课程的名称。不需要加载课程所有信息。当选择指定课程之后，再加载某门课详细信息
- 渲染已经选择次数及总次数：4/8 （如何获得选中次数？）（首页已经加载课表数据，可以缓存，不需要重新请求？）
- 所有的实验室全部列出，区分出人数不够的教室。前提（加载全部可用实验室的名称，做成标签？ 表格可与课表的表格复用）
- 点击任何一个小方块，弹出模态框，包含所有课程信息。可选择指定周
- 重新预约，不更新，而是直接移除再重新创建（单独在某一个组件里移除？）
- 自己的课可以用别的颜色突出显示

**12.22**  
在测试`login.http`时出了问题，返回的data对象除了`account`字段均为空：
```json
{
  "code": 200,
  "message": null,
  "data": {
    "id": null,
    "name": null,
    "account": "1234567890",
    "role": null,
    "telephone": null,
    "createTime": null,
    "updateTime": null
  }
}
```
原因：没有在`application.yml`中添加如下配置：
```yml
  jackson:
    default-property-inclusion: non_null
```
添加之后成功读出：
```json
{
  "code": 200,
  "data": {
    "id": "01JFMDR2KH87DXM7V3C1PW1YEX",
    "name": "杨过",
    "account": "1234567890",
    "role": "ikp1",
    "telephone": "12345678981",
    "createTime": "2024-12-21T18:53:52",
    "updateTime": "2024-12-21T18:53:52"
  }
}
```
但是我不知道为什么

**12.30**  
写完了一个简单的查看实验室的admincontroller的功能：
```java
    @GetMapping("labs")
    public ResultVO findAllLabs() {
        return ResultVO.succuss(adminService.findAllLabs());
    }
```
测试：
```http request
###
GET http://localhost:8080/api/admin/labs
token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic2ZlMyIsInVpZCI6IjAxSkdBUDhFNVNGVlRQWURaUTdGS0FFRUdEIiwiaWF0IjoxNzM1NTI1Nzk0LCJleHAiOjE3MzU2MTIxOTR9.elTK-aKfFxPKMT5IBjIktF1W8pKaCxbgsOvzNLfycgI

```
总是返回：
```json
"No static resource api/admin/labs."
```
很简单的实现，看了几遍没有错，其他相同类型的功能也没有错。重启项目也没用。最后清除缓存，成功。

**1.1**  
粗心错误,记录一下避免再犯
测试添加单个功能的时候总是报错：
```json
{
  "code": 400,
  "message": "rawPassword cannot be null"
}
```
测试servic没问题，所以问题在controller上，发现是每带请求体注解`@RequestBody`
```java
 @PostMapping("users")
    public ResultVO addSingleUser(@RequestBody User user) {
        adminService.addSingleUser(user);
        return ResultVO.succuss(user);
    }
```
加上后解决