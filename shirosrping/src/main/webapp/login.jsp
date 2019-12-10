<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/8
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/login.action" method="post">
        userName:<input type="text" name="userName" value="${user.userName}" />
        password:<input type="password" name="password" value="${user.password}" />
        <input type="submit" value="登录"/><font color="red">${errorMsg}</font>
    </form>
</body>
</html>
