package br.com.devsource.gs1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author guilherme.pacheco
 */
public class SessionTest {

  @ParameterizedTest
  @ValueSource(ints = { -1, 0 })
  void testSession_ValidLength(int length) throws Exception {
    assertThatThrownBy(() -> new Session(SessionType.ALPHABETIC, length, true))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Session invalid lenght");
  }

  @Test
  public void testToString_Varied() throws Exception {
    Session session = new Session(SessionType.ALPHABETIC, 3, true);

    assertThat(session).hasToString("a..3");
  }

  @Test
  public void testToString() throws Exception {
    Session session = new Session(SessionType.ALPHANUMERIC, 30, false);

    assertThat(session).hasToString("an30");
  }

  @Test
  public void testValueOf_VariedValue() throws Exception {
    Session session = Session.valueOf("an..30");

    assertThat(session.getType()).isEqualTo(SessionType.ALPHANUMERIC);
    assertThat(session.getLength()).isEqualTo(30);
    assertThat(session.isVaried()).isTrue();
  }

  @Test
  public void testValue_NoVariedValue() throws Exception {
    Session session = Session.valueOf("an10");

    assertThat(session.getType()).isEqualTo(SessionType.ALPHANUMERIC);
    assertThat(session.getLength()).isEqualTo(10);
    assertThat(session.isVaried()).isFalse();
  }

  @ParameterizedTest
  @ValueSource(strings = { "..a30", "w..30" })
  public void testValueOf_InvalidPrefix(String source) throws Exception {
    assertThatThrownBy(() -> Session.valueOf(source))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Invalid session type");
  }

  @ParameterizedTest
  @ValueSource(strings = { "a30..", "a...30", "a" })
  public void testValueOf_InvalidVariedEnd(String source) throws Exception {
    assertThatThrownBy(() -> Session.valueOf(source))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageStartingWith("Invalid length: ");
  }

  @ParameterizedTest
  @NullAndEmptySource
  public void testValueOf_EmptyAndBlank(String source) throws Exception {
    assertThatThrownBy(() -> Session.valueOf(source))
      .hasMessageStartingWith("Session cannot be blank");
  }

}
