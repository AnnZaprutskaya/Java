<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/Templates/Header.jsp" />
<jsp:include page="/Templates/contentHeader.jsp" />

<body>
<section class="main">
    <c:forEach items="${listOfData}" var="item">
           <jsp:include page="/Templates/Preview.jsp" >
           <jsp:param name="item" value="${item.value}" />
           <jsp:param name="key" value="${item.key}" />
           <jsp:param name="mode" value="${requestScope.mode}" />
           </jsp:include>
    </c:forEach>
    <br>
    <button onclick="location.href='${pageContext.request.contextPath}/content/logoff'" type="button">Выйти</button>
    <c:if test="${requestScope.mode == 'Sd'||requestScope.mode == 'St'||requestScope.mode == 'Pr'}">

	<form action="${pageContext.request.contextPath}/content/list?mode=${requestScope.mode}" method="POST">
		<br/><input type="submit" value="Пересчитать отметки">
	</form>
    </c:if>
    <c:if test="${sessionScope.User.role == 'Admin'}">
    <br>
<button onclick="location.href='${pageContext.request.contextPath}/content/edit?mode=${requestScope.mode}'" type="button">Добавить</button>
</c:if>
</section>
</body>
