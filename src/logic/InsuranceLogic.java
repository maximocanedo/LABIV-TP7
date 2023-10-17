package logic;

import max.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.InsuranceCategoryData;
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
		static String CategoryNotFound = "El ID de categoría debe corresponder a una categoría existente. ";
		
	}
	
	@Override
	public LogicResponse<Insurance> delete(Insurance data) throws SQLException {
		LogicResponse<Insurance> finalResponse = new LogicResponse<Insurance>();
		try {
			TransactionResponse<?> transactionResult = DATA.delete(data);
			boolean deleted = transactionResult.rowsAffected > 0;
			finalResponse.die(deleted, deleted ? Text.DeletedSuccessfully : Text.ErrorTryingToDeleteRecord);
		} catch(SQLException e) {
			finalResponse.err(e);
			throw e;
		}		
		return finalResponse;
	}
	
	@Override
	public LogicResponse<Insurance> modify(Insurance data) throws SQLException {
		LogicResponse<Insurance> finalResponse = new LogicResponse<Insurance>();
		LogicResponse<Insurance> validationResponse = validate(data, false);
		if(validationResponse.status) {
			try {
				TransactionResponse<?> transactionResult = DATA.modify(data);
				boolean modified = transactionResult.rowsAffected > 0;
				finalResponse.die(modified, modified ? Text.ModifiedSuccessfully : Text.ErrorTryingToModifyRecord);
			} catch(SQLException e) {
				finalResponse.err(e);
				throw e;
			}	
		} else {
			finalResponse.die(false, validationResponse.message);
		}
		return finalResponse;
	}

	@Override
	public LogicResponse<Insurance> exists(Integer n) {
		boolean exists = false;
		try {
			exists = DATA.exists(n);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		boolean finalResult = exists;
		return new LogicResponse<Insurance>(finalResult, "");
	}

	@Override
	public LogicResponse<Insurance> getAll() {
		LogicResponse<Insurance> finalResult = new LogicResponse<Insurance>();
		try {
			TransactionResponse<Insurance> dataFetched = DATA.getAll();
			finalResult.fill(dataFetched.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return finalResult;
	}
	
	public LogicResponse<Insurance> filterByCategory(Integer cat) {
		LogicResponse<Insurance> finalResult = new LogicResponse<Insurance>();
		try {
			TransactionResponse<Insurance> dataFetched = DATA.filterByCategory(cat);
			finalResult.fill(dataFetched.rowsReturned);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return finalResult;
	}

	@Override
	public LogicResponse<Insurance> getById(Integer id) {
		LogicResponse<Insurance> finalResult = new LogicResponse<Insurance>();
		try {
			TransactionResponse<Insurance> dataFetched = DATA.getById(id);
			finalResult.fill(dataFetched.objectReturned);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return finalResult;
	}

	@Override
	public LogicResponse<Insurance> insert(Insurance data) throws SQLException {
		LogicResponse<Insurance> finalResult = new LogicResponse<Insurance>();
		LogicResponse<Insurance> validationResult = validate(data, true);
		if(validationResult.status) {
			try {
				TransactionResponse<?> transactionResult = DATA.insert(data);
				boolean added = transactionResult.rowsAffected > 0;
				finalResult.die(added, added ? Text.AddedSuccessfully : Text.ErrorTryingToAddRecord);
			} catch(SQLException e) {
				finalResult.err(e);
				throw e;
			}	
		} else {
			finalResult.die(false, validationResult.message);
		}
			
		return finalResult;
	}

	@Override
	public LogicResponse<Insurance> validate(Insurance i, boolean validatePK) {
		SchemaValidationResult svr = Insurance._schema.validate(i.toDictionary());
		return new LogicResponse<Insurance>(svr.status, svr.message);
		
	}

	
	@Override
	public Insurance convert(Dictionary row) {
	    Insurance insurance = new Insurance();
	    InsuranceCategory category = new InsuranceCategory();
	    if (row.$("idSeguro") != null) {
	        Integer idSeguro = row.$("idSeguro");
	        BigInteger bigIntegerIdSeguro = BigInteger.valueOf(idSeguro);
	        insurance.setId(bigIntegerIdSeguro.intValue()); 
	    }
	    if (row.$("descripcion") != null) {
	        insurance.setDescription(row.$("descripcion"));
	    }
	    if (row.$("costoContratacion") != null) {
	        BigDecimal costoContratacion = row.$("costoContratacion");
	        insurance.setHiringCost(costoContratacion.doubleValue());
	    }
	    if (row.$("costoAsegurado") != null) {
	        BigDecimal costoAsegurado = row.$("costoAsegurado");
	        insurance.setInsuredCost(costoAsegurado.doubleValue());
	    }
	    if(row.$("cat_des") != null) {
	    	String catDes = row.$("cat_des");
	    	category.setDescription(catDes);
	    }
	    if(row.$("idTipo") != null) {
	    	int idTipo = row.$("idTipo");
	    	category.setId(idTipo);
	    }
	    insurance.setCategory(category);
	    return insurance;
	}



	@Override
	public List<Insurance> convert(List<Dictionary> rows) {
		List<Insurance> insuranceArray = new ArrayList<Insurance>();
		for(Dictionary row : rows) {
			Insurance insurance = convert(row);
			insuranceArray.add(insurance);
		}
		return insuranceArray;
	}

}
