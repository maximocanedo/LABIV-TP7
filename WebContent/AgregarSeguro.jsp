<%@ page import = "entidad.*" %> 
<%@ page import = "dao.*"%>
<%@ page import = "negocio.*" %>
<%@ page import="max.*" %>

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
<title>Insert title here</title>
</head>
<body>


<div>
<ul class="nav nav-pills nav-fill gap-2 p-1 small bg-primary rounded-5 shadow-sm" id="pillNav2" style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-primary); --bs-nav-pills-link-active-bg: var(--bs-white);">
  <li class="nav-item">
    <a href="Inicio.jsp?Param=1" class="nav-link rounded-5">Inicio</a>
  </li>
  <li class="nav-item">
    <a href="AgregarSeguro.jsp?Param=2" class="nav-link active rounded-5">Agregar Seguro</a>
  </li>
  <li class="nav-item">
    <a href="ListarSeguros.jsp?Param=3" class="nav-link rounded-5" >Listar Seguros</a>
  </li>
</ul>
</div>

<h1>Agregar seguros</h1>

<%
	
  	SeguroDao segDao = new SeguroDao();
	int idActual = segDao.ultimoID()+1;
  	TipoSeguroDao TsDao = new TipoSeguroDao();
  	TipoSeguroNegocio tsNeg = new TipoSeguroNegocio();
%>
<br>
<table>
<tr>
	<td><b>Id Seguro</b></td>
	<td><%=idActual%></td>
</tr>
<tr>
	<td><b>Descripcion</b></td>
	<td><input type="text" name="txtDescripcion" /></td>
</tr>
<tr>
	<td><b>Tipo de seguro</b></td>
	<td><select name="tipoSeguro">
						<%
						LogicResponse<TipoSeguro> lista = tsNeg.getAll();
						if(lista.listReturned !=null){
							for(TipoSeguro tipo : lista.listReturned){
								out.println("<option value="+tipo.getIdTipo()+">"+tipo.getDescripcion()+"</option>");		
							}
						}
						%>
					  </select></td>
</tr>
<tr>
	<td><b>Costo contratación</b></td>
	<td><input type="text" name= txtCostoContratacion /></td>
</tr>
<tr>
	<td><b>Costo máximo asegurado</b></td>
	<td><input type="text" name= txtCostoAsegurado /></td>
</tr>
<tr>
	<td></td>
	<td><input type="submit" name= "btnAceptar" value="Aceptar" /></td>
</tr>

</table>




</body>
</html>