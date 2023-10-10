package logic;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.InsuranceCategoryData;
import entity.InsuranceCategory;
import max.*;

public class InsuranceCategoryLogic implements IRecordLogic<InsuranceCategory, Integer> {

	public static InsuranceCategoryData DATA = new InsuranceCategoryData();
	
	public static class Text {
		static String DeletedSuccessfully = "El registro se eliminó correctamente. ";
		static String ErrorTryingToDeleteRecord = "Hubo un error al intentar eliminar el registro. ";
		static String ModifiedSuccessfully = "El registro se modificó correctamente. ";
		static String ErrorTryingToModifyRecord = "Hubo un error al intentar modificar el registro. ";
		static String DuplicateID = "El ID ya existe. ";
		static String BadDescription = "La descripción debe contener entre 0 y 50 caracteres. ";
		static String AddedSuccessfully = "El registro se agregó correctamente. ";
		static String ErrorTryingToAddRecord = "Hubo un error al intentar agregar el registro. ";
		
	}
	
	@Override
	public LogicResponse<InsuranceCategory> delete(InsuranceCategory data) throws SQLException {
		LogicResponse<InsuranceCategory> lr = new LogicResponse<InsuranceCategory>();
		try {
			TransactionResponse<?> trDelete = DATA.delete(data);
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
	public LogicResponse<InsuranceCategory> modify(InsuranceCategory data) throws SQLException {
		LogicResponse<InsuranceCategory> lr = new LogicResponse<InsuranceCategory>();
		LogicResponse<InsuranceCategory> v = validate(data, false);
		if(v.status) {
			try {
				TransactionResponse<?> trInsert = DATA.modify(data);
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
		} else {
			lr.status = false;
			lr.message = v.message;
		}
		return lr;
	}

	@Override
	public LogicResponse<InsuranceCategory> exists(Integer n) {
		boolean exists = false;
		try {
			exists = DATA.exists(n);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		boolean f = exists;
		return new LogicResponse<InsuranceCategory>() {{
			status = f;
		}};
	}

	@Override
	public LogicResponse<InsuranceCategory> getAll() {
		LogicResponse<InsuranceCategory> data = new LogicResponse<InsuranceCategory>();
		try {
			TransactionResponse<InsuranceCategory> res = DATA.getAll();
			data.listReturned = res.rowsReturned;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public LogicResponse<InsuranceCategory> getById(Integer arg0) {
		LogicResponse<InsuranceCategory> data = new LogicResponse<InsuranceCategory>();
		try {
			TransactionResponse<InsuranceCategory> res = DATA.getById(arg0);
			data.objectReturned = res.objectReturned;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public LogicResponse<InsuranceCategory> insert(InsuranceCategory data) throws SQLException {
		LogicResponse<InsuranceCategory> lr = new LogicResponse<InsuranceCategory>();
		LogicResponse<InsuranceCategory> v = validate(data, true);
		if(v.status) {
			try {
				TransactionResponse<?> trInsert = DATA.insert(data);
				boolean added = trInsert.rowsAffected > 0;
				lr.die(added, added ? Text.AddedSuccessfully : Text.ErrorTryingToAddRecord);
			} catch(SQLException e) {
				lr.err(e);
				throw e;
			}	
		} else {
			lr.die(false, v.message);
		}
			
		return lr;
	}

	// TODO: Validar todos los campos e implementar en otros métodos.
	@Override
	public LogicResponse<InsuranceCategory> validate(InsuranceCategory i, boolean validatePK) {
		boolean descriptionOK = (i.getDescription()) instanceof String 
				&& (i.getDescription()).length() >= 0 
				&& (i.getDescription()).length() <= 50;
		boolean finalBool = descriptionOK;
		String finalMessage = (!descriptionOK ? Text.BadDescription : "");
		return new LogicResponse<InsuranceCategory>(finalBool, finalMessage);
		
	}

	@Override
	public InsuranceCategory convert(Dictionary row) {
		InsuranceCategory insurance = new InsuranceCategory();
	    if (row.$("idTipo") != null) {
	        Integer idSeguro = row.$("idTipo");
	        BigInteger bigIntegerIdSeguro = BigInteger.valueOf(idSeguro);
	        insurance.setId(bigIntegerIdSeguro.intValue()); 
	    }
	    if (row.$("descripcion") != null) {
	        insurance.setDescription(row.$("descripcion"));
	    }
	    return insurance;
	}



	@Override
	public List<InsuranceCategory> convert(List<Dictionary> ld) {
		List<InsuranceCategory> l = new ArrayList<InsuranceCategory>();
		for(Dictionary d : ld) {
			InsuranceCategory i = convert(d);
			l.add(i);
		}
		return l;
	}

}
