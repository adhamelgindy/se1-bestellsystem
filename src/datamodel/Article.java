package datamodel;


public class Article {

	private String id;
	private String description;
	private long unitPrice;
	private int unitsInStore;
	
	protected Article(String id, String descr, long price, int units) {
		this.id = id;
		if(descr == null) {
			this.description = "";
		}else {
			this.description = descr;
		}
		if(price < 0 || price == Long.MAX_VALUE) {
			this.unitPrice = 0;
		}else {
			this.unitPrice = price;
		}
		if(units < 0 || units == Long.MAX_VALUE) {
			this.unitsInStore = 0;
		}else {
			this.unitsInStore = units;
		}
	}
	
	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public long getUnitPrice() {
		return unitPrice;
	}
	
	public int getUnitsInStore() {
		return unitsInStore;
	}
	
	public void setDescription(String descr) {
		if(descr == null) {
			description = "";
		}else {
			description = descr;
		}
	}
	
	public void setUnitPrice(long price) {
		if(price < 0 || price == Long.MAX_VALUE) {
			unitPrice = 0;
		}else {
			unitPrice = price;
		}
	}
	
	public void setUnitsInStore(int number) {
		if(number < 0 || number == Integer.MAX_VALUE) {
			unitsInStore = 0;
		}else {
			unitsInStore = number;
		}
	}
	
}


//package datamodel;
//
//public class Article {
//	
//	String id;
//	String description;
//	long unitPrice;
//	int unitsInStore;
//	
//	protected Article (String id, String descr, long price, int units) {
//		this.id = id;
//        this.description =descr;
//        unitPrice = price;
//        unitsInStore = units;
//    }
//	
//	public String getId() {
//		return id;
//	}
//	
//	public String getDescription() {
//		return description;
//	}
//	
//	public void setDescription(String descr) {
//		this.description = descr;
//	}
//
//	public long getUnitPrice() {
//		return unitPrice;
//	}
//	
//	public void setUnitPrice(long price) {
//		this.unitPrice = price;
//	}
//	
//	public int getUnitsInStore() {
//		return unitsInStore;
//	}
//	
//	public void setUnitsInStore(int number) {
//		this.unitsInStore = number;
//	}
//}
