
package at.edu.hti.shop.domain.calculation.def;

import at.edu.hti.shop.domain.shipping.def.IShippment;

/**
 * prize calculator
 * 
 * @author nickl
 * @version $Revision$
 */
public interface IPrizeCalculation {

  /**
   * Calculate prize
   * 
   * @param shippment the {@link IOrder}
   * @return the calculated prize
   */
  double calcPrize(IShippment shippment);

  /**
   * check if this {@link IPrizeCalculation} is repsonsible to calculate the prize
   * 
   * @param shippment the {@link IOrder}
   * @return true, if this {@link IPrizeCalculation} is repsonsible to calculate the prize
   */
  boolean shouldCalculate(IShippment shippment);
}

//---------------------------- Revision History ----------------------------
//$Log$
//
