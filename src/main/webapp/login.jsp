<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <form action="/Image/uploadHeadPic" method="post" enctype="multipart/form-data">  
    <table>  
        <tr>  
            <td width="100" align="right">照片：</td>  
            <td><input type="file" name="file"/> 提交  <input type="submit"></td>  
        </tr>  
    </table>  
</form>  
<form name="serForm" action="/Image/uploadHeadPic" method="post"  enctype="multipart/form-data">
<h1>采用流的方式上传文件</h1>
<input type="file" name="file">
<input type="submit" value="upload"/>
</form>
 
<form name="Form2" action="/Image/uploadHeadPic" method="post"  enctype="multipart/form-data">
<h1>采用multipart提供的file.transfer方法上传文件</h1>
<input type="file" name="file">
<input type="submit" value="upload"/>
</form>
 
<form name="Form2" action="Image/uploadHeadPic.do" method="post"  enctype="multipart/form-data">
<h1>使用spring mvc提供的类的方法上传文件</h1>
<input type="file" name="file">
<!--  <input type="text"  name="id" value="1" />
 <input  type="text"  name="usertype" value="1" />  --> 
<input type="submit" value="upload"/>
</form>
  </body>
</html>
