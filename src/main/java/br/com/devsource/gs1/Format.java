package br.com.devsource.gs1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author guilherme.pacheco
 */
final class Format {

  private List<Session> sessions;

  public Format(List<Session> sessions) {
    Validate.notEmpty(sessions);
    this.sessions = sessions;
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
    return sessions.stream().map(Object::toString).collect(Collectors.joining("+"));
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
    Predicate<Session> valid = s -> s.getSessionType().validate(Gs1128Utils.value(s, value));
    return getDataSessions().stream().allMatch(valid);
  }

  private boolean validLength(String value) {
    if (isVaried()) {
      return value.length() <= getLength();
    } else {
      return value.length() == getLength();
    }
  }

  public int getLength() {
    return getDataSessions().stream().mapToInt(Session::getLength).sum();
  }

  public boolean isVaried() {
    return getDataSessions().stream().anyMatch(Session::isVaried);
  }

  public static Format valueOf(String value) {
    String[] split = StringUtils.split(value, "+");
    List<Session> list = Arrays.stream(split).map(Session::valueOf).collect(Collectors.toList());
    return new Format(list);
  }

}
