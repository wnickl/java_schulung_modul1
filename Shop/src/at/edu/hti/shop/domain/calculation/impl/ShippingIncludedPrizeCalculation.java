
package at.edu.hti.shop.domain.calculation.impl;

import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * in case cost of order is greater than 10 this calculation includes shipping costs.
 * 
 * @author nickl
 * @version $Revision$
 */

public class ShippingIncludedPrizeCalculation extends AbstractPrizeCalculation {

  /** {@inheritDoc} */
  @Override
  public double calcPrize(IShippment order) {
    return sumProductPrizes(order);
  }

  /** {@inheritDoc} */
  @Override
  public boolean shouldCalculate(IShippment order) {
    double sum = sumProductPrizes(order);

    if (sum < 10) {
      return false;
    }
    return true;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
