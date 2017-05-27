package br.com.devsource.gs1;

import static br.com.devsource.gs1.AiFactory.create;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * @author guilherme.pacheco
 */
public class Gs1128DecoderTest {

  private Gs1128Engine engine;

  @Before
  public void setUp() throws Exception {
    engine = new Gs1128Engine();
  }

  @Test
  public void testDecode() throws Exception {
    Gs1128Decoder decoder = engine.decoder();

    Map<AI, String> result = decoder.decode("]C101053912345678921505123110APO1536Z");

    assertThat(result).containsEntry(AIs.GTIN, "05391234567892");
    assertThat(result).containsEntry(AIs.BEST_BEFORE, "051231");
    assertThat(result).containsEntry(AIs.BATCH_LOT, "APO1536Z");
  }

  @Test
  public void testDecodeSuffix() throws Exception {
    AI COUNTRY_PROCESS = create("422", "País de processamento", "COUNTRY - PROCESS", "n3+n3");
    engine.registerAi(COUNTRY_PROCESS);
    Gs1128Decoder decoder = engine.decoder();

    Map<AI, String> result = decoder.decode("]C1010539123456789242210115051231");

    assertThat(result).containsEntry(AIs.GTIN, "05391234567892");
    assertThat(result).containsEntry(AIs.BEST_BEFORE, "051231");
    assertThat(result).containsEntry(COUNTRY_PROCESS, "101");
  }

}
