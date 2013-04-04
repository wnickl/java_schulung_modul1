
package at.edu.hti.shop.domain.shipping.def;

import java.util.List;

import at.edu.hti.shop.domain.order.def.IOrder;
import at.edu.hti.shop.domain.order.def.IOrderLine;

/**
 * Shippment created for some {@link IOrder} <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public interface IShippment {

  /**
   * Add {@link IDelivery} to shit shippment
   * 
   * @param delivery the {@link IDelivery}
   */
  void addDelivery(IDelivery delivery);

  /**
   * Add undeliverable line
   * 
   * @param ol the {@link IOrderLine}
   */
  void addUndeliverableLine(IOrderLine ol);

  /**
   * @return the total cost of this shippment
   */
  double calculateTotalCost();

  /**
   * @return the list of {@link IDelivery} of this shippment
   */
  List<IDelivery> getDeliveries();
}

//---------------------------- Revision History ----------------------------
//$Log$
//
