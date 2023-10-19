package data;

import java.math.BigInteger;
import java.sql.SQLException;

import entity.*;
import logic.InsuranceCategoryLogic;
import max.net.*;
import max.data.*;

public class InsuranceCategoryData implements IRecord<InsuranceCategory, Integer> {
	
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
							"table", "tiposeguros"
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
     * Elimina un registro InsuranceCategory de la base de datos.
     * @param n El objeto InsuranceCategory a eliminar.
     * @return Un objeto TransactionResponse que indica el resultado de la operación.
     * @throws SQLException Si ocurre un error durante la eliminación.
     */
	@Override
	public TransactionResponse<?> delete(InsuranceCategory n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector(db).transact(
					"DELETE FROM tiposeguros WHERE idTipo = @id", 
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
     * Verifica si existe un registro InsuranceCategory con el ID especificado en la base de datos.
     * @param n El objeto InsuranceCategory con el ID a buscar.
     * @return true si existe, false si no existe o si ocurrió un error durante la operación.
     * @throws SQLException Si ocurre un error durante la operación.
     */
	@Override
	public boolean exists(Integer n) throws SQLException {
		boolean r = false;
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(
					"SELECT idTipo FROM tiposeguros WHERE idTipo = @id", 
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
	 * Obtiene todos los registros InsuranceCategory de la base de datos.
	 * @return Un objeto TransactionResponse que contiene una lista de objetos InsuranceCategory.
	 * @throws SQLException Si ocurre un error al obtener los registros de la base de datos.
	 */
	@Override
	public TransactionResponse<InsuranceCategory> getAll() throws SQLException {
		return select("SELECT * FROM tiposeguros");
	}

	/**
	 * Obtiene un registro InsuranceCategory por su ID desde la base de datos.
	 * @param id El ID del InsuranceCategory que se desea obtener.
	 * @return Un objeto TransactionResponse que contiene el objeto InsuranceCategory correspondiente al ID, o null si no se encuentra.
	 * @throws SQLException Si ocurre un error al buscar el registro en la base de datos.
	 */
	@Override
	public TransactionResponse<InsuranceCategory> getById(Integer id) throws SQLException {
		TransactionResponse<InsuranceCategory> fl = select(
				"SELECT * FROM tiposeguros WHERE idTipo = @id", 
				Dictionary.fromArray("id", id));
		if(fl != null && fl.rowsReturned.size() > 0) {
			InsuranceCategory i = fl.rowsReturned.get(0);
			TransactionResponse<InsuranceCategory> fr = new TransactionResponse<InsuranceCategory>() {{
				objectReturned = i;
			}};
			return fr;
		}
		return null;
	}

	/**
	 * Inserta un nuevo registro InsuranceCategory en la base de datos.
	 * @param n El objeto InsuranceCategory que se desea insertar.
	 * @return Un objeto TransactionResponse que indica el resultado de la operación.
	 * @throws SQLException Si ocurre un error al insertar el registro en la base de datos.
	 */
	@Override
	public TransactionResponse<?> insert(InsuranceCategory n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector(db).transact(
					"INSERT INTO tiposeguros (descripcion) SELECT @description", 
					Dictionary.fromArray(
						"description", n.getDescription()
					));
		} catch(SQLException e) {
			e.printStackTrace();
			t.dbError = e;
			throw e;
		}
		return t;
	}

	/**
	 * Modifica un registro InsuranceCategory existente en la base de datos.
	 * @param n El objeto InsuranceCategory con las modificaciones que se desean aplicar.
	 * @return Un objeto TransactionResponse que indica el resultado de la operación.
	 * @throws SQLException Si ocurre un error al modificar el registro en la base de datos.
	 */
	@Override
	public TransactionResponse<?> modify(InsuranceCategory n) throws SQLException {
		TransactionResponse<?> t = TransactionResponse.create();
		try {
			t = new Connector(db).transact(
					"UPDATE tiposeguros SET descripcion = @descripcion WHERE idTipo = @id", 
					Dictionary.fromArray(
						"descripcion",  n.getDescription(),
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
	 * Ejecuta una consulta SQL en la base de datos y devuelve una lista de objetos InsuranceCategory
	 * basada en la consulta especificada.
	 *
	 * @param query La consulta SQL que se ejecutará en la base de datos.
	 * @return Un objeto TransactionResponse que contiene una lista de objetos InsuranceCategory
	 *         que coinciden con la consulta.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta en la base de datos.
	 */
	@Override
	public TransactionResponse<InsuranceCategory> select(String query) throws SQLException {
		TransactionResponse<InsuranceCategory> r = new TransactionResponse<InsuranceCategory>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query);
			r.rowsReturned = new InsuranceCategoryLogic().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

	/**
	 * Ejecuta una consulta SQL parametrizada en la base de datos y devuelve una lista de objetos InsuranceCategory
	 * basada en la consulta especificada y los parámetros proporcionados.
	 *
	 * @param query  La consulta SQL parametrizada que se ejecutará en la base de datos.
	 * @param params Un objeto Dictionary que contiene los parámetros y sus valores para la consulta.
	 * @return Un objeto TransactionResponse que contiene una lista de objetos Insurance
	 *         que coinciden con la consulta.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta en la base de datos.
	 */
	@Override
	public TransactionResponse<InsuranceCategory> select(String query, Dictionary params) throws SQLException {
		TransactionResponse<InsuranceCategory> r = new TransactionResponse<InsuranceCategory>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query, params);
			r.rowsReturned = new InsuranceCategoryLogic().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

	/**
	 * Ejecuta una consulta SQL parametrizada en la base de datos y devuelve una lista de objetos InsuranceCategory
	 * basada en la consulta especificada y los parámetros proporcionados como un arreglo de objetos.
	 *
	 * @param query  La consulta SQL parametrizada que se ejecutará en la base de datos.
	 * @param params Un arreglo de objetos que contiene los parámetros y sus valores para la consulta.
	 * @return Un objeto TransactionResponse que contiene una lista de objetos Insurance
	 *         que coinciden con la consulta.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta en la base de datos.
	 */
	@Override
	public TransactionResponse<InsuranceCategory> select(String query, Object[] params) throws SQLException {
		TransactionResponse<InsuranceCategory> r = new TransactionResponse<InsuranceCategory>();
		try {
			TransactionResponse<Dictionary> results = new Connector(db).fetch(query, params);
			r.rowsReturned = new InsuranceCategoryLogic().convert(results.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return r;
	}

}
