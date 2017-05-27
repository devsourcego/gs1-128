package br.com.devsource.gs1;

/**
 * @author guilherme.pacheco
 */
public final class AiFactory {

  public static AI create(String code, String description, String title, String format) {
    return new AI() {

      @Override
      public String getTitle() {
        return title;
      }

      @Override
      public String getFormat() {
        return format;
      }

      @Override
      public String getDescription() {
        return description;
      }

      @Override
      public String getCode() {
        return code;
      }

      @Override
      public String toString() {
        return description;
      }

    };
  }

}
