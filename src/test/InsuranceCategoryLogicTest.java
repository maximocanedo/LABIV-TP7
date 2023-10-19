package test;

import max.data.*;
import entity.*;
import logic.*;
import data.*;
import java.sql.SQLException;

public class InsuranceCategoryLogicTest {
	
	public static InsuranceCategory catPrueba = new InsuranceCategory() {{
		setDescription("Prueba");
	}};

    public static void main(String[] args) throws SQLException {
        try {
        	//testDelete();
            //testModify();
        	//testExists();
        	int nid = new InsuranceCategoryData().getNextId();
        	out("Next ID: " + nid);
        	testGetAll();
        	//testGetById();
        	//testInsert();
        	//testValidate();
        	if(2 + 2 == 9) throw new SQLException();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
    }

    public static void testDelete() throws SQLException {
        InsuranceCategoryLogic logic = new InsuranceCategoryLogic();
        InsuranceCategory data = new InsuranceCategory() {{
        	setId(7);
        }}; // Aquí deberías proporcionar un objeto válido
        LogicResponse<InsuranceCategory> result = logic.delete(data);
        out(result.message);
        // Verifica el resultado y muestra un mensaje apropiado
    }

    public static void testModify() throws SQLException {
        InsuranceCategoryLogic logic = new InsuranceCategoryLogic();
        InsuranceCategory data = new InsuranceCategory() {{
        	setId(7);
        	setDescription("Prueba (Modificada)");
        }}; // Proporciona un objeto válido
        LogicResponse<InsuranceCategory> result = logic.modify(data);
        out(result.message);
        // Verifica el resultado y muestra un mensaje apropiado
    }

    public static void testExists() {
        InsuranceCategoryLogic logic = new InsuranceCategoryLogic();
        Integer id = 7; // Proporciona un ID válido
        LogicResponse<InsuranceCategory> result = logic.exists(id);
        out(result.status);
        // Verifica el resultado y muestra un mensaje apropiado
    }

    public static void testGetAll() {
        InsuranceCategoryLogic logic = new InsuranceCategoryLogic();
        LogicResponse<InsuranceCategory> result = logic.getAll();
        for(InsuranceCategory c : result.listReturned) {
        	out(c.toString());
        }
        // Verifica el resultado y muestra un mensaje apropiado
    }

    public static void testGetById() {
        InsuranceCategoryLogic logic = new InsuranceCategoryLogic();
        Integer id = 7; // Proporciona un ID válido
        LogicResponse<InsuranceCategory> result = logic.getById(id);
   
        // Verifica el resultado y muestra un mensaje apropiado
    }
    public static void out(Object e) {
    	System.out.println(e);
    }

    public static void testInsert() throws SQLException {
        InsuranceCategoryLogic logic = new InsuranceCategoryLogic();
        LogicResponse<InsuranceCategory> result = logic.insert(catPrueba);
        out(result.message);
        // Verifica el resultado y muestra un mensaje apropiado
    }

    public static void testValidate() {
        InsuranceCategoryLogic logic = new InsuranceCategoryLogic();
        InsuranceCategory data = new InsuranceCategory(); // Proporciona un objeto con campos válidos e inválidos
        LogicResponse<InsuranceCategory> result = logic.validate(data, true);
        // Verifica el resultado y muestra un mensaje apropiado
    }
}
