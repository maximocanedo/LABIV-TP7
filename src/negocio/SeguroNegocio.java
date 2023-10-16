package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.*;
import max.Dictionary;
import max.IRecordLogic;
import max.LogicResponse;
import max.SchemaValidationResult;

public class SeguroNegocio implements IRecordLogic<Seguro, Integer> {

	@Override
	public Seguro convert(Dictionary d) {
		Seguro obj = new Seguro();
		if(d.$("idSeguro") != null) {
			int id = d.$("idSeguro");
			obj.setId(id);
		}
		if(d.$("descripcion") != null) {
			String description = d.$("descripcion");
			obj.setDescripcion(description);
		}
		if(d.$("idTipo") != null) {
			TipoSeguro t = new TipoSeguro();
			t.setId(d.$("idTipo"));
			obj.setTipo(t);
		}
		if(d.$("costoContratacion") != null) {
			Double cc = d.$("costoContratacion");
			obj.setCostoContratacion(cc);
		}
		if(d.$("costoAsegurado") != null) {
			Double ca = d.$("costoAsegurado");
			obj.setCostoAsegurado(ca);
		}
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

	public Dictionary toDictionary(Seguro obj) {
		Dictionary d = Dictionary.fromArray(
			"idSeguro", obj.getId(),
			"descripcion", obj.getDescripcion(),
			"idTipo", obj.getTipo().getId(),
			"costoContratacion", obj.getCostoContratacion(),
			"costoAsegurado", obj.getCostoAsegurado()
		);
		return d;
	}
	
	@Override
	public LogicResponse<Seguro> delete(Seguro arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
		Dictionary data = toDictionary(s);
		SchemaValidationResult v = Seguro._schema.validate(data);
		r.die(v.status, v.message);
		return r;
	}

}
