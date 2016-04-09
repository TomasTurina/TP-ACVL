package Model;

public class Operation {
	Share share;
	float initialPrice;
	int quantity;
	float cost;
	
	public Operation(Share share,float initialPrice,int quantity,float cost) {
		this.share = share;
		this.initialPrice=initialPrice;
		this.quantity = quantity;
		this.cost = cost;
	}

	public void setShare(Share share) {
		this.share=share;
	}
	
	public Share getShare() {
		return share;
	}
	
	public void setInitialPrice(float initialPrice) {
		this.initialPrice=initialPrice;
	}
	
	public float getInitialPrice() {
		return initialPrice;
	}
	
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setCost(float cost) {
		this.cost=cost;
	}
	
	public float getCost() {
		return cost;
	}
}
