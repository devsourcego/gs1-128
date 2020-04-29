package br.com.devsource.gs1;

import static br.com.devsource.gs1.Session.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author guilherme.pacheco
 */
public class FormatTest {

  @Test
  public void testToString() throws Exception {
    Format format = new Format(valueOf("n2"), valueOf("n6"));

    assertThat(format).hasToString("n2+n6");
  }

  @Test
  public void testToString_VariasSessoesDeDados() throws Exception {
    Format format = new Format(valueOf("n2"), valueOf("n3"), valueOf("an6"));

    assertThat(format).hasToString("n2+n3+an6");
  }

  @Test
  public void testValueOf() throws Exception {
    Format format = Format.valueOf("n2+n16");

    assertThat(format.getIdentifier()).hasToString("n2");
    assertThat(format.getDataSessions()).extracting(a -> a.toString()).contains("n16");
  }

  @ParameterizedTest
  @MethodSource("isVariedProvider")
  public void testIsVaried(String source, boolean varied) throws Exception {
    assertThat(Format.valueOf(source).isVaried()).isEqualTo(varied);
  }

  static Stream<Arguments> isVariedProvider() {
    return Stream.of(
      arguments("n2+n16", false),
      arguments("n2+n..16", true),
      arguments("n2+n16+n..3", true));
  }

  @ParameterizedTest
  @MethodSource("getLengthProvider")
  public void testGetLength(String source, int length) throws Exception {
    Format format = Format.valueOf(source);

    assertThat(format.getLength()).isEqualTo(length);
  }

  static Stream<Arguments> getLengthProvider() {
    return Stream.of(
      arguments("n2+n16", 16),
      arguments("n2+n3+an4", 7));
  }

  @ParameterizedTest
  @MethodSource("isValidProvider")
  public void testIsValid(String source, String value, boolean valid) throws Exception {
    Format format = Format.valueOf(source);

    assertThat(format.isValid(value)).isEqualTo(valid);
  }

  static Stream<Arguments> isValidProvider() {
    return Stream.of(
      arguments("n2+n4", "", false),
      arguments("n2+n4", null, false),
      arguments("n2+n4", "123", false),
      arguments("n2+n4", "1234", true),
      arguments("n2+n4", "12345", false),
      arguments("n2+n..5", "1234", true),
      arguments("n2+n..5", "12345", true),
      arguments("n2+n..5", "123456", false),
      arguments("n2+n3", "12a", false),
      arguments("n2+a3", "ab1", false),
      arguments("n2+a3", "abc", true),
      arguments("n2+an3", "a1#", false),
      arguments("n2+an3", "a1c", true),
      arguments("n2+an..4", "a1c", true));
  }

  @ParameterizedTest
  @MethodSource("getDataSessionsProvider")
  public void testGetDataSessions(String source, List<String> sessions) throws Exception {
    Format format = Format.valueOf(source);

    assertThat(format.getDataSessions()).extracting(a -> a.toString()).containsExactlyElementsOf(sessions);
  }

  static Stream<Arguments> getDataSessionsProvider() {
    return Stream.of(
      arguments("n2+n16", List.of("n16")),
      arguments("n2+n4+n..10", List.of("n4", "n..10")),
      arguments("n2+an3+n3", List.of("an3", "n3")));
  }

}
