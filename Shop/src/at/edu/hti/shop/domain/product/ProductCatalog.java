
package at.edu.hti.shop.domain.product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.product.def.IProductCatalogInitializer;
import at.edu.hti.shop.domain.product.def.IProductCategory;
import at.edu.hti.shop.domain.product.def.IProductDatabase;
import at.edu.hti.shop.domain.product.impl.XmlProductCatalogInitializer;

/**
 * Global Product Catalog containing all product related information. <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public class ProductCatalog implements IProductDatabase {

  private static final String KEY_INITIALIZER = "initializer";
  private static final String URI_PROPERTIES = "/at/edu/hti/shop/domain/product/product.catalog.properties";
  private static final Logger logger = LogManager.getLogger(ProductCatalog.class.getName());

  /** The one and only instance of this class */
  private static volatile ProductCatalog instance;

  /**
   * @return single instance of ProductCatalog
   */
  public static ProductCatalog getInstance() {
    logger.trace("getInstance()");
    ProductCatalog pc = instance;
    if (pc == null) {
      synchronized (ProductCatalog.class) {
        pc = instance;
        if (pc == null) {
          instance = pc = new ProductCatalog();
        }
      }
    }
    return pc;
  }

  /** The master of desaster - delegate used for real functionality */
  private final IProductDatabase masterOfDesaster;

  /**
   * Instantiates a new product catalog; private on purpose.
   */
  private ProductCatalog() {
    logger.trace("ProductCatalog()");
    Properties properties = new Properties();
    try (InputStream stream = ProductCatalog.class.getResourceAsStream(URI_PROPERTIES)) {
      logger.debug("loading properties from " + URI_PROPERTIES);
      properties.load(stream);
      stream.close();
    } catch (FileNotFoundException e) {
      logger.error(e.getMessage(), e);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    String initializerClass = properties.getProperty(KEY_INITIALIZER);
    IProductCatalogInitializer initializer = null;
    if (initializerClass != null) {
      try {
        logger.debug("using initializer for product database: " + initializerClass);
        initializer = (IProductCatalogInitializer) Class.forName(initializerClass).newInstance();
      } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
        logger.error(e.getMessage(), e);
      }
    }

    if (initializer == null) {
      logger.info("using default initializer " + XmlProductCatalogInitializer.class.getName());
      initializer = new XmlProductCatalogInitializer();
    }
    logger.info("initializing product catalog with class: " + initializerClass);
    masterOfDesaster = initializer.initializeProductCatalog(properties);
  }

  /** {@inheritDoc} */
  @Override
  public IProductCategory findOrCreateCategory(String category) {
    IProductCategory pc = masterOfDesaster.findOrCreateCategory(category);
    logger.trace("based on category-name [" + category + "] returning: " + pc);
    return pc;
  }

  /** {@inheritDoc} */
  @Override
  public IProduct getProductById(long id) {
    return masterOfDesaster.getProductById(id);
  }

  /** {@inheritDoc} */
  @Override
  public IProduct getProductByName(String name) {
    return masterOfDesaster.getProductByName(name);
  }

  /** {@inheritDoc} */
  @Override
  public List<IProduct> getProductsByCategory(String category) {
    return masterOfDesaster.getProductsByCategory(category);
  }

  /** {@inheritDoc} */
  @Override
  public List<IProduct> getProductsForVendor(String vendor) {
    return masterOfDesaster.getProductsForVendor(vendor);
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
