<%@ page import="java.sql.DriverManager" %>
<%@ page import="max.*" %>
<%@ page import="max.LogicResponse" %>
<%@ page import="data.*" %>
<%@ page import="entity.*" %>
<%@ page import="logic.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>LABIV-TP7 ï¿½ Agregar seguro</title>
        <!-- Agregar metadatos PENDIENTE -->
        <link rel="stylesheet" href="index.css" />
        <script src="./index.js"></script>
    </head>
    <body>
    <br />
    <%! InsuranceCategoryData ICD = new InsuranceCategoryData(); %>
	<%! InsuranceData ID = new InsuranceData(); %>
	<%! InsuranceLogic IL = new InsuranceLogic(); %>
    <%! InsuranceCategoryLogic ICL = new InsuranceCategoryLogic(); %>
    
    <br />
    <br />
    <ul>
    <li>Example </li>
      <%
      	LogicResponse<Insurance> ins = IL.getAll();
      	if(ins.listReturned != null) {
      		for(Insurance i : ins.listReturned) {
      			%>
      			<li><%=i.toString() %></li>
      			<%
      		}
      	}
      %>
    </ul>
    <br />
		<form action="AgregarSeguro.jsp" method="POST">
			<input type="number" name="txtId" readonly value="<%=ICD.getNextId() %>" />
			<select name="category">
				<option value="" selected disabled>Seleccione una opción. </option>
				<%
					LogicResponse<InsuranceCategory> res = ICL.getAll();
					if(res.listReturned != null) {
						for(InsuranceCategory categ : res.listReturned) {%>
						<option value="<%=categ.getId() %>"><%=categ.getDescription() %></option>
					  <%}
					}
				%>
			</select>
            <input type="text" name="txtDescription" placeholder="DescripciÃ³n" /> <br />
            <button type="submit">Agregar</button>
        </form>
        <br />
        <%
        String txtDescriptionValue = request.getParameter("txtDescription");
        if (txtDescriptionValue != null) {
        	%>
        	Descripción dada: <%=txtDescriptionValue %>
        	
        	<%
        }
        %>
    </body>
</html>