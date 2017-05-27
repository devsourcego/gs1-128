package br.com.devsource.gs1;

/**
 * @author guilherme.pacheco
 */
public enum AIs implements AI {

  /** Código de Série de Unidade Logística */
  SSCC("00", "SSCC", "Código de Série de Unidade Logística", "n2+n18"),
  /** Número Global de Item Comercial */
  GTIN("01", "GTIN", "Número Global de Item Comercial", "n2+n14"),
  /** GTIN de itens comerciais contidos em uma unidade logística */
  CONTENT("02", "CONTENT", "GTIN de itens comerciais contidos em uma unidade logística", "n2+n14"),
  /** Número de Batch ou Lote */
  BATCH_LOT("10", "BATCH/LOT", "Número de Batch ou Lote", "n2+an..20"),
  /** Data de produção */
  PROD_DATE("11", "PROD DATE", "Data de produção", "n2+n6"),
  /** Data de vencimento */
  DUE_DATE("12", "DUE DATE", "Data de vencimento", "n2+n6"),
  /** Data de embalagem */
  PACK_DATE("13", "PACK DATE", "Data de embalagem", "n2+n6"),
  /** Data de durabilidade mínima */
  BEST_BEFORE("15", "BEST BEFORE or SELL BY", "Data de durabilidade mínima", "n2+n6"),
  /** Data de durabilidade máxima */
  EXPIRY("17", "USE BY or EXPIRY", "Data de durabilidade máxima", "n2+n6"),
  /** Variante do produto */
  VARIANT("20", "VARIANT", "Variante do produto", "n2+n2"),
  /** Número de série */
  SERIAL("21", "SERIAL", "Número de série", "n2+an..20"),
  QTY_DATE_BATCH("22", "QTY / DATE / BATCH", "Dados secundários para produtos específicos "
      + "do setor de saúde (HIBCC)", "n2+an..29");

  private String code;
  private String title;
  private String description;
  private String format;

  private AIs(String code, String title, String description, String format) {
    this.code = code;
    this.title = title;
    this.description = description;
    this.format = format;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String getFormat() {
    return format;
  }

  @Override
  public String toString() {
    return description;
  }

}
