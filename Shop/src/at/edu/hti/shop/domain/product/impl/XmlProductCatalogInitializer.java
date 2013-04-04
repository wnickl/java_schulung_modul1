
package at.edu.hti.shop.domain.product.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import at.edu.hti.shop.domain.product.ProductCatalog;
import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.product.def.IProductCatalogInitializer;
import at.edu.hti.shop.domain.product.def.IProductCategory;
import at.edu.hti.shop.domain.product.def.IProductDatabase;
import at.edu.hti.shop.domain.shipping.impl.ShippingMode;

/**
 * {@link IProductCatalogInitializer} implementation that retrieves product information from xml
 * file. <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public class XmlProductCatalogInitializer implements IProductCatalogInitializer {

  /** logger instance */
  private static final Logger logger = LogManager.getLogger(XmlProductCatalogInitializer.class.getName());

  /** {@inheritDoc} */
  @Override
  public IProductDatabase initializeProductCatalog(Properties prop) {
    if (prop == null) {
      throw new NullPointerException("'properties' argument must not be null");
    }
    logger.debug("initializeProductCatalog()");

    String uri = prop.getProperty(Configuration.KEY_DATA_SOURCE_URI);
    logger.info("URI for product data: " + uri);
    SAXReader reader = new SAXReader();
    Document document = null;

    List<IProduct> productList = new LinkedList<>();
    List<IProductCategory> categoryList = new LinkedList<>();

    try (InputStream is = ProductCatalog.class.getResourceAsStream(uri)) {
      document = reader.read(is);
      Element products = document.getRootElement();

      Element productNode = products.elements(Configuration.ELEMENT_NAME_PRODUCTS).get(0);
      List<Element> productElements = productNode.elements(Configuration.ELEMENT_NAME_PRODUCT);
      logger.info("loading " + productElements.size() + " products from data-source");
      for (Element e : productElements) {
        productList.add(parseProduct(e));
      }
      Element categoryNode = products.elements(Configuration.ELEMENT_NAME_CATEGORIES).get(0);
      for (Element e : categoryNode.elements(Configuration.ELEMENT_NAME_CATEGORY)) {
        categoryList.add(parseCategory(e));
      }
    } catch (DocumentException e) {
      logger.error(e.getMessage(), e);
    } catch (IOException e1) {
      logger.error(e1.getMessage(), e1);
    }
    return new ProductDatabaseImpl(productList, categoryList);
  }

  /**
   * create {@link IProductCategory} instance from element
   * 
   * @param e the {@link Element}
   * @return the created {@link IProductCategory} instance
   */
  private IProductCategory parseCategory(Element e) {
    String name = e.attribute(Configuration.ATTR_NAME).getValue();
    String strIsShippable = e.attribute(Configuration.ATTR_SHIPPABLE).getValue();
    boolean isShippable = strIsShippable == null ? false : strIsShippable.trim().toLowerCase().equals("true") ? true : false;
    String strShippingMode = e.attribute(Configuration.ATTR_SHIPPING_MODE).getValue();
    ShippingMode shippingMode = ShippingMode.getEnum(strShippingMode);

    logger.info("parsed element " + e.getName() + "> name=" + name + " isShippable=" + isShippable + " shippingMode=" + shippingMode);
    return new ProductCategory(name, isShippable, shippingMode);
  }

  /**
   * create {@link IProduct} instance from element
   * 
   * @param e the {@link Element}
   * @return the created {@link IProduct} instance
   */
  private IProduct parseProduct(Element e) {
    long id = Long.valueOf(e.attribute(Configuration.ATTR_ID).getValue()).longValue();
    String category = e.attribute(Configuration.ATTR_CATEGORY).getValue();
    String name = e.attribute(Configuration.ATTR_NAME).getValue();
    long prize = Long.valueOf(e.attribute(Configuration.ATTR_PRIZE).getValue()).longValue();
    int supplyPeriod = Integer.valueOf(e.attribute(Configuration.ATTR_SUPPLY_PERIOD).getValue()).intValue();
    String vendor = e.attribute(Configuration.ATTR_VENDOR).getValue();
    long weight = Long.valueOf(e.attribute(Configuration.ATTR_WEIGHT).getValue()).longValue();

    logger.info("parsed element " + e.getName() + "> id=" + id + " name=" + name + " category=" + category + " prize=" + prize + " supply-period="
      + supplyPeriod + " vendor=" + vendor + " weight=" + weight);
    return new Product(id, name, prize, weight, category, supplyPeriod, vendor);
  }

  /**
   * internal class holding configuration related constantss
   */
  private static final class Configuration {
    private static final String ATTR_ID = "id";
    private static final String ATTR_NAME = "name";
    private static final String ATTR_CATEGORY = "category";
    private static final String ATTR_PRIZE = "prize";
    private static final String ATTR_WEIGHT = "weight";
    private static final String ATTR_VENDOR = "vendor";
    private static final String ATTR_SUPPLY_PERIOD = "supplyPeriodInDays";
    private static final String ELEMENT_NAME_PRODUCTS = "products";
    private static final String ELEMENT_NAME_PRODUCT = "product";
    private static final String ELEMENT_NAME_CATEGORIES = "categories";
    private static final String ELEMENT_NAME_CATEGORY = "category";
    private static final String KEY_DATA_SOURCE_URI = "data-source-uri";
    private static final String ATTR_SHIPPABLE = "isShippable";
    private static final String ATTR_SHIPPING_MODE = "shippingMode";
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
