package system;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import datamodel.Article;
import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;


public class OutputProcessor implements Components.OutputProcessor {
	
	private final OrderProcessor orderProcessor;
	private final InventoryManager inventoryManager;
	private final int printLineWidth = 95;
	

	 OutputProcessor(InventoryManager inventoryManager, OrderProcessor orderProcessor) {
		this.inventoryManager = inventoryManager;
		this.orderProcessor = orderProcessor; 
	}

	@Override
	public void printOrders(List<Order> orders, boolean printVAT) {
		StringBuffer sbAllOrders = new StringBuffer( "-------------" );
		StringBuffer sbLineItem = new StringBuffer();

		printVAT = true;
		for (Order ord : orders) {
			Customer customer = ord.getCustomer();

			String customerName = splitName( customer, customer.getLastName() + ", " + customer.getFirstName() );
//			String customerName = splitName( customer ,  customer.getFirstName() + ", " + customer.getLastName() );

			String customerOrders = "#" + ord.getId() + ", " + customerName + "'s Bestellung: ";
			//String customerOrders = "#" + ord.getId() + ", " + ord.getCustomer().getFirstName() + "'s Bestellung: ";
			long priceOrder = 0;			
			
			for (OrderItem oI : ord.getItems()) {
				priceOrder += oI.getArticle().getUnitPrice() * oI.getUnitsOrdered();
				customerOrders += " " + oI.getUnitsOrdered() + "x " + oI.getDescription();
			}
			long priceTotal = 0;
			priceTotal += priceOrder;		//Total Value
			String Orderfmt = fmtPrice(priceOrder, " EUR", 14);

			sbLineItem = fmtLine(customerOrders, Orderfmt, printLineWidth);
			sbAllOrders.append("\n");
			sbAllOrders.append(sbLineItem);
		}

		String fmtPriceTotal = pad(fmtPrice(26756, "", " EUR"), 14, true);
		sbAllOrders.append("\n")
			.append(fmtLine("----------", " ----------",printLineWidth))
			.append("\n")
			.append(fmtLine("Gesamtwert aller Bestellungen:",
					fmtPriceTotal,printLineWidth));
		System.out.println( sbAllOrders.toString() );
		
	}

	@Override
	public void printInventory(boolean sortedByInventoryValue) {
		List<Article> catalog = new ArrayList<Article>();
		System.out.println( "\n" );
		/*
		 * Implement sortedByInventoryValue flag
		 */
		if(sortedByInventoryValue) {
			List<Article> sorted = catalog.stream()
			.sorted((a1, a2) -> {
			long value1 = a1.getUnitPrice() * a1.getUnitsInStore();
			long value2 = a2.getUnitPrice() * a2.getUnitsInStore();
			if( value1 == value2) {
				return 0;
			} else if(value1 > value2) {
				return 1;
			}
			return -1;
			})
			.collect(Collectors.toList());
			sorted.stream()
			.forEach(a -> { System.out.println(a);
		});
	}
		
	}

	@Override
	public String fmtPrice(long price, String currency) {
		String fmtPrice = pad( fmtPrice( price, "", " " + currency ), 14, true );
		return fmtPrice;
	}

	@Override
	public String fmtPrice(long price, String currency, int width) {
		String fmtPrice = pad( fmtPrice( price, "", " " + currency ), 14, true );
		return fmtPrice;
	}

	@Override
	public StringBuffer fmtLine(String leftStr, String rightStr, int width) {
		StringBuffer sb = new StringBuffer( leftStr );
		int shiftable = 0;		// leading spaces before first digit
		for( int i=1; rightStr.charAt( i ) == ' ' && i < rightStr.length(); i++ ) {
			shiftable++;
		}
		final int tab1 = width - rightStr.length() + 1;	// - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max( 0, excess - shiftable );
		if( shift2 > 0 ) {
			rightStr = rightStr.substring( shift2, rightStr.length() );
			excess -= shift2;
		}
		if( excess > 0 ) {
			switch( excess ) {
			case 1:	sb.delete( sbLen - excess, sbLen ); break;
			case 2: sb.delete( sbLen - excess - 2 , sbLen ); sb.append( ".." ); break;
			default: sb.delete( sbLen - excess - 3, sbLen ); sb.append( "..." ); break;
			}
		}
		String strLineItem = String.format( "%-" + ( tab1 - 1 ) + "s%s", sb.toString(), rightStr );
		sb.setLength( 0 );
		sb.append( strLineItem );
		return sb;
	}
	
	//diese drei methoden sind extra
	private String pad( String str, int width, boolean rightAligned ) {
		String fmtter = ( rightAligned? "%" : "%-" ) + width + "s";
		String padded = String.format( fmtter, str );
		return padded;
	}
	
	private String fmtPrice( long price, String prefix, String postfix ) {
		StringBuffer fmtPriceSB = new StringBuffer();
		if( prefix != null ) {
			fmtPriceSB.append( prefix );
		}

		fmtPriceSB = fmtPrice( fmtPriceSB, price );

		if( postfix != null ) {
			fmtPriceSB.append( postfix );
		}
		return fmtPriceSB.toString();
	}
	
	private StringBuffer fmtPrice( StringBuffer sb, long price ) {
		if( sb == null ) {
			sb = new StringBuffer();
		}
		double dblPrice = ( (double)price ) / 100.0;			// convert cent to Euro
		DecimalFormat df = new DecimalFormat( "#,##0.00",
			new DecimalFormatSymbols( new Locale( "de" ) ) );	// rounds double to 2 digits

		String fmtPrice = df.format( dblPrice );				// convert result to String in DecimalFormat
		sb.append( fmtPrice );
		return sb;
	}

	/**
	* Split single‐String name to first‐ and last name and set to the customer
	* object, e.g. single‐String "Eric Meyer" is split into "Eric" and "Meyer".
	*
	* @param customer object for which first‐ and lastName are set
	* @param name single‐String name that is split into first‐ and last name
	* @return returns single‐String name extracted from customer object
	*/
	@Override
	public String splitName(Customer customer, String name) {
		String firstName = customer.getLastName();
		String lastName = customer.getFirstName();
		 boolean komma  = false;
	        String eingabe = name;
	        for (int i = 0; i < eingabe.length(); i++) {
	            if (eingabe.charAt(i) == ',') {
	                komma = true;
	            } 
	        }
	        if (komma == true){
	            String [] nAngabe = eingabe.trim().split(", ");
	            lastName = nAngabe[0];
	            firstName = nAngabe[1];
	        } else {
	            String [] nAngabe = eingabe.trim().split(" ");

	                lastName = nAngabe[nAngabe.length - 1]; //wegen der Indizierung
	                firstName = "";
	                for (int i = 0; i < nAngabe.length - 1; i++) {
	                    if(i > 0) {
	                        firstName += " ";
	                    }
	                    firstName += nAngabe[i];
	                }
	        }
	        customer.setFirstName(firstName);
	        customer.setLastName(lastName);
			return lastName + ", " + firstName;
			//return eingabe;
	}

	/**
	* Returns single‐String name obtained from first‐ and lastName attributes of
	* Customer object, e.g. "Eric", "Meyer" returns single‐String "Meyer, Eric".
	*
	* @param customer object referred to
	* @return sanitized name derived from first‐ and lastName attributes
	*/
	@Override
	public String singleName(Customer customer) {
		String fullName = customer.getLastName() + customer.getFirstName();
		return fullName;
	}

}
