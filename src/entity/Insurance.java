package entity;

import max.ReferenceInfo;
import max.Schema;
import max.SchemaProperty;

@SuppressWarnings("serial")
public class Insurance {
	@Override
	public String toString() {
		return "Insurance { ID: " + id + ", Description: " + description + ", Category: " + category + ", Hiring Cost : $"
				+ hiringCost + ", Insured Cost: $" + insuredCost + " }";
	}
	public String toJSON() {
		return "{ \"id\": " + id + ", \"description\": \"" + description + "\", \"category\": " + category.toJSON() + 
				", \"hiringCost\": " + hiringCost + ", \"insuredCost\": " + insuredCost + " }";
	}
	private int id; // Primary Key, not null, autoincrements
	private String description; // varchar(200)
	private InsuranceCategory category; // id int
	private double hiringCost; // decimal
	private double insuredCost; // decimal
	
	public static Schema _schema = new Schema("seguros", "segurosgroup") {{ 
		setProperties(
			new SchemaProperty("id") {{
				required = false;
				type = Integer.class;
			}},
			new SchemaProperty("descripcion") {{
				required = true;
				type = String.class;
				maxlength = 200;
			}},
			new SchemaProperty("idTipo") {{
				required = true;
				type = Integer.class;
				ref = InsuranceCategory._schema.ref("idTipo");
			}},
			new SchemaProperty("costoContratacion") {{
				required = true;
				type = Double.class;
				min = 0;
			}},
			new SchemaProperty("costoAsegurado") {{
				required = true;
				type = Double.class;
				min = 0;
			}}
		);
	}};
	
	
	
	public Insurance() {}
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
	public InsuranceCategory getCategory() {
		return category;
	}
	public void setCategory(InsuranceCategory category) {
		this.category = category;
	}
	public double getHiringCost() {
		return hiringCost;
	}
	public void setHiringCost(double hiringCost) {
		this.hiringCost = hiringCost;
	}
	public double getInsuredCost() {
		return insuredCost;
	}
	public void setInsuredCost(double insuredCost) {
		this.insuredCost = insuredCost;
	}
	
}
