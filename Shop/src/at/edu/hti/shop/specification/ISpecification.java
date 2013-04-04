
package at.edu.hti.shop.specification;

/**
 * The Interface ISpecification.
 * 
 * @param <T> the generic type
 * @author nickl
 * @version $Revision$
 */

public interface ISpecification<T> {

  /**
   * Is satisfied by.
   * 
   * @param candidate the candidate
   * @return true, if successful
   */
  boolean IsSatisfiedBy(T candidate);

  /**
   * And.
   * 
   * @param other the other
   * @return the {@link ISpecification}
   */
  ISpecification<T> And(ISpecification<T> other);

  /**
   * Or.
   * 
   * @param other the other
   * @return the {@link ISpecification}
   */
  ISpecification<T> Or(ISpecification<T> other);

  /**
   * Not.
   * 
   * @return the {@link ISpecification}
   */
  ISpecification<T> Not();
}