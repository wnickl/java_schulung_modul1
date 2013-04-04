
package at.edu.hti.shop.domain.shipping.def;

import at.edu.hti.shop.domain.order.def.IOrder;

/**
 * interface to create {@link Shippment} for {@link IOrder} in different ways. <br />
 * 
 * @author nickl
 * @version $Revision$
 */
public interface IShippmentCreator {

  /**
   * Create {@link IShippment} for some {@link IOrder}
   * 
   * @param order the {@link IOrder}
   * @return the created {@link IShippment}
   */
  IShippment createShippment(IOrder order);
}

//---------------------------- Revision History ----------------------------
//$Log$
//
