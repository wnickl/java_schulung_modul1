
package at.edu.hti.shop;

import junit.framework.Assert;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import at.edu.hti.shop.domain.order.def.IOrder;
import at.edu.hti.shop.domain.order.impl.Order;
import at.edu.hti.shop.domain.product.ProductCatalog;
import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.shipping.def.IShippment;

public class WorkflowTest {

  /** logger instance */
  private static final Logger logger = LogManager.getLogger(WorkflowTest.class.getName());

  public static final class TopSeller {
    public static final IProduct pfirsich = ProductCatalog.getInstance().getProductByName("pfirsich");
    public static final IProduct kiwi = ProductCatalog.getInstance().getProductByName("kiwi");
    public static final IProduct aepfel = ProductCatalog.getInstance().getProductByName("aepfel");
    public static final IProduct birnen = ProductCatalog.getInstance().getProductByName("birnen");
    public static final IProduct angeberAuto = ProductCatalog.getInstance().getProductByName("mercedes-GLK");
  }

  @Test
  public void testWorkflow() {

    IOrder shopOrder = new Order();
    Assert.assertNotNull(shopOrder);

    shopOrder.changeAmount(TopSeller.aepfel, 4);
    shopOrder.changeAmount(TopSeller.birnen, 2);

    logger.info("number of products in order: " + shopOrder.getProductCount());
    logger.info("order: " + shopOrder);
    Assert.assertEquals(2, shopOrder.getProductCount());

    shopOrder.changeAmount(TopSeller.aepfel, 8);

    logger.info("number of products in order: " + shopOrder.getProductCount());
    logger.info("order: " + shopOrder);
    Assert.assertEquals(2, shopOrder.getProductCount());

    shopOrder.changeAmount(TopSeller.birnen, 0);

    logger.info("number of products in order: " + shopOrder.getProductCount());
    logger.info("order: " + shopOrder);
    Assert.assertEquals(1, shopOrder.getProductCount());

    shopOrder.changeAmount(TopSeller.pfirsich, 5);
    shopOrder.changeAmount(TopSeller.kiwi, 6);

    logger.info("number of products in order: " + shopOrder.getProductCount());
    logger.info("order: " + shopOrder);
    Assert.assertEquals(3, shopOrder.getProductCount());

    IShippment shippment = shopOrder.generateShippment();
    logger.info("shippment: " + shippment);
    Assert.assertEquals(1, shippment.getDeliveries().size());

    logger.info("total cost of order: " + shippment.calculateTotalCost());

    shopOrder.changeAmount(TopSeller.angeberAuto, 5);
    Assert.assertEquals(4, shopOrder.getProductCount());

    shippment = shopOrder.generateShippment();
    logger.info("shippment: " + shippment);
    Assert.assertEquals(2, shippment.getDeliveries().size());

    logger.info("total cost of order: " + shippment.calculateTotalCost());
    Assert.assertEquals(Double.valueOf(614947.0), Double.valueOf(shippment.calculateTotalCost()));
  }
}
