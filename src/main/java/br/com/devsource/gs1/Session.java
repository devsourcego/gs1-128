package br.com.devsource.gs1;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author guilherme.pacheco
 */
final class Session {

  private static final String VARIED_CONSTANT = "..";

  private final SessionType sessionType;
  private final boolean varied;
  private final int length;

  public Session(SessionType sessionType, int length, boolean varied) {
    this.sessionType = Validate.notNull(sessionType);
    Validate.isTrue(length > 0);
    this.length = length;
    this.varied = varied;
  }

  public SessionType getSessionType() {
    return sessionType;
  }

  public int getLength() {
    return length;
  }

  public boolean isVaried() {
    return varied;
  }

  public static Session valueOf(String value) {
    SessionType type = sessionType(value);
    String strLength = value.substring(type.name().length());
    boolean varied = strLength.startsWith(VARIED_CONSTANT);
    int length = createLength(strLength, varied);
    return new Session(type, length, varied);
  }

  private static SessionType sessionType(String value) {
    Validate.notBlank(value);
    return SessionType.valuePrefix(value)
      .orElseThrow(() -> new IllegalArgumentException("Invalid session type"));
  }

  private static Integer createLength(String strLength, boolean varied) {
    try {
      if (varied) {
        String lengthValue = StringUtils.substringAfter(strLength, VARIED_CONSTANT);
        return Integer.valueOf(lengthValue);
      }
      return Integer.valueOf(strLength);
    } catch (NumberFormatException ex) {
      String msg = String.format("Invalid length: '%s'", strLength);
      throw new IllegalArgumentException(msg);
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(sessionType.name().toLowerCase());
    builder.append(varied ? VARIED_CONSTANT : "");
    builder.append(length);
    return builder.toString();
  }

}
