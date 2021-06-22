package datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	final long id;
	final Date date;
	final Customer customer;
	private final List<OrderItem> items;
	
	protected Order(long id, Date date, Customer customer) {
		if(date == null) {
			this.date = new Date();
		} else {
			this.date = date;
		}
		this.id = id;
		this.customer = customer;
		this.items = new ArrayList<OrderItem>();
	}
	
	public long getId() {
		return id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Iterable<OrderItem> getItems() {
		return items;
	}
	
	public int count() {
		//return 0;
		return items.size();
	}
	
	public Order addItem(OrderItem item) {
		
		if (!(this.items.contains(item)) && item != null) {
			items.add(item);
		}
		return this;
		
	}
	
	public Order removeItem(OrderItem item) {
		items.remove(item);
		//return this.removeItem(item);
		return this;
		
	}
	
	public Order clearItems() {
		items.removeAll(items);
		//return this.clearItems();
		return this;
	}
}
