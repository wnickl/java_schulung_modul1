
package at.edu.hti.shop.domain.shipping.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.edu.hti.shop.domain.order.def.IOrder;
import at.edu.hti.shop.domain.order.def.IOrderLine;
import at.edu.hti.shop.domain.order.impl.OrderLine;
import at.edu.hti.shop.domain.product.def.IProduct;
import at.edu.hti.shop.domain.product.def.IProductCategory;
import at.edu.hti.shop.domain.shipping.def.IDelivery;
import at.edu.hti.shop.domain.shipping.def.IShippment;
import at.edu.hti.shop.domain.shipping.def.IShippmentCreator;

/**
 * default implementation of {@link IShippmentCreator}. this basic algorithm just separates items to
 * be shipped into product categories (different shipping mode) and for each shipping mode at least
 * on delivery is created. Check if some {@link OrderLine} can be added to delivery is done line
 * based. An improvement could be to do this per piece because in case weight of {@link OrderLine}
 * exceeds {@link IDelivery#MAX_WEIGHT} these items are not shippable at all.<br />
 * 
 * @author nickl
 * @version $Revision$
 */

public class DefaultShippmentCreator implements IShippmentCreator {

  /** logger instance */
  private static final Logger logger = LogManager.getLogger(DefaultShippmentCreator.class.getName());

  /** {@inheritDoc} */
  @Override
  public IShippment createShippment(IOrder order) {
    if (order == null) {
      throw new NullPointerException("'order' must not be null");
    }
    if (order.getProductLines().size() == 0) {
      return null;
    }
    IShippment s = new Shippment(order);
    Map<IProductCategory, List<IOrderLine>> linesPerCategory = new HashMap<>();
    for (Entry<IProduct, OrderLine> e : order.getProductLines().entrySet()) {
      IProductCategory pc = e.getKey().getCategory();
      List<IOrderLine> list = null;
      if (!linesPerCategory.containsKey(pc)) {
        list = new ArrayList<IOrderLine>();
        linesPerCategory.put(pc, list);
      } else {
        list = linesPerCategory.get(pc);
      }
      list.add(e.getValue());
    }
    for (Entry<IProductCategory, List<IOrderLine>> e : linesPerCategory.entrySet()) {
      IProductCategory pc = e.getKey();
      logger.info("creating delivery instance for shipping mode > " + pc.getShippingMode());
      IDelivery delivery = new Delivery(pc.getShippingMode());

      //lines have all same shippment mode due to same product category
      for (IOrderLine ol : e.getValue()) {
        if (delivery.canDeliver(ol)) {
          delivery.addOrderLine(ol);
        } else {
          //in case line can not be added at all do not create new delivery because of max_weight exceeded
          if (delivery.getOrderLines().size() > 0) {
            s.addDelivery(delivery);
            delivery = new Delivery(pc.getShippingMode());
          }
          //if new delivery was create line can now be added, otherwise line can not be added at all
          if (delivery.canDeliver(ol)) {
            delivery.addOrderLine(ol);
          } else {
            s.addUndeliverableLine(ol);
          }
        }
        //in case no line was addable nothing to deliver
      }
      if (delivery.getOrderLines().size() > 0) {
        s.addDelivery(delivery);
      }
    }
    return s;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
