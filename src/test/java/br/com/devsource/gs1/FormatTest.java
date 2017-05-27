package br.com.devsource.gs1;

import static br.com.devsource.gs1.Session.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Guilherme Pacheco
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

    assertThat(format.getIdentifier().toString()).hasToString("n2");
    assertThat(format.getDataSessions()).extracting(a -> a.toString()).contains("n16");
  }

  @Test
  public void testIsVaried() throws Exception {
    assertTrue(Format.valueOf("n2+n..16").isVaried());
  }

  @Test
  public void testIsVaried_NaoVariadoUmCampo() throws Exception {
    assertFalse(Format.valueOf("n2+n16").isVaried());
  }

  @Test
  public void testIsVaried_NaoVariadoVariosCampo() throws Exception {
    assertTrue(Format.valueOf("n2+n16+n..3").isVaried());
  }

  @Test
  public void testGetLength() throws Exception {
    Format format = Format.valueOf("n2+n16");

    assertThat(format.getLength()).isEqualTo(16);
  }

  @Test
  public void testGetLength_VariosSegumentos() throws Exception {
    Format format = Format.valueOf("n2+n3+an4");

    assertThat(format.getLength()).isEqualTo(7);
  }

  @Test
  public void testIsValid_TamanhoFixoMenor() throws Exception {
    Format format = Format.valueOf("n2+n4");

    assertThat(format.isValid("123")).isFalse();
  }

  @Test
  public void testIsValid_TamanhoFixoIgual() throws Exception {
    Format format = Format.valueOf("n2+n4");

    assertThat(format.isValid("1234")).isTrue();
  }

  @Test
  public void testIsValid_TamanhoFixoMaior() throws Exception {
    Format format = Format.valueOf("n2+n4");

    assertThat(format.isValid("12345")).isFalse();
  }

  @Test
  public void testIsValid_TamanhoVariadoMenorAoMaximo() throws Exception {
    Format format = Format.valueOf("n2+n..5");

    assertThat(format.isValid("1234")).isTrue();
  }

  @Test
  public void testIsValid_TamanhoVariadoIgualAoMaximo() throws Exception {
    Format format = Format.valueOf("n2+n..5");

    assertThat(format.isValid("12345")).isTrue();
  }

  @Test
  public void testIsValid_TamanhoVariadoMaiorMaximo() throws Exception {
    Format format = Format.valueOf("n2+n..5");

    assertThat(format.isValid("123456")).isFalse();
  }

  @Test
  public void testIsValid_DadosNumericosInvalidos() throws Exception {
    Format format = Format.valueOf("n2+n3");

    assertThat(format.isValid("12a")).isFalse();
  }

  @Test
  public void testIsValid_AlfabeticosInvalidos() throws Exception {
    Format format = Format.valueOf("n2+a3");

    assertThat(format.isValid("ab1")).isFalse();
  }

  @Test
  public void testIsValid_AlfanumericosValido() throws Exception {
    Format format = Format.valueOf("n2+an3");

    assertThat(format.isValid("a1#")).isFalse();
  }

  @Test
  public void testGetDataSessions() throws Exception {
    Format format = Format.valueOf("n2+an3");

    assertThat(format.getDataSessions()).hasSize(1);
    assertThat(format.getDataSessions()).extracting(a -> a.toString()).contains("an3");
  }

  @Test
  public void testGetDataSessions_ManyDataSessions() throws Exception {
    Format format = Format.valueOf("n2+an3+n3");

    assertThat(format.getDataSessions()).hasSize(2);
    assertThat(format.getDataSessions()).extracting(a -> a.toString()).contains("an3", "n3");
  }

}
