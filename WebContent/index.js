
"use strict";
class Header {
	constructor(settings) {
		let obj = {
			title: settings.title ?? "Trabajo Práctico",
			group: settings.group ?? "",
			links: settings.links ?? [["Link 1", "./Link1"]],
			otherLinks: settings.otherLinks ?? [
				["Repositorio", "https://github.com/maximocanedo"],
				["Foro", ""],
			],
		};
		this.header = document.createElement("div");
		this.header.classList.add("header");
		const title = document.createElement("span");
		title.classList.add("title");
		title.innerText = `${obj.title} · ${obj.group}`;
		this.header.append(title);
		obj.links.map((link) => {
			const linkEl = document.createElement("a");
			linkEl.setAttribute("href", link[1]);
			linkEl.innerText = link[0];
			this.header.append(linkEl);
		});
		for (let i = 0; i < 1; i++) {
			const space = document.createElement("div");
			space.classList.add("space");
			this.header.append(space);
		}
		obj.otherLinks.map((link) => {
			const linkEl = document.createElement("a");
			linkEl.setAttribute("href", link[1]);
			linkEl.innerText = link[0];
			this.header.append(linkEl);
		});
	}
	putOnPage() {
		document.body.prepend(this.header);
	}
}

window.onload = () => {
	let header = new Header({
		title: "Trabajo Práctico N.º 7",
		group: "Resolución de Máximo Canedo",
		links: [["Inicio", "/Inicio.jsp"], ["Agregar seguro", "/AgregarSeguro.jsp"], ["Listar seguros", "/ListarSeguros.jsp"]],
		otherLinks: [
			["Repositorio", "https://github.com/maximocanedo/LABIV-TP7"],
			["Foro", "https://example.org"],
		],
	});
	header.putOnPage();
};
