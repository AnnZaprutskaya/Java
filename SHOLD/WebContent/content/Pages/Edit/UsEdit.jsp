<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/Templates/Header.jsp" />
<jsp:include page="/Templates/contentHeader.jsp" />
<body>
<section class="main">
<form action="${pageContext.request.contextPath}/content/edit" method="POST">
<br/>Пользователь
<br/><input required type="text" name="id" style="width:400px;" value="${listOfData['username']}">
<br/>Роль
<br/><input required type="text" name="role" style="width:400px;" value="${listOfData['role']}">
<br/><input type="submit" value="Добавить">
</form>
</section>
</body>