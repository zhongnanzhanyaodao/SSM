<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>登录页面</title>
  </head>
  
  <body>
   		<form id="loginForm" name=loginForm  method="post" action="${ctx}/admin/login/login" >  
		   <div align="center">
				<tr>  
					<td>姓名:</td>  
					<td><input type="name" name="name"></td>  
				</tr>  
				<tr>  
					<td>密码:</td>  
					<td><input type="password" name="password"></td>  
				</tr>
				<div id="messageBox" <c:if test="${message == null}">style="display: none;"</c:if>>  
					<label id="loginError" >用户名或密码错误, 请重试!</label>
				</div>
				<br/>
				<br/>
				<tr>  
					<td><input type="submit" value="登录" /></td>  
				</tr>  
			</div>
		</form>
	
  </body>
</html>