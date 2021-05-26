//package application;
//
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import datamodel.Article;
//import datamodel.Customer;
//import datamodel.Order;
//import datamodel.OrderItem;
//
//
///**
// * 
// * Main class to launch the application.
// * 
// * @author svgr64
// *
// */
//public class Application_1 {
//
//	private final int printLineWidth = 84;
//
//
//	/**
//	 * main() to launch application.
//	 * 
//	 * @param args command line arguments
//	 */
//	public static void main(String[] args) {
//
//		System.out.println( "SE1-Bestellsystem" );
//
//		Application_1 application = new Application_1();
//
//		Customer cEric = new Customer( "C86516", "Eric Schulz-Mueller", "eric2346@gmail.com" );
//		Customer cAnne = new Customer( "C64327", "Meyer, Anne", "+4917223524" );
//		Customer cNadine = new Customer( "C12396", "Nadine-Ulla Blumenfeld", "+4915292454" );
//		Customer cTimo = new Customer("C94325", "Timo Werner", "tw@gmail.com");
//		Customer cSandra = new Customer("C85694", "Sandra Müller", "samue62@gmx.de");
//
//		Article aTasse = new Article( "SKU-458362", "Tasse", 299, 2000 );
//		Article aBecher = new Article( "SKU-693856", "Becher", 149, 8400 );
//		Article aKanne = new Article( "SKU-518957", "Kanne", 2000, 2400 );
//		Article aTeller = new Article( "SKU-638035", "Teller", 649, 7000 );
//		Article aKaffeemaschine = new Article( "SKU-162534", "Kaffeemaschine", 2999, 500 );
//		Article aTeekocher = new Article( "SKU-898932", "Teekocher", 1999, 2000 );
//
//		// Eric's 1st order
//		Order o5234 = new Order( 5234968294L, new Date(), cEric );
//		OrderItem oi1 = new OrderItem( aKanne.getDescription(), aKanne, 1 );	// 1x Kanne
//		o5234.addItem( oi1 );	// add OrderItem to Order 5234968294L
//
//		// Eric's 2nd order
//		Order o8592 = new Order( 8592356245L, new Date(), cEric );
//		o8592.addItem(	// add three OrderItems to Order 8592356245L
//			new OrderItem( aTeller.getDescription(), aTeller, 4 )		// 4x Teller
//		).addItem(
//			new OrderItem( aBecher.getDescription(), aBecher, 8 )		// 8x Becher
//		).addItem(
//			new OrderItem( "passende Tassen", aTasse, 4 )				// 4x passende Tassen
//		);
//
//		// Anne's order
//		Order o3563 = new Order( 3563561357L, new Date(), cAnne );
//		o3563.addItem(
//			new OrderItem( aKanne.getDescription() + " aus Porzellan", aKanne, 1 )
//		);
//
//		// Nadine's order
//		Order o6135 = new Order( 6135735635L, new Date(), cNadine );
//		o6135.addItem(													// 12x Teller
//			new OrderItem( aTeller.getDescription() + " blau/weiss Keramik", aTeller, 12 )
//		);
//		
//		// Timo's order
//		Order o1337 = new Order( 4835735356L, new Date(), cTimo );
//		o1337.addItem(
//			new OrderItem( aKaffeemaschine.getDescription(), aKaffeemaschine, 1)
//		).addItem(
//			new OrderItem( aTasse.getDescription(), aTasse, 6 )
//		);
//		
//		// Sandra's order
//		Order o5432 = new Order( 6399437335L, new Date(), cSandra );
//		o5432.addItem(new OrderItem( aTeekocher.getDescription(), aTeekocher, 1)
//				).addItem(
//					new OrderItem( aBecher.getDescription(), aBecher, 4 )
//				).addItem(
//					new OrderItem( aTeller.getDescription(), aTeller, 4 )
//				);
//
//		List<Order> orders = new ArrayList<Order>(List.of( o5234, o8592, o3563, o6135, o1337, o5432));		
//
//		application.printOrders( orders );		// Ausgabe aller Bestellungen
//
//	}
//
//
//	/**
//	 * Print orders to System.out in format (example):
//	 * 
//	 * #5234968294, Eric's Bestellung: 1x Kanne                           20,00 EUR
//	 * #8592356245, Eric's Bestellung: 4x Teller, 8x Becher, 4x Tassen    49,84 EUR
//	 * #3563561357, Anne's Bestellung: 1x Kanne aus Porzellan             20,00 EUR
//	 * #6135735635, Nadine Ulla's Bestellung: 12x Teller blau/weiss Ker.. 77,88 EUR
//	 * #4835735356, Timo's Bestellung: 1x Kaffeemaschine, 6x Tasse        47,93 EUR
//	 * #6399437335, Sandra's Bestellung: 1x Teekocher, 4x Becher, 4x Te.. 51,91 EUR
//	 * -------------                                    ------------- -------------
//	 * Gesamtwert aller Bestellungen:                                    267,56 EUR
//
//	 * |<----------------------------<printLineWidth>----------------------------->|
//	 * 
//	 * @param orders list of orders to print
//	 * 
//	 */
//	private void printOrders( List<Order> orders ) {
//		StringBuffer sbAllOrders = new StringBuffer( "-------------" );
//		StringBuffer sbLineItem = new StringBuffer();
//
//		
//		for (Order ord : orders) {
//			String customerOrders = "#" + ord.getId() + ", " + ord.getCustomer().getFirstName() + "'s Bestellung: ";
//			long priceOrder = 0;			
//			
//			for (OrderItem oI : ord.getItems()) {
//				priceOrder += oI.getArticle().getUnitPrice() * oI.getUnitsOrdered();
//				customerOrders += " " + oI.getUnitsOrdered() + "x " + oI.getDescription();
//			}
//			long priceTotal = 0;
//			priceTotal += priceOrder;		//Total Value
//			String Orderfmt = fmtPrice(priceOrder, " EUR", 14);
//
//			sbLineItem = fmtLine(customerOrders, Orderfmt, printLineWidth);
//			sbAllOrders.append("\n");
//			sbAllOrders.append(sbLineItem);
//		}
//
//		String fmtPriceTotal = pad(fmtPrice(26756, "", " EUR"), 14, true);
//		sbAllOrders.append("\n")
//			.append(fmtLine("----------", " ----------",printLineWidth))
//			.append("\n")
//			.append(fmtLine("Gesamtwert aller Bestellungen:",
//					fmtPriceTotal,printLineWidth));
//		System.out.println( sbAllOrders.toString() );
//	}
//
//
//	/*
//	 * Private methods.
//	 */
//
//	/**
//	 * Format long-price in 1/100 (cents) to String using DecimalFormatter.
//	 * Append currency. For example, 299, "EUR" -> "2,99 EUR"
//	 * 
//	 * @param price price as long in 1/100 (cents)
//	 * @param currency currency as String, e.g. "EUR"
//	 * @return price as String with currency and padded to minimum width
//	 */
//	private String fmtPrice( long price, String currency ) {
//		String fmtPrice = pad( fmtPrice( price, "", " " + currency ), 14, true );
//		return fmtPrice;
//	}
//
//
//	/**
//	 * Format long-price in 1/100 (cents) to String using DecimalFormatter, add
//	 * currency and pad to minimum width right-aligned.
//	 * For example, 299, "EUR", 12 -> "    2,99 EUR"
//	 * 
//	 * @param price price as long in 1/100 (cents)
//	 * @param currency currency as String, e.g. "EUR"
//	 * @param width minimum width to which result is padded
//	 * @return price as String with currency and padded to minimum width
//	 */
//	private String fmtPrice( long price, String currency, int width ) {
//		String fmtPrice = pad( fmtPrice( price, "", " " + currency ), 14, true );
//		return fmtPrice;
//	}
//
//
//	/**
//	 * Format long-price in 1/100 (cents) to String using DecimalFormatter and
//	 * prepend prefix and append postfix String.
//	 * For example, 299, "(", ")" -> "(2,99)"
//	 * 
//	 * @param price price as long in 1/100 (cents)
//	 * @param prefix String to prepend before price
//	 * @param postfix String to append after price
//	 * @return price as String
//	 */
//	private String fmtPrice( long price, String prefix, String postfix ) {
//		StringBuffer fmtPriceSB = new StringBuffer();
//		if( prefix != null ) {
//			fmtPriceSB.append( prefix );
//		}
//
//		fmtPriceSB = fmtPrice( fmtPriceSB, price );
//
//		if( postfix != null ) {
//			fmtPriceSB.append( postfix );
//		}
//		return fmtPriceSB.toString();
//	}
//
//
//	/**
//	 * Format long-price in 1/100 (cents) to String using DecimalFormatter.
//	 * For example, 299 -> "2,99"
//	 * 
//	 * @param sb StringBuffer to which price is added
//	 * @param price price as long in 1/100 (cents)
//	 * @return StringBuffer with added price
//	 */
//	private StringBuffer fmtPrice( StringBuffer sb, long price ) {
//		if( sb == null ) {
//			sb = new StringBuffer();
//		}
//		double dblPrice = ( (double)price ) / 100.0;			// convert cent to Euro
//		DecimalFormat df = new DecimalFormat( "#,##0.00",
//			new DecimalFormatSymbols( new Locale( "de" ) ) );	// rounds double to 2 digits
//
//		String fmtPrice = df.format( dblPrice );				// convert result to String in DecimalFormat
//		sb.append( fmtPrice );
//		return sb;
//	}
//
//
//	/**
//	 * Pad string to minimum width, either right-aligned or left-aligned
//	 * 
//	 * @param str String to pad
//	 * @param width minimum width to which result is padded
//	 * @param rightAligned flag to chose left- or right-alignment
//	 * @return padded String
//	 */
//	private String pad( String str, int width, boolean rightAligned ) {
//		String fmtter = ( rightAligned? "%" : "%-" ) + width + "s";
//		String padded = String.format( fmtter, str );
//		return padded;
//	}
//
//
//	/**
//	 * Format line to a left-aligned part followed by a right-aligned part padded to
//	 * a minimum width.
//	 * For example:
//	 * 
//	 * <left-aligned part>                          <>       <right-aligned part>
//	 * 
//	 * "#5234968294, Eric's Bestellung: 1x Kanne         20,00 EUR   (3,19 MwSt)"
//	 * 
//	 * |<-------------------------------<width>--------------------------------->|
//	 * 
//	 * @param leftStr left-aligned String
//	 * @param rightStr right-aligned String
//	 * @param totalWidth minimum width to which result is padded
//	 * @return String with left- and right-aligned parts padded to minimum width
//	 */
//	private StringBuffer fmtLine( String leftStr, String rightStr, int totalWidth ) {
//		StringBuffer sb = new StringBuffer( leftStr );
//		int shiftable = 0;		// leading spaces before first digit
//		for( int i=1; rightStr.charAt( i ) == ' ' && i < rightStr.length(); i++ ) {
//			shiftable++;
//		}
//		final int tab1 = totalWidth - rightStr.length() + 1;	// - ( seperator? 3 : 0 );
//		int sbLen = sb.length();
//		int excess = sbLen - tab1 + 1;
//		int shift2 = excess - Math.max( 0, excess - shiftable );
//		if( shift2 > 0 ) {
//			rightStr = rightStr.substring( shift2, rightStr.length() );
//			excess -= shift2;
//		}
//		if( excess > 0 ) {
//			switch( excess ) {
//			case 1:	sb.delete( sbLen - excess, sbLen ); break;
//			case 2: sb.delete( sbLen - excess - 2 , sbLen ); sb.append( ".." ); break;
//			default: sb.delete( sbLen - excess - 3, sbLen ); sb.append( "..." ); break;
//			}
//		}
//		String strLineItem = String.format( "%-" + ( tab1 - 1 ) + "s%s", sb.toString(), rightStr );
//		sb.setLength( 0 );
//		sb.append( strLineItem );
//		return sb;
//	}
//}
