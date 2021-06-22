package datamodel;

public class OrderItem {

	String description;
	final Article article;
	int unitsOrdered;
	
	protected OrderItem (String descr, Article article, int units) {
		if(descr == null) {
			this.description = "";
		}else {
			this.description = descr;
		}
		if(units < 0) {
			this.unitsOrdered = 0;
		}else {
			this.unitsOrdered = units;
		}
		this.article = article;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String descr) {
		if(descr == null) {
			description = "";
		}else {
			description = descr;

		}

	}
	
	public Article getArticle() {
		return article;
	}
	
	public int getUnitsOrdered() {
		return unitsOrdered;
	}
	
	public void setUnitsOrdered(int number) {
		if(number < 0) {
			unitsOrdered = 0;
		}else {
			unitsOrdered = number;
		}
	}

}
