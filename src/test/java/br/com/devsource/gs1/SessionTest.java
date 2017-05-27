package br.com.devsource.gs1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

/**
 * @author Guilherme Pacheco
 */
public class SessionTest {

  @Test
  public void testToString() throws Exception {
    Session session = new Session(SessionType.A, 3, true);

    assertThat(session).hasToString("a..3");
  }

  @Test
  public void testToString_SemVariacao() throws Exception {
    Session session = new Session(SessionType.AN, 30, false);

    assertThat(session).hasToString("an30");
  }

  @Test
  public void testValueOf_VariedValue() throws Exception {
    Session session = Session.valueOf("an..30");

    assertThat(session.getSessionType()).isEqualTo(SessionType.AN);
    assertThat(session.getLength()).isEqualTo(30);
    assertThat(session.isVaried()).isTrue();
  }

  @Test
  public void testValue_NoVariedValue() throws Exception {
    Session session = Session.valueOf("an10");

    assertThat(session.getSessionType()).isEqualTo(SessionType.AN);
    assertThat(session.getLength()).isEqualTo(10);
    assertThat(session.isVaried()).isFalse();
  }

  @Test
  public void testValueOf_TipoInvalido() throws Exception {
    assertThatThrownBy(() -> Session.valueOf("w..30"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Invalid session type");
  }

  @Test
  public void testValueOf_VariacaoAntes() throws Exception {
    assertThatThrownBy(() -> Session.valueOf("..a30"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Invalid session type");
  }

  @Test
  public void testValueOf_VariacaoDepois() throws Exception {
    assertThatThrownBy(() -> Session.valueOf("a30.."))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Invalid length: '30..'");
  }

  @Test
  public void testValueOf_VariacaoInvalida() throws Exception {
    assertThatThrownBy(() -> Session.valueOf("a...30"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Invalid length: '...30'");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueOf_SemComprimento() throws Exception {
    Session.valueOf("a");
  }
}
