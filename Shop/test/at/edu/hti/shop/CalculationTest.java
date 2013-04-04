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

import at.edu.hti.shop.domain.calculation.def.IPrizeCalculation;
import at.edu.hti.shop.domain.calculation.impl.ShippingCostsPrizeCalculation;
import at.edu.hti.shop.domain.calculation.impl.ShippingIncludedPrizeCalculation;
import at.edu.hti.shop.domain.order.def.IOrder;
import at.edu.hti.shop.domain.order.impl.Order;
import at.edu.hti.shop.domain.product.ProductCatalog;
import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * test prize calculations <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public class CalculationTest {

  @Test(expected = NullPointerException.class)
  public void test_ShippingCostsPrizeCalculation_NPE() {
    IPrizeCalculation scpc = new ShippingCostsPrizeCalculation();
    scpc.shouldCalculate(null);
  }

  @Test
  public void test_ShippingCostsPrizeCalculation() {
    IOrder order = new Order();
    IProduct product = ProductCatalog.getInstance().getProductByName("aepfel");
    order.changeAmount(product, 10);
    IShippment shippment = order.generateShippment();
    IPrizeCalculation scpc = new ShippingCostsPrizeCalculation();
    boolean shouldCalculate = scpc.shouldCalculate(shippment);
    Assert.assertFalse(shouldCalculate);
    double prize = scpc.calcPrize(shippment);
    Assert.assertEquals(Double.valueOf(prize), Double.valueOf(250));
  }

  @Test(expected = NullPointerException.class)
  public void test_ShippingIncludedPrizeCalculation_NPE() {
    IPrizeCalculation scpc = new ShippingIncludedPrizeCalculation();
    scpc.shouldCalculate(null);
  }

  @Test
  public void test_ShippingIncludedPrizeCalculation() {
    IOrder order = new Order();
    IProduct product = ProductCatalog.getInstance().getProductByName("aepfel");
    order.changeAmount(product, 10);
    IShippment shippment = order.generateShippment();
    IPrizeCalculation scpc = new ShippingIncludedPrizeCalculation();
    boolean shouldCalculate = scpc.shouldCalculate(shippment);
    Assert.assertTrue(shouldCalculate);
    double prize = scpc.calcPrize(shippment);
    Assert.assertEquals(Double.valueOf(prize), Double.valueOf(240));
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
