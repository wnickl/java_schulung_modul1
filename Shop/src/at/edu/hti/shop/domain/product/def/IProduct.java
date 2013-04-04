
package at.edu.hti.shop.domain.product.def;

/**
 * interface to represent some product that can be selled / baught <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public interface IProduct extends Comparable<IProduct> {

  /**
   * @return the category
   */
  IProductCategory getCategory();

  /**
   * @return the category name
   */
  String getCategoryName();

  /**
   * @return the id
   */
  long getId();

  /**
   * @return the name
   */
  String getName();

  /**
   * @return the prize
   */
  double getPrize();

  /**
   * @return the supply period
   */
  int getSupplyPeriod();

  /**
   * @return the vendor
   */
  String getVendor();

  /**
   * @return the weight
   */
  double getWeight();

}

//---------------------------- Revision History ----------------------------
//$Log$
//
