<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/Templates/Header.jsp" />
<jsp:include page="/Templates/contentHeader.jsp" />
<body>
<section class="main">
<form action="${pageContext.request.contextPath}/content/edit" method="POST">
<br/>Группа
<br/><input required type="text" name="group_number" style="width:400px;" value="${listOfData['groupnumber']}">
<br/><input type="submit" value="Добавить">
</form>
</section>
</body>