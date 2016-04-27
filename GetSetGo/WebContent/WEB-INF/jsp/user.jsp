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

      <!--   <div class="col s12 m4 l3"> 
		<div style="height: 100%; margin-top: 90%; border: 1px solid black"><img src="<%=request.getContextPath()%>/resources/images/user.png" width="100" height="100"/>
	    <div style="margin-top: 5%;">Name: ABCD</div>
	    <div>Address: 123, XYZ, CO</div>
	    </div>
      </div> -->

		<div class="row" style="margin-top: 5%;">
			<div class="container">
			<p>Upcoming Tournaments</p>
			<div class="slider">
			    <ul class="slides">
			      <li>
			        <img src="http://lorempixel.com/580/250/nature/1"> <!-- random image -->
			        <div class="caption center-align">
			          <h3>This is our big Tagline!</h3>
			          <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
			        </div>
			      </li>
			      <li>
			        <img src="http://lorempixel.com/580/250/nature/2"> <!-- random image -->
			        <div class="caption left-align">
			          <h3>Left Aligned Caption</h3>
			          <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
			        </div>
			      </li>
			      <li>
			        <img src="http://lorempixel.com/580/250/nature/3"> <!-- random image -->
			        <div class="caption right-align">
			          <h3>Right Aligned Caption</h3>
			          <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
			        </div>
			      </li>
			      <li>
			        <img src="http://lorempixel.com/580/250/nature/4"> <!-- random image -->
			        <div class="caption center-align">
			          <h3>This is our big Tagline!</h3>
			          <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
			        </div>
			      </li>
			    </ul>
			  </div>
			  </div>
		</div>
		<div class="row">
			<div class="container">
			Registered Tournaments
			<ul class="collapsible" data-collapsible="accordion">
			    <li>
			      <div class="collapsible-header"><i class="material-icons">filter_drama</i>First</div>
			      <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
			    </li>
			    <li>
			      <div class="collapsible-header"><i class="material-icons">place</i>Second</div>
			      <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
			    </li>
			    <li>
			      <div class="collapsible-header"><i class="material-icons">whatshot</i>Third</div>
			      <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
			    </li>
			  </ul>
			  </div>
		</div>
    </div>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/materialize.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/init.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
      $('.slider').slider({full_width: true, height: 200});
    });
  
  $('.dropdown-button').dropdown({
      inDuration: 300,
      outDuration: 225,
      constrain_width: false, // Does not change width of dropdown to that of the activator
      hover: true, // Activate on hover
      gutter: 0, // Spacing from edge
      belowOrigin: true, // Displays dropdown below the button
      alignment: 'left' // Displays dropdown with edge aligned to the left of button
    }
  );
  </script>


  </body>
</html>
