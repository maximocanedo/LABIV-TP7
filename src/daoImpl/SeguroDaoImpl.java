package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SeguroDao;
import entidad.Seguro;
import entidad.TipoSeguro;

public class SeguroDaoImpl implements SeguroDao{
	
	public boolean existe(Seguro seguro) {

		PreparedStatement statement= null;
		Connection conexion = null;
		ResultSet resultSet= null;  
		boolean existe = false;
		String consulta = "select * from seguros where idSeguro = (?)";
		try 
		{
			conexion = Conexion.getConexion().getSQLConexion();
			
			statement = conexion.prepareStatement(consulta);
			statement.setInt(1, seguro.getIdSeguro());
			
			resultSet = statement.executeQuery();
	        existe = resultSet.next();
		}
		catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conexion != null) {
	                conexion.rollback();
	            }
	            if (statement != null) {
	        	statement.close();}
	            if (resultSet != null) {
	            	resultSet.close();}
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        
	    } 
        
		return existe;
	}
	private boolean ejecutarSPInsert(Seguro seguro, String SP) {
	    Connection conexion = null;
	    CallableStatement callst = null;
	    boolean SPExitoso = false;

	    try {
	        conexion = Conexion.getConexion().getSQLConexion();
	        callst = conexion.prepareCall(SP);
	        callst.setInt(1, seguro.getIdSeguro());
	        callst.setString(2, seguro.getDescripcion());
	        callst.setInt(3, seguro.getIdTipo().getIdTipo());
	        callst.setDouble(4, seguro.getCostoContratacion());
	        callst.setDouble(5, seguro.getCostoAsegurado());
	        callst.execute();
	        conexion.commit(); // Confirmar la transacción
	        SPExitoso = true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conexion != null) {
	                conexion.rollback(); // Hacer rollback en caso de error
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    } 

	    return SPExitoso;
	}
	@Override
	public boolean insert(Seguro seguro) {
		String Insert= "{call crearSeguro(?, ?, ?, ?, ?)}";
		Boolean seguroCreado= ejecutarSPInsert(seguro, Insert);
		return seguroCreado;
		
	}
	
	@Override
	public List<Seguro> readAll() {
		PreparedStatement statement= null;
		ResultSet resultSet= null; 
		ArrayList<Seguro> seguros = new ArrayList<Seguro>();
		Conexion conexion = null;
		try 
		{
			
			conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement("select * from Seguros");
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				seguros.add(getSeguro(resultSet));
			}
		} 
		catch (SQLException e)  
		{
			e.printStackTrace();
		}
		
		return seguros; 
	}
	
	private Seguro getSeguro(ResultSet resultSet) throws SQLException
	{
		Integer idSeguro = resultSet.getInt("idSeguro");
		String descripcion = resultSet.getString("descripcion");
		
		TipoSeguro idTipo= TipoSeguroDaoImpl.BuscarPorID(resultSet.getInt("idTipo"));
		
		Double costoContratacion = resultSet.getDouble("costoContratacion");
		Double costoAsegurado = resultSet.getDouble("costoAsegurado");
		return new Seguro(idSeguro, descripcion, idTipo, costoContratacion, costoAsegurado);
	}

	@Override
	public boolean delete(Seguro seguro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(Seguro seguro) {
		// TODO Auto-generated method stub
		return false;
	}

}
