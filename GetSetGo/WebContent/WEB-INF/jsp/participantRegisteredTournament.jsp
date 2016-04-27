<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
  <title>GetSetGo: Tournament Scheduler</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  
</head>
<body>
<script src="<%=request.getContextPath()%>/resources/js/loginDetails.js"></script>
  <nav class="white" role="navigation">
    <div class="nav-wrapper container">
      <a id="logo-container" href="/HelloWeb/hello" class="brand-logo"><img src="<%=request.getContextPath()%>/resources/images/logo.jpg" width="30" height="30" />GetSetGo</a>
      <ul class="right hide-on-med-and-down">
      	<li style="margin-top:10%"><a class='dropdown-button' data-activates='dropdown1'><img src="<%=request.getContextPath()%>/resources/images/user.png" width="30" height="30" /></a></li>
      	<li></li>
        <li><a href="/HelloWeb/logout">Logout</a></li>
      </ul>
      
      <ul id='dropdown1' class='dropdown-content'>
	    <li><a href="#!">one</a></li>
	    <li><a href="#!">two</a></li>
	    <li class="divider"></li>
	    <li><a href="#!">three</a></li>
	  </ul>

      <ul id="nav-mobile" class="side-nav">
        <li><a href="#">Navbar Link</a></li>
      </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>

  <div class="row">
	<div class="row" style="margin-top: 5%;">
			<div class="container">
			<p>Tournament Details</p>
			<div class="col s6">
				<p><strong>Tournament Name : <c:out value="${tObj.tournamentName}" /></strong></p>
				<p>Description : <c:out value="${tObj.tournamentDesc}" /><br/>
				Dates : <c:out value="${tObj.tournamentStartDate}" /> - <c:out value="${tObj.tournamentEndDate}" /><br/>
				Timings: <c:out value="${tObj.tournamentStartTime}" /> - <c:out value="${tObj.tournamentEndTime}" /><br/>
				Address : <c:out value="${tObj.tournamentAddress}" /><br/>
				City : <c:out value="${tObj.tournamentCity}" /><br/>
				State : <c:out value="${tObj.tournamentState}" /><br/>
				Country : <c:out value="${tObj.tournamentCountry}" /><br />
				Zip : <c:out value="${tObj.tournamentZip}" /></p>
				
			  </div>
			  <div class="col s6">
			  	<p>Unregistered Games</p>
			  	<div class="row">
			  		<c:forEach items="${unRegGameList}" var="element">
						<p><a href="<c:url value='/participant/game/unreg/${element.getGame_id()}' />" ><c:out value="${element.getGame_name()}" /></a></p>
					</c:forEach>
			  	</div>
			  	<p>Registered Games</p>
			  	<div class="row">
			  		<c:forEach items="${regGamesList}" var="element">
						<p><strong><c:out value="${element.getGame_name()}" /></strong></p>
					</c:forEach>
			  	</div>
			  	
			  </div>
			  
			  </div>
		</div>
    </div>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/materialize.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/init.js"></script>
  </body>
</html>
