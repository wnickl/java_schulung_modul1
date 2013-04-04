
package at.edu.hti.shop.specification;

/**
 * The Class CompositeSpecification.
 * 
 * @param <T> the generic type
 * @author nickl
 * @version $Revision$
 */

public abstract class CompositeSpecification<T> implements ISpecification<T> {

  /** {@inheritDoc} */
  @Override
  public abstract boolean IsSatisfiedBy(T candidate);

  /** {@inheritDoc} */
  @Override
  public ISpecification<T> And(ISpecification<T> other) {
    return new AndSpecification<T>(this, other);
  }

  /** {@inheritDoc} */
  @Override
  public ISpecification<T> Or(ISpecification<T> other) {
    return new OrSpecification<T>(this, other);
  }

  /** {@inheritDoc} */
  @Override
  public ISpecification<T> Not() {
    return new NotSpecification<T>(this);
  }
}
