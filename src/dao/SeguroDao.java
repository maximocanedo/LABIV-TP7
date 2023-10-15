package dao;

import java.util.List;

import entidad.Seguro;

public interface SeguroDao {
	public boolean insert(Seguro seguro);
	public boolean delete(Seguro seguro);
	public boolean modify(Seguro seguro);
	public List<Seguro> readAll();
	public boolean existePersona(Seguro seguro);
}
