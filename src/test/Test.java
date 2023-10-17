package test;

import max.*;
import entidad.*;
import negocio.*;

public class Test {
	public static void testSeguros() {
		Seguro e = new Seguro() {{
			setDescripcion("Seguro de vida");
			setTipo(new TipoSeguro() {{
				setId(2);
			}});
			setCostoAsegurado(197.99);
			setCostoContratacion(10);
		}};
		LogicResponse<Seguro> v = new SeguroNegocio().validate(e, true);
		System.out.println(v.message);
	}
	public static void main(String[] args) {
		testSeguros();
	}
}
