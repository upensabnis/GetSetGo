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
	    <form class="col s12" action="/HelloWeb/hello/user" method="POST">
	      <div class="row">
	        <div class="input-field col s6">
	          <input id="user_name" type="text" class="validate" name="user_name">
	          <label for="user_name">User Name(Email ID)</label>
	        </div>
	       </div>
	       <div class="row">
	        <div class="input-field col s6">
	          <input id="pwd" type="password" class="validate">
	          <label for="pwd">Password</label>
	        </div>
	      </div>
	      <button class="btn waves-effect waves-light" type="submit" name="action">Submit
		    <i class="material-icons right">send</i>
		  </button>
	    </form>
  	</div>

    </div>
  </div>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/materialize.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/init.js"></script>


  </body>
</html>