/** 
 * Copyright 2013 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id$
 * <br /> <br />
 *
 */

package at.edu.hti.shop;

import junit.framework.Assert;

import org.junit.Test;

import at.edu.hti.shop.domain.product.ProductCatalog;
import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.product.def.IProductCategory;
import at.edu.hti.shop.domain.shipping.impl.ShippingMode;

/**
 * basic workflow test
 * 
 * @author nickl
 * @version $Revision$
 */

public class ProductCatalogTest {

  @Test
  public void testGetInstance() {
    ProductCatalog instance = ProductCatalog.getInstance();
    Assert.assertNotNull(instance);
  }

  @Test
  public void testGetProduct() {
    IProduct pfirsich = ProductCatalog.getInstance().getProductByName("pfirsich");
    Assert.assertNotNull(pfirsich);
  }

  @Test
  public void testfindOrCreateCategory() {
    IProductCategory pc = ProductCatalog.getInstance().findOrCreateCategory("DUMMY");
    Assert.assertNotNull(pc);
    Assert.assertEquals(ShippingMode.UNKNOWN, pc.getShippingMode());
    Assert.assertTrue(pc.isShippable());
  }

  @Test
  public void testGetProductByName() {
    IProduct aepfel = ProductCatalog.getInstance().getProductByName("aepfel");
    Assert.assertNotNull(aepfel);
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
