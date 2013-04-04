
package at.edu.hti.shop.domain.shipping.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.edu.hti.shop.domain.calculation.def.PrizeCalculationFactory;
import at.edu.hti.shop.domain.order.def.IOrder;
import at.edu.hti.shop.domain.order.def.IOrderLine;
import at.edu.hti.shop.domain.shipping.def.IDelivery;
import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * default implementation of {@link IShippment} <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public class Shippment implements IShippment {

  /** logger instance */
  private static final Logger logger = LogManager.getLogger(Shippment.class.getName());

  private final List<IDelivery> deliveries = new ArrayList<>();
  private final IOrder order;
  private final List<IOrderLine> undeliverableLines = new ArrayList<>();

  /**
   * default constructor
   * 
   * @param order the {@link IOrder}
   */
  public Shippment(IOrder order) {
    this.order = order;
  }

  /** {@inheritDoc} */
  @Override
  public void addDelivery(IDelivery delivery) {
    logger.debug("adding delivery " + delivery);
    deliveries.add(delivery);
  }

  @Override
  public void addUndeliverableLine(IOrderLine ol) {
    logger.debug("adding undeliverable line " + ol);
    undeliverableLines.add(ol);
  }

  /** {@inheritDoc} */
  @Override
  public double calculateTotalCost() {
    return PrizeCalculationFactory.calculatePrize(this);
  }

  /** {@inheritDoc} */
  @Override
  public List<IDelivery> getDeliveries() {
    return Collections.unmodifiableList(deliveries);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    int lineWidth = 100;
    sb.append(StringUtils.rightPad("", lineWidth, '*'));
    sb.append("\n");
    sb.append("shippment: order-id=" + order.getId());
    sb.append(" number-of-lines=" + order.getProductCount());
    sb.append(" number-of-deliveries=" + deliveries.size());
    sb.append("\n");
    sb.append(StringUtils.rightPad("", lineWidth, '-'));
    sb.append("\n");
    for (IDelivery delivery : deliveries) {
      sb.append("delivery shipping-mode=" + delivery.getShippingMode() + " delivery-time=" + delivery.getDeliveryTime());
      sb.append("\n");
      for (IOrderLine ol : delivery.getOrderLines()) {
        sb.append("\t");
        sb.append(StringUtils.rightPad("product: name=" + ol.getProduct().getName(), 25));
        sb.append(StringUtils.rightPad(" product-prize=" + ol.getProduct().getPrize(), 25));
        sb.append(StringUtils.rightPad(" requested-amount=" + ol.getAmount(), 25));
        sb.append(StringUtils.rightPad(" weight=" + ol.getWeight(), 25));
        sb.append("\n");
      }
    }
    if (undeliverableLines.size() > 0) {
      sb.append(StringUtils.rightPad("", lineWidth, '-'));
      sb.append("\n");
      sb.append("not deliverable lines\n");
      for (IOrderLine ol : undeliverableLines) {
        sb.append("\t");
        sb.append(StringUtils.rightPad("product: name=" + ol.getProduct().getName(), 25));
        sb.append(StringUtils.rightPad(" product-prize=" + ol.getProduct().getPrize(), 25));
        sb.append(StringUtils.rightPad(" requested-amount=" + ol.getAmount(), 25));
        sb.append(StringUtils.rightPad(" weight=" + ol.getWeight(), 25));
        sb.append("\n");
      }
    }
    sb.append(StringUtils.rightPad("", lineWidth, '*'));
    sb.append("\n");
    return sb.toString();
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
