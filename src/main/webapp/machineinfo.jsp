<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<body background="myimage.jpg">
<marquee  direction="left" height="30%"><h1 align="orange"><font align="center" color="Green">Welcome to Demo Application of Spring Boot </font></h1> </marquee>
<h1 align="center"><font  color="orange"> Machineinfo Details are as Follows </font></h1>
<table border = 1  align="center">
<td width="300"> <h2>  ${hostname} </h2></td>
<td width="300"> <h2>${ip} </h2> </td>
<td width="300"> <h2>${os} </h2></td>
</table>






</body>
</html>