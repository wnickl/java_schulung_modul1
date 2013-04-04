
package at.edu.hti.shop.domain.order.impl;

import java.io.Serializable;

import at.edu.hti.shop.domain.order.def.IOrderLine;
import at.edu.hti.shop.domain.product.def.IProduct;

/**
 * default implementation of {@link IOrderLine}
 * 
 * @author nickl
 * @version $Revision$
 */
public class OrderLine implements Comparable<OrderLine>, Serializable, IOrderLine {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 4680676708373647515L;

  /** The {@link IProduct} */
  private final IProduct product;

  /** The amount requested. */
  private int amount;

  /**
   * constructor
   * 
   * @param product the {@link IProduct}
   * @param amount the amount requested
   */
  public OrderLine(IProduct product, int amount) {
    if (product == null) {
      throw new NullPointerException("'product' must not be null");
    } else if (amount < 0) {
      throw new IllegalArgumentException("'amount' must not be greater or equals zero");
    }
    this.product = product;
    this.amount = amount;
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(OrderLine o) {
    return product.compareTo(o.product);
  }

  /** {@inheritDoc} */
  @Override
  public int getAmount() {
    return amount;
  }

  /** {@inheritDoc} */
  @Override
  public IProduct getProduct() {
    return product;
  }

  /** {@inheritDoc} */
  @Override
  public double getWeight() {
    return product.getWeight() * amount;
  }

  /** {@inheritDoc} */
  @Override
  public void setAmount(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("'amount' must not be greater or equals zero");
    }
    this.amount = amount;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "OrderLine [" + product + ", " + amount + "]";
  }

}
