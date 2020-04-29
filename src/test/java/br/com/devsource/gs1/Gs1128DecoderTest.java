package br.com.devsource.gs1;

import static br.com.devsource.gs1.AiFactory.create;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author guilherme.pacheco
 */
public class Gs1128DecoderTest {

  private Gs1128Engine engine;

  @BeforeEach
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
  public void testDecode_WithVariedSize() throws Exception {
    Gs1128Decoder decoder = engine.decoder();

    Map<AI, String> result = decoder.decode("]C1010539123456789210APO1536Z15051231");

    assertThat(result).containsEntry(AIs.GTIN, "05391234567892");
    assertThat(result).containsEntry(AIs.BATCH_LOT, "APO1536Z");
    assertThat(result).containsEntry(AIs.BEST_BEFORE, "051231");
  }

  @Test
  public void testDecode_AIRegistred() throws Exception {
    AI NSN = create("7001", "OTAN number", "n4+n13");
    engine.registerAi(NSN);
    Gs1128Decoder decoder = engine.decoder();

    Map<AI, String> result = decoder.decode("]C101053912345678927001123456789012315051231");

    assertThat(result).containsEntry(AIs.GTIN, "05391234567892");
    assertThat(result).containsEntry(AIs.BEST_BEFORE, "051231");
    assertThat(result).containsEntry(NSN, "1234567890123");
  }

}
