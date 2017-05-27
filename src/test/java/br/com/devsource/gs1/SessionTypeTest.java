package br.com.devsource.gs1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * @author Guilherme Pacheco
 */
public class SessionTypeTest {

  @Test
  public void testValidate_Numero() throws Exception {
    SessionType.N.validate("1234567");
  }

  @Test
  public void testValidate_NumeroInvalido() throws Exception {
    assertThat(SessionType.N.validate("1234567a")).isFalse();
  }

  @Test
  public void testValidate_Alfabetico() throws Exception {
    SessionType.A.validate("asdf");
  }

  @Test
  public void testValidate_AlfabeticoInvalido() throws Exception {
    assertThat(SessionType.A.validate("asdf1")).isFalse();
  }

  @Test
  public void testValidate_AlfaNumerico() throws Exception {
    SessionType.AN.validate("asdf1234");
  }

  @Test
  public void testValidate_AlfaNumericoInvalido() throws Exception {
    assertThat(SessionType.AN.validate("asdf1234$%")).isFalse();
  }

}
