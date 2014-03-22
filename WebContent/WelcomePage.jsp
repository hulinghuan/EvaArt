<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eva_Art</title>
</head>
<body>
	<head>login test</head>
	<s:form action="login">
		<s:textfield name="loginEmail" value=""/>
		<s:textfield name="password" value=""/>
		<s:submit key="login" value="login"/>
	</s:form>
	<head>register_test</head>
	<s:form action="register">
		<s:textfield name="loginEmail" value=""/>
		<s:textfield name="password" value=""/>
		<s:submit key="register" value="register"/>
	</s:form>
</body>
</html>