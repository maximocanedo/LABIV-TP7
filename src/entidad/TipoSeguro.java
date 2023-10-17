package entidad;

import max.Dictionary;
import max.Schema;
import max.SchemaProperty;

public class TipoSeguro {

	private int id;
	private String descripcion;
	
	public Schema _schema = Schema.fromArray(
			"idTipo", new SchemaProperty() {{
				required = false;
				type = Integer.class;
			}},
			"descripcion", new SchemaProperty() {{
				required = true;
				type = String.class;
			}}
		);
	
	public TipoSeguro() {
		
	}
	public TipoSeguro(int id, String desc) {
		this.id = id;
		this.descripcion = desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int idTipo) {
		this.id = idTipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "TipoSeguro [idTipo=" + id + ", descripcion=" + descripcion + "]";
	}
	public String toJSON() {
		return "{ \"idTipo\": " + id + ", \"descripcion\": \"" + descripcion + "\" }";
	}
	public Dictionary toDictionary() {
		return Dictionary.fromArray(
				"idTipo", id,
				"descripcion", descripcion
			);
	}
	
}
