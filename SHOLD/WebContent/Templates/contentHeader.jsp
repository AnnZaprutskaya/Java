<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<body>
<style>
body
{
	
	background-image: url("../img/3.jpg"); /* Путь к фоновому рисунку */
    background-position: left bottom; /* Положение фона */
    background-repeat: repeat; /* Повторяем фон по горизонтали */
	}
header{
background-color:#fff;
border-radius:15px;
height: 150px;
width:92%;
margin: 10px 50px;
padding-bottom:10px;
}
a
{
color:#000;
}
a:hover
{
color:#6699CC;
font-weight:normal;
text-decoration:none;
}
header a
{
margin-top:50%;
margin: 0 30px;
font-size:20px;
color:#000;
}
header a:hover
{
text-decoration:underline;
font-weight:normal;
color:#006699;
}
section.main
{
background-color:#fff;
border-radius:15px;
margin: 40px 50px;
padding:20px 50px;
text-align:left;
}
input[type="submit"] {
padding: 10px;
background-color:#6699CC;
margin:10px;
}
input[type="submit"]:hover {
background-color:#33CCFF;
}
div.viewItem
{
float:left;
border: 2px #ccc dashed;
border-radius:20px;
padding:20px;
width:40%;
clear:right;
}
figure.viewFig
{
float:left;
mrgin:10px 20px;
}
</style>

<header>
<section class="logo">
<img src="../img/ic.png" alt="logo" height="110px" width="auto" />
<img src="../img/logo.png" alt="name" height="120px" width="auto" />
</section>
<a href="${pageContext.request.contextPath}/content/list?mode=St">Студенты</a>
<a href="${pageContext.request.contextPath}/content/list?mode=Gr">Группы</a>
<a href="${pageContext.request.contextPath}/content/list?mode=Pr">Преподаватели</a>
<a href="${pageContext.request.contextPath}/content/list?mode=Sd">Предметы</a>
<a href="${pageContext.request.contextPath}/content/list?mode=Mr">Оценки</a>
<c:if test="${sessionScope.User.role == 'Admin'}">
   <a href="${pageContext.request.contextPath}/content/list?mode=Us">Пользователи</a>
</c:if>

</header>

</body>