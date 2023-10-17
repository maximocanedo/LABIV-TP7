package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SeguroDao;
import entidad.*;
import max.Dictionary;
import max.IRecordLogic;
import max.LogicResponse;
import max.SchemaValidationResult;
import max.TransactionResponse;

public class SeguroNegocio implements IRecordLogic<Seguro, Integer> {
	
	public SeguroDao DAO = new SeguroDao();
	
	@Override
	public Seguro convert(Dictionary d) {
		Seguro obj = new Seguro();
		TipoSeguro t = new TipoSeguro();
		if(d.$("idSeguro") != null) {
			int id = d.$("idSeguro");
			obj.setId(id);
		}
		if(d.$("descripcion") != null) {
			String description = d.$("descripcion");
			obj.setDescripcion(description);
		}
		if(d.$("idTipo") != null) {
			t.setId(d.$("idTipo"));
		}
		if(d.$("Tipo_Descripcion") != null) {
			t.setDescripcion(d.$("Tipo_Descripcion"));
		}
		if(d.$("costoContratacion") != null) {
			Double cc = d.$("costoContratacion");
			obj.setCostoContratacion(cc);
		}
		if(d.$("costoAsegurado") != null) {
			Double ca = d.$("costoAsegurado");
			obj.setCostoAsegurado(ca);
		}
		obj.setTipo(t);
		return obj;
	}

	@Override
	public List<Seguro> convert(List<Dictionary> p) {
		List<Seguro> l = new ArrayList<Seguro>();
		for(Dictionary d : p) {
			l.add(convert(d));
		}
		return l;		
	}

	
	public static class T {
		static String deletedSuccessfully = "El registro se eliminó correctamente. ";
		static String couldNotDelete = "Hubo un problema al intentar eliminar el registro. ";
	}
	@Override
	public LogicResponse<Seguro> delete(Seguro arg0) throws SQLException {
		TransactionResponse<?> opr = DAO.delete(arg0);
		LogicResponse<Seguro> res = new LogicResponse<Seguro>();
		boolean ok = opr.rowsAffected > 0;
		res.die(ok, ok ? T.deletedSuccessfully : T.couldNotDelete);
		return res;
	}

	@Override
	public LogicResponse<Seguro> exists(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Seguro> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Seguro> getById(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Seguro> insert(Seguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Seguro> modify(Seguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicResponse<Seguro> validate(Seguro s, boolean e) {
		LogicResponse<Seguro> r = new LogicResponse<Seguro>();
		Dictionary data = s.toDictionary();
		SchemaValidationResult v = Seguro._schema.validate(data);
		r.die(v.status, v.message);
		return r;
	}

}
