package entidad;

public class TipoSeguro {
	private Integer idTipo;
	private String descripcion;

	@Override
	public String toString() {
		return descripcion;
	}
	public TipoSeguro(Integer id, String descripcion) {
		this.idTipo = id;
		this.descripcion = descripcion;
	}
	public TipoSeguro() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIdTipo() {
		return idTipo;
	}
	
	public void setIdTipo(Integer id) {
		this.idTipo= id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
