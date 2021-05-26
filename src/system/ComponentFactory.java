package system;

import datamodel.RawDataFactory;

public final class ComponentFactory {
	
	private static ComponentFactory instance = null;
	  /**
	   * Private constructor.
	   */
	
	static InventoryManager inventoryManager;
	static OrderProcessor orderProcessor;
	static OutputProcessor outputProcessor;
	static DataFactory dataFactory;
	
	  private ComponentFactory() { // add dependencies
	    this.inventoryManager = new InventoryManager();
	    this.orderProcessor = new OrderProcessor( inventoryManager );
	    this.outputProcessor = new OutputProcessor(inventoryManager, orderProcessor);
	   
	    // Object objectRawFactory = null;
//	    this.dataFactory = new DataFactory( objectRawFactory, inventoryManager, outputProcessor );
	    RawDataFactory.RawDataFactoryIntf objectRawFactory = RawDataFactory.getInstance( this );
	    this.dataFactory = new DataFactory( objectRawFactory, inventoryManager, outputProcessor );
	  }
	  /**
	   * Public access method to Factory singleton instance that is created
	   * when getInstance() is first invoked. Subsequent invocations return
	   * the reference to the same instance.
	   *
	   * @return Factory singleton instance
	   */
	  public static ComponentFactory getInstance() {
	    if( instance == null ) {
	instance = new ComponentFactory();
	    }
	    return instance;
	  }
	  
	  public static Components.InventoryManager getInventoryManager() {
		return inventoryManager;
		    
		  }
	  
	  public static Components.OrderProcessor getOrderProcessor() {
			return orderProcessor;
			    
			  }
	  public static Components.OutputProcessor getOutputProcessor() {
			return outputProcessor;
			    
			  }
	  public static Components.DataFactory getDataFactory () {
			return dataFactory ;
			    
			  }

}
