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
      <a id="logo-container" href="#" class="brand-logo">GetSetGo</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="/HelloWeb/hello/logout">Logout</a></li>
      </ul>

      <ul id="nav-mobile" class="side-nav">
        <li><a href="#">Navbar Link</a></li>
      </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>

  <div class="container">
    <div class="section">

      <!--   Icon Section   -->
      <div class="row">
	    <form class="col s12" action="/HelloWeb/match/addmatch" method="POST">
	      <div class="row">
	        <div class="input-field col s6">
	          <input id="mName" type="text" class="validate" name="mName">
	          <label for="mName">Match Name</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="mTag" type="text" class="validate" name="mTag">
	          <label for="mTag">Match Tag</label>
	        </div>
	       </div>
	       
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="mDesc" type="text" name="mDesc">
	          <label for="mDesc">Description</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="mGenderType" type="text" name="mGenderType">
	          <label for="mGenderType">Gender</label>
	        </div>
	       </div>
	       
	       <div class="row">
	       	<div class="input-field col s6">
	          <input id="mCardinality" type="text" name="mCardinality">
	          <label for="mCardinality">Match Cardinality</label>
	        </div>
	       </div>
	       
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="mSDate" type="date" class="datepicker" name="mSDate">
	          <label for="mSDate">Start Date</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="mEDate" type="date" class="datepicker" name="mEDate">
	          <label for="mEDate">End Date</label>
	        </div>
	       </div>
	       
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="mSTime" type="text" name="mSTime">
	          <label for="mSTime">Start Time</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="mETime" type="text" name="mETime">
	          <label for="mETime">End Time</label>
	        </div>
	       </div>
	       
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="mLoc" type="text" name="mLoc">
	          <label for="mLoc">Match Location</label>
	        </div>
	        <div class="input-field col s6">
	          <input type="hidden" id="matchType" name="matchType">
	        </div>
	        <div class="input-field col s6">
	          <input type="hidden" id="gameName" name="gameName">
	        </div>
	       </div>
	       
	       <a class='dropdown-button btn' href='#' data-activates='dropdown1' id="matchTypeChange">Choose a match type</a>

		  <div class="row">
		  <ul id='dropdown1' class='dropdown-content'>
		    <li><a onClick="changeMatchType('online')">Online</a></li>
		    <li><a onClick="changeMatchType('outdoor')">Outdoor</a></li>
		  </ul>
		  </div>
		  
		   <a class='dropdown-button btn' href='#' data-activates='dropdown2' id="gameNameChange">Choose a Game</a>

		  <div class="row">
		  <ul id='dropdown2' class='dropdown-content'>
		  <c:forEach items="${allGames}" var="element">
					<li><a onClick="changeGameName('<c:out value="${element.getGame_name()}" />');"><c:out value="${element.getGame_name()}" /></a></li>
		</c:forEach>
		</ul>
		</div>
		  
		  <!--   <div class="row">
		  <ul id='dropdown2' class='dropdown-content'>
		    <li><a onClick="changeGameName('basketball')">Basketball</a></li>
		    <li><a onClick="changeGameName('football')">Football</a></li>
		    <li><a onClick="changeGameName('badminton')">Badminton</a></li>
		    <li><a onClick="changeGameName('tabletennis')">Table Tennis</a></li>
		  </ul>
		  </div> -->
	      
	      
	      <div class="row">
	      <button class="btn waves-effect waves-light" type="submit" name="action">Submit
		    <i class="material-icons right">send</i>
		  </button>
		  </div>
	    </form>
  	</div>

    </div>
  </div>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/materialize.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/init.js"></script>
  <script>
  $('.datepicker').pickadate({
	    selectMonths: true, // Creates a dropdown to control month
	    selectYears: 15 // Creates a dropdown of 15 years to control year
	  });
  
  function changeMatchType(mtype){
		document.getElementById("matchType").innerHTML = mtype;
		document.getElementById("matchTypeChange").innerHTML = mtype;
		
		var a = document.getElementById("matchType");
		a.value = mtype;
	}
  
  function changeGameName(gname){
		var gName = String(gname);
	  console.log(gName);
	  document.getElementById("gameName").innerHTML = gName;
		document.getElementById("gameNameChange").innerHTML = gName;
		
		var g = document.getElementById("gameName");
		g.value = gName;
	}
  </script>
  </body>
</html>
