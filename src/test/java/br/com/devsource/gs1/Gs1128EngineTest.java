package br.com.devsource.gs1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author guilherme.pacheco
 */
public class Gs1128EngineTest {

  private Gs1128Engine engine;

  @BeforeEach
  public void setUp() throws Exception {
    engine = new Gs1128Engine();
  }

  @Test
  public void testEncoder() throws Exception {
    Gs1128Encoder encoder = engine.encoder();

    assertThat(encoder).isNotNull();
    assertThat(encoder).isInstanceOf(Gs1128Encoder.class);
  }

  @Test
  public void testDecoder() throws Exception {
    Gs1128Decoder decoder = engine.decoder();

    assertThat(decoder).isNotNull();
    assertThat(decoder).isInstanceOf(Gs1128Decoder.class);
  }

  @Test
  public void testRegisterAi() throws Exception {
    engine.registerAi(AiFactory.create("422", "País de processamento", "n3+n3"));

    List<String> codes = engine.getAisRegistred().stream().map(AI::getCode).collect(Collectors.toList());

    assertThat(codes).contains("422");
  }

}
