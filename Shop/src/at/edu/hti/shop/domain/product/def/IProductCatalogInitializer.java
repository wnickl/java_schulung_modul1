
package at.edu.hti.shop.domain.product.def;

import java.util.Properties;

/**
 * initializer of product databases, can e.g. be done from xml file, database, web-service, etc. <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public interface IProductCatalogInitializer {

  /**
   * Initialize product catalog
   * 
   * @param prop the {@link Properties} to use
   * @return the {@link IProductDatabase} instance
   */
  IProductDatabase initializeProductCatalog(Properties prop);
}

//---------------------------- Revision History ----------------------------
//$Log$
//
