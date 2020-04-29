package br.com.devsource.gs1;

import java.util.Iterator;

/**
 * @author guilherme.pacheco
 */
final class Gs1128Utils {

  public static final String PREFIX = "]C1";
  public static final char END_AI_VARIED = (char) 29;

  private Gs1128Utils() {
    super();
  }

  public static Iterator<Character> iterator(String value) {
    return value.chars().mapToObj(c -> (char) c).iterator();
  }

  public static String value(AI ai, Iterator<Character> iterator) {
    Format format = Format.valueOf(ai.getFormat());
    if (format.isVaried()) {
      return variedValue(iterator);
    } else {
      return fixedValue(format.getLength(), iterator);
    }
  }

  public static String value(Session session, String value) {
    Iterator<Character> iterator = iterator(value);
    if (session.isVaried()) {
      return variedValue(iterator);
    } else {
      return fixedValue(session.getLength(), iterator);
    }
  }

  private static String fixedValue(int size, Iterator<Character> iterator) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < size; i++) {
      builder.append(iterator.next());
    }
    return builder.toString();
  }

  private static String variedValue(Iterator<Character> iterator) {
    StringBuilder builder = new StringBuilder();
    while (iterator.hasNext()) {
      char value = iterator.next();
      if (value == END_AI_VARIED) {
        break;
      }
      builder.append(value);
    }
    return builder.toString();
  }

}
