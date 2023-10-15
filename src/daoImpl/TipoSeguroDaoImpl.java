package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoSeguroDao;
import entidad.Seguro;
import entidad.TipoSeguro;

public class TipoSeguroDaoImpl implements TipoSeguroDao{

	@Override
	public boolean insert(TipoSeguro tipoSeguro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TipoSeguro tipoSeguro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(TipoSeguro tipoSeguro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TipoSeguro> readAll() {
		PreparedStatement statement= null;
		ResultSet resultSet= null; 
		ArrayList<TipoSeguro> tipoSeguros = new ArrayList<TipoSeguro>();
		Conexion conexion = null;
		try 
		{
			
			conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement("select * from tipoSeguros");
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				tipoSeguros.add(getTipoSeguros(resultSet));
			}
		} 
		catch (SQLException e)  
		{
			e.printStackTrace();
		}
		
		return tipoSeguros; 
	}
	
	private static TipoSeguro getTipoSeguros(ResultSet resultSet) throws SQLException
	{
		Integer idSeguro = resultSet.getInt("idTipo");
		String descripcion = resultSet.getString("descripcion");
		
		return new TipoSeguro(idSeguro, descripcion);
	}

	@Override
	public boolean existe(TipoSeguro tipoSeguro) {
		// TODO Auto-generated method stub
		return false;
	}

	public static TipoSeguro BuscarPorID(int id) {
		// TODO Auto-generated method stub
		PreparedStatement statement= null;
		ResultSet resultSet= null; 
		Conexion conexion = null;
		
		try 
		{
			
			conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement("select * from tipoSeguros where idTipo= "+id);
			resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				TipoSeguro tp= new TipoSeguro();
				tp= getTipoSeguros(resultSet);
				return tp;
			}
		} 
		catch (SQLException e)  
		{
			e.printStackTrace();
		}
		
		return null; 
	}

}
