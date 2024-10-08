package max.net;

import java.sql.Connection;
import java.sql.SQLException;

import max.data.Dictionary;
import max.data.TransactionResponse;

public interface IConnector {
	
	/* M�todos */
	public Connection openConnection() throws SQLException;
	// Funci�n Fetch original
	public TransactionResponse<Dictionary> fetch(String query, Object[] parameters) throws SQLException;
	// Funciones Fetch que en alg�n momento llaman al original.
	public TransactionResponse<Dictionary> fetch(String query, Dictionary parameters) throws SQLException;
	public TransactionResponse<Dictionary> fetch(String query) throws SQLException;
	// Funci�n Transact original
	public TransactionResponse<?> transact(String query, Object[] parameters) throws SQLException;
	// Funciones Transact que en alg�n momento llaman al original.
	public TransactionResponse<?> transact(String query, Dictionary parameters) throws SQLException;
	public TransactionResponse<?> transact(String query) throws SQLException;
	
}