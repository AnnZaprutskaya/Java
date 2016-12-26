<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<br>
<c:if test="${sessionScope.User.role == 'Admin'}">
<button onclick="location.href='${pageContext.request.contextPath}/content/edit?mode=${param.mode}&key=${param.key}'" type="button">Изменить</button>
</c:if>
<form action="${pageContext.request.contextPath}/content/list" method="POST">
<br/><input type="submit" value="Удалить">
</form>
</body>