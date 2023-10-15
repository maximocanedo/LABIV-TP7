<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script type="text/javascript" src="CargarNav.js"></script>

<title>Inicio</title>
</head>
<body id= "Inicio" onload="nav()">
<div>
<ul class="nav nav-pills nav-fill gap-2 p-1 small bg-primary rounded-5 shadow-sm" id="pillNav2" style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-primary); --bs-nav-pills-link-active-bg: var(--bs-white);">
  <li class="nav-item" >
    <a href="Inicio.jsp" class="nav-link active rounded-5">Inicio</a>
  </li>
  <li class="nav-item">
    <a href="Agregar_Seguro.jsp" class="nav-link rounded-5">Agregar Seguro</a>
  </li>
  <li class="nav-item" >
    <a href="Listar_Seguros.jsp" class="nav-link rounded-5">Listar Seguros</a>
  </li>
</ul>
</div>


</body>


</html>