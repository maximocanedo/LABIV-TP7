package entidad;

public class TipoSeguro {
	private Integer idTipo;
	private String descripcion;
	
	private static Integer cont=0;
	
	public static Integer proxId() {
		return cont++;
	}
	@Override
	public String toString() {
		return descripcion;
	}
	public TipoSeguro(String descripcion) {
		cont++;
		this.idTipo = cont;
		this.descripcion = descripcion;
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
