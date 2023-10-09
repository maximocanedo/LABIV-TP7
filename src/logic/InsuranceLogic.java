package logic;

import max.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.InsuranceData;
import entity.*;

public class InsuranceLogic implements IRecordLogic<Insurance, Integer> {

	public static InsuranceData DATA = new InsuranceData();
	
	public static class Text {
		static String DeletedSuccessfully = "El registro se eliminó correctamente. ";
		static String ErrorTryingToDeleteRecord = "Hubo un error al intentar eliminar el registro. ";
		static String ModifiedSuccessfully = "El registro se modificó correctamente. ";
		static String ErrorTryingToModifyRecord = "Hubo un error al intentar modificar el registro. ";
		static String DuplicateID = "El ID ya existe. ";
		static String BadDescription = "La descripción debe contener entre 0 y 200 caracteres. ";
		static String BadCategory = "La categoría debe ser una instancia de InsuranceCategory y tener un ID mayor a 0. ";
		static String BadHiringCost = "El costo de contratación debe ser mayor o igual a 0. ";
		static String BadInsuredCost = "El costo asegurado debe ser mayor o igual a 0. ";
		static String AddedSuccessfully = "El registro se agregó correctamente. ";
		static String ErrorTryingToAddRecord = "Hubo un error al intentar agregar el registro. ";
		
		
	}
	
	@Override
	public LogicResponse<Insurance> delete(Insurance data) throws SQLException {
		LogicResponse<Insurance> lr = new LogicResponse<Insurance>();
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
	public LogicResponse<Insurance> modify(Insurance data) throws SQLException {
		LogicResponse<Insurance> lr = new LogicResponse<Insurance>();
		LogicResponse<Insurance> v = validate(data, false);
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
	public LogicResponse<Insurance> exists(Integer n) {
		boolean exists = false;
		try {
			exists = DATA.exists(n);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		boolean f = exists;
		return new LogicResponse<Insurance>() {{
			status = f;
		}};
	}

	@Override
	public LogicResponse<Insurance> getAll() {
		LogicResponse<Insurance> data = new LogicResponse<Insurance>();
		try {
			TransactionResponse<Insurance> res = DATA.getAll();
			data.listReturned = res.rowsReturned;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public LogicResponse<Insurance> getById(Integer arg0) {
		LogicResponse<Insurance> data = new LogicResponse<Insurance>();
		try {
			TransactionResponse<Insurance> res = DATA.getById(arg0);
			data.objectReturned = res.objectReturned;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public LogicResponse<Insurance> insert(Insurance data) throws SQLException {
		LogicResponse<Insurance> lr = new LogicResponse<Insurance>();
		LogicResponse<Insurance> v = validate(data, true);
		if(v.status) {
			try {
				TransactionResponse<?> trInsert = DATA.insert(data);
				if(trInsert.rowsAffected > 0) {
					lr.status = true;
					lr.message = Text.AddedSuccessfully;
					
				} else {
					lr.status = false;
					lr.message = Text.ErrorTryingToAddRecord;
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

	// TODO: Validar todos los campos e implementar en otros métodos.
	@Override
	public LogicResponse<Insurance> validate(Insurance i, boolean validatePK) {
		
		boolean idOK = !validatePK;
		if(validatePK) {
			try {
				idOK = !DATA.exists(i.getId());
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		boolean descriptionOK = (i.getDescription()) instanceof String 
				&& (i.getDescription()).length() >= 0 
				&& (i.getDescription()).length() <= 200;
		boolean catOK = (i.getCategory()) instanceof InsuranceCategory
				&& (i.getCategory()).getId() >= 0;
		boolean hcOK = (i.getHiringCost()) >= 0;
		boolean icOK = (i.getInsuredCost()) >= 0;
		boolean finalBool = (idOK && descriptionOK && catOK && hcOK && icOK);
		String finalMessage = 
				(!idOK ? Text.DuplicateID : 
					(!descriptionOK ? Text.BadDescription :
						!catOK ? Text.BadCategory :
							!hcOK ? Text.BadHiringCost :
								!icOK ? Text.BadInsuredCost : ""));
		return new LogicResponse<Insurance>() {{
			status = finalBool;
			message = finalMessage;
		}};
		
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
