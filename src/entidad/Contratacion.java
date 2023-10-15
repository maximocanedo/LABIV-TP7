package entidad;

public class Contratacion {
	private Integer idContratacion;
	private Usuario nombreUsuario;
	private Seguro idSeguro;
	private double costoContratacion;
	public Integer getIdContratacion() {
		return idContratacion;
	}
	public void setIdContratacion(Integer idContratacion) {
		this.idContratacion = idContratacion;
	}
	public Usuario getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(Usuario nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public Seguro getIdSeguro() {
		return idSeguro;
	}
	public void setIdSeguro(Seguro idSeguro) {
		this.idSeguro = idSeguro;
	}
	public double getCostoContratacion() {
		return costoContratacion;
	}
	public void setCostoContratacion(double costoContratacion) {
		this.costoContratacion = costoContratacion;
	}
	public Contratacion(Integer idContratacion, Usuario nombreUsuario, Seguro idSeguro, double costoContratacion) {
		this.idContratacion = idContratacion;
		this.nombreUsuario = nombreUsuario;
		this.idSeguro = idSeguro;
		this.costoContratacion = costoContratacion;
	}
	@Override
	public String toString() {
		return "Contratacion [idContratacion=" + idContratacion + ", nombreUsuario=" + nombreUsuario + ", idSeguro="
				+ idSeguro.toString() + ", costoContratacion=" + costoContratacion + "]";
	}
	
}
