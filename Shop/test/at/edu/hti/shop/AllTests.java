
package at.edu.hti.shop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * test-suite <br />
 * 
 * @author nickl
 * @version $Revision$
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ProductCatalogTest.class, WorkflowTest.class, CalculationTest.class, OrderTest.class})
public class AllTests {

  private static final Logger logger = LogManager.getLogger(AllTests.class.getName());

  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(AllTests.class.getAnnotation(SuiteClasses.class).value());
    for (Failure failure : result.getFailures()) {
      logger.error(failure.toString());
    }
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
