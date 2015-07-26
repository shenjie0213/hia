<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@  include file="/WEB-INF/jsp/public/commons.jspf"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
  <head>
  <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
    	<script type="text/javascript" src="script/hia.js"></script>
    
    <title>My JSP 'show.jsp' starting page</title>
    
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
  <script type="text/javascript">
	function Ajax(id){  
       
          var aid=id;
          document.getElementById(id+"S").innerHTML ="";
          console.log(id);
  		 	var nameColl =document.getElementById(id+"C").value;
  		 	    console.log(nameColl);
  		 	nameColl+="_"+id;
  		 	var temp="_"+id;
  		 	
           if(nameColl==temp)
            {
              document.getElementById(id+"S").innerHTML ="名称不能为空";
            return false;  
            }
             $.ajax({
            type: "POST",
            url: "upocket_ajaxCollection.action",
            contentType: "application/json", //
            dataType: "text", //
         	
             data: encodeURI(nameColl),  //传递中文编码
            success: function (jsonResult) {
           
            if(jsonResult==("")){
            console.log(temp+"zheli");
            	  document.getElementById(aid+"S").innerHTML ="该collection已存在";
            	
            }
            else{
	            var nameid=jsonResult.split("_");
	            var name=nameid[0];
	            var id=nameid[1];
	            console.log(id+"id");
	            var select = document.getElementById(aid+"Select");
	          	 select.options.add(new Option(name, id));
         		}
         	
            },
            error:function(e) {
				alert("cucouco");
				}
        });
}

	</script>
  
  
   <div >
   		我的pocket111
   </div>
    <div>
    	<div style="float: left;margin-left: 200px;" >
	    	<div>
				<div style="color: red">时尚</div>    	
	    		<div>品牌</div>
	    		<div>
	    				<c:forEach items="${FListBrand}" var="item">
	    					<div><a href="upocket_show.action?authorId=${authorId}&brandId=${item.brandId}&cateId=${cateId}&pocketType=时尚">${item.brandName}</a></div>
	    				</c:forEach>
	    		</div>
	    		<div>品类</div>
	    		<div>
	    			<c:forEach items="${FListCate}" var="item">
	    					<div><a href="upocket_show.action?authorId=${authorId}&brandId=${brandId}&cateId=${item.catId}&pocketType=时尚">${item.catName}</a></div>
	    				</c:forEach>
	    		</div>
	    		<div>C</div>
	    		<div>
	    			<c:forEach items="${FListColl}" var="item">
	    					<div><a href="upocket_show.action?authorId=${authorId}&collId=${item.collId}&pocketType=时尚">${item.name}</a></div>
	    				</c:forEach>
	    		</div>
	    	
	    	</div>    			
    			
    			<div>
				<div style="color: red">美妆</div>    	
	    		<div>品牌</div>
	    		<div>
	    				<c:forEach items="${BListBrand}" var="item">
	    					<div><a href="upocket_show.action?authorId=${authorId}&brandId=${item.brandId}&cateId=${cateId}&pocketType=美妆">${item.brandName}</a></div>
	    				</c:forEach>
	    		</div>
	    		<div>品类</div>
	    		<div>
	    			<c:forEach items="${BListCate}" var="item">
	    					<div><a href="upocket_show.action?authorId=${authorId}&brandId=${brandId}&cateId=${item.catId}&pocketType=美妆">${item.catName}</a></div>
	    				</c:forEach>
	    		</div>
	    		<div>C</div>
	    		<div>
	    			<c:forEach items="${BListColl}" var="item">
	    					<div><a href="upocket_show.action?authorId=${authorId}&collId=${item.collId}&pocketType=美妆">${item.name}</a></div>
	    				</c:forEach>
	    		</div>
	    	
	    	</div>    
	    	
	    	<div>
				<div style="color: red">家居</div>    	
	    		<div>品牌</div>
	    		<div>
	    				<c:forEach items="${HListBrand}" var="item">
	    					<div><a href="upocket_show.action?authorId=${authorId}&brandId=${item.brandId}&cateId=${cateId}&pocketType=家居">${item.brandName}</a></div>
	    				</c:forEach>
	    		</div>
	    		<div>品类</div>
	    		<div>
	    			<c:forEach items="${HListCate}" var="item">
	    					<div><a href="upocket_show.action?authorId=${authorId}&brandId=${brandId}&cateId=${item.catId}&pocketType=家居">${item.catName}</a></div>
	    				</c:forEach>
	    		</div>
	    		<div>C</div>
	    		<div>
	    			<c:forEach items="${HListColl}" var="item">
	    					<div><a href="upocket_show.action?authorId=${authorId}&collId=${item.collId}&pocketType=家居">${item.name}</a></div>
	    				</c:forEach>
	    		</div>
	    	
	    	</div>    
    			
    	</div>
    	<div style="float: left;margin-left: 60px;width: 490px">
    		<div>
    			<div style="float: left"><a href="upocket_show.action?authorId=${authorId}&pocketType=时尚">时尚</a></div>
    			<div style="float: left;margin-left: 200px;"><a href="upocket_show.action?authorId=${authorId}&pocketType=美妆">美妆</a></div>
    			<div style="float: left;margin-left: 200px;'"><a href="upocket_show.action?authorId=${authorId}&pocketType=家居">家居</a></div>
    			<div>
    				<c:if test="${selectedB!=null}">
    				 已选品牌：<a href="upocket_show.action?authorId=${authorId}&cateId=${cateId}">${selectedB.brandName}</a>
    				</c:if>
    				<c:if test="${selectedC!=null}">
    				 已选品类：<a href="upocket_show.action?authorId=${authorId}&brandId=${brandId}">${selectedC.catName}</a>
    				</c:if>
    			</div>
    			
    			<div>
 						<script type="text/javascript">
						function showC(id){
						document.getElementById(id).style.display="block";
						}
						function noshowC(id){
						document.getElementById(id).style.display="none";
						}
					</script>
    			<c:forEach items="${recordList}" var="item">
				
					
					<div class="goodsimg" style="float: left;">
					
						<div onmouseover="showC(${item.goodsId})"  onmouseout="noshowC(${item.goodsId})">
							<div style="display: none;" id="${item.goodsId}">
								  <div class="bs-example bs-example-padded-bottom"> <c:if test="${sessionScope.user!=null}">
    											<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal${item.goodsId}"  style="color: white;background-color: grey ">
     												 +
   												 </button>
   										</c:if>
   								</div>
   										
   										
   								
   								
								<div><a href="upocket_remove.action?authorId=${authorId}&brandId=${brandId}&pocketType=${pocketType}&collId=${collId}&goodsId=${item.goodsId}"><span style="color: red;font-size: large;">-删除</span></a></div>
							</div>
							<a href="ggoods_show.action?goodsId=${item.goodsId}"><img src="${item.imgSmall}" /></a> 
						</div>
						<div id="myModal${item.goodsId}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
										    <div class="modal-dialog" role="document">
										      <div class="modal-content">
												<form action="upocket_collection.action"  name="collectionF">
														 <input type="hidden" value="${item.goodsId}" name="goodsId">
														 <input type="hidden" value="${authorId}" name="authorId">
														 <input type="hidden" value="${cateId}" name="cateId">
														 <input type="hidden" value="${pocketType}" name="pocketType">
														 <input type="hidden" value="${collId}" name="collId">
												        <div class="modal-header">
												          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												          <h4 class="modal-title" id="myModalLabel">添加到Pocket</h4>
												        </div>
												        <div class="modal-body">
												       
												        选择collection
												         	<select name="collectionId" class="textarea" id="${item.goodsId}Select">
												         		<c:forEach items="${collectionList}" var="cl">
												  				<option value="${cl.collId}" >${cl.name}</option>
												  				</c:forEach>
																</select>
														<div>
												          + 创建新的Collection${item.goodsId}
												          </div>
												          <div>
												          <input type="text"  value="" style="background-color: blue" id="${item.goodsId}C"><span onclick="Ajax(${item.goodsId})">创建</span>
												          </div>
												          <span id="${item.goodsId}S"></span>
												        </div>
												        <div class="modal-footer">
												          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
												          <button type="submit" class="btn btn-primary">确定</button>
												        </div>
													</form>
										      </div><!-- /.modal-content -->
										    </div><!-- /.modal-dialog -->
						</div><!-- /.modal -->
					</div>
					
					<style type="text/css"	>
					.goodsimg img{
					height: 80px;
					width: 80px;
					margin-left: 10px;
					margin-top: 10px;
						}
			
		
					</style>
				
				</c:forEach>	
    			</div>
    			
    			<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>
    			<form action="upocket_show.action">
    				<input type="hidden" name="authorId" value="${authorId}">
    				<input type="hidden" name="brandId" value="${brandId}">
    				<input type="hidden" name="pocketType" value="${pocketType}">
    				<input type="hidden" name="collId" value="${collId}">
    			</form>
    		</div>
    	
    	</div>
    </div>
    <br>
  </body>
</html>
