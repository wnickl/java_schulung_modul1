
package at.edu.hti.shop.domain.product.impl;

import java.io.Serializable;

import at.edu.hti.shop.domain.product.ProductCatalog;
import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.product.def.IProductCategory;

/**
 * implementation of {@link IProduct}
 * 
 * @author nickl
 * @version $Revision$
 */
public class Product implements Serializable, IProduct {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -3948976963430944559L;

  /** The name. */
  private final String name;

  /** The id. */
  private final long id;

  /** The prize. */
  private final double prize;

  /** The weight. */
  private final double weight;

  /** The category name. */
  private final String categoryName;

  /** The supply period. */
  private final int supplyPeriod;

  /** The vendor. */
  private final String vendor;

  /**
   * constructor
   * 
   * @param id the id
   * @param name the name
   * @param prize the prize
   * @param weight the weight
   * @param category the category
   * @param supplyPeriod the supply period
   * @param vendor the vendor
   */
  public Product(long id, String name, double prize, double weight, String categoryName, int supplyPeriod, String vendor) {
    this.name = name;
    this.id = id;
    this.prize = prize;
    this.weight = weight;
    this.categoryName = categoryName;
    this.supplyPeriod = supplyPeriod;
    this.vendor = vendor;
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(IProduct o) {
    return Long.valueOf(getId()).compareTo(Long.valueOf(o.getId()));
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
    Product other = (Product) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public IProductCategory getCategory() {
    return ProductCatalog.getInstance().findOrCreateCategory(categoryName);
  }

  /** {@inheritDoc} */
  @Override
  public String getCategoryName() {
    return categoryName;
  }

  /** {@inheritDoc} */
  @Override
  public long getId() {
    return id;
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return name;
  }

  /** {@inheritDoc} */
  @Override
  public double getPrize() {
    return prize;
  }

  /** {@inheritDoc} */
  @Override
  public int getSupplyPeriod() {
    return supplyPeriod;
  }

  /** {@inheritDoc} */
  @Override
  public String getVendor() {
    return vendor;
  }

  /** {@inheritDoc} */
  @Override
  public double getWeight() {
    return weight;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ id >>> 32);
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Product [name=");
    builder.append(name);
    builder.append(", id=");
    builder.append(id);
    builder.append(", prize=");
    builder.append(prize);
    builder.append(", weight=");
    builder.append(weight);
    builder.append(", categoryName=");
    builder.append(categoryName);
    builder.append(", supplyPeriod=");
    builder.append(supplyPeriod);
    builder.append(", vendor=");
    builder.append(vendor);
    builder.append("]");
    return builder.toString();
  }

}
