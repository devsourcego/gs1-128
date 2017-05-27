package br.com.devsource.gs1;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;

/**
 * @author guilherme.pacheco
 */
public class Gs1128EncoderTest {

  private Gs1128Encoder encoder;

  @Before
  public void setUp() throws Exception {
    encoder = new Gs1128Encoder();
  }

  @Test
  public void testEncode() throws Exception {
    Collection<Segment> segments = new ArrayList<>();
    segments.add(new Segment(AIs.GTIN, "05391234567892"));
    segments.add(new Segment(AIs.BEST_BEFORE, "051231"));
    segments.add(new Segment(AIs.BATCH_LOT, "APO1536Z"));

    String barcode = encoder.encode(segments);

    assertThat(barcode).isEqualTo("]C101053912345678921505123110APO1536Z");
  }

}
