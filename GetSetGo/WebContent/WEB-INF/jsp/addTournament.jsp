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
        <li><a href="/HelloWeb/logout">Logout</a></li>
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
	    <form class="col s12" action="/HelloWeb/tournament/addtournament" method="POST">
	      <div class="row">
	        <div class="input-field col s6">
	          <input id="tName" type="text" class="validate" name="tName">
	          <label for="tName">Name</label>
	        </div>
	       </div>
	       
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="tDesc" type="text" name="tDesc">
	          <label for="tDesc">Description</label>
	        </div>
	       </div>
	       
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="tSDate" type="date" class="datepicker" name="tSDate">
	          <label for="tSDate">Start Date</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="tEDate" type="date" class="datepicker" name="tEDate">
	          <label for="tEDate">End Date</label>
	        </div>
	       </div>
	       
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="tSTime" type="text" name="tSTime">
	          <label for="tSTime">Start Time</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="tETime" type="text" name="tETime">
	          <label for="tETime">End Time</label>
	        </div>
	       </div>
	       
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="tAddress" type="text" name="tAddress">
	          <label for="tAddress">Address</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="tCity" type="text" name="tCity">
	          <label for="tCity">City</label>
	        </div>
	       </div>
	       
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="tCountry" type="text" name="tCountry">
	          <label for="tCountry">Country</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="tState" type="text" name="tState">
	          <label for="tState">State</label>
	        </div>
	       </div>
	       
	       <div class="row">
	       	<div class="input-field col s6">
	          <input id="tZip" type="text" name="tZip">
	          <label for="tZip">ZipCode</label>
	        </div>
	        <div class="input-field col s6">
	          <input type="hidden" id="gid1" name="gid1">
	        </div>
	        <div class="input-field col s6">
	          <input type="hidden" id="gid2" name="gid2">
	        </div>
	        <div class="input-field col s6">
	          <input type="hidden" id="gid3" name="gid3">
	        </div>
	        <div class="input-field col s6">
	          <input type="hidden" id="gid4" name="gid4">
	        </div>
	       </div>
	       
	       <p>
				<input type="checkbox" id="c5" name="c5"/>
      			<label for="c5">BasketBall</label>
    		</p>
    		 <p>
				<input type="checkbox" id="c6" />
      			<label for="c6">FootBall</label>
    		</p>
    		 <p>
				<input type="checkbox" id="c7" />
      			<label for="c7">Badminton</label>
    		</p>
    		 <p>
				<input type="checkbox" id="c8" />
      			<label for="c8">Table Tennis</label>
    		</p>
        
	      
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
  
  $('input[type="checkbox"][id="c5"]').change(function() {
	     if(this.checked) {
	         console.log("C5 checked")
	 		var g = document.getElementById("gid1");
	 		g.value = "1";
	     }else{
	    	 var g = document.getElementById("gid1");
		 		g.value = "";
	     }
	 });
  
  $('input[type="checkbox"][id="c6"]').change(function() {
	     if(this.checked) {
	         console.log("C6 checked")
	 		var g = document.getElementById("gid2");
	 		g.value = "2";
	     }else{
	    	 var g = document.getElementById("gid2");
		 		g.value = "";
	     }
	 });
  
  $('input[type="checkbox"][id="c7"]').change(function() {
	     if(this.checked) {
	         console.log("C7 checked")
	 		var g = document.getElementById("gid3");
	 		g.value = "3";
	     }else{
	    	 var g = document.getElementById("gid3");
		 		g.value = "";
	     }
	 });
  
  $('input[type="checkbox"][id="c8"]').change(function() {
	     if(this.checked) {
	         console.log("C5 checked")
	 		var g = document.getElementById("gid4");
	 		g.value = "4";
	     }else{
	    	 var g = document.getElementById("gid4");
		 		g.value = "";
	     }
	 });
  </script>
  </body>
</html>
