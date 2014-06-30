<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/estilos.css" />
<link type="text/css" rel="stylesheet" href="css/menu.css" />
<title>Farmacia</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="menu/menu.jsp"></jsp:include>		
	</div>
	<div class="content">
		Bienvenido: ${sessionScope.usuario.nombre_usuario}
	</div>
	<div class="footer">
		<jsp:include page="menu/footer.html"></jsp:include>
	</div>
</div>
</body>
</html>