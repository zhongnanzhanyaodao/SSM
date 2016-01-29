<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>普通用户主页</title>
	<script type="text/javascript">
		function logout(){
		    var url="${ctx}/login/logout";
		    window.location.href = url;
		}
	</script>
  </head>
  
  <body>
          欢迎来到主页!
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <input type="button" value="退出" onclick="logout()"/>
  </body>
</html>