package entidad;

public class Seguro {
	private Integer idSeguro;
	private String descripcion;
	private TipoSeguro idTipo;
	private double costoContratacion;
	private double costoAsegurado;
	
	
	@Override
	public String toString() {
		return "Seguro [idSeguro=" + idSeguro + ", descripcion=" + descripcion + ", idTipo=" + idTipo.toString()
				+ ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
	public Seguro(Integer idSeguro, String descripcion, TipoSeguro idTipo, double costoContratacion,
			double costoAsegurado) {
		this.idSeguro = idSeguro;
		this.descripcion = descripcion;
		this.idTipo = idTipo;
		this.costoContratacion = costoContratacion;
		this.costoAsegurado = costoAsegurado;
	}
	public Integer getIdSeguro() {
		return idSeguro;
	}
	public void setIdSeguro(Integer idSeguro) {
		this.idSeguro = idSeguro;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public TipoSeguro getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(TipoSeguro idTipo) {
		this.idTipo = idTipo;
	}
	public double getCostoContratacion() {
		return costoContratacion;
	}
	public void setCostoContratacion(float costoContratacion) {
		this.costoContratacion = costoContratacion;
	}
	public double getCostoAsegurado() {
		return costoAsegurado;
	}
	public void setCostoAsegurado(float costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}
}
