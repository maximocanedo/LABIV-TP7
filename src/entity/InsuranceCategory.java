package entity;

import java.sql.Types;

import max.schema.*;

@SuppressWarnings("serial")
public class InsuranceCategory {
	public static Schema _schema = new Schema("tiposeguros", "segurosgroup") {{
		setProperties(
			new SchemaProperty("idTipo") {{
				required = false;
				type = Types.INTEGER;
			}},
			new SchemaProperty("descripcion") {{
				required = true;
				type = Types.VARCHAR;
			}});
	}};
	@Override
	public String toString() {
		return "InsuranceCategory { ID: " + id + ", Description: " + description + " }";
	}
	public String toJSON() {
		return "{ \"id\": "+ id + ", \"description\": \"" + description + "\" }";
	}
	private int id;
	private String description; // varchar(50)
	public InsuranceCategory() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
