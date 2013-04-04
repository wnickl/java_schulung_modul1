
package at.edu.hti.shop.domain.shipping.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * enumeration of shipping modes <br />
 * 
 * @author nickl
 * @version $Revision$
 */

public enum ShippingMode {
  LAND("land"),
  AVIATION("aviation"),
  SHIP("ship"),
  UNKNOWN("unknown");

  private final String token;
  private static final Logger logger = LogManager.getLogger(ShippingMode.class.getName());

  /**
   * get enum constant for string value; will return {@link #UNKNOWN} if no other contant matches.
   * 
   * @param strShippingMode the string value
   * @return the {@link ShippingMode}
   */
  public static ShippingMode getEnum(String strShippingMode) {
    logger.debug("getEnum: argument=" + strShippingMode);
    if (strShippingMode == null) {
      return UNKNOWN;
    }
    String toGet = strShippingMode.trim();
    for (ShippingMode sm : values()) {
      if (sm.getToken().equals(toGet)) {
        return sm;
      }
    }
    return UNKNOWN;
  }

  /**
   * constructor
   * 
   * @param token the string value used in config file
   */
  ShippingMode(String token) {
    this.token = token;
  }

  /**
   * @return the token used in config file for this enum
   */
  public String getToken() {
    return token;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
