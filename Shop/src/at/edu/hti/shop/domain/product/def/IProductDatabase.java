
package at.edu.hti.shop.domain.product.def;

import java.util.List;

/**
 * The Interface IProductDatabase
 * 
 * @author nickl
 * @version $Revision$
 */

public interface IProductDatabase {

  /**
   * Find or create {@link IProductCategory}.
   * 
   * @param category the name of the category
   * @return the {@link IProductCategory}
   */
  IProductCategory findOrCreateCategory(String category);

  /**
   * find {@link IProduct} by id
   * 
   * @param id the id
   * @return the {@link IProduct}
   */
  IProduct getProductById(long id);

  /**
   * find {@link IProduct} by name.
   * 
   * @param name the name of the {@link IProduct}
   * @return the {@link IProduct}
   */
  IProduct getProductByName(String name);

  /**
   * find product for category
   * 
   * @param category the name of the category
   * @return the list of {@link IProducts}
   */
  List<IProduct> getProductsByCategory(String category);

  /**
   * find products for vendor
   * 
   * @param vendor the name of the vendor
   * @return the list of {@link IProducts}
   */
  List<IProduct> getProductsForVendor(String vendor);

}

//---------------------------- Revision History ----------------------------
//$Log$
//
