package dao;

import java.util.List;

import entidad.Usuario;

public interface UsuarioDao {
	public boolean insert(Usuario usuario);
	public boolean delete(Usuario usuario);
	public boolean modify(Usuario usuario);
	public List<Usuario> readAll();
	public boolean existePersona(Usuario usuario);
}
