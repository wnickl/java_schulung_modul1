
package at.edu.hti.shop.domain.order.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.edu.hti.shop.domain.order.def.IOrder;
import at.edu.hti.shop.domain.order.def.IOrderLine;
import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.shipping.def.IShippment;
import at.edu.hti.shop.domain.shipping.impl.ShippmentFactory;

/**
 * The Class Order as container for {@link OrderLines}
 * 
 * @author nickl
 * @version $Revision$
 */
public class Order implements IOrder, Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** unique sequence of ids for orders */
  private static final AtomicLong orderIdSequence = new AtomicLong(0);

  /** logger instance */
  private static final Logger logger = LogManager.getLogger(Order.class.getName());

  /** The product lines of this order */
  private final Map<IProduct, OrderLine> productLines = new HashMap<>();

  /** The id of this order */
  private final long id;

  /**
   * constructor
   */
  public Order() {
    this.id = orderIdSequence.incrementAndGet();
  }

  /** {@inheritDoc} */
  @Override
  public double calcPrize() {
    return generateShippment().calculateTotalCost();
  }

  /** {@inheritDoc} */
  @Override
  public void changeAmount(IProduct prod, int amount) {
    logger.debug("prod=" + prod + " amount=" + amount);
    if (prod == null) {
      throw new NullPointerException("'product' must not be null");
    }
    OrderLine ol = productLines.get(prod);
    if (ol == null) {
      if (amount > 0) {
        ol = new OrderLine(prod, amount);
        productLines.put(prod, ol);
      } else {
        return;
      }
    } else {
      ol.setAmount(amount);
    }
    if (amount == 0) {
      productLines.remove(prod);
    }
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(IOrder o) {
    return Long.valueOf(getId()).compareTo(Long.valueOf(o.getId()));
  }

  /** {@inheritDoc} */
  @Override
  public IShippment generateShippment() {
    return ShippmentFactory.createShippment(this);
  }

  /** {@inheritDoc} */
  @Override
  public long getId() {
    return id;
  }

  /** {@inheritDoc} */
  @Override
  public int getProductCount() {
    return productLines.size();
  }

  /** {@inheritDoc} */
  @Override
  public Map<IProduct, OrderLine> getProductLines() {
    return productLines;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    int lineWidth = 100;
    sb.append(StringUtils.rightPad("", lineWidth, '*'));
    sb.append("\n");
    sb.append("order: id=" + getId());
    sb.append(" number-of-lines=" + getProductCount());
    sb.append("\n");
    sb.append(StringUtils.rightPad("", lineWidth, '-'));
    sb.append("\n");
    for (Entry<IProduct, OrderLine> e : getProductLines().entrySet()) {
      IProduct p = e.getKey();
      IOrderLine ol = e.getValue();
      sb.append("\t");
      sb.append(StringUtils.rightPad("product: name=" + p.getName(), 25));
      sb.append(StringUtils.rightPad(" product-prize=" + p.getPrize(), 25));
      sb.append(StringUtils.rightPad(" requested-amount=" + ol.getAmount(), 25));
      sb.append(StringUtils.rightPad(" weight=" + ol.getWeight(), 25));
      sb.append("\n");
    }
    sb.append(StringUtils.rightPad("", lineWidth, '*'));
    sb.append("\n");
    return sb.toString();
  }

}
