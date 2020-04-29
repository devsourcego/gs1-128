package br.com.devsource.gs1;

/**
 * @author guilherme.pacheco
 */
public final class AiFactory {

  private AiFactory() {
    super();
  }

  public static AI create(String code, String description, String format) {
    return new AI() {

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
    };
  }

}
