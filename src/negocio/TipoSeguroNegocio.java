package negocio;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoSeguroDao;
import entidad.TipoSeguro;
import max.Dictionary;
import max.IRecordLogic;
import max.LogicResponse;
import max.TransactionResponse;

public class TipoSeguroNegocio implements IRecordLogic<TipoSeguro, Integer>{
	
	public static TipoSeguroDao tsDao = new TipoSeguroDao();
	
	
	
	@Override
	public LogicResponse<TipoSeguro> getAll() {
		
		LogicResponse<TipoSeguro> result = new LogicResponse<TipoSeguro>();
		try {
			TransactionResponse<TipoSeguro> tipos = tsDao.getAll();
			result.listReturned= tipos.rowsReturned;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	
	
	@Override
	public TipoSeguro convert(Dictionary row) {
		TipoSeguro seguro = new TipoSeguro();
	    if (row.$("idTipo") != null) {
	        Integer idSeguro = row.$("idTipo");
	        BigInteger bigIntegerIdSeguro = BigInteger.valueOf(idSeguro);
	        seguro.setIdTipo(bigIntegerIdSeguro.intValue()); 
	    }
	    if (row.$("descripcion") != null) {
	        seguro.setDescripcion(row.$("descripcion"));
	    }
	    return seguro;
		
	}

	@Override
	public List<TipoSeguro> convert(List<Dictionary> arg0) {
		List<TipoSeguro> l = new ArrayList<TipoSeguro>();
		for(Dictionary d : arg0) {
			TipoSeguro i = convert(d);
			l.add(i);
		}
		return l;
	}

	@Override
	public LogicResponse<TipoSeguro> delete(TipoSeguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoSeguro> exists(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public LogicResponse<TipoSeguro> getById(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoSeguro> insert(TipoSeguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoSeguro> modify(TipoSeguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<TipoSeguro> validate(TipoSeguro arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	} 
}
