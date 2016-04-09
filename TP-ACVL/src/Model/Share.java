package Model;

public class Share {
	String company;
	float price;
	float variation;
	
	public Share(String company,float price,float variation) {
		this.company = company;
		this.price = price;
		this.variation = variation;
	}
	
	public void setCompany(String company) {
		this.company=company;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setPrice(float price) {
		this.price=price;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setVariation(float variation) {
		this.variation=variation;
	}
	
	public float getVariation() {
		return variation;
	}
}
