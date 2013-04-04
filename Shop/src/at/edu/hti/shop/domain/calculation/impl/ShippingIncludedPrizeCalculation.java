
package at.edu.hti.shop.domain.calculation.impl;

import at.edu.hti.shop.domain.calculation.def.IPrizeCalculation;
import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * in case cost of order is greater than 10 this calculation includes shipping costs.
 * 
 * @author nickl
 * @version $Revision$
 */

public class ShippingIncludedPrizeCalculation implements IPrizeCalculation {

  /** {@inheritDoc} */
  @Override
  public double calcPrize(IShippment shippment) {
    if (shippment == null) {
      throw new NullPointerException("'shippment' must not be null");
    }
    return PrizeCalculationUtils.sumProductPrizes(shippment);
  }

  /** {@inheritDoc} */
  @Override
  public boolean shouldCalculate(IShippment shippment) {
    if (shippment == null) {
      throw new NullPointerException("'shippment' must not be null");
    }
    double sum = PrizeCalculationUtils.sumProductPrizes(shippment);

    if (sum < 10) {
      return false;
    }
    return true;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
