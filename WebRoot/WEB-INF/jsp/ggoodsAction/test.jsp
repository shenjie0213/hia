<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@  include file="/WEB-INF/jsp/public/commons.jspf"%>
<html>

	<head>
		<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  var pagenum="111";

   $.ajax({
            type: "POST",
            url: "ggoods_Ajaxtest.action",
            contentType: "application/json", //
            dataType: "text", //
         	
            data: pagenum,  //传递中文编码
            success: function (jsonResult) {
           
            if(jsonResult==("")){
            	
            	
            }
            else{
           
            var obj=eval("("+jsonResult+")");
            var list=obj.recordList;
             console.log(jsonResult);
             console.log("===============");
             console.log(obj);
             for(var i=0;i<list.length;i++){
            	
                var id=list[i].goodsId;
                var imgurl=list[i].imgSmall;
                var testdiv = document.getElementById("testdiv");
 				 testdiv.innerHTML=testdiv.innerHTML+"<a href=ggoods_show.action?goodsId="+id+"><img src='"+imgurl+"'></a>";
               
             }
          	/*   for(var i=0;i<jsonResult.length;i++){
          
				for(var p in jsonResult[i]){
				//	alert(jsonResult[i][p]);
					console.log(jsonResult[i][p]+"========================")
				} 
			}*/
	           /*  var nameid=jsonResult.split("_");
	            var name=nameid[0];
	            var id=nameid[1];
	            var select = document.getElementById("Select");
	          	 select.options.add(new Option(name, id)); */
         		}
         	
            },
            error:function(e) {
				alert("cucouco");
				}
        });
  
  </script>
  <body>
    This is my JSP page. <br>
    	<div id="testdiv"  class="testdiv"></div>
    <style>
    	.testdiv img{
    	width: 80px;
    	}
    
    </style>
  </body>
</html>
