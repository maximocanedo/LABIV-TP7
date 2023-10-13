package dao;

import java.sql.SQLException;

import entidad.Seguro;
import max.Dictionary;
import max.IRecord;
import max.TransactionResponse;

public class SeguroDao  implements IRecord<Seguro, Integer>{

	protected final String db = "segurosgroup";
	
	
	@Override
	public TransactionResponse<Seguro> getAll() throws SQLException {
		// 
		return select("SELECT seguros.*, tiposeguros.descripcion 'Tipo_Descripcion' FROM seguros "
				+ "INNER JOIN tiposeguros ON seguros.idTipo = tiposeguros.idTipo ");
	}
	
	
	@Override
	public TransactionResponse<?> delete(Seguro arg0) throws SQLException {
		return null;
	}

	@Override
	public boolean exists(Integer arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public TransactionResponse<Seguro> getById(Integer arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<?> insert(Seguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<?> modify(Seguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<Seguro> select(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<Seguro> select(String arg0, Dictionary arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<Seguro> select(String arg0, Object[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
