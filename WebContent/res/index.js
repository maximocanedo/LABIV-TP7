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
const actualizarID = (async () => {
	const p = document.querySelector("#nid");
	if(p == null) return;
	let data = await fetch(`./NextIdServlet`, {
		method: "GET",
	})
		.then((raw) => raw.json())
		.then((json) => {
			// console.log(json.data);
			p.value = json.data;
			p.focus();
			p.blur();
			return json.data;
		});
});
(async () => {
	const k = document.querySelector("#select-category--filter");
	if (k == null) return;
	k.innerHTML = "";
	let data = await fetch(`./ListarCategorias`, {
		method: "GET",
	})
		.then((raw) => raw.json())
		.then((json) => {
			console.log(json.data);
			let da = [{id: -1, description: "Todas las categorías"}, ...json.data];
			return da;
		});
	let generateEl = (id, description) => {
		const li = document.createElement("li");
		li.classList.add("mdc-list-item");
		li.setAttribute("data-value", id);
		li.setAttribute("role", "option");
		li.setAttribute("aria-selected", "false");

		const _ripple = document.createElement("span");
		_ripple.classList.add("mdc-list-item__ripple");

		const txt = document.createElement("span");
		txt.classList.add("mdc-list-item__text");
		txt.innerText = description;

		li.append(_ripple, txt);
		return li;
	};
	var md = new mdc.select.MDCSelect(document.querySelector(".mdc-select"));
	for (let i = 0; i < data.length; i++) {
		let element = generateEl(data[i].id, data[i].description);
		k.append(element);
	}
	md.layout();
	md.layoutOptions();
})();

actualizarID();
(async () => {
	const k = document.querySelector("#select-category");
	if (k == null) return;
	k.innerHTML = "";
	let data = await fetch(`./ListarCategorias`, {
		method: "GET",
	})
		.then((raw) => raw.json())
		.then((json) => {
			console.log(json.data);
			return json.data;
		});
	let generateEl = (id, description) => {
		const li = document.createElement("li");
		li.classList.add("mdc-list-item");
		li.setAttribute("data-value", id);
		li.setAttribute("role", "option");
		li.setAttribute("aria-selected", "false");

		const _ripple = document.createElement("span");
		_ripple.classList.add("mdc-list-item__ripple");

		const txt = document.createElement("span");
		txt.classList.add("mdc-list-item__text");
		txt.innerText = description;

		li.append(_ripple, txt);
		return li;
	};
	var md = new mdc.select.MDCSelect(document.querySelector(".mdc-select"));
	for (let i = 0; i < data.length; i++) {
		let element = generateEl(data[i].id, data[i].description);
		k.append(element);
	}
	md.layout();
	md.layoutOptions();
})();

const agregarEvent = async () => {
	let d = {
		description: document.addForm.description.value,
		category: document.addForm.category.value,
		hiringCost: document.addForm.hiringCost.value,
		insuredCost: document.addForm.insuredCost.value,
	};
	console.log({d});
	let data = await fetch(
		`./AgregarServlet?description=${encodeURIComponent(
			d.description
		)}&category=${encodeURIComponent(
			d.category
		)}&hiringCost=${encodeURIComponent(
			d.hiringCost
		)}&insuredCost=${encodeURIComponent(d.insuredCost)}`,
		{
			method: "POST",
		}
	)
		.then((raw) => raw.json())
		.then((json) => {
			showSnackbar(json.message);
			(async () => {
				const p = document.querySelector("#nid");
				if(p == null) return;
				let data = await fetch(`./NextIdServlet`, {
					method: "GET",
				})
					.then((raw) => raw.json())
					.then((json) => {
						// console.log(json.data);
						p.value = json.data;
						p.focus();
						p.blur();
						return json.data;
					});
			})();
		});
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

		
	});
	const form = document.querySelector("form#j");
	
	const valForm = (e) => {
		if(!form.checkValidity()) {
			form.reportValidity();
			e.preventDefault();
			return false;
		}
		const selectedText = document.querySelector(".real-value");
		if (selectedText.value.length == 0) {
			e.preventDefault(); // Evita el envío del formulario si no hay elemento seleccionado.
			showSnackbar("Elija una categoría válida. ");
			return false;
		}
		actualizarID();
		return form.checkValidity() && selectedText.value.length > 0;
		};
		if(form != null) form.addEventListener("submit", async function (event) {
			valForm(event);
			await actualizarID();
			
		});
	
	document.querySelectorAll(".mdc-select.listar").forEach((element) => {
		var md = new mdc.select.MDCSelect(element);
		md.required = false;
	});
	
	if(document.querySelector("#btnAgregar") != null) {
		document.querySelector("#btnAgregar").addEventListener("click", async (e) => {
			e.preventDefault();
			if(valForm(e)) agregarEvent();
			await actualizarID();
		});
	}

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
	if(document.querySelector(".mdc-data-table") != null) loadTable();
	// Evitar envío de formulario
})();
