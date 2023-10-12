<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="max.*" %>
<%@ page import="max.LogicResponse" %>
<%@ page import="data.*" %>
<%@ page import="entity.*" %>
<%@ page import="logic.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>LABIV-TP7 · Listar seguros</title>
        <!-- Agregar metadatos PENDIENTE -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/material/material-components-web.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/index.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css" />
        <script>
        </script>
        <script src="${pageContext.request.contextPath}/index.js"></script>
    </head>
    
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
                    <span id="demo-label" class="mdc-floating-label">Categoría</span>
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
                            <th class="mdc-data-table__header-cell" role="columnheader" scope="col">Descripción</th>
                            <th class="mdc-data-table__header-cell" role="columnheader" scope="col">Categoría</th>
                            <th class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric"
                                role="columnheader" scope="col">Costo de contratación</th>
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