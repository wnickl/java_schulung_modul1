
package at.edu.hti.shop.domain.calculation.impl;

import at.edu.hti.shop.domain.calculation.def.IPrizeCalculation;
import at.edu.hti.shop.domain.order.def.IOrderLine;
import at.edu.hti.shop.domain.shipping.def.IDelivery;
import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * abstract implementation of {@link IPrizeCalculation}
 * 
 * @author nickl
 * @version $Revision$
 */

public abstract class AbstractPrizeCalculation implements IPrizeCalculation {

  /** {@inheritDoc} */
  @Override
  public abstract double calcPrize(IShippment order);

  /** {@inheritDoc} */
  @Override
  public abstract boolean shouldCalculate(IShippment order);

  /**
   * Sum product prizes.
   * 
   * @param order the {@link IOrder}
   * @return the summed prize of all requested product items
   */
  protected double sumProductPrizes(IShippment order) {
    double sum = 0;
    for (IDelivery d : order.getDeliveries()) {
      for (IOrderLine ol : d.getOrderLines()) {
        sum += ol.getProduct().getPrize() * ol.getAmount();
      }
    }
    return sum;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
