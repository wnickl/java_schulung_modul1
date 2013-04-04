
package at.edu.hti.shop.domain.order.def;

import java.util.Map;

import at.edu.hti.shop.domain.order.impl.OrderLine;
import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * order object as container for requested products an base for shippment calculation <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public interface IOrder extends Comparable<IOrder> {

  /**
   * Calculate prize of line by multiplying request amount with price of {@link IProduct}
   * 
   * @return the calculated prize
   */
  double calcPrize();

  /**
   * Change amount.
   * 
   * @param prod the {@link IProduct}
   * @param amount the amount
   * @return true, if successfully changed
   */
  void changeAmount(IProduct prod, int amount);

  /**
   * Generate shippment for this order
   * 
   * @return the generated {@link IShippment}
   */
  IShippment generateShippment();

  long getId();

  /**
   * @return the line count of this order
   */
  int getProductCount();

  /**
   * Gets the product lines.
   * 
   * @return the product lines
   */
  Map<IProduct, OrderLine> getProductLines();
}

//---------------------------- Revision History ----------------------------
//$Log$
//
