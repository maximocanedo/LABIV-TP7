package dao;

import java.sql.SQLException;

import entidad.TipoSeguro;
import max.*;
import max.Dictionary;
import max.IRecord;
import max.TransactionResponse;
import negocio.TipoSeguroNegocio;
//quitar este de abajo


public class TipoSeguroDao implements IRecord<TipoSeguro, Integer> {

	protected final String db = "segurosgroup";
	
	@Override
	public TransactionResponse<TipoSeguro> getAll() throws SQLException {

	    return select("SELECT * FROM tiposeguros");

	}
	
	@Override
	public TransactionResponse<?> delete(TipoSeguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Integer arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public TransactionResponse<TipoSeguro> getById(Integer arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<?> insert(TipoSeguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<?> modify(TipoSeguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResponse<TipoSeguro> select(String query) throws SQLException {
		TransactionResponse<TipoSeguro> r = new TransactionResponse<TipoSeguro>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query);
			r.rowsReturned = new TipoSeguroNegocio().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

	@Override
	public TransactionResponse<TipoSeguro> select(String query, Dictionary params) throws SQLException {
		TransactionResponse<TipoSeguro> r = new TransactionResponse<TipoSeguro>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query, params);
			r.rowsReturned = new TipoSeguroNegocio().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

	@Override
	public TransactionResponse<TipoSeguro> select(String query, Object[] params) throws SQLException {
		TransactionResponse<TipoSeguro> r = new TransactionResponse<TipoSeguro>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query, params);
			r.rowsReturned = new TipoSeguroNegocio().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	
	}

	
}
