<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Common Page </h1>  
    <p>Hello <security:authentication property="principal.username"/>:<security:authentication property="principal.sysUsers.userName"/>, 每个人都能访问的页面.</p> 
    
    <a href="<%=request.getContextPath() %>/main/admin"> Go AdminPage </a>  
    <br />  
    <a href="<%=request.getContextPath() %>/auth/logout">退出登录</a>
    
       
    <security:authorize access="hasRole('ROLE_ADMIN')">
    <br />  这个标签用来决定它的内容是否会被执行.
	    This content will only be visible to users who have  
	    the "ROLE_ADMIN" authority in their list of GrantedAuthoritys.  
	</security:authorize>
	<security:authorize url="/main/admin"> 
    <br />   这个标签用来决定它的内容是否会被执行.
	    <a href="<%=request.getContextPath() %>/main/admin"> Go AdminPage </a>  
	</security:authorize> 
	<security:authorize url="/auth/login"> 
    <br />   这个标签用来决定它的内容是否会被执行.
	    <a href="<%=request.getContextPath() %>/auth/logout">退出登录</a>
	</security:authorize>
</body>
</html>