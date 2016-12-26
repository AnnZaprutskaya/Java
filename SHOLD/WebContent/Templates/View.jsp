<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<section class="main">
<figure class="viewFig">
<img src="../img/${param.mode}.png" alt="type-icon" height="190px" width="auto" />
</figure>
<div class="viewItem">
    <c:forEach items="${param.list}" var="item">
		<br>${item}
    </c:forEach>
</div>
</section>
</body>
