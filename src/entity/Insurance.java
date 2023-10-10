package entity;

public class Insurance {
	@Override
	public String toString() {
		return "Insurance { ID: " + id + ", Description: " + description + ", Category: " + category + ", Hiring Cost : $"
				+ hiringCost + ", Insured Cost: $" + insuredCost + " }";
	}
	private int id; // Primary Key, not null, autoincrements
	private String description; // varchar(200)
	private InsuranceCategory category; // id int
	private double hiringCost; // decimal
	private double insuredCost; // decimal
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
