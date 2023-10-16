package entidad;

public class TipoSeguro {

	private int id;
	private String descripcion;
	
	
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
	
	
}
