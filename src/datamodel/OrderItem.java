package datamodel;

public class OrderItem {

	String description;
	final Article article;
	int unitsOrdered;
	
	protected OrderItem (String descr, Article article, int units) {
		description = descr;
		this.article = article;
		unitsOrdered = units;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String descr) {
		description = descr;
	}
	
	public Article getArticle() {
		return article;
	}
	
	public int getUnitsOrdered() {
		return unitsOrdered;
	}
	
	public void setUnitsOrdered(int number) {
		unitsOrdered = number;
	}
}
