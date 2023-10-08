package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.*;
import max.*;

public class InsuranceData implements IRecord<Insurance, Integer> {

	@Override
	public Insurance convert(Dictionary d) {
		Insurance n = new Insurance();
		if(d.get("idSeguro") != null) n.setId((int)d.get("idSeguro"));
		if(d.get("descripcion") != null) n.setDescription((String)d.get("descripcion"));
		if(d.get("costoContratacion") != null) n.setHiringCost((double)d.get("costoContratacion"));
		if(d.get("costoAsegurado") != null) n.setInsuredCost((double)d.get("costoAsegurado"));
		return n;
	}

	@Override
	public List<Insurance> convert(List<Dictionary> ld) {
		List<Insurance> l = new ArrayList<Insurance>();
		for(Dictionary d : ld) {
			Insurance i = convert(d);
			l.add(i);
		}
		return l;
	}

	@Override
	public TransactionResponse<?> delete(Insurance n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector().transact(
					"DELETE FROM seguros WHERE idSeguro = @id", 
					Dictionary.fromArray(
						"id",  n.getId()
					));
		} catch(SQLException e) {
			e.printStackTrace();
			t.dbError = e;
			throw e;
		}
		return t;
	}

	@Override
	public boolean exists(Integer n) throws SQLException {
		boolean r = false;
		try {
			TransactionResponse<Dictionary> results = new Connector().fetch(
					"SELECT idSeguro FROM seguros WHERE idSeguros = @id", 
					Dictionary.fromArray("id", n)
					);
			r = (results != null && results.rowsReturned.size() > 0);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

	@Override
	public TransactionResponse<Insurance> getAll() throws SQLException {
		return select("SELECT * FROM seguros");
	}

	@Override
	public TransactionResponse<Insurance> getById(Integer id) throws SQLException {
		TransactionResponse<Insurance> fl = select("SELECT * FROM seguros WHERE idSeguro = @id", Dictionary.fromArray("id", id));
		if(fl != null && fl.rowsReturned.size() > 0) {
			Insurance i = fl.rowsReturned.get(0);
			TransactionResponse<Insurance> fr = new TransactionResponse<Insurance>() {{
				objectReturned = i;
			}};
			return fr;
		}
		return null;
	}

	@Override
	public TransactionResponse<?> insert(Insurance n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector().transact(
					"INSERT INTO seguros (descripcion, costoContratacion, costoAsegurado) SELECT @descripcion, @cc, @ca", 
					Dictionary.fromArray(
						"descripcion",  n.getDescription(),
						"cc", n.getHiringCost(),
						"ca", n.getInsuredCost()
					));
		} catch(SQLException e) {
			e.printStackTrace();
			t.dbError = e;
			throw e;
		}
		return t;
	}

	@Override
	public TransactionResponse<?> modify(Insurance n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector().transact(
					"UPDATE seguros SET descripcion = @descripcion, costoContratacion = @cc, costoAsegurado = @ca WHERE idSeguro = @id", 
					Dictionary.fromArray(
						"descripcion",  n.getDescription(),
						"cc", n.getHiringCost(),
						"ca", n.getInsuredCost(),
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
	public TransactionResponse<?> modify(Insurance arg0, Integer arg1) throws SQLException {
		// Método OBSOLETO que olvidé de eliminar de la librería. Recomendable usar el de un sólo parámetro.
		return null;
	}

	@Override
	public TransactionResponse<Insurance> select(String query) throws SQLException {
		TransactionResponse<Insurance> r = new TransactionResponse<Insurance>();
		List<Insurance> list = new ArrayList<Insurance>();
		try {
			TransactionResponse<Dictionary> results = new Connector().fetch(query);
			r.rowsReturned = convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		r.rowsReturned = list;
		return r;
	}

	@Override
	public TransactionResponse<Insurance> select(String query, Dictionary params) throws SQLException {
		TransactionResponse<Insurance> r = new TransactionResponse<Insurance>();
		List<Insurance> list = new ArrayList<Insurance>();
		try {
			TransactionResponse<Dictionary> results = new Connector().fetch(query, params);
			r.rowsReturned = convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		r.rowsReturned = list;
		return r;
	}

	@Override
	public TransactionResponse<Insurance> select(String query, Object[] params) throws SQLException {
		TransactionResponse<Insurance> r = new TransactionResponse<Insurance>();
		List<Insurance> list = new ArrayList<Insurance>();
		try {
			TransactionResponse<Dictionary> results = new Connector().fetch(query, params);
			r.rowsReturned = convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		r.rowsReturned = list;
		return r;
	}

}
