"use strict";
import * as mdce from "./material/material-components-web.js";
const formatearDinero = (cantidad) => {
	const formato = new Intl.NumberFormat("es-AR", {
		style: "currency",
		currency: "ARS",
		minimumFractionDigits: 2,
	});

	return formato.format(cantidad);
};
(() => {
	let datatable;
	const createTr = () => {
		const tr = datatable.content.insertRow();
		tr.classList.add("mdc-data-table__row");
		return tr;
	};

	const createCell = (tr, innerHTML, isNumber = false) => {
		const td = tr.insertCell();
		td.classList.add("mdc-data-table__cell");
		if (isNumber) td.classList.add("mdc-data-table__cell--numeric");
		td.append(innerHTML);
		return td;
	};

	const createRowFromJSON = (data) => {
		let tr = createTr();
		let idCell,
			descriptionCell,
			categoryCell,
			hiringCostCell,
			insuredCostCell;
		idCell = createCell(tr, data.id, true);
		descriptionCell = createCell(tr, data.description);
		let categoryLink = document.createElement("a");
		categoryLink.setAttribute("href", "#");
		categoryLink.innerText = data.category.description;
		categoryLink.addEventListener("click", (e) => {
			showDialog(
				`ID: ${data.category.id}.\nDescripción: ${data.category.description}`
			);
		});
		categoryCell = createCell(tr, categoryLink);
		hiringCostCell = createCell(tr, formatearDinero(data.hiringCost), true);
		insuredCostCell = createCell(
			tr,
			formatearDinero(data.insuredCost),
			true
		);

		return tr;
	};
	const loadData = (idCategory = -1) => {
		return fetch("./ListarServlet?category=" + idCategory)
			.then((response) => {
				if (!response.ok) {
					throw new Error(
						`Error al obtener los datos: ${response.status}`
					);
				}
				return response.json();
			})
			.then((data) => {
				return data.data;
			})
			.catch((error) => {
				console.error("Error:", error);
			});
	};
	const loadTable = () => {
		const catId = document.querySelector(".real-value").value;
		const page = 1;
		const itemsPerPage = 5;
		datatable.showProgress();
		const dataa = loadData(catId).then((data) => {
			setTimeout(() => {
				clearTable();
				for (let i = 0; i < data.length; i++) {
					let row = createRowFromJSON(data[i]);
					//window.datatable.content.append(row);
				}
			}, 1000);
			setTimeout(() => {
				datatable.hideProgress();
			}, 1000);
		});
	};
	window.loadTable = loadTable;

	window.mdc = mdce.default.mdc;

	//mdc.ripple.MDCRipple.attachTo(document.querySelector(".foo-button"));

	document.querySelectorAll(".mdc-text-field").forEach((element) => {
		var md = new mdc.textField.MDCTextField(element);
	});
	document.querySelectorAll(".mdc-select").forEach((element) => {
		var md = new mdc.select.MDCSelect(element);
		md.helperTextContent = "Elegí una categoría. ";
		md.required = true;
		md.useDefaultValidation = true;

		const form = element.closest("form");
		if (form != null) {
			form.addEventListener("submit", function (event) {
				const selectedText = element.querySelector(".real-value");
				if (!selectedText.value.trim()) {
					event.preventDefault(); // Evita el envío del formulario si no hay elemento seleccionado.
				}
			});
		}
	});
	document.querySelectorAll(".mdc-select.listar").forEach((element) => {
		var md = new mdc.select.MDCSelect(element);
		md.required = false;
	});

	document.querySelectorAll(".mdc-snackbar").forEach((element) => {
		var md = new mdc.snackbar.MDCSnackbar(element);
		window.showSnackbar = (message) => {
			md.labelText = message;
			md.open();
		};
	});
	let s = ".mdc-top-app-bar";
	document.querySelectorAll(s).forEach((element) => {
		var list = new mdc.topAppBar.MDCTopAppBar(element);
	});
	document.querySelectorAll(".mdc-data-table").forEach((element) => {
		datatable = new mdc.dataTable.MDCDataTable(element);
		window.clearTable = () => {
			datatable.getRows().forEach((row) => row.remove());
		};
	});
	document.querySelectorAll(".mdc-drawer").forEach((element) => {
		var list = new mdc.list.MDCList(element);
		list.wrapFocus = true;
	});

	document.querySelectorAll(".mdc-dialog").forEach((element) => {
		var md = new mdc.dialog.MDCDialog(element);
		window.showDialog = (message) => {
			document.querySelector("#my-dialog-content").innerText = message;
			md.open();
		};
	});
	(() => loadTable())();
	// Evitar envío de formulario
})();
