
package at.edu.hti.shop.domain.product.def;

import at.edu.hti.shop.domain.shipping.impl.ShippingMode;

/**
 * interface telling category of some product <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public interface IProductCategory extends Comparable<IProductCategory> {

  /**
   * @return the name of the category
   */
  String getName();

  /**
   * @return the shipping mode to be used for this category
   */
  ShippingMode getShippingMode();

  /**
   * @return true, if products of this category are shippable
   */
  boolean isShippable();
}

//---------------------------- Revision History ----------------------------
//$Log$
//
