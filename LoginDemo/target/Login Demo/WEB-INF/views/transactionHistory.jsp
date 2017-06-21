<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/css/common.css" rel="stylesheet">
<link href="./resources/css/font-awesome.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>

<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
}

.attrli {
    float: left;
	width: 130px;
	margin-top: 16px;
	margin-left: 36px;
}
.arrow {
    float: left;
	width: 40px;
	padding-right: 61px;
}
.attr{
    display: block;
    color: #000000;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
	border: 1px solid #000000;
	border-radius:50%;
}
.secondrow{
    display: block;
    color: 000000;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}
.thirdrow{
margin: 15px 88px;
}
.secondul{
	margin:-28px 6px;
}
.oval {
    width: 33px;
    height: 22px;
    background: #ea2222;
    border-radius: 0%;
 }
 
 .oval2 {
    width: 33px;
    height: 22px;
    background: #FFFF00;
    border-radius: 0%;
 }
 
 .oval3 {
    width: 33px;
    height: 22px;
    background: #25cb4d;
    border-radius: 0%;
 }
 
</style>
<title>Transaction History</title>
</head>
<body>
	
	<script type="text/javascript">
		function updateStatuss(dId, dSource, dDestination, dStatus) {
			//alert("id = " + dId + " \nstatus = " +  dStatus + " \nsource = " + dSource + " \n destination = " + dDestination);
			document.getElementById("dDocId").value = dId;
			document.getElementById("dDestination").value = dDestination;
			document.getElementById("dSource").value = dSource;
		}

		function goBack() {
			window.history.back();
		}
	</script>
	<div class="panel-heading">
			<button class="btn btn-primary" onclick="goBack()">Go Back</button>
			<div class="container-fluid"
				style="float:right;">
			<a href="logout" class="btn btn-danger" >Logout</a>
			</div>
	</div>
	<div class="panel panel-success">
		<div class="panel-heading" >
			<h3 align="center">Search Result</h3>
			<div class="container-fluid"
				style="float:right;margin-top:-49px;">
					<form class="navbar-form navbar-left" action="showTrxHistory">
						<div class="form-group">
							<input class="form-control" name="searchDocId" id="searchDocId" placeholder="Search by DocId">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
			</div>
		</div>
	</div>
<!-- 	#25CB4D = GREEN ||  #8A978D = GREY || #EA2222 = RED-->

	<%-- 	<%
		String a = (String)request.getParameter("lastStatus");
		%>
		${lastStatus} --%>
		<%-- <c:if test="${lastStatus == 'Approved'}">
			<li class="attrli"><span class="attr" href="#about"
			style="background-color: #FFFF00;"><font color="black">Approved by Custom</font></span></li>
		</c:if>	
		<c:if test="${lastStatus == 'Initial'}">
			<li class="attrli"><span class="attr" href="#about"
			style="background-color: #FFFF00;"><font color="black">Initiated by Warehouse</font></span></li>
		</c:if>	
		<c:if test="${lastStatus == 'Processed'}">
			<li class="attrli"><span class="attr" href="#about"
			style="background-color: #FFFF00;"><font color="black">Processed by Third Party</font></span></li>
		</c:if>
		<c:if test="${lastStatus == 'Received'}">
			<li class="attrli"><span class="attr" href="#about"
			style="background-color: #FFFF00;"><font color="black">Received at Warehouse</font></span></li>
		</c:if>	 --%>
		
	<div class="container" style="background:white !important;">
		<ul>
			<% if (((String)request.getAttribute("statusList")).contains("Initial")){%>
			<c:choose>
				<c:when test="${lastStatus == 'Initial'}">
				<li class="attrli"><span class="attr"
				style="background-color: #FFFF00;">Initiated by Warehouse</span></li>
				</c:when>
				<c:otherwise>
					<li class="attrli"><span class="attr"
				style="background-color:#25CB4D;">Initiated by Warehouse</span></li>
				</c:otherwise>
			</c:choose>
			<%} 
			else if (((String)request.getAttribute("statusList")).contains("Initial")){%>
			<li class="attrli"><span class="attr"
			style="background-color: #25CB4D;">Initiated by Warehouse</span></li>
			<%}
			else { %>
				<li class="attrli"><span class="attr"
				style="background-color: #8A978D;">Initiated by Warehouse</span></li>
			<%} %>			
			<li class="arrow"><img src="./resources/img/right-arrow.png" alt="Mountain View"
				style="width: 100px; height: 95px;"></li>
			<% if (((String)request.getAttribute("statusList")).contains("Processed")){%>
			<c:choose>
				<c:when test="${lastStatus == 'Processed'}">
				<li class="attrli"><span class="attr"
				style="background-color: #FFFF00;">Processed by Third Party</span></li>
				</c:when>
				<c:otherwise>
					<li class="attrli"><span class="attr"
				style="background-color:#25CB4D;">Processed by Third Party</span></li>
				</c:otherwise>
			</c:choose>
			<%} else { %>
				<li class="attrli"><span class="attr" href="#news"
				style="background-color: #8A978D;">Processed by Third Party</span></li>
			<%} %>
			<li class="arrow"><img src="./resources/img/right-arrow.png" alt="Mountain View"
				style="width: 100px; height: 95px;"></li>
			<% if (((String)request.getAttribute("statusList")).contains("Verified")){%>	
				<c:choose>
				<c:when test="${lastStatus == 'Verified'}">
				<li class="attrli"><span class="attr"
				style="background-color: #FFFF00;">Verified by Custom</span></li>
				</c:when>
				<c:otherwise>
					<li class="attrli"><span class="attr"
				style="background-color:#25CB4D;">Verified by Custom</span></li>
				</c:otherwise>
			</c:choose>
			<%} else { %>
				<li class="attrli"><span class="attr" href="#contact"
				style="background-color: #8A978D;">Verified by Custom</span></li>
			<%} %>
			<li class="arrow"><img src="./resources/img/right-arrow.png" alt="Mountain View"
				style="width: 100px; height: 95px;"></li>
			<% if (((String)request.getAttribute("statusList")).contains("Approved")){%>
			
			<c:choose>
				<c:when test="${lastStatus == 'Approved'}">
				<li class="attrli"><span class="attr"
				style="background-color: #FFFF00;">Approved by Custom</span></li>
				</c:when>
				<c:otherwise>
					<li class="attrli"><span class="attr"
				style="background-color:#25CB4D;">Approved by Custom</span></li>
				</c:otherwise>
			</c:choose>
			<%} else { %>
				<li class="attrli"><span class="attr" href="#about"
				style="background-color: #8A978D;">Approved by Custom</span></li>
			<%} %>
			<li class="arrow"><img src="./resources/img/right-arrow.png" alt="Mountain View"
				style="width: 100px; height: 95px;"></li>
			<% if (((String)request.getAttribute("statusList")).contains("Received")){%>
				<c:choose>
				<c:when test="${lastStatus == 'Received'}">
				<li class="attrli"><span class="attr"
				style="background-color: #FFFF00;">Received at Warehouse</span></li>
				</c:when>
				<c:otherwise>
					<li class="attrli"><span class="attr"
				style="background-color:#25CB4D;">Received at Warehouse</span></li>
				</c:otherwise>
			</c:choose>
			<%} else { %>
				<li class="attrli"><span class="attr" href="#about"
				style="background-color: #8A978D;">Received at Warehouse</span></li>
			<%} %>
		</ul>
		<ul class="secondul">
			<li class="attrli"><a class="secondrow" href="#home"></a></li>
			<li class="arrow"><img src="./resources/img/down-arrow.png" alt="Mountain View"
				style="width: 46px; height: 65px;"></li>
			<li class="attrli"><a class="secondrow" href="#news"></a></li>
			<li class="arrow"><img src="./resources/img/down-arrow.png" alt="Mountain View"
				style="width: 46px; height: 65px;"></li>
			<li class="attrli"><a class="secondrow" href="#contact"></a></li>
			<li class="arrow"><img src="./resources/img/down-arrow.png" alt="Mountain View"
				style="width: 46px; height: 65px;"></li>
			<li class="attrli"><a class="secondrow" href="#about"></a></li>
		</ul>
		<ul class="thirdrow">
			<% if (((String)request.getAttribute("statusList")).contains("thirdparty_Rejected")){%>
				<c:choose>
				<c:when test="${lastStatus == 'Rejected'}">
				<li class="attrli"><span class="attr"
				style="background-color: #FFFF00;">Rejected by Third Party</span></li>
				</c:when>
				<c:otherwise>
					<li class="attrli"><span class="attr"
				style="background-color:#25CB4D;">Rejected by Third Party</span></li>
				</c:otherwise>
			</c:choose>
			<%} else { %>
				<li class="attrli"><span class="attr" href="#home"
				style="background-color: #8A978D;">Rejected by Third Party</span></li>
			<%} %>
			<li class="arrow" style="width: 43px; height: 65px;"></li>
			<% if (((String)request.getAttribute("statusList")).contains("custom_Rejected") && !((String)request.getAttribute("statusList")).contains("Verified")){%>
				<c:choose>
				<c:when test="${lastStatus == 'Rejected'}">
				<li class="attrli"><span class="attr"
				style="background-color: #FFFF00;">Rejected by Custom</span></li>
				</c:when>
				<c:otherwise>
					<li class="attrli"><span class="attr"
				style="background-color:#25CB4D;">Rejected by Custom</span></li>
				</c:otherwise>
			</c:choose>
			<%} else { %>
				<li class="attrli"><span class="attr" href="#news"
				style="background-color: #8A978D;">Rejected by Custom</span></li>
			<%} %>
			<li class="arrow" style="width: 43px; height: 65px;"></li>
			<% if (((String)request.getAttribute("statusList")).contains("custom_Rejected") && ((String)request.getAttribute("statusList")).contains("Verified")){%>
				<c:choose>
				<c:when test="${lastStatus == 'Rejected'}">
				<li class="attrli"><span class="attr"
				style="background-color: #FFFF00;">Rejected by Custom</span></li>
				</c:when>
				<c:otherwise>
					<li class="attrli"><span class="attr"
				style="background-color:#25CB4D;">Rejected by Custom</span></li>
				</c:otherwise>
				</c:choose>
			<%} else { %>
				<li class="attrli"><span class="attr" href="#contact"
				style="background-color: #8A978D;">Rejected by Custom</span></li>
			<%} %>
		</ul>
		<table width="auto" align="right">
		 <tr>
		    <td><div class="oval2"></div></td>
		    <td>&nbsp;&nbsp;Current Status</td>
		 </tr>
		 <tr height="10px"></tr>
<!-- 		 <tr> -->
<!-- 		    <td><div class="oval3"></div></td> -->
<!-- 		    <td>&nbsp;&nbsp;Status reached successfully</td> -->
<!-- 		</tr> -->
<!-- 		<tr height="10px"></tr> -->
<!-- 		<tr> -->
<!-- 		    <td><div class="oval"></div></td> -->
<!-- 		    <td>&nbsp;&nbsp;Status reached after rejection</td> -->
<!-- 		</tr> -->
		</table>
	</div>

	<div class="container" style="margin-top: 21px;">
		<table class="table table-bordered">

			<thead>
				<tr>
					<th>Document ID</th>
					<th>Source</th>
					<th>Destination</th>
					<th>Status</th>
				</tr>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${docsbyId}" var="order">
					<c:set var="order" value="${order}" scope="request"></c:set>
					<tr>
						<td id="tDocId"><a
							href="openDoc?docId=${order.getDocumentId()}" target="_blank">${order.getDocumentId()}</a></td>
						<td>${order.getSource()}</td>
						<td>${order.getDestination()}</td>
						<td>${order.getStatus()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 align="center">Document Transaction History</h3>
		</div>
	</div>

	<div class="container">
		<!-- 		<h3 align="center">Document History</h3> -->
		<table class="table table-bordered">

			<thead>
				<tr>
					<th>Transaction ID</th>
					<th>Last Updated On</th>
					<th>Document ID</th>
					<th>Updated By</th>
					<th>Status</th>
				</tr>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${trxHistory}" var="history">
					<c:set var="history" value="${history}" scope="request"></c:set>
					<tr>
						<td>${history.getTrxId()}</td>
						<td>${history.getTimeStamp()}</td>
						<td>${history.getDocumentId()}</td>
						<td>${history.getUpdatedBy()}</td>
						<td>${history.getStatus()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>