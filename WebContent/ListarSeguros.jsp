<%@ page import="data.*"%>
<%@ page import="entity.*" %>
<%@ page import="logic.*" %>
<%@ page import="data.*"%>
<%@ page import="max.data.*" %>
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
    <a href="AgregarSeguro.jsp?Param=2" class="nav-link rounded-5">Agregar Seguro</a>
  </li>
  <li class="nav-item">
    <a href="ListarSeguros.jsp?Param=3" class="nav-link active rounded-5" >Listar Seguros</a>
  </li>
</ul>
</div>

 
    <%! InsuranceCategoryData ICD = new InsuranceCategoryData(); %>
	<%! InsuranceData ID = new InsuranceData(); %>
	<%! InsuranceLogic IL = new InsuranceLogic(); %>
    <%! InsuranceCategoryLogic ICL = new InsuranceCategoryLogic(); %>
    <body>
    <main class="def-main add-s">
        <br><br><br>
        <span class="mdc-typography--headline4">Listar seguros</span>
        <br>
        <div class="inline-container">
            <div class="mdc-select listar mdc-select--filled demo-width-class ">
                <input type="hidden" class="real-value" name="category">
                <div class="mdc-select__anchor" role="button" aria-haspopup="listbox"
                    aria-expanded="false" aria-labelledby="demo-label demo-selected-text">
                    <span class="mdc-select__ripple"></span>
                    <span id="demo-label" class="mdc-floating-label">Categoria</span>
                    <span class="mdc-select__selected-text-container">
                        <span id="demo-selected-text" class="mdc-select__selected-text"></span>
                    </span>
                    <span class="mdc-select__dropdown-icon">
                        <svg class="mdc-select__dropdown-icon-graphic" viewBox="7 10 10 5" focusable="false">
                            <polygon class="mdc-select__dropdown-icon-inactive" stroke="none" fill-rule="evenodd"
                                points="7 10 12 15 17 10">
                            </polygon>
                            <polygon class="mdc-select__dropdown-icon-active" stroke="none" fill-rule="evenodd"
                                points="7 15 12 10 17 15">
                            </polygon>
                        </svg>
                    </span>
                    <span class="mdc-line-ripple"></span>
                </div>

                <div class="mdc-select__menu mdc-menu mdc-menu-surface mdc-menu-surface--fullwidth">
                    <ul class="mdc-list" role="listbox" aria-label="Food picker listbox">
                    <li class="mdc-list-item mdc-list-item--selected" aria-selected="true" data-value="-1" role="option">
                            <span class="mdc-list-item__ripple"></span>
                            <span class="mdc-list-item__text">
                                Todas las categorías
                            </span>
                        </li>
                    <%
								LogicResponse<InsuranceCategory> res = ICL.getAll();
								if(res.listReturned != null) {
									for(InsuranceCategory categ : res.listReturned) {%>
									<li class="mdc-list-item" aria-selected="false" data-value="<%=categ.getId() %>" role="option">
		                                <span class="mdc-list-item__ripple"></span>
		                                <span class="mdc-list-item__text">
		                                    <%=categ.getDescription() %>
		                                </span>
		                            </li>
								  <%}
								}
							%>
                        
                        
                    </ul>
                </div>

            </div>
        </div><br>
        <div class="f-h btns">
            <div class="mdc-touch-target-wrapper">
                <button type="button" id="filterBtn" onclick="window.loadTable();" class="mdc-button mdc-button--touch  mdc-button--raised">
                    <span class="mdc-button__ripple"></span>
                    <span class="mdc-button__touch"></span>
                    <span class="mdc-button__label">Filtrar</span>
                </button>
            </div>
        </div>
        <br>

        <div class="mdc-data-table">
            <div class="mdc-data-table__table-container">
                <table class="mdc-data-table__table" >
                    <thead>
                        <tr class="mdc-data-table__header-row">
                            <th class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric"
                                role="columnheader" scope="col">ID</th>
                            <th class="mdc-data-table__header-cell" role="columnheader" scope="col">Descripcion</th>
                            <th class="mdc-data-table__header-cell" role="columnheader" scope="col">Categoria</th>
                            <th class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric"
                                role="columnheader" scope="col">Costo de contratacion</th>
                            <th class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric"
                                role="columnheader" scope="col">Costo asegurado</th>
                        </tr>
                    </thead>
                    <tbody class="mdc-data-table__content">

                    </tbody>
                </table>
            </div>
            
            <div class="mdc-data-table__progress-indicator">
                <div class="mdc-data-table__scrim"></div>
                <div class="mdc-linear-progress mdc-linear-progress--indeterminate mdc-data-table__linear-progress"
                    role="progressbar" aria-label="Data is being loaded...">
                    <div class="mdc-linear-progress__buffer">
                        <div class="mdc-linear-progress__buffer-bar"></div>
                        <div class="mdc-linear-progress__buffer-dots"></div>
                    </div>
                    <div class="mdc-linear-progress__bar mdc-linear-progress__primary-bar">
                        <span class="mdc-linear-progress__bar-inner"></span>
                    </div>
                    <div class="mdc-linear-progress__bar mdc-linear-progress__secondary-bar">
                        <span class="mdc-linear-progress__bar-inner"></span>
                    </div>
                </div>
            </div>
                
        </div>
    </main>
    <br>



    </form>
    </main>
    <aside class="mdc-snackbar">
        <div class="mdc-snackbar__surface" role="status" aria-relevant="additions">
            <div class="mdc-snackbar__label" aria-atomic="false">

            </div>
            <div class="mdc-snackbar__actions" aria-atomic="true">

            </div>
        </div>
    </aside>
    <div class="mdc-dialog">
        <div class="mdc-dialog__container">
            <div class="mdc-dialog__surface" role="alertdialog" aria-modal="true" aria-labelledby="my-dialog-title"
                aria-describedby="my-dialog-content">
                <div class="mdc-dialog__content" id="my-dialog-content">

                </div>
                <div class="mdc-dialog__actions">

                    <button type="button" class="mdc-button mdc-dialog__button" data-mdc-dialog-action="discard">
                        <div class="mdc-button__ripple"></div>
                        <span class="mdc-button__label">Aceptar</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="mdc-dialog__scrim"></div>
    </div>
    <script type="module" src="res/material/material-components-web.js"></script>
    <script type="module" src="res/index.js"></script>
</body>
</html>