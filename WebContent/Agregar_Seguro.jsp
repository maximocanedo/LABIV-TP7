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

<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/checkout/">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
<link href="/docs/5.3/dist/css/bootstrap.min.css" rel="stylesheet">

<meta name="theme-color" content="#712cf9">
<link rel="stylesheet" type="text/css" href="Agregar_Seguro.css" >
<script type="text/javascript" src="CargarNav.js"></script>
<title>Agregar Seguro</title>
</head>
<body id= "Agregar Seguro" onload="nav()">

<div>
<ul class="nav nav-pills nav-fill gap-2 p-1 small bg-primary rounded-5 shadow-sm" id="pillNav2" style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-primary); --bs-nav-pills-link-active-bg: var(--bs-white);">
  <li class="nav-item">
    <a href="Inicio.jsp" class="nav-link rounded-5">Inicio</a>
  </li>
  <li class="nav-item">
    <a href="Agregar_Seguro.jsp" class="nav-link active rounded-5">Agregar Seguro</a>
  </li>
  <li class="nav-item">
    <a href="Listar_Seguros.jsp" class="nav-link rounded-5" >Listar Seguros</a>
  </li>
</ul>
</div>
<h1>Agregar Seguro</h1>
<form action="" method="post">
<label>ID Seguro</label><label id="IdSeguro"> aca debe ir el id</label><br/>
<label>Descripción</label><input type="text" placeholder="Ingrese una descripción..."><br/>
<label>Tipo de seguro</label>
<select id="TiposSeguro">
<option value="">Seleccionar</option>
</select><br/>
<label>Costo contratación</label><input type="text"placeholder="---"/><br/>
<label>Costo Máximo Asegurado</label><input type="text" placeholder="---"/>

</form>

</body>
</html>