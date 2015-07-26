<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
    <%@  include file="/WEB-INF/jsp/public/commons.jspf"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>发现</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/reset.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/brand-2.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/head.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/calendar.css">

   
    
<script type="text/javascript" src="${pageContext.request.contextPath}/script/hia.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/calendar.js"></script>


</head>
<body >
	<div class="web-bar"><!-- 第一块 -->
		<div class="web-bar-one">
			<!-- 头像 登录前-->
			
			<c:if test="${sessionScope.user==null}">
				<div class="user-iconinfo" id="login">
					<input type="button" value="登录"  onclick="login()"><span>●</span><a href="uuser_registerUI.action?registerPreUrl=home_indexList.action">注册</a>
				</div>
				<div class="user-login" id="user-login">
					<form method="post" action="uuser_login.action?registerPreUrl=home_indexList.action">
						<fieldset>
							<div class="item-name">
								<input type="text" name="phonenum" id="form_email" class="inp" placeholder="邮箱/手机号" >
							</div>
							<div class="item-password">
								<input type="password" name="password" id="form_password" class="inp" placeholder="密码" >
								<div class="opt">
									<a href="forget-password.html">忘记密码</a>
								</div>
							</div>
							<div class="item-remember">
								<label class="form_remember">
									<input type="checkbox" name="remember" id="form_remember">记住我
								</label>
							</div>
							<div class="item-submit">
								<input type="submit" value="登录" class="bn-submit">
								<input type="button" value="取消" class="bn-submit" onclick="cancel()" title="暂时先不登录">
								</div>
						</fieldset>
					</form>
				</div> 
				</c:if>
				<c:if test="${sessionScope.user!=null}">
				<div class="user-iconinfo" onmouseover="document.getElementById('user-fullinfo').style.display='block'" onmouseout=			                         "document.getElementById('user-fullinfo').style.display=''">
					
						<div class="user-pic">
							<a href="#"><img src="img/hiapic/头像.jpg"></a>
							
						</div>
						<div class="fl">欢迎：${sessionScope.user.username}</div>
						<div class="user-fullinfo" id="user-fullinfo">
							<div class="empty"><i class="hia-icon">&#xea1c;</i></div>
							<ul>
								<li class="user-name"><a href="#">我的主页</a></li>
								<li class="user-home"><a href="#">我的心愿单</a></li>
								<li class="user-home"><a href="#">我的消息</a></li>
								<li class="user-home"><a href="#">账号设置</a></li>
								<li class="user-out"><a href="uuser_logout.action?registerPreUrl=home_indexList.action">退出</a></li>
							</ul>
						</div>
				</div>
				
			</c:if>
					<p><a href="#">Poster</a></p>
					<c:if test="${sessionScope.user!=null}">
						<p><a href="upocket_show.action?authorId=${sessionScope.user.userId}">Pocket</a></p> 
					</c:if>
					<c:if test="${sessionScope.user==null}">
						<p><a href="uuser_loginUI.action?registerPreUrl=home_indexList.action">Pocket</a></p> 
					</c:if>
				</div>
			</div>
			<div class="web-icon"><!-- 第二块 -->
				<div class="icon-pic"><!-- hia图标 -->
					<a href="#"><img src="style/img/hiapic/hia.gif"></a>
				</div>
				<div class="nav-bar"><!-- 导航栏 -->
					<ul>
						<li><a href="#"><i class="hia-icon" style="font-size:14px">&#xe902;</i>&nbsp;首页</a></li>
						<li><a href="#"><i class="hia-icon" style="font-size:15px">&#xe9ce;</i>&nbsp;动态</a></li>
						<li><a href="gbrand_brandIndex.action"><i class="hia-icon" style="font-size:13px">&#xe935;</i>&nbsp;品牌</a></li>
					</ul>
				</div>
				<div class="nav-search"><!-- 搜索框 -->
					
					<form name="" method="" action="">
						<input type="text" name="" class="basic-btn"/>
						
						<p><img src="style/img/hiapic/search.png" style="width:15px; margin-left:5px;"></p>
					</form>
				</div>
			</div>
					<div style="margin-left: 200px;float: left;width: 800px;" >
							<div ><div>时尚</div>
								<div><img src="#" width="800px;" height="200px"></div>
							
									<div class="leftbar" style="width: 200px;float: left"> 
												<div>
													<div>品类</div>
													<div>
															<c:forEach items="${FMap}" var="item">
																 <span >${item.key.catName}</span>
																 <ul>
																	<c:forEach  items="${item.value}" var="list" >
														
																		<li >
																		<a style="color: red;" href="gbrand_show.action?brandId=${brandId}&&cateId=${list.catId}&&attrnow=${attrnow}">${list.catName}</a>
																		</li>  		
																 	</c:forEach>
																</ul>
															</c:forEach>
													</div>
													<div>热门collection</div>
													<div>
														<ul>
																<c:forEach items="${fuc}" var="item">
																	<li >
																		<a style="color: red;" href="#">${item.name}</a>
																		</li>  	
																</c:forEach>
														</ul>
													</div>
												</div>
									</div>
									<style>.rightP img{
										margin-left: 20;
										margin-top: 20px;
										width: 100px;
									}</style>
									<div class="rightP" style="float: left;width: 500px"> 
										<ul>
											<c:forEach items="${fgoosList}" var="item">
												<li  style="float: left">
													<a  href="ggoods_show.action?goodsId=${item.goodsId}"><img src="${item.imgSmall}"></a>
												</li>  	
											</c:forEach>
										</ul>
									</div>
							</div>
							<div ><div style="float: left;width: 500px">美妆</div>
								<div><img src="#" width="800px;" height="200px"></div>
							
									<div class="leftbar" style="width: 200px;float: left"> 
												<div>
													<div>品类</div>
													<div>
															<c:forEach items="${BMap}" var="item">
																 <span >${item.key.catName}</span>
																 <ul>
																	<c:forEach  items="${item.value}" var="list" >
														
																		<li >
																		<a style="color: red;" href="gbrand_show.action?brandId=${brandId}&&cateId=${list.catId}&&attrnow=${attrnow}">${list.catName}</a>
																		</li>  		
																 	</c:forEach>
																</ul>
															</c:forEach>
													</div>
													<div>热门collection</div>
													<div>
														<ul>
																<c:forEach items="${buc}" var="item">
																	<li >
																		<a style="color: red;" href="#">${item.name}</a>
																		</li>  	
																</c:forEach>
														</ul>
													</div>
												</div>
									</div>
									<style>.rightP img{
										margin-left: 20;
										margin-top: 20px;
										width: 100px;
									}</style>
									<div class="rightP" style="float: left;width: 500px"> 
										<ul>
											<c:forEach items="${bgoosList}" var="item">
												<li  style="float: left">
													<a  href="ggoods_show.action?goodsId=${item.goodsId}"><img src="${item.imgSmall}"></a>
												</li>  	
											</c:forEach>
										</ul>
									</div>
							</div>
							<div ><div style="float: left;width: 500px">家居</div>
								<div><img src="#" width="800px;" height="200px"></div>
							
									<div class="leftbar" style="width: 200px;float: left;"> 
												<div>
													<div>品类</div>
													<div>
															<c:forEach items="${HMap}" var="item">
																 <span >${item.key.catName}</span>
																 <ul>
																	<c:forEach  items="${item.value}" var="list" >
														
																		<li >
																		<a style="color: red;" href="gbrand_show.action?brandId=${brandId}&&cateId=${list.catId}&&attrnow=${attrnow}">${list.catName}</a>
																		</li>  		
																 	</c:forEach>
																</ul>
															</c:forEach>
													</div>
													<div>热门collection</div>
													<div>
														<ul>
																<c:forEach items="${huc}" var="item">
																	<li >
																		<a style="color: red;" href="#">${item.name}</a>
																		</li>  	
																</c:forEach>
														</ul>
													</div>
												</div>
									</div>
									<style>.rightP img{
										margin-left: 20;
										margin-top: 20px;
										width: 100px;
									}</style>
									<div class="rightP" style="float: left;width: 500px"> 
										<ul>
											<c:forEach items="${hgoosList}" var="item">
												<li  style="float: left">
													<a  href="ggoods_show.action?goodsId=${item.goodsId}"><img src="${item.imgSmall}"></a>
												</li>  	
											</c:forEach>
										</ul>
									</div>
							</div>
					</div>
	</body>
</noframes></html>