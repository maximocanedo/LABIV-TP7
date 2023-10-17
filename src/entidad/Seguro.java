package entidad;

import max.*;

public class Seguro {

	
	private int id;
	private String descripcion;
	private TipoSeguro tipo;
	private double costoContratacion;
	private double costoAsegurado;
	
	// Schema
	public static Schema _schema = Schema.fromArray(
			"id", new SchemaProperty() {{
				required = false; // No es requerido porque es automático y no hay que validarlo.
				type = Integer.class;
				min = 0;
			}},
			"descripcion", new SchemaProperty() {{
				required = true;
				type = String.class;
				minlength = 0;
				maxlength = 200;
			}},
			"idTipo", new SchemaProperty() {{
				required = true;
				type = Integer.class;
				ref = new ReferenceInfo("tiposeguros", "idTipo", "segurosgroup");
			}},
			"costoContratacion", new SchemaProperty() {{
				required = true;
				type = Double.class;
				min = 0;
			}},
			"costoAsegurado", new SchemaProperty() {{
				required = true;
				type = Double.class;
				min = 0;
			}}
		);
	
	public Seguro() {
		
	}
	
	public Seguro(int id,String desc ,TipoSeguro idTipo, double costoC, double costoA) {
		this.id = id;
		this.descripcion = desc;
		this.tipo = idTipo;
		this.costoContratacion = costoC;
		this.costoAsegurado = costoA;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoSeguro getTipo() {
		return tipo;
	}

	public void setTipo(TipoSeguro tipo) {
		this.tipo = tipo;
	}

	public double getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(double costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public double getCostoAsegurado() {
		return costoAsegurado;
	}

	public void setCostoAsegurado(double costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro [idSeguro=" + id + ", descripcion=" + descripcion + ", idTipo=" + tipo.toString()
				+ ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
	public String toJSON() {
		return "{ "
				+ "\"idSeguro\": " + id + ","
				+ "\"descripcion\": \"" + descripcion + "\""
				+ "\"tipo\": " + tipo.toJSON() + ","
				+ "\"costoContratacion\": " + costoContratacion + ", "
				+ "\"costoAsegurado\": " + costoAsegurado + " "
				+ "}";
	}
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"idSeguro", id,
				"descripcion", descripcion,
				"idTipo", tipo.getId(),
				"costoContratacion", costoContratacion,
				"costoAsegurado", costoAsegurado
			);
	}
	
	
}
