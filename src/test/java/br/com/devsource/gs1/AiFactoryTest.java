package br.com.devsource.gs1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author guilherme.pacheco
 */
public class AiFactoryTest {

  @Test
  public void testCreate() throws Exception {
    AI ai = AiFactory.create("422", "País de processamento", "n3+n3");

    assertThat(ai).isNotNull();
    assertThat(ai.getCode()).isEqualTo("422");
    assertThat(ai.getDescription()).isEqualTo("País de processamento");
    assertThat(ai.getFormat()).isEqualTo("n3+n3");
  }

}
