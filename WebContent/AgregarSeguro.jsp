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
<title>Insert title here</title>
</head>
<body>

<a href="Inicio.jsp?Param=1">Inicio</a>&nbsp
<a href="AgregarSeguro.jsp?Param=2">Agregar seguro</a>&nbsp
<a href="ListarSeguros.jsp?Param=3">Listar seguro</a>

<h1>Agregar seguros</h1>

<%
	int idActual = 0;
  	SeguroDao segDao = new SeguroDao();
  	
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
	<td><b>Costo contrataci�n</b></td>
	<td><input type="text" name= txtCostoContratacion /></td>
</tr>
<tr>
	<td><b>Costo m�ximo asegurado</b></td>
	<td><input type="text" name= txtCostoAsegurado /></td>
</tr>
<tr>
	<td></td>
	<td><input type="submit" name= "btnAceptar" value="Aceptar" /></td>
</tr>

</table>




</body>
</html>