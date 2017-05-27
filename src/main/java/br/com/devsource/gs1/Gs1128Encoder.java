package br.com.devsource.gs1;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author guilherme.pacheco
 */
public final class Gs1128Encoder {

  Gs1128Encoder() {
    super();
  }

  public String encode(Collection<Segment> segments) {
    Validate.notEmpty(segments);
    String barcode = joinSegments(segments);
    return checkBarcode(barcode);
  }

  private String checkBarcode(String barcode) {
    return StringUtils.removeEnd(barcode, String.valueOf(Gs1128Utils.END_AI_VARIED));
  }

  private String joinSegments(Collection<Segment> segments) {
    String joinValue = segments.stream().map(Segment::encode).collect(Collectors.joining());
    return Gs1128Utils.PREFIX.concat(joinValue);
  }

}
