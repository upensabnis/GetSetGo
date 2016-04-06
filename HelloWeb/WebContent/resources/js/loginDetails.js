function checkLoginData() {
	console.log("Inside the function")
	
	var username = document.getElementById("user_name").value;
	var pwd = document.getElementById("pwd").value;
	
	console.log("Username:"+username);
	console.log("Password:"+pwd);
	
	//request.setAttribute("message", "User deleted");
	window.location = "/HelloWeb/hello/user";
}