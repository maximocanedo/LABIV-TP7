package logic;

import max.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.InsuranceData;
import entity.*;

public class InsuranceLogic implements IRecordLogic<Insurance, Integer> {

	public static InsuranceData ID = new InsuranceData();
	
	public static class Text {
		static String DeletedSuccessfully = "El registro se eliminó correctamente. ";
		static String ErrorTryingToDeleteRecord = "Hubo un error al intentar eliminar el registro. ";
		static String ModifiedSuccessfully = "El registro se modificó correctamente. ";
		static String ErrorTryingToModifyRecord = "Hubo un error al intentar modificar el registro. ";
		
	}
	
	@Override
	public LogicResponse<Insurance> delete(Insurance data) throws SQLException {
		LogicResponse<Insurance> lr = new LogicResponse<Insurance>();
		try {
			TransactionResponse<?> trDelete = ID.delete(data);
			if(trDelete.rowsAffected > 0) {
				lr.status = true;
				lr.message = Text.DeletedSuccessfully;
			} else {
				lr.status = false;
				lr.message = Text.ErrorTryingToDeleteRecord;
			}
		} catch(SQLException e) {
			lr.exception = e;
			throw e;
		}		
		return lr;
	}
	
	@Override
	public LogicResponse<Insurance> modify(Insurance data) throws SQLException {
		LogicResponse<Insurance> lr = new LogicResponse<Insurance>();
		try {
			TransactionResponse<?> trInsert = ID.modify(data);
			if(trInsert.rowsAffected > 0) {
				lr.status = true;
				lr.message = Text.ModifiedSuccessfully;
			} else {
				lr.status = false;
				lr.message = Text.ErrorTryingToModifyRecord;
			}
		} catch(SQLException e) {
			lr.exception = e;
			throw e;
		}		
		return lr;
	}

	@Override
	public LogicResponse<Insurance> exists(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Insurance> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Insurance> getById(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Insurance> insert(Insurance arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Insurance> select(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Insurance> select(String arg0, Dictionary arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Insurance> select(String arg0, Object[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO: Validar todos los campos e implementar en otros métodos.
	@Override
	public LogicResponse<Insurance> validate(Insurance arg0) {
		// TODO Auto-generated method stub
		return null;
	}

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

}
