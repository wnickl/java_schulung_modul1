
package at.edu.hti.shop.domain.product.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.product.def.IProductCategory;
import at.edu.hti.shop.domain.product.def.IProductDatabase;
import at.edu.hti.shop.domain.shipping.impl.ShippingMode;

/**
 * implementation of {@link IProductDatabase} <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public final class ProductDatabaseImpl implements IProductDatabase {

  /** logger instance */
  private static final Logger logger = LogManager.getLogger(ProductDatabaseImpl.class.getName());

  private final Map<String, IProduct> nameProductMap;
  private final Map<Long, IProduct> idProductMap;
  private final Map<IProductCategory, List<IProduct>> categoryProductMap;
  private final Map<String, List<IProduct>> vendorProductMap;
  private final Map<String, IProductCategory> categoryMap;

  ProductDatabaseImpl(List<IProduct> products, List<IProductCategory> categoryList) {
    //defense copy
    categoryMap = new HashMap<>(categoryList.size() * 1, 5);
    for (IProductCategory cat : categoryList) {
      categoryMap.put(cat.getName(), cat);
    }
    nameProductMap = new HashMap<>(products.size() * 1, 5);
    idProductMap = new HashMap<>(products.size() * 1, 5);
    categoryProductMap = new HashMap<>(products.size() * 1, 5);
    vendorProductMap = new HashMap<>(products.size() * 1, 5);

    for (IProduct p : products) {
      if (logger.isDebugEnabled()) {
        logger.debug("processing product " + p);
      }
      nameProductMap.put(p.getName(), p);
      idProductMap.put(Long.valueOf(p.getId()), p);
      List<IProduct> catplist = null;

      IProductCategory cat = findOrCreateCategory(p.getCategoryName());
      if (!categoryProductMap.containsKey(cat)) {
        catplist = new LinkedList<IProduct>();
        categoryProductMap.put(cat, catplist);
      } else {
        catplist = categoryProductMap.get(cat);
      }
      catplist.add(p);

      List<IProduct> venplist = null;
      if (!vendorProductMap.containsKey(p.getVendor())) {
        venplist = new LinkedList<IProduct>();
        vendorProductMap.put(p.getVendor(), venplist);
      } else {
        venplist = vendorProductMap.get(p.getVendor());
      }
      venplist.add(p);
    }

  }

  /** {@inheritDoc} */
  @Override
  public IProductCategory findOrCreateCategory(String category) {
    IProductCategory cat = categoryMap.get(category);
    if (cat == null) {
      logger.warn("unknown product category " + category + ", assuming it is shipable!");
      cat = new ProductCategory(category, true, ShippingMode.UNKNOWN);
      categoryMap.put(category, cat);
    }
    return cat;
  }

  /** {@inheritDoc} */
  @Override
  public IProduct getProductById(long id) {
    IProduct product = idProductMap.get(Long.valueOf(id));
    if (product == null) {
      logger.debug("no product for id " + id + " found");
    }
    return product;
  }

  /** {@inheritDoc} */
  @Override
  public IProduct getProductByName(String name) {
    IProduct product = nameProductMap.get(name);
    if (product == null) {
      logger.debug("no product for name " + name + " found");
    }
    return product;
  }

  /** {@inheritDoc} */
  @Override
  public List<IProduct> getProductsByCategory(String categoryName) {
    IProductCategory category = findOrCreateCategory(categoryName);
    List<IProduct> list = categoryProductMap.get(category);
    if (list == null || list.size() == 0) {
      logger.debug("no products for category " + categoryName + " found");
    }
    return list;
  }

  /** {@inheritDoc} */
  @Override
  public List<IProduct> getProductsForVendor(String vendor) {
    List<IProduct> list = vendorProductMap.get(vendor);
    if (list == null || list.size() == 0) {
      logger.debug("no products for vendor " + vendor + " found");
    }
    return list;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
