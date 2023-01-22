/**
 * 
 */
package provided.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * This base class provides common code for the recipe entities in the recipe
 * schema.
 * 
 * @author Promineo
 *
 */
public abstract class EntityBase {
  /**
   * This converts from a decimal amount (like 16.25) to a fractional amount
   * (like "16 1/4"). If the given amount is not null and is greater than zero
   * it adds a space on the end so: "1 1/2" -> "1 1/2 ". It handles anything
   * evenly divisible by 2 or 3, so: 1/4, 1/2, 1/3, etc.
   * 
   * @param value The amount to convert. It may be {@code null}.
   * @return The converted amount.
   */
  protected String toFraction(BigDecimal value) {
    String result = "";
    Double amount = Objects.isNull(value) ? null : value.doubleValue();

    if (Objects.nonNull(amount) && amount > 0.0) {
      int wholePart = Double.valueOf(Math.floor(amount)).intValue();
      double fractionalPart = amount - wholePart;
      Factor twoFactor = findFactor(fractionalPart, 16, 2);
      Factor threeFactor = findFactor(fractionalPart, 15, 5);

      /*
       * Pick the factor to use. This just picks the factor with the lowest
       * value. So, the value .6667 has a factor of 3 for 3-factor (2/3) but
       * 2-factor has a factor of 16 (11/16). In this case pick the 3-factor.
       */
      Factor factor =
          twoFactor.factor < threeFactor.factor ? twoFactor : threeFactor;

      /*
       * Only use the whole part if it's greater than zero. Otherwise this would
       * generate values like "0 1/2" instead of "1/2".
       */
      if (wholePart > 0) {
        result += Integer.valueOf(wholePart).toString();
      }

      /* If there is a fractional part, finish the result. */
      if (factor.num != 0) {
        /*
         * If the result is not empty, add a space. So: "2" becomes "2 ". This
         * allows the fraction to be spaced properly.
         */
        if (!result.isEmpty()) {
          result += " ";
        }

        /* Now, add the fractional part onto the result. */
        result += factor;
      }

      result += " ";
    }

    return result;
  }

  /**
   * Find the closest match given the factor and divisor.
   * 
   * @param fractionalPart This is the fractional part to match (i.e., .25).
   * @param factor This is the smallest fraction to use when creating the
   *        result. So, a factor of 16 might return 1/16 or 1/8, 1/4, etc.
   * @param divisor This is the value to use when dividing the factor to get the
   *        result.
   * @return The lowest factor (i.e., 1/2 instead of 8/16).
   */
  private Factor findFactor(double fractionalPart, int factor, int divisor) {
    /*
     * Multiply the fractional part by the factor, round the result and convert
     * to an integer. For example, .33333333 * 15 = 4.999999999, which, rounded
     * is 5. This, applied with the factor, becomes 5/15.
     */
    int num = Double.valueOf(Math.round(fractionalPart * factor)).intValue();

    /*
     * Reduce the factor. If each part (num and factor) is divisible evenly by
     * the divisor, divide both. So, 5 and 15 are evenly divided by 5. The
     * result becomes 1/3.
     */
    while (num != 0 && num % divisor == 0 && factor % divisor == 0) {
      num /= divisor;
      factor /= divisor;
    }

    return new Factor(num, factor);
  }

  /**
   * This inner class holds the fractional values.
   * 
   * @author Promineo
   *
   */
  private static class Factor {
    int num;
    int factor;

    Factor(int num, int factor) {
      this.num = num;
      this.factor = factor;
    }

    @Override
    public String toString() {
      return num + "/" + factor;
    }
  }
}
