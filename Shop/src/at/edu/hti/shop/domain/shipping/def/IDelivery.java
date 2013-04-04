
package at.edu.hti.shop.domain.shipping.def;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import at.edu.hti.shop.domain.order.def.IOrderLine;
import at.edu.hti.shop.domain.shipping.impl.ShippingMode;

/**
 * The Interface IDelivery.
 * 
 * @author nickl
 * @version $Revision$
 */
public interface IDelivery extends Comparable<IDelivery> {

  /** The max weights per shipping mode */
  Map<ShippingMode, Double> MAX_WEIGHTS = new EnumMap<ShippingMode, Double>(ShippingMode.class) {

    private static final long serialVersionUID = 1L;

    {
      put(ShippingMode.AVIATION, Double.valueOf(3000));
      put(ShippingMode.LAND, Double.valueOf(5000));
      put(ShippingMode.SHIP, Double.valueOf(3000000));
      put(ShippingMode.UNKNOWN, Double.valueOf(Double.MAX_VALUE));
    }
  };

  /** days needed to ship per default */
  int DEFAULT_SHIPPING_TIME = 1;

  /**
   * Add order line to this delivery
   * 
   * @param ol the {@link IOrderLine}
   */
  void addOrderLine(IOrderLine ol);

  /**
   * @param ol the {@link IOrderLine} to check
   * @return true, if {@link IOrderLine} can be added to this delivery
   */
  boolean canDeliver(IOrderLine ol);

  /**
   * @return the delivery time in days
   */
  int getDeliveryTime();

  /**
   * @return the list of {@link IOrderLine} of this delivery
   */
  List<IOrderLine> getOrderLines();

  /**
   * @return the {@link ShippingMode} of this delivery
   */
  ShippingMode getShippingMode();

  /**
   * @return the weight of this delivery
   */
  double getWeight();

  /**
   * Remove order line from this delivery
   * 
   * @param ol the {@link IOrderLine}
   */
  void removeOrderLine(IOrderLine ol);

}

//---------------------------- Revision History ----------------------------
//$Log$
//
