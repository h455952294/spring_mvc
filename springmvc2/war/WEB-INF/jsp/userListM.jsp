<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>springmvc</title>
</head>
<body>
    springmvc_ModelAndView<br/>
    <c:forEach items="${userList}" var="u">
        ${u.userName},${u.userId}<br/>
    </c:forEach>
</body>
</html>
