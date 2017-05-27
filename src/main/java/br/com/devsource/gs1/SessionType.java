package br.com.devsource.gs1;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author Guilherme Pacheco
 */
enum SessionType {

  AN("Alfanumérico", v -> Character.isAlphabetic(v) || Character.isDigit(v)),
  A("Alfabético", v -> Character.isAlphabetic(v)),
  N("Numérico", v -> Character.isDigit(v));

  private String description;
  private Predicate<Character> validateCharset;

  private SessionType(String description, Predicate<Character> validateCharset) {
    this.validateCharset = validateCharset;
    this.description = description;
  }

  public boolean validate(String value) {
    Validate.notBlank(value);
    for (char charValue : value.toCharArray()) {
      if (!validateCharset.test(charValue)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    return description;
  }

  public static Optional<SessionType> valuePrefix(String value) {
    Stream<SessionType> stream = Arrays.stream(values());
    return stream.filter(v -> StringUtils.startsWithIgnoreCase(value, v.name())).findFirst();
  }

}
