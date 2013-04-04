
package at.edu.hti.shop.specification;

/**
 * The Class NotSpecification.
 * 
 * @param <T> the generic type
 * @author nickl
 * @version $Revision$
 */

public class NotSpecification<T> extends CompositeSpecification<T> {

  /** The Wrapped. */
  private final ISpecification<T> Wrapped;

  /**
   * Instantiates a new not specification.
   * 
   * @param x the x
   */
  public NotSpecification(ISpecification<T> x) {
    Wrapped = x;
  }

  /** {@inheritDoc} */
  @Override
  public boolean IsSatisfiedBy(T candidate) {
    return !Wrapped.IsSatisfiedBy(candidate);
  }
}