package br.com.devsource.gs1;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

/**
 * @author guilherme.pacheco
 */
enum SessionType {

  ALPHANUMERIC("an", v -> Character.isAlphabetic(v) || Character.isDigit(v)),
  ALPHABETIC("a", v -> Character.isAlphabetic(v)),
  NUMERIC("n", v -> Character.isDigit(v));

  private final String symbol;
  private final Predicate<Character> validator;

  private SessionType(String symbol, Predicate<Character> validator) {
    this.symbol = symbol;
    this.validator = validator;
  }

  public boolean isValid(String value) {
    if (StringUtils.isBlank(value)) {
      return false;
    }
    return value.chars().mapToObj(c -> (char) c).allMatch(validator::test);
  }

  public String getSymbol() {
    return symbol;
  }

  public int length() {
    return symbol.length();
  }

  public static Optional<SessionType> valueByPrefix(String value) {
    if (StringUtils.isBlank(value)) {
      return Optional.empty();
    }
    return EnumSet.allOf(SessionType.class).stream()
      .filter(v -> StringUtils.startsWithIgnoreCase(value, v.symbol))
      .findFirst();
  }

}
