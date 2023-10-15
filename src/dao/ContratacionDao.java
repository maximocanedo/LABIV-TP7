package dao;

import java.util.List;

import entidad.Contratacion;

public interface ContratacionDao {
	public boolean insert(Contratacion contratacion);
	public boolean delete(Contratacion contratacion);
	public boolean modify(Contratacion contratacion);
	public List<Contratacion> readAll();
	public boolean existePersona(Contratacion contratacion);
}
