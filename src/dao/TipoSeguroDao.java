package dao;

import java.util.List;

import entidad.TipoSeguro;

public interface TipoSeguroDao {
	public boolean insert(TipoSeguro tipoSeguro);
	public boolean delete(TipoSeguro tipoSeguro);
	public boolean modify(TipoSeguro tipoSeguro);
	public List<TipoSeguro> readAll();
	public boolean existePersona(TipoSeguro tipoSeguro);
}
