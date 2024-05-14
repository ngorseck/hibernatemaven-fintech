<!DOCTYPE html>
<html>
	<head>
		<title>Slide Navbar</title>
		<link rel="stylesheet" type="text/css" href="slide navbar style.css">
		<link rel="stylesheet" type="text/css" href="public/css/style.css">
		<link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
	</head>
	<body>
		<div class="main">  	
			<input type="checkbox" id="chk" aria-hidden="true">
	
				<div class="signup">
					<form method="post" action="singup">
						<label for="chk" aria-hidden="true">Sign up</label>
						<input type="email" name="mail" placeholder="Email" required="">
	          			<input type="password" name="pwd" placeholder="Password" required="">
						<button>Sign up</button>
					</form>
				</div>
	
				<div class="login">
					<form method="post" action="login">
						<label for="chk" aria-hidden="true">Login</label>
						<input type="email" name="email" placeholder="Email" required="">
						<input type="password" name="password" placeholder="Password" required="">
						<button>Login</button>
					</form>
				</div>
		</div>
	</body>
</html>