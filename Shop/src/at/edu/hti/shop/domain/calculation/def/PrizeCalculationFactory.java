
package at.edu.hti.shop.domain.calculation.def;

import java.util.ArrayList;
import java.util.List;

import at.edu.hti.shop.domain.calculation.impl.ShippingCostsPrizeCalculation;
import at.edu.hti.shop.domain.calculation.impl.ShippingIncludedPrizeCalculation;
import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * A factory for creating {@link IPrizeCalculation} objects.
 * 
 * @author nickl
 * @version $Revision$
 */

public final class PrizeCalculationFactory {

  /** list of {@link IPrizeCalculation} instances to use */
  private final static List<IPrizeCalculation> calcs = new ArrayList<>();

  static {
    calcs.add(new ShippingCostsPrizeCalculation());
    calcs.add(new ShippingIncludedPrizeCalculation());
  }

  /**
   * Calculate prize; first {@link IPrizeCalculation} instance responsible will calculate prize.
   * 
   * @param shippment the {@link IShippment}
   * @return the prize for the {@link IShippment}
   */
  public static double calculatePrize(IShippment shippment) {
    for (IPrizeCalculation pc : calcs) {
      if (pc.shouldCalculate(shippment)) {
        return pc.calcPrize(shippment);
      }
    }
    throw new IllegalStateException("no calculation found for this order");
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
