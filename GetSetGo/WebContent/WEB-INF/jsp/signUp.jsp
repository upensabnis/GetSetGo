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
        <li><a href="/HelloWeb/hello/login">Login</a></li>
        <li><a href="/HelloWeb/hello">Sign Up</a></li>
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
	    <form class="col s12" action="/HelloWeb/signup/signupformfilled" method="POST">
	      <div class="row">
	        <div class="input-field col s6">
	          <input id="name" type="text" class="validate" name="name">
	          <label for="name">Name</label>
	        </div>
	       </div>
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="email" type="text" class="validate" name="email">
	          <label for="email">Email</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="pwd" type="password" class="validate" name="pwd">
	          <label for="pwd">Password</label>
	        </div>
	       </div>
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="gender" type="text" class="validate" name="gender">
	          <label for="gender">Gender</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="contact" type="text" class="validate" name="contact">
	          <label for="contact">Contact Number</label>
	        </div>
	        <div class="input-field col s6">
	          <input type="hidden" class="validate" id="role" name="role">
	        </div>
	       </div>
	       
	       <a class='dropdown-button btn' href='#' data-activates='dropdown1' id="roleChange">Choose a role</a>

		  <div class="row">
		  <ul id='dropdown1' class='dropdown-content'>
		    <li><a onClick="changeRole('host')">Host</a></li>
		    <li><a onClick="changeRole('participant')">Participant</a></li>
		    <li><a onClick="changeRole('admin')">Admin</a></li>
		  </ul>
		  </div>

	      
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
  
  <script type="text/javascript">
  
  	function changeRole(role){
  		document.getElementById("role").innerHTML = role;
  		document.getElementById("roleChange").innerHTML = role;
  		
  		var a = document.getElementById("role");
  		a.value = role;
  	}
  	
  
  </script>


  </body>
</html>
