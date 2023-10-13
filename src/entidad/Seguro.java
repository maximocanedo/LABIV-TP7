package entidad;

public class Seguro {

	
	private int idSeguro;
	private String descripcion;
	private TipoSeguro idTipoSeguro;
	private int costoContratacion;
	private int costoAsegurado;
	
	public Seguro(){
		
	}
	
	public Seguro(int id,String desc ,TipoSeguro idTipo, int costoC, int costoA) {
		this.idSeguro = id;
		this.descripcion = desc;
		this.idTipoSeguro = idTipo;
		this.costoContratacion = costoC;
		this.costoAsegurado = costoA;
	}

	public int getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoSeguro getIdTipoSeguro() {
		return idTipoSeguro;
	}

	public void setIdTipoSeguro(TipoSeguro idTipoSeguro) {
		this.idTipoSeguro = idTipoSeguro;
	}

	public int getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(int costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public int getCostoAsegurado() {
		return costoAsegurado;
	}

	public void setCostoAsegurado(int costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro [idSeguro=" + idSeguro + ", descripcion=" + descripcion + ", idTipoSeguro=" + idTipoSeguro
				+ ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
	
	
}
