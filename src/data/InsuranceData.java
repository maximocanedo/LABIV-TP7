package data;

import java.math.BigInteger;
import java.sql.SQLException;

import entity.*;
import logic.InsuranceLogic;
import max.*;

/**
 * Esta clase maneja la capa de acceso a datos para la entidad Insurance.
 * Proporciona métodos para interactuar con la base de datos y realizar operaciones CRUD.
 */
public class InsuranceData implements IRecord<Insurance, Integer> {
	
	/**
     * Nombre de la base de datos a la que se conecta esta clase.
     */
	protected final String db = "segurosgroup";
	
	/**
     * Obtiene el próximo ID disponible para un nuevo registro de seguro.
     * @return El próximo ID disponible, o -1 si no se puede obtener.
     */
	public int getNextId() {
		int id = -1;
		try {
			TransactionResponse<Dictionary> tri = new Connector(db).fetch(
					"SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = @database AND TABLE_NAME = @table",
					Dictionary.fromArray(
							"database", "segurosgroup",
							"table", "seguros"
							)
					);
			if(tri.rowsReturned.size() > 0) {
				Dictionary row = tri.rowsReturned.get(0);
				BigInteger bigIntValue = row.$("AUTO_INCREMENT");
	            id = bigIntValue.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0 ? bigIntValue.intValue() : -1;
	           
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
     * Elimina un registro Insurance de la base de datos.
     * @param n El objeto Insurance a eliminar.
     * @return Un objeto TransactionResponse que indica el resultado de la operación.
     * @throws SQLException Si ocurre un error durante la eliminación.
     */
	@Override
	public TransactionResponse<?> delete(Insurance n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector(db).transact(
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

	/**
     * Verifica si existe un registro Insurance con el ID especificado en la base de datos.
     * @param n El objeto Insurance con el ID a buscar.
     * @return true si existe, false si no existe o si ocurrió un error durante la operación.
     * @throws SQLException Si ocurre un error durante la operación.
     */
	@Override
	public boolean exists(Integer n) throws SQLException {
		boolean r = false;
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(
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
	
	/**
	 * Obtiene todos los registros Insurance de la base de datos.
	 * @return Un objeto TransactionResponse que contiene una lista de objetos Insurance.
	 * @throws SQLException Si ocurre un error al obtener los registros de la base de datos.
	 */
	@Override
	public TransactionResponse<Insurance> getAll() throws SQLException {
		return select("SELECT * FROM seguros");
	}

	/**
	 * Obtiene un registro Insurance por su ID desde la base de datos.
	 * @param id El ID del seguro que se desea obtener.
	 * @return Un objeto TransactionResponse que contiene el objeto Insurance correspondiente al ID, o null si no se encuentra.
	 * @throws SQLException Si ocurre un error al buscar el registro en la base de datos.
	 */
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

	/**
	 * Inserta un nuevo registro Insurance en la base de datos.
	 * @param n El objeto Insurance que se desea insertar.
	 * @return Un objeto TransactionResponse que indica el resultado de la operación.
	 * @throws SQLException Si ocurre un error al insertar el registro en la base de datos.
	 */
	@Override
	public TransactionResponse<?> insert(Insurance n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector(db).transact(
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

	/**
	 * Modifica un registro Insurance existente en la base de datos.
	 * @param n El objeto Insurance con las modificaciones que se desean aplicar.
	 * @return Un objeto TransactionResponse que indica el resultado de la operación.
	 * @throws SQLException Si ocurre un error al modificar el registro en la base de datos.
	 */
	@Override
	public TransactionResponse<?> modify(Insurance n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector(db).transact(
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

	/**
	 * Ejecuta una consulta SQL en la base de datos y devuelve una lista de objetos Insurance
	 * basada en la consulta especificada.
	 *
	 * @param query La consulta SQL que se ejecutará en la base de datos.
	 * @return Un objeto TransactionResponse que contiene una lista de objetos Insurance
	 *         que coinciden con la consulta.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta en la base de datos.
	 */
	@Override
	public TransactionResponse<Insurance> select(String query) throws SQLException {
		TransactionResponse<Insurance> r = new TransactionResponse<Insurance>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query);
			r.rowsReturned = new InsuranceLogic().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

	/**
	 * Ejecuta una consulta SQL parametrizada en la base de datos y devuelve una lista de objetos Insurance
	 * basada en la consulta especificada y los parámetros proporcionados.
	 *
	 * @param query  La consulta SQL parametrizada que se ejecutará en la base de datos.
	 * @param params Un objeto Dictionary que contiene los parámetros y sus valores para la consulta.
	 * @return Un objeto TransactionResponse que contiene una lista de objetos Insurance
	 *         que coinciden con la consulta.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta en la base de datos.
	 */
	@Override
	public TransactionResponse<Insurance> select(String query, Dictionary params) throws SQLException {
		TransactionResponse<Insurance> r = new TransactionResponse<Insurance>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query, params);
			r.rowsReturned = new InsuranceLogic().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

	/**
	 * Ejecuta una consulta SQL parametrizada en la base de datos y devuelve una lista de objetos Insurance
	 * basada en la consulta especificada y los parámetros proporcionados como un arreglo de objetos.
	 *
	 * @param query  La consulta SQL parametrizada que se ejecutará en la base de datos.
	 * @param params Un arreglo de objetos que contiene los parámetros y sus valores para la consulta.
	 * @return Un objeto TransactionResponse que contiene una lista de objetos Insurance
	 *         que coinciden con la consulta.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta en la base de datos.
	 */
	@Override
	public TransactionResponse<Insurance> select(String query, Object[] params) throws SQLException {
		TransactionResponse<Insurance> r = new TransactionResponse<Insurance>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query, params);
			r.rowsReturned = new InsuranceLogic().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

}
