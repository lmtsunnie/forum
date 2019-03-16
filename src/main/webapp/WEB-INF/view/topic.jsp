<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" type="text/css" href="css/topic.css">
</head>
<body>
<%@ include file="header.jsp" %>


	<div class="main w clearfix">
		<div class="main-left">
			<div class="share">
				<div class="share-left"><span class="glyphicon glyphicon-th-large"></span>&nbsp;话题广场</div>
				<div class="share-right">
					<a href="#" id="open-mask"><span class="glyphicon glyphicon-pencil"></span>&nbsp;申请添加话题</a>
				</div>
			</div>
			<div class="topic-root">
				<div class="topic-root-wrap">
					<c:forEach items="${topicList}" var="topic">
                        <a href="#">${topic.name}</a>
                    </c:forEach>

				</div>
			</div>
			<%--话题列表--%>
			<div class="topic-list">
				<div class="topic-list-wrap clearfix">
                    <c:forEach items="${topicList}" var="topic" varStatus="status">
                        <c:choose>
                            <c:when test="${status.count%2!=0}">
                                <div class="topic-odd relative">
                                    <a href="#" class="topic-image"><img src="${topic.image}"></a>
                                    <div class="topic-content">
                                        <a href="#" class="topic-name">${topic.name}</a>
                                        <a href="#" class="topic-desc">${topic.content}</a>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="topic-even relative">
                                    <a href="#" class="topic-image"><img src="${topic.image}"></a>
                                    <div class="topic-content">
                                        <a href="#" class="topic-name">${topic.name}</a>
                                        <a href="#" class="topic-desc">${topic.content}</a>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
				</div>
			</div>
			<div class="topic-more">
				<a href="#">更多</a>
			</div>
		</div>
		<div class="main-right">
			
			<div class="hot-user">
				<div class="clearfix"><div class="hot-user-title"><span></span>&nbsp;推荐网站</div></div>
				<ul class="hot-user-list">
					<li class="clearfix">
						<a href="https://bit.edu.cn/" class="hot-user-image"><img src="http://qiniu.limengting.site/logo2.jpg"></a>
						<a href="https://bit.edu.cn/" class="hot-user-name">北理工官网</a>
					</li>
					<li class="clearfix">
						<a href="https://rek.rek.me/bitren/" class="hot-user-image"><img src="http://qiniu.limengting.site/nav1.png"></a>
						<a href="https://rek.rek.me/bitren/" class="hot-user-name">北理人导航</a>
					</li>
					<li class="clearfix">
						<a href="https://limengting.site/" class="hot-user-image"><img src="http://qiniu.limengting.site/lock.png"></a>
						<a href="https://limengting.site/" class="hot-user-name">作者个人博客</a>
					</li>
					<li class="clearfix">
						<a href="http://jwc.bit.edu.cn/" class="hot-user-image"><img src="http://qiniu.limengting.site/tie.png"></a>
						<a href="http://jwc.bit.edu.cn/" class="hot-user-name">教务处</a>
					</li>
                    <li class="clearfix">
                        <a href="https://bitpt.cn/" class="hot-user-image"><img src="http://qiniu.limengting.site/star1.png"></a>
                        <a href="https://bitpt.cn/" class="hot-user-name">极速之星</a>
                    </li>
                    <li class="clearfix">
                        <a href="http://www.imooc.com/" class="hot-user-image"><img src="http://qiniu.limengting.site/computer.png"></a>
                        <a href="http://www.imooc.com/" class="hot-user-name">计算机学院</a>
                    </li>
                    <li class="clearfix">
                        <a href="http://www.jikexueyuan.com/" class="hot-user-image"><img src="http://qiniu.limengting.site/signal1.png"></a>
                        <a href="http://www.jikexueyuan.com/" class="hot-user-name">信息与电子学院</a>
                    </li>

                    <li class="clearfix">
                        <a href="http://sae.bit.edu.cn/" class="hot-user-image"><img src="http://qiniu.limengting.site/plane1.png"></a>
                        <a href="http://sae.bit.edu.cn/" class="hot-user-name">宇航学院</a>
                    </li>
                    <li class="clearfix">
                        <a href="http://smen.bit.edu.cn/" class="hot-user-image"><img src="http://qiniu.limengting.site/design1.png"></a>
                        <a href="http://smen.bit.edu.cn/" class="hot-user-name">艺术与设计学院</a>
                    </li>
                    <li class="clearfix">
                        <a href="http://me.bit.edu.cn/" class="hot-user-image"><img src="http://qiniu.limengting.site/car1.png"></a>
                        <a href="http://me.bit.edu.cn/" class="hot-user-name">机车学院</a>
                    </li>
                    <li class="clearfix">
                        <a href="http://opt.bit.edu.cn/" class="hot-user-image"><img src="http://qiniu.limengting.site/math2.png"></a>
                        <a href="http://opt.bit.edu.cn/" class="hot-user-name">数学学院</a>
                    </li>
				</ul>
			</div>
		</div>
	</div>

	<div class="mask"></div>
	<div class="upon-mask">
		<form>
			<input type="text" name="" placeholder="请输入话题名称">
			<button>提交申请</button>
		</form>
		<span id="close-mask">×</span>
	</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript">
	$(function(){

		var openMask = $("#open-mask");
		var closeMask = $("#close-mask");
		var mask = $(".mask");
		var uponMask = $(".upon-mask");

		openMask.click(function(){
			mask.show();
			uponMask.show();
		});

		closeMask.click(function(){
			mask.hide();
			uponMask.hide();
		});
	});
</script>
</body>
</html>













