<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<jsp:include page="/Templates/Header.jsp" />
<jsp:include page="/Templates/contentHeader.jsp" />
<body>
<section class="main">
${requestScope.Message}
<form action="${pageContext.request.contextPath}/login" method="POST">
<br/>Имя пользователя:
<br/><input type="text" name="login" style="width:400px;" value="">
<br/>Пароль:
<br/><input type="password" name="password" style="width:400px;" value="">
<br/><input type="submit" value="Войти">
</form>
</section>
</body>