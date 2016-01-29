<%@ page  contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>测试文件上传</title>
	<script type="text/javascript">
		function exportExcel(){
		    var url="${ctx}/excel/export";
		    window.open(url);
		}
	</script>
  </head>
  
  <body>
  
   <form id="fileUploadForm" name="fileUploadForm"  align="center" method="post" action="${ctx}/user/fileUpload" enctype="multipart/form-data">  
	   <div align="center">
			<tr>  
				<td>File:</td>  
				<td><input type="file" name="thefile"></td>  
			</tr>  
			<tr>  
				<td><input type="hidden" name="fileOwner" value="1002"/></td>  
				<td><input type="submit" value="submit"></td>  
			</tr>
			<br/>
			<br/>
			<tr>  
				<td><input type="button" value="导出Excel" onclick="exportExcel()"/></td>  
			</tr>  
		</div>
	</form>
	
  </body>
</html>