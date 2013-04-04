
package at.edu.hti.shop.domain.shipping.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.edu.hti.shop.domain.order.def.IOrderLine;
import at.edu.hti.shop.domain.shipping.def.IDelivery;

/**
 * The Class Delivery as default implementation for {@link IDelivery}
 * 
 * @author nickl
 * @version $Revision$
 */

public class Delivery implements IDelivery {

  /** The Constant logger. */
  private static final Logger logger = LogManager.getLogger(Delivery.class.getName());

  /** The order lines. */
  private final List<IOrderLine> orderLines = new ArrayList<>();

  /** The shipping mode. */
  private final ShippingMode shippingMode;

  public Delivery(ShippingMode shippingMode) {
    this.shippingMode = shippingMode;
  }

  /** {@inheritDoc} */
  @Override
  public void addOrderLine(IOrderLine ol) {
    logger.debug("ol=" + ol);
    orderLines.add(ol);
  }

  /** {@inheritDoc} */
  @Override
  public boolean canDeliver(IOrderLine ol) {
    ShippingMode olSM = ol.getProduct().getCategory().getShippingMode();
    if (olSM != getShippingMode()) {
      logger.info("can not add orderline " + ol + " because of different shipping mode [ol:" + olSM + ";this:" + getShippingMode() + "]");
      return false;
    } else if (ol.getWeight() + getWeight() > MAX_WEIGHTS.get(olSM).doubleValue()) {
      logger.info("can not add orderline " + ol + " because max weight [" + MAX_WEIGHTS.get(olSM).doubleValue() + "] would be exceeded!");
      return false;
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(IDelivery o) {
    int c = getShippingMode().compareTo(o.getShippingMode());
    if (c == 0) {
      c = Integer.valueOf(getDeliveryTime()).compareTo(Integer.valueOf(o.getDeliveryTime()));
    }
    if (c == 0) {
      c = Integer.valueOf(getOrderLines().size()).compareTo(Integer.valueOf(o.getOrderLines().size()));
    }
    return c;
  }

  /** {@inheritDoc} */
  @Override
  public int getDeliveryTime() {
    int days = 0;
    for (IOrderLine ol : orderLines) {
      int supplyPeriod = ol.getProduct().getSupplyPeriod();
      if (supplyPeriod > days) {
        days = supplyPeriod;
      }
    }
    //we need DEFAULT_SHIPPING_TIME at least for shippment + max(supply period) of product
    return days + DEFAULT_SHIPPING_TIME;
  }

  /** {@inheritDoc} */
  @Override
  public List<IOrderLine> getOrderLines() {
    return Collections.unmodifiableList(orderLines);
  }

  /** {@inheritDoc} */
  @Override
  public ShippingMode getShippingMode() {
    return shippingMode;
  }

  /** {@inheritDoc} */
  @Override
  public double getWeight() {
    long weight = 0;
    for (IOrderLine ol : orderLines) {
      weight += ol.getWeight();
    }
    return weight;
  }

  /** {@inheritDoc} */
  @Override
  public void removeOrderLine(IOrderLine ol) {
    logger.debug("ol=" + ol);
    orderLines.remove(ol);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    int lineWidth = 100;
    sb.append(StringUtils.rightPad("", lineWidth, '*'));
    sb.append("\n");
    sb.append("delivery shipping-mode=" + getShippingMode() + " delivery-time=" + getDeliveryTime());
    sb.append("\n");
    for (IOrderLine ol : getOrderLines()) {
      sb.append("\t");
      sb.append(StringUtils.rightPad("product: name=" + ol.getProduct().getName(), 25));
      sb.append(StringUtils.rightPad(" product-prize=" + ol.getProduct().getPrize(), 25));
      sb.append(StringUtils.rightPad(" requested-amount=" + ol.getAmount(), 25));
      sb.append(StringUtils.rightPad(" weight=" + ol.getWeight(), 25));
      sb.append("\n");
    }
    sb.append("\n");
    sb.append(StringUtils.rightPad("", lineWidth, '*'));
    return sb.toString();
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
