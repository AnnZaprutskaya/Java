<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/Templates/Header.jsp" />
<jsp:include page="/Templates/contentHeader.jsp" />
<body>
<section class="main">
<form action="${pageContext.request.contextPath}/content/edit" method="POST">
<br/>Имя
<br/><input required type="text" name="first_name" style="width:400px;" value="${listOfData['firstname']}">
<br/>Фамилия
<br/><input required type="text" name="second_name" style="width:400px;" value="${listOfData['secondname']}">
<br/>Отчество
<br/><input required type="text" name="father_name" style="width:400px;" value="${listOfData['fathername']}">
<br/>Дата рождения
<br/><input required type="date" name="birth_date" style="width:400px;" value="${listOfData['birthdate']}">
<br/><input type="submit" value="Добавить">
</form>
</section>
</body>