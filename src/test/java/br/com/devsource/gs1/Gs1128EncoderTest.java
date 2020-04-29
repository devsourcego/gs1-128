package br.com.devsource.gs1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author guilherme.pacheco
 */
public class Gs1128EncoderTest {

  private Gs1128Encoder encoder;
  private Collection<Segment> segments;

  @BeforeEach
  public void setUp() throws Exception {
    encoder = new Gs1128Encoder();
    segments = new ArrayList<>();
  }

  @Test
  public void testEncode() throws Exception {
    segments.add(new Segment(AIs.GTIN, "05391234567892"));
    segments.add(new Segment(AIs.BEST_BEFORE, "051231"));

    String barcode = encoder.encode(segments);

    assertThat(barcode).isEqualTo("]C1010539123456789215051231");
  }

  @Test
  public void testEncode_WithvariedSize() throws Exception {
    segments.add(new Segment(AIs.GTIN, "05391234567892"));
    segments.add(new Segment(AIs.BATCH_LOT, "APO1536Z"));
    segments.add(new Segment(AIs.BEST_BEFORE, "051231"));

    String barcode = encoder.encode(segments);

    assertThat(barcode).isEqualTo("]C1010539123456789210APO1536Z15051231");
  }

}
