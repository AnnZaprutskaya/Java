<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/Templates/Header.jsp" />
<jsp:include page="/Templates/contentHeader.jsp" />
<body>
<section class="main">
<form action="${pageContext.request.contextPath}/content/edit" method="POST">
<br/>Название
<br/><input required type="text" name="name" style="width:400px;" value="">
<br/>Количество часов
<br/><input required type="number" name="hours" style="width:400px;" value="">
<br/>Профессор
<br/><select name='professor'>    
    <c:forEach items="${listOfProfessors}" var="item">
    <c:if test="${listOfData['professor'] == item.key }">
        <option selected value="item.key">${item.value}</option>
        </c:if>
        <c:if test="${!(listOfData['professor'] == item.key) }">
        <option value="item.key">${item.value}</option>
        </c:if>
    </c:forEach>
</select>
<br/><input type="submit" value="Добавить">
</form>
</section>
</body>