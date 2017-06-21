<%@page import="com.dipak.login.model.Document"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="java.util.List"%>
<%@page import="com.dipak.login.service.DocumentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="en" ng-app="myModule">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Bonded Warehouse</title>
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/css/common.css" rel="stylesheet">
<link href="./resources/css/font-awesome.min.css" rel="stylesheet">
<script src="./resources/js/angular.min.js"></script>
<script src="./resources/js/script.js"></script>
</head>
<body ng-controller="myController">
	<style type="text/css">
.logout-button {
	display: block;
	width: 115px;
	height: 47px;
	background: #ed0c21;
	padding: 10px;
	text-align: center;
	border-radius: 5px;
	color: white;
	margin-left: 1376px;
}

.modal-body .form-horizontal .col-sm-2, .modal-body .form-horizontal .col-sm-10
	{
	width: 100%
}

.modal-body .form-horizontal .control-label {
	text-align: left;
}

.modal-body .form-horizontal .col-sm-offset-2 {
	margin-left: 15px;
}
</style>

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
function updateStatuss(dId, dSource, dDestination, dStatus) {
    //alert("id = " + dId + " \nstatus = " +  dStatus + " \nsource = " + dSource + " \n destination = " + dDestination);
    document.getElementById("dDocId").value = dId;
    document.getElementById("dDestination").value = dDestination;
    document.getElementById("dSource").value = dSource;
} 

function updateDoc(dId) {
    //alert("id = " + dId + " \nstatus = " +  dStatus + " \nsource = " + dSource + " \n destination = " + dDestination);
    document.getElementById("dDocId").value = dId;
} 

function myFunction() {
    location.reload();
}
</script>
   <div id="light" class="white_content" $("body").css("overflow", "hidden");>
   <img style="height: 50px;margin-left:46%; margin-top:-4%; width: 50px; position:fixed" ng-src="./resources/img/close-icon.png" ng-click="StopTimer();"/>
   <div id="loadingIcon" style="display:none; margin-left:20%;"><img ng-src="./resources/img/Loading_icon.gif"/></div>
   <div id="noData" style="display:none;">No GPS data available!</div>
   <div id="mappop">
   </div>
   </div>
  <div id="fade" class="black_overlay"></div>
	<div class="panel-heading">
			<button class="btn btn-warning" onclick="myFunction()">Refresh</button>
			<div class="container-fluid"
				style="float:right;">
			<a href="logout" class="btn btn-danger" >Logout</a>
			</div>
	</div>
	<div class="panel panel-success">
		<div class="panel-heading" >
			<h3 align="center">Warehouse</h3>
			<a style="margin-top:-69px;" href="createNewDoc" class="btn btn-info">Create CB Document</a>
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

	<div class="panel-header">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-12 text-center">
									<div class="huge">${status.getInitiated()}</div>
									<div>Initiated Docs!</div>
								</div>
							</div>
						</div>
						<a href="${pageContext.request.contextPath}/initiated">
							<div class="panel-footer">
								<span class="pull-center">View Details</span>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-2">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-12 text-center">
									<div class="huge">${status.getProcessed()}</div>
									<div>Processed Docs!</div>
								</div>
							</div>
						</div>
						<a href="${pageContext.request.contextPath}/processed">
							<div class="panel-footer">
								<span class="pull-center">View Details</span>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-2">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-12 text-center">
									<div class="huge">${status.getVerified()}</div>
									<div>Verified Docs!</div>
								</div>
							</div>
						</div>
						<a href="${pageContext.request.contextPath}/verified">
							<div class="panel-footer">
								<span class="pull-center">View Details</span>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-2">
					<div class="panel panel-red">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-12 text-center">
									<div class="huge">${status.getRejected()}</div>
									<div>Rejected Docs!</div>
								</div>
							</div>
						</div>
						<a href="${pageContext.request.contextPath}/rejected">
							<div class="panel-footer">
								<span class="pull-center">View Details</span>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-2">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-12 text-center">
									<div class="huge">${status.getApproved()}</div>
									<div>Approved Docs!</div>
								</div>
							</div>
						</div>
						<a href="${pageContext.request.contextPath}/approved">
							<div class="panel-footer">
								<span class="pull-center">View Details</span>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-2">
					<div class="panel panel-blue">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-12 text-center">
									<div class="huge">${status.getReceived()}</div>
									<div>Received Docs!</div>
								</div>
							</div>
						</div>
						<a href="${pageContext.request.contextPath}/received">
							<div class="panel-footer">
								<span class="pull-center">View Details</span>
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<c:if test="${statusUpdate == 'document successfully updated!!'}">
			<div class="alert alert-success">
				<strong>"${statusUpdate}"</strong>
			</div>
		</c:if>
		<div class="container">
			<h3 align="center">Documents</h3>
			<c:if test="${action != 'warehouseLogin'}">
			 	<table class="table table-bordered">
	
					<thead>
						<tr>
							<th>Id</th>
							<th>Source</th>
							<th>Destination</th>
							<th>Status</th>
							<c:forEach items="${documents}" var="order" end="0">
								<c:if test="${order.getStatus() == 'Rejected'}">
									<th>Remarks</th>
									<th>Rejected By</th>
								</c:if>
							</c:forEach> 
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${documents}" var="order" varStatus="loop">
							<c:set var="order" value="${order}" scope="request"></c:set>
							<c:forEach items="${updatedByList}" var="updated" varStatus="loop2">
							<c:if test="${loop.index == loop2.index }">
								<tr>
								<td id="tDocId"><a href="openDoc?docId=${order.getDocumentId()}" target="_blank">${order.getDocumentId()}</a></td>
								<td>${order.getSource()}</td>
								<td>${order.getDestination()}</td>
								<td>${order.getStatus()}</td>
								<c:if test="${order.getStatus() == 'Approved'}">
									<td>
										<button id="update_button"
											class="update_button btn" data-toggle="modal"
											data-target="#updateStatus1" title="Update Status"
											onclick="updateStatuss(${order.getDocumentId()} , '${order.getSource()}' , '${order.getDestination()}' , '${order.getStatus()}' )"><img style="width:30px; height:30px;" src="./resources/img/update-status.png"/></button>
										<img ng-src="./resources/img/truck-search.png" title="locate the truck" alt="Truck Track" style="width:50px; height:50px; cursor:pointer;" ng-click="initMap('${order.getTruckId()}');"/>
									</td>
								</c:if>
								
								<c:if test="${order.getStatus() == 'Rejected'}">
									<td>${order.getReasonCode()}</td>
									<td>${updated}</td>
									<td>
									<c:if test="${order.getReasonCode() == 'Reason code 1' || order.getReasonCode() == 'Reason code 3' }" var="true">	
										<a href="updateDoc?id=${order.getDocumentId()}"><img title="Update Document" style="width:40px; height:40px;" src="./resources/img/update-doc.png"/></a>
									</c:if>
										<img ng-src="./resources/img/truck-search.png" title="Locate the truck" alt="Truck Track" style="width:50px; height:50px; cursor:pointer;" ng-click="initMap('${order.getTruckId()}');"/>
									</td>
								</c:if>
							</tr>
							</c:if>
							</c:forEach>
						</c:forEach>
						
						<c:if test="${order.getStatus() != 'Rejected'}">
							<c:forEach items="${documents}" var="order" varStatus="loop">
								<c:set var="order" value="${order}" scope="request"></c:set>
								<tr>
								<td id="tDocId"><a href="openDoc?docId=${order.getDocumentId()}" target="_blank">${order.getDocumentId()}</a></td>
								<td>${order.getSource()}</td>
								<td>${order.getDestination()}</td>
								<td>${order.getStatus()}</td>
								<td>
								<c:if test="${order.getStatus() == 'Approved'}">
										<button id="update_button" class="update_button btn" data-toggle="modal" data-target="#updateStatus1" title="Update Status"
											onclick="updateStatuss(${order.getDocumentId()} , '${order.getSource()}' , '${order.getDestination()}' , '${order.getStatus()}' )"><img style="width:30px; height:30px;" src="./resources/img/update-status.png"/></button>
								</c:if>				
										<img ng-src="./resources/img/truck-search.png" title="Locate the truck" alt="Truck Track" style="width:50px; height:50px; cursor:pointer;" ng-click="initMap('${order.getTruckId()}');"/>						
							    </td>
							</tr>
							</c:forEach>
						</c:if>
	
					</tbody>
				</table>
			</c:if>
			
			<c:if test="${action == 'warehouseLogin'}">
			
				 <table class="table table-bordered">
				
					<thead>
						<tr>
							<th>Id</th>
							<th>Source</th>
							<th>Destination</th>
							<th>Status</th>
							<c:forEach items="${docListRejected}" var="order" end="0">
								<c:if test="${order.getStatus() == 'Rejected'}">
									<th>Remarks</th>
									<th>Rejected By</th>
									<th>Action</th>
								</c:if>
							</c:forEach> 
						</tr>
					</thead>
					<!-- Warehouse Login -->
					<tbody>
						<c:forEach items="${docListRejected}" var="order" varStatus="loop">
							<c:set var="order" value="${order}" scope="request"></c:set>
							<c:forEach items="${updatedByList}" var="updated" varStatus="loop2">
							<c:if test="${loop.index == loop2.index }">
								<tr>
								<td id="tDocId"><a href="openDoc?docId=${order.getDocumentId()}" target="_blank">${order.getDocumentId()}</a></td>
								<td>${order.getSource()}</td>
								<td>${order.getDestination()}</td>
								<td>${order.getStatus()}</td>
								<td>${order.getReasonCode()}</td>
								<td>${updated}</td>
								<td>
								<c:if test="${order.getReasonCode() == 'Reason code 1' || order.getReasonCode() == 'Reason code 3' }" var="true">
								    <a href="updateDoc?id=${order.getDocumentId()}"><img title="Update Document" style="width:40px; height:40px;" src="./resources/img/update-doc.png"/></a>
								</c:if>
								    <img ng-src="./resources/img/truck-search.png" title="Locate the truck" alt="Truck Track" style="width:50px; height:50px; cursor:pointer;" ng-click="initMap('${order.getTruckId()}');"/>						
								</td>
								</tr>
							</c:if>
							</c:forEach>
						</c:forEach>
						
							<c:forEach items="${docListApproved}" var="order" varStatus="loop">
								<c:set var="order" value="${order}" scope="request"></c:set>
								<tr>
								<td id="tDocId"><a href="openDoc?docId=${order.getDocumentId()}" target="_blank">${order.getDocumentId()}</a></td>
								<td>${order.getSource()}</td>
								<td>${order.getDestination()}</td>
								<td>${order.getStatus()}</td>
								<td>NA</td>
								<td>NA</td>
								<c:if test="${order.getStatus() == 'Approved'}">
								<td>
									<button id="update_button" class="update_button btn" data-toggle="modal" title="Update Status" data-target="#updateStatus1" onclick="updateStatuss(${order.getDocumentId()} , '${order.getSource()}' , '${order.getDestination()}' , '${order.getStatus()}' )"><img style="width:30px; height:30px;" src="./resources/img/update-status.png"/></button>
									<img ng-src="./resources/img/truck-search.png" title="Locate the truck" alt="Truck Track" style="width:50px; height:50px; cursor:pointer;" ng-click="initMap('${order.getTruckId()}');"/>
								</td>
								</c:if>
							</tr>
							</c:forEach>
	
					</tbody>
				</table> 
			</c:if>
		</div>
		<div class="modal fade" id="updateStatus1" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Update Status</h4>
					</div>

					<!-- Modal Body -->
					<div class="modal-body">

						<form role="form" method="post" action="updateStatus">
							<input type="hidden" name="username" value=${username} id="username" />
							<div class="form-group">
								<table>
									<tr>
										<td><font face="verdana" size="2px">Document Id:</font></td>
										<td>
											<!-- 											<input type="text" name="id" id="dDocId" value=""readonly="readonly" /> -->
											<div class="form-group">
												<input type="text" class="form-control" name="id"
													id="dDocId" readonly="readonly">
											</div>
										</td>
									</tr>
									<tr>
										<td><font face="verdana" size="2px">Source:</font></td>
										<td>
											<!-- 											<input type="text" id="dDestination" value="" readonly="readonly" /> -->
											<div class="form-group">
												<input type="text" id="dDestination" value=""
													readonly="readonly" class="form-control">
											</div>
										</td>
									</tr>
									<tr>
										<td><font face="verdana" size="2px">Destination:</font></td>
										<td>
											<!-- 											<input type="text" id="dSource" value=""readonly="readonly" /> -->
											<div class="form-group">
												<input type="text" id="dSource" value="" readonly="readonly"
													class="form-control" />
											</div>
										</td>
									</tr>
									<tr>
										<td>Status</td>
										<td><select name="status" id="status"
											class="form-control">
												<option value="Received">Received at Warehouse</option>
										</select></td>
									</tr>
									<!-- <tr>
										<td>Remarks</td>
										<td>
											<select name="reasonCode" id="reasonCode"
											class="form-control">
												<option value="Reason1">Reason code 1</option>
												<option value="Reason2">Reason code 2</option>
												<option value="Reason3">Reason code 3</option>
										</select>
										</td>
									</tr> -->
								</table>
								<input type="hidden" id="reasonCode" name="reasonCode" value="" />
								
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary" value="Update">Save</button>
							</div>
						</form>


					</div>


				</div>
			</div>

		</div>
	</div>


	<footer class="footer panel-footer">
		<div class="container-fluid text-center">Copy Right @ IBM</div>
	</footer>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC-Ja0SVTIm46UW6x06uLCuxmoUjVOMrE8">
    </script>
</body>
</html>