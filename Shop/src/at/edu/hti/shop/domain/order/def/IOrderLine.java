
package at.edu.hti.shop.domain.order.def;

import at.edu.hti.shop.domain.product.def.IProduct;

/**
 * order line as part of some {@link IOrder} to request a {@link IProduct} in some quantity <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public interface IOrderLine {

  /**
   * @return the amount requested
   */
  int getAmount();

  /**
   * @return the {@link IProduct}
   */
  IProduct getProduct();

  /**
   * @return the weight of products requested
   */
  double getWeight();

  /**
   * Sets the amount of requested items
   * 
   * @param amount the new amount
   */
  void setAmount(int amount);

}

//---------------------------- Revision History ----------------------------
//$Log$
//
