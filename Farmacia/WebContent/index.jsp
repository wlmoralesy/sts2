<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/Farmacia/css/estilos.css" />
<link rel="icon" href="/Farmacia/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/Farmacia/favicon.ico" type="image/x-icon" />
<title>Farmacia</title>
</head>
<body>
<div class="body">
	<div class="login">
		<form method="post" action="/Farmacia/login.html">
			<table>
				<tr>
					<td colspan="2">
						<img alt="" src="/Farmacia/img/logo.gif">
					</td>
				</tr>
				<tr>
					<td class="tGray12">Usuario</td>
					<td><input name="usu_usu" type="text"></td>
				</tr>
				<tr>
					<td class="tGray12">Clave</td>
					<td><input name="usu_pas" type="password"></td>
				</tr>
				<tr>
					<td colspan="2" align="center" style="height:45px;">
						<input id="button" type="submit" name="button" value="Ingresar" class="button"><br/>
						<span class="tBlack11">${requestScope.error}</span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<span class="tFooterLogin">&#169; 2013 Farmacias Acorfarma, Inc.</span>
					</td>
				</tr>
			</table>			
		</form>
	</div>
</div>
</body>
</html>