<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>LABIV-TP7 · Agregar seguro</title>
        <!-- Agregar metadatos PENDIENTE -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css" />
        <script src="${pageContext.request.contextPath}/index.js"></script>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/material/material-components-web.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/index.css" />
    </head>
    
<body>
    <main class="def-main add-s">
        <br><br><br>
        <span class="mdc-typography--headline4">Agregar un seguro</span>
        <form action="#e" id="j" name="addForm">
            <br><br>
            <div class="inline-container">
                <label class="mdc-text-field mdc-text-field--filled">
                    <span class="mdc-text-field__ripple"></span>
                    <span class="mdc-floating-label" id="my-label-id">ID</span>
                    <input class="mdc-text-field__input" name="id" type="number" value="" id="nid" readonly
                        aria-labelledby="my-label-id">
                    <span class="mdc-line-ripple"></span>
                </label>
            </div>
            <br>
            <div class="inline-container">
                <label class="mdc-text-field mdc-text-field--filled">
                    <span class="mdc-text-field__ripple"></span>
                    <span class="mdc-floating-label" id="my-label-id">Descripción</span>
                    <input required class="mdc-text-field__input" name="description" minlength="1" maxlength="200"
                        type="text" aria-labelledby="my-label-id">
                    <span class="mdc-line-ripple"></span>
                </label>
                <div class="mdc-text-field-helper-line">
                    <div class="mdc-text-field-helper-text mdc-text-field-helper-text--persistent mdc-text-field-helper-text--validation-msg"
                        role="alert"></div>
                    <div class="mdc-text-field-character-counter"></div>
                </div>
            </div>
            <br>
            <div class="inline-container">
                <div class="mdc-select mdc-select--filled demo-width-class mdc-select--required">
                    <input type="hidden" class="real-value" name="category">
                    <div class="mdc-select__anchor" aria-required="true" role="button" aria-haspopup="listbox"
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
                        <ul id="select-category" class="mdc-list" role="listbox" aria-label="Food picker listbox">
							
                        </ul>
                    </div>

                </div>
            </div>
            <br>
            <div class="inline-container">
                <label class="mdc-text-field mdc-text-field--filled">
                    <span class="mdc-text-field__ripple"></span>
                    <span class="mdc-floating-label" id="my-label-id">Costo de contratación</span>
                    <input class="mdc-text-field__input" required name="hiringCost" step="0.01" min="0" type="number"
                        aria-labelledby="my-label-id">
                    <span class="mdc-line-ripple"></span>
                </label>
            </div>
            <br />
            <div class="inline-container">
                <label class="mdc-text-field mdc-text-field--filled">
                    <span class="mdc-text-field__ripple"></span>
                    <span class="mdc-floating-label" id="my-label-id">Costo asegurado</span>
                    <input class="mdc-text-field__input" required name="insuredCost" step="0.01" min="0" type="number"
                        aria-labelledby="my-label-id">
                    <span class="mdc-line-ripple"></span>
                </label>
            </div>
<br>
            <div class="f-h btns">
                <div class="mdc-touch-target-wrapper">
                    <button type="clear" class="mdc-button mdc-button--touch ">
                        <span class="mdc-button__ripple"></span>
                        <span class="mdc-button__touch"></span>
                        <span class="mdc-button__label">Limpiar</span>
                    </button>
                </div>
                <div class="mdc-touch-target-wrapper">
                    <button type="submit" id="btnAgregar" class="mdc-button mdc-button--touch  mdc-button--raised">
                        <span class="mdc-button__ripple"></span>
                        <span class="mdc-button__touch"></span>
                        <span class="mdc-button__label">Enviar</span>
                    </button>
                </div>
            </div>
        </form>
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
                    Discard draft?
                </div>
                <div class="mdc-dialog__actions">
                    <button type="button" class="mdc-button mdc-dialog__button" data-mdc-dialog-action="cancel">
                        <div class="mdc-button__ripple"></div>
                        <span class="mdc-button__label">Cancel</span>
                    </button>
                    <button type="button" class="mdc-button mdc-dialog__button" data-mdc-dialog-action="discard">
                        <div class="mdc-button__ripple"></div>
                        <span class="mdc-button__label">Discard</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="mdc-dialog__scrim"></div>
    </div>
    <script type="module" src="${pageContext.request.contextPath}/res/material/material-components-web.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/res/index.js"></script>
</body>
</html>