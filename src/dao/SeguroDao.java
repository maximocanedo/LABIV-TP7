package dao;

import java.sql.SQLException;

import entidad.Seguro;
import max.Connector;
import max.Dictionary;
import max.IRecord;
import max.LogicResponse;
import max.TransactionResponse;
import negocio.SeguroNegocio;

public class SeguroDao  implements IRecord<Seguro, Integer>{

	protected final String db = "segurosgroup";
	
	
	@Override
	public TransactionResponse<Seguro> getAll() throws SQLException {
		// 
		return select("SELECT seguros.*, tiposeguros.descripcion 'Tipo_Descripcion' FROM seguros "
				+ "INNER JOIN tiposeguros ON seguros.idTipo = tiposeguros.idTipo ");
	}
	
	
	@Override
	public TransactionResponse<?> delete(Seguro n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector(db).transact(
					"DELETE FROM seguros idSeguro = @id", 
					Dictionary.fromArray(
						"id", n.getId()
					));
		} catch(SQLException e) {
			e.printStackTrace();
			t.dbError = e;
			throw e;
		}
		return t;
		
	}

	@Override
	public boolean exists(Integer id) throws SQLException {
		TransactionResponse<Dictionary> res = new Connector(db).fetch(
					"SELECT COUNT(idSeguro) as counted FROM seguros WHERE idSeguro = @id", 
					Dictionary.fromArray("id", id)
				);
		if(res.nonEmptyResult()) {
			Dictionary row = res.rowsReturned.get(0);
			long counted = row.$("counted");
			return counted > 0;			
		}
		return false;
	}

	

	@Override
	public TransactionResponse<Seguro> getById(Integer id) throws SQLException {
		return select("SELECT * FROM seguros WHERE idSeguro = @id", Dictionary.fromArray("id", id));
	}

	@Override
	public TransactionResponse<?> insert(Seguro n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		LogicResponse<Seguro> validated = new SeguroNegocio().validate(n, true);
		if(validated.status) {
			try {
				t = new Connector(db).transact(
						"INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) "
						+ "SELECT @descripcion, @tipo, @cc, @ca", 
						Dictionary.fromArray(
							"descripcion",  n.getDescripcion(),
							"cc", n.getCostoContratacion(),
							"ca", n.getCostoAsegurado(),
							"tipo", n.getTipo().getId()
						));
			} catch(SQLException e) {
				e.printStackTrace();
				t.dbError = e;
				throw e;
			}
			return t;
		}
		t.status = false;
		return t;
	}

	@Override
	public TransactionResponse<?> modify(Seguro n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		LogicResponse<Seguro> validated = new SeguroNegocio().validate(n, true);
		if(validated.status) {
			try {
				t = new Connector(db).transact(
						"UPDATE seguros SET descripcion = @descripcion, idTipo = @tipo, costoContratacion = @cc, costoAsegurado = @ca WHERE idSeguro = @id", 
						Dictionary.fromArray(
							"descripcion",  n.getDescripcion(),
							"cc", n.getCostoContratacion(),
							"ca", n.getCostoAsegurado(),
							"id", n.getId(),
							"tipo", n.getTipo().getId()
						));
			} catch(SQLException e) {
				e.printStackTrace();
				t.dbError = e;
				throw e;
			}
			return t;
		}
		t.status = false;
		return t;
		
		
	}

	@Override
	public TransactionResponse<Seguro> select(String query) throws SQLException {
		TransactionResponse<Seguro> r = new TransactionResponse<Seguro>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query);
			r.rowsReturned = new SeguroNegocio().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

	@Override
	public TransactionResponse<Seguro> select(String query, Dictionary params) throws SQLException {
		TransactionResponse<Seguro> r = new TransactionResponse<Seguro>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query, params);
			r.rowsReturned = new SeguroNegocio().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

	@Override
	public TransactionResponse<Seguro> select(String query, Object[] params) throws SQLException {
		TransactionResponse<Seguro> r = new TransactionResponse<Seguro>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query, params);
			r.rowsReturned = new SeguroNegocio().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

}
