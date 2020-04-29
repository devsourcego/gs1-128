package br.com.devsource.gs1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author guilherme.pacheco
 */
public class SessionTypeTest {

  @Test
  public void testIsValid_Numeric() throws Exception {
    assertThat(SessionType.NUMERIC.isValid("1234567")).isTrue();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "abc1", "abc", "#@!" })
  public void testIsValid_NumericInvalid(String source) throws Exception {
    assertThat(SessionType.NUMERIC.isValid(source)).isFalse();
  }

  @Test
  public void testIsValid_Alphabetic() throws Exception {
    SessionType.ALPHABETIC.isValid("abc");
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "abc1", "123", "!@#" })
  public void testIsValid_AlphabeticInvalid(String source) throws Exception {
    assertThat(SessionType.ALPHABETIC.isValid(source)).isFalse();
  }

  @ParameterizedTest
  @ValueSource(strings = { "abc", "abc1123", "123" })
  public void testIsValid_Alphanumeric(String source) throws Exception {
    assertThat(SessionType.ALPHANUMERIC.isValid(source)).isTrue();
  }

  @Test
  public void testIsValid_AlphanumericInvalid() throws Exception {
    assertThat(SessionType.ALPHANUMERIC.isValid("abc$%")).isFalse();
  }

  @ParameterizedTest
  @MethodSource("valueByPrefixProvider")
  public void testValueByPrefix(SessionType type, List<String> values) throws Exception {
    for (String value : values) {
      assertThat(SessionType.valueByPrefix(value)).contains(type);
    }
  }

  static Stream<Arguments> valueByPrefixProvider() {
    return Stream.of(
      arguments(SessionType.ALPHABETIC, List.of("a", "A")),
      arguments(SessionType.ALPHANUMERIC, List.of("an", "AN", "aN", "An")),
      arguments(SessionType.NUMERIC, List.of("n", "N")));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "w", "1", ".." })
  public void testValueByPrefix_Invalid(String source) throws Exception {
    assertThat(SessionType.valueByPrefix(source)).isEmpty();
  }

}
