package test;

import java.sql.SQLException;

import data.*;
import entity.*;
import logic.*;

import max.data.*;
import max.tools.*;


public class Test {

	public static InsuranceData DATA = new InsuranceData();
	public static InsuranceLogic LOGIC = new InsuranceLogic();
	

	public static InsuranceCategoryData CDATA = new InsuranceCategoryData();
	public static InsuranceCategoryLogic CLOGIC = new InsuranceCategoryLogic();
	
	public static void out(Object e) {
		Misc.out(e);
	}
	
	public static void main(String[] args) {
		testInsert();
	}
	
	private static InsuranceCategory seguroDeCasas = new InsuranceCategory() {{
		setId(1);
		setDescription("Seguro de casas");
	}};
	private static InsuranceCategory seguroDeVida = new InsuranceCategory() {{
		setId(2);
		setDescription("Seguro de vida");
	}};
	private static InsuranceCategory seguroDeMotos = new InsuranceCategory() {{
		setId(3);
		setDescription("Seguro de motos");
	}};
	private static InsuranceCategory categoriaNoExistente = new InsuranceCategory() {{
		setId(599);
		setDescription("Categoría que no debería existir en la base de datos. ");
	}};
	
	public static void testInsert() {
		Insurance legitInsurance = new Insurance() {{
			setDescription("Luchemos por la vida");
			setCategory(seguroDeVida);
			setHiringCost(670.00);
			setInsuredCost(10700);
		}};
		try {
			LogicResponse<Insurance> insertResult = LOGIC.insert(legitInsurance);
			out(insertResult.message);
			if(insertResult.exception != null) out(insertResult.exception.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void testInsertCategory() {
		InsuranceCategory legitCategory = new InsuranceCategory() {{
			setDescription("Seguro de celulares");
		}};
		try {
			LogicResponse<InsuranceCategory> insertResult = CLOGIC.insert(legitCategory);
			if(insertResult.status) {
				out("Se agregó el registro con éxito. ");
			} else out("No se agregó el registro. ");
			if(insertResult.exception != null) out(insertResult.exception.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void testGetAll() {
		LogicResponse<Insurance> lri = LOGIC.getAll();
		for(Insurance i : lri.listReturned) {
			out(i.toString());
		}
	}

}
