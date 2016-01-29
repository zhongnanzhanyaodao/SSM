<%@ page  contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap core CSS -->
    <!-- <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"> -->
    
      <link href="${ctx}/static/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
   
      
   
  </head>
  <body>
    <h1>你好，世界！</h1>
	
	<div class="dropdown">
	  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
	    Dropdown
	    <span class="caret"></span>
	  </button>
	  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
	    <li><a href="#">Action</a></li>
	    <li><a href="#">Another action</a></li>
	    <li><a href="#">Something else here</a></li>
	    <li><a href="#">Separated link</a></li>
	  </ul>
	</div>
	<br/>
	<br/>
	<br/>
	<!--进度条  -->
	<div class="progress">
	  <div class="progress-bar" style="width: 0%;"></div>
	</div>
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   <!--  <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script> -->
     <script src="${ctx}/static/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
     <!-- <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
    <script src="${ctx}/static/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
    <script type="text/javascript">
      var p = 0;
      $(function(){
        run();
      });
      function run(){
            p+=5;
            $("div[class=progress-bar]").css("width",p+"%");
            if(p<100){
                var timer=setTimeout("run()",500);
            }else{
                alert("加载完毕！");
            }
       }
	</script> 
  </body>
</html>