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
 * The Class CorrectShippingModeSpecification.
 */

public class CorrectShippingModeSpecification extends CompositeSpecification<IOrderLine> {

  /** The shipping mode. */
  private final ShippingMode shippingMode;

  /**
   * constructor
   * 
   * @param shippingMode the shipping mode
   */
  public CorrectShippingModeSpecification(ShippingMode shippingMode) {
    this.shippingMode = shippingMode;
  }

  /** {@inheritDoc} */
  @Override
  public boolean IsSatisfiedBy(IOrderLine candidate) {
    if (candidate.getProduct().getCategory().getShippingMode() == shippingMode) {
      return true;
    }
    return false;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
