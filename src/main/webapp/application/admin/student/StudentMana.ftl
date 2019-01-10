<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>

<!-- jQuery框架 -->
<script type="text/javascript"  src="/static/plugin/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript"  src="/static/application/admin/student/student.js"></script>
<script>
	total = <#if total??>${total}</#if>
</script>



<body style="background-color: #DCDCDC;">
	<#if studentList??>
	   <table width="100%" border="1" cellpadding="0" cellspacing="0">
		   <tr><th>ID</th><th>姓名</th><th>密码</th><th>学号</th>
			   <#if studentList?size == 0>
				   <tr><td colspan="7" style="text-align:center">暂无学生</td></tr>
			   <#else >
			   <#list studentList as student>
				   <tr>
					   <td><#if student.id??>${student.id}</#if></td>
                       <td><#if student.name??>${student.name}</#if></td>
                       <td><#if student.password??>${student.password}</#if></td>
                       <td><#if student.stuNumber??>${student.stuNumber}</#if></td>
				   </tr>
			   </#list>
			   </#if>
		   </tr>
	   </table>
	</#if>
	<div id="getPage"></div>
<div id="pageNumber"></div>

</body>
</html>