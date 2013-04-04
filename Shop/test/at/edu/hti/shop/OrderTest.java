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

import at.edu.hti.shop.domain.order.def.IOrder;
import at.edu.hti.shop.domain.order.def.IOrderLine;
import at.edu.hti.shop.domain.order.impl.Order;
import at.edu.hti.shop.domain.order.impl.OrderLine;
import at.edu.hti.shop.domain.product.ProductCatalog;
import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * test orders <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public class OrderTest {

  @Test
  public void testOrderLine() {
    IProduct product = ProductCatalog.getInstance().getProductByName("kiwi");
    Assert.assertNotNull(product);
    int amount = 5;
    IOrderLine ol = new OrderLine(product, amount);
    Assert.assertTrue(ol.getAmount() == 5);
    Assert.assertEquals(product, ol.getProduct());
    Assert.assertTrue("ol.weight=" + ol.getWeight(), ol.getWeight() == 1410.0);
  }

  @Test
  public void testOrder() {
    IProduct kiwi = ProductCatalog.getInstance().getProductByName("kiwi");
    Assert.assertNotNull(kiwi);
    IProduct apfel = ProductCatalog.getInstance().getProductByName("aepfel");
    Assert.assertNotNull(apfel);

    IOrder order = new Order();
    order.changeAmount(apfel, 10);
    order.changeAmount(kiwi, 20);

    Assert.assertTrue(order.getProductCount() == 2);
    Assert.assertTrue(order.getId() > 0);
    Assert.assertTrue(order.getProductLines().size() == 2);

    IShippment shippment = order.generateShippment();
    Assert.assertNotNull(shippment);

    double prize = order.calcPrize();
    Assert.assertTrue("prize=" + prize, prize == 240.0);
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
