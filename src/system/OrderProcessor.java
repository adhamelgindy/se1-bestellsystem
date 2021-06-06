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
		
		return vat (grossValue, 1);
	}

	@Override //do
	public long vat(long grossValue, int rateIndex) {
		//Brutto-Preis / 1.19 * 0,19 = enthaltene MwSt.
		long ergebnis = 0;
		if (rateIndex == 1) {
			//runden nicht casten
			
			ergebnis = Math.round(grossValue * 0.19 / 1.19);
			//ergebnis = (double)(grossValue * 0.19 / 1.19);
	       
			
		} else if(rateIndex == 2) {
			
			ergebnis = Math.round(grossValue * 0.07 / 1.07);
		}
		return ergebnis;
	}

}


//17,328 => 17,33 runden 