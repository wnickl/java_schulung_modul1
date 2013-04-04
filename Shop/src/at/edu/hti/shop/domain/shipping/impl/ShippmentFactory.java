
package at.edu.hti.shop.domain.shipping.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.edu.hti.shop.domain.order.def.IOrder;
import at.edu.hti.shop.domain.product.ProductCatalog;
import at.edu.hti.shop.domain.shipping.def.IShippment;
import at.edu.hti.shop.domain.shipping.def.IShippmentCreator;

/**
 * factory providing configured {@link IShippmentCreator} instance to create {@link IShippment} for
 * {@link IOrder} <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public class ShippmentFactory {

  private static final Logger logger = LogManager.getLogger(ShippmentFactory.class.getName());
  private static final String URI_PROPERTIES = "/at/edu/hti/shop/domain/product/shippment.factory.properties";
  private static final String KEY_CREATOR_CLASS = "creator";
  private final static IShippmentCreator shippmentCreator;

  static {
    logger.trace("static:ShippmentFactory");
    Properties properties = new Properties();
    try (InputStream stream = ProductCatalog.class.getResourceAsStream(URI_PROPERTIES)) {
      logger.debug("loading properties from " + URI_PROPERTIES);
      properties.load(stream);
      stream.close();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    String creatorClass = properties.getProperty(KEY_CREATOR_CLASS);

    IShippmentCreator sc = null;
    if (creatorClass != null) {
      try {
        logger.debug("using creator for shippment factory: " + creatorClass);
        sc = (IShippmentCreator) Class.forName(creatorClass).newInstance();
      } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
        logger.error(e.getMessage(), e);
      }
    }

    if (sc == null) {
      logger.info("using default creator " + DefaultShippmentCreator.class.getName());
      sc = new DefaultShippmentCreator();
    }
    shippmentCreator = sc;
    logger.info("using shippment creator: " + creatorClass);
  }

  /**
   * Creates a new {@link IShippment} instance
   * 
   * @param order the {@link IOrder}
   * @return the {@link IShippment} created
   */
  public static IShippment createShippment(IOrder order) {
    return shippmentCreator.createShippment(order);
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
