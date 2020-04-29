package br.com.devsource.gs1;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author guilherme.pacheco
 */
final class Session {

  private static final String VARIED_CONSTANT = "..";

  private final SessionType type;
  private final boolean varied;
  private final int length;

  public Session(SessionType type, int length, boolean varied) {
    this.type = Validate.notNull(type);
    Validate.isTrue(length > 0, "Session invalid lenght");
    this.length = length;
    this.varied = varied;
  }

  public SessionType getType() {
    return type;
  }

  public int getLength() {
    return length;
  }

  public boolean isVaried() {
    return varied;
  }

  public static Session valueOf(String value) {
    Validate.notBlank(value, "Session cannot be blank");
    SessionType type = sessionType(value);
    String strLength = value.substring(type.length());
    boolean varied = strLength.startsWith(VARIED_CONSTANT);
    int length = createLength(strLength, varied);
    return new Session(type, length, varied);
  }

  private static SessionType sessionType(String value) {
    return SessionType.valueByPrefix(value).orElseThrow(() -> new IllegalArgumentException("Invalid session type"));
  }

  private static Integer createLength(String strLength, boolean varied) {
    try {
      if (varied) {
        String lengthValue = StringUtils.substringAfter(strLength, VARIED_CONSTANT);
        return Integer.parseInt(lengthValue);
      }
      return Integer.valueOf(strLength);
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException(String.format("Invalid length: '%s'", strLength), ex);
    }
  }

  @Override
  public String toString() {
    return new StringBuilder(type.getSymbol())
      .append(varied ? VARIED_CONSTANT : StringUtils.EMPTY)
      .append(length)
      .toString();
  }

}
