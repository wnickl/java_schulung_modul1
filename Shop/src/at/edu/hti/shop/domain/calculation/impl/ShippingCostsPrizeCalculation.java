
package at.edu.hti.shop.domain.calculation.impl;

import at.edu.hti.shop.domain.calculation.def.IPrizeCalculation;
import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * in case cost of order is less than {@value #SHIPPING_COSTS} this calculation will add shipping
 * costs of {@value #SHIPPING_COSTS}.
 * 
 * @author nickl
 * @version $Revision$
 */

public class ShippingCostsPrizeCalculation implements IPrizeCalculation {

  /** The Constant SHIPPING_COSTS. */
  private static final int SHIPPING_COSTS = 10;

  /** {@inheritDoc} */
  @Override
  public double calcPrize(IShippment order) {
    return PrizeCalculationUtils.sumProductPrizes(order) + SHIPPING_COSTS;
  }

  /** {@inheritDoc} */
  @Override
  public boolean shouldCalculate(IShippment order) {
    double sum = PrizeCalculationUtils.sumProductPrizes(order);

    if (sum < SHIPPING_COSTS) {
      return true;
    }
    return false;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
