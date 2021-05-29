package system;

import datamodel.Order;

final class OrderProcessor implements Components.OrderProcessor {


	private final InventoryManager inventoryManager;

	OrderProcessor(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}

	@Override
	public boolean accept(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long orderValue(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override //do  // rateIndex: 1 as default (19% MwSt)
	public long vat(long grossValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override //do
	public long vat(long grossValue, int rateIndex) {
		int percantage;
		if (rateIndex == 1) {
			percantage = 19;
		} else if(rateIndex == 2) {
			percantage = 7;
		}
		return 0;
	}

}
