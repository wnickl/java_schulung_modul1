
package at.edu.hti.shop.domain.product.impl;

import java.io.Serializable;

import at.edu.hti.shop.domain.product.def.IProductCategory;
import at.edu.hti.shop.domain.shipping.impl.ShippingMode;

/**
 * The Class ProductCategory.
 * 
 * @author nickl
 * @version $Revision$
 */

public class ProductCategory implements IProductCategory, Serializable {

  /** serial version UID */
  private static final long serialVersionUID = -8783789925579491059L;

  /** The name. */
  private final String name;

  /** is product category shippable. */
  private final boolean isShippable;

  /** The shipping mode. */
  private final ShippingMode shippingMode;

  /**
   * constructor
   * 
   * @param name the name
   * @param isShippable the is shippable
   * @param shippingMode the shipping mode
   */
  public ProductCategory(String name, boolean isShippable, ShippingMode shippingMode) {
    this.name = name;
    this.isShippable = isShippable;
    this.shippingMode = shippingMode;
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(IProductCategory o) {
    //group by shipping mode
    int c = shippingMode.compareTo(o.getShippingMode());
    if (c == 0) {
      //in case of same shipping mode sort by name
      c = getName().compareTo(o.getName());
    }
    return c;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ProductCategory other = (ProductCategory) obj;
    if (isShippable != other.isShippable) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (shippingMode != other.shippingMode) {
      return false;
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return name;
  }

  /** {@inheritDoc} */
  @Override
  public ShippingMode getShippingMode() {
    return shippingMode;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (isShippable ? 1231 : 1237);
    result = prime * result + (name == null ? 0 : name.hashCode());
    result = prime * result + (shippingMode == null ? 0 : shippingMode.hashCode());
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isShippable() {
    return isShippable;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ProductCategory [name=");
    builder.append(name);
    builder.append(", isShippable=");
    builder.append(isShippable);
    builder.append(", shippingMode=");
    builder.append(shippingMode);
    builder.append("]");
    return builder.toString();
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
