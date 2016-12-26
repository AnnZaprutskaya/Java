<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/Templates/Header.jsp" />
<jsp:include page="/Templates/contentHeader.jsp" />
<body>
<section class="main">
<form action="${pageContext.request.contextPath}/content/edit" method="POST">
<br/>Предмет:
<br/><select name='study'>    
    <c:forEach items="${listOfStudies}" var="item">
    <c:if test="${listOfData['study'] == item.key }">
        <option selected value="item.key">${item.value}</option>
        </c:if>
        <c:if test="${!(listOfData['study'] == item.key) }">
        <option value="item.key">${item.value}</option>
        </c:if>
    </c:forEach>
</select>
<br/>Студент:
<br/><select name='student'>    
    <c:forEach items="${listOfStudents}" var="item">
    <c:if test="${listOfData['student'] == item.key }">
        <option selected value="item.key">${item.value}</option>
        </c:if>
        <c:if test="${!(listOfData['student'] == item.key) }">
        <option value="item.key">${item.value}</option>
        </c:if>
    </c:forEach>
</select>
<br/>Дата:
<br/><input required type="text" name="date" style="width:400px;" value="">
<br/>Преподаватель:
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
<br/>Отметка:
<br/><input required type="text" name="mark" style="width:400px;" value="">
<br/>Комментарии:
<br/><input required type="text" name="comments" style="width:400px;" value="">
<br/><input type="submit" value="Добавить">
</form>
</section>
</body>