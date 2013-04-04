
package at.edu.hti.shop.specification;

/**
 * The Class AndSpecification.
 * 
 * @param <T> the generic type
 * @author nickl
 * @version $Revision$
 */

public class AndSpecification<T> extends CompositeSpecification<T> {

  /** The One. */
  private final ISpecification<T> One;

  /** The Other. */
  private final ISpecification<T> Other;

  /**
   * Instantiates a new and specification.
   * 
   * @param x the x
   * @param y the y
   */
  public AndSpecification(ISpecification<T> x, ISpecification<T> y) {
    One = x;
    Other = y;
  }

  /** {@inheritDoc} */
  @Override
  public boolean IsSatisfiedBy(T candidate) {
    return One.IsSatisfiedBy(candidate) && Other.IsSatisfiedBy(candidate);
  }
}