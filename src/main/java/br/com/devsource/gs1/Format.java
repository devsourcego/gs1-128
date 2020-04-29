package br.com.devsource.gs1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author guilherme.pacheco
 */
final class Format {

  private static final String JOIN_SYMBOL = "+";
  private final List<Session> sessions;

  public Format(List<Session> sessions) {
    this.sessions = Validate.notEmpty(sessions);
  }

  public Format(Session... sessions) {
    this(Arrays.asList(sessions));
  }

  public Session getIdentifier() {
    return sessions.get(0);
  }

  public List<Session> getDataSessions() {
    return sessions.subList(1, sessions.size());
  }

  @Override
  public String toString() {
    return sessions.stream().map(String::valueOf).collect(Collectors.joining(JOIN_SYMBOL));
  }

  public boolean isValid(String value) {
    if (StringUtils.isBlank(value)) {
      return false;
    }
    if (!validLength(value)) {
      return false;
    }
    return validate(value);
  }

  private boolean validate(String value) {
    return getDataSessions().stream().allMatch(s -> s.getType().isValid(Gs1128Utils.value(s, value)));
  }

  private boolean validLength(String value) {
    return isVaried() ? value.length() <= getLength() : value.length() == getLength();
  }

  public int getLength() {
    return getDataSessions().stream().mapToInt(Session::getLength).sum();
  }

  public boolean isVaried() {
    return getDataSessions().stream().anyMatch(Session::isVaried);
  }

  public static Format valueOf(String value) {
    String[] split = StringUtils.split(value, JOIN_SYMBOL);
    List<Session> list = Arrays.stream(split).map(Session::valueOf).collect(Collectors.toList());
    return new Format(list);
  }

}
