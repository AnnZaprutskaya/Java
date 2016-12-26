<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/Templates/Header.jsp" />
<jsp:include page="/Templates/contentHeader.jsp" />
<body>
<section class="main">
	       <jsp:include page="/Templates/View.jsp" >
           <jsp:param name="list" value="${listOfData}" />
           </jsp:include>
           <jsp:include page="/Templates/viewButtons.jsp" >
           <jsp:param name="key" value="${requestScope.key}" />
           <jsp:param name="mode" value="${requestScope.mode}" />
           </jsp:include>
 </section>
</body>