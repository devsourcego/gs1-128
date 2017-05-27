package br.com.devsource.gs1;

import org.junit.Test;

/**
 * @author Guilherme Pacheco
 */
public class SegmentTest {

  @Test
  public void testSegmento() throws Exception {
    new Segment(AIs.BEST_BEFORE, "051231");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSegmento_ValorInvalido() throws Exception {
    new Segment(AIs.BEST_BEFORE, "05123");
  }

}
