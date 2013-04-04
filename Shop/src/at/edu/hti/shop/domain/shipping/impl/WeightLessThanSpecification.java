/** 
 * Copyright 2013 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id$
 * <br /> <br />
 *
 */

package at.edu.hti.shop.domain.shipping.impl;

import at.edu.hti.shop.domain.order.def.IOrderLine;
import at.edu.hti.shop.specification.CompositeSpecification;

/**
 * check if weight of orderline is ok <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public class WeightLessThanSpecification extends CompositeSpecification<IOrderLine> {

  private final Double maxWeight;

  /**
   * constructor
   * 
   * @param maxWeight the max weight
   */
  public WeightLessThanSpecification(Double maxWeight) {
    this.maxWeight = maxWeight;
  }

  /** {@inheritDoc} */
  @Override
  public boolean IsSatisfiedBy(IOrderLine candidate) {
    if (candidate.getWeight() < maxWeight.doubleValue()) {
      return true;
    }
    return false;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
