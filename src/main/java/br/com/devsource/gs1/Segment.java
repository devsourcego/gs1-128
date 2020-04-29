package br.com.devsource.gs1;

import org.apache.commons.lang3.Validate;

/**
 * @author guilherme.pacheco
 */
public final class Segment {

  private final AI ai;
  private final String value;
  private final Format format;

  public Segment(AI ai, String value) {
    this.ai = Validate.notNull(ai, "Invalid AI");
    format = Format.valueOf(ai.getFormat());
    this.value = validateValue(format, value);
  }

  private String validateValue(Format format, String value) {
    Validate.notBlank(value, "Invalid segment value");
    Validate.notNull(format, "Invalid format");
    Validate.isTrue(format.isValid(value));
    return value;
  }

  String encode() {
    String codeValue = ai.getCode().concat(value);
    if (format.isVaried()) {
      return codeValue + Gs1128Utils.END_AI_VARIED;
    }
    return codeValue;
  }

}
