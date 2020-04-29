package br.com.devsource.gs1;

/**
 * @author guilherme.pacheco
 */
public enum AIs implements AI {

  SSCC("00", "Serial Shipping Container Code", "n2+n18"),
  GTIN("01", "GTIN-14", "n2+n14"),
  CONTENT("02", "Number of containers", "n2+n14"),
  BATCH_LOT("10", "Batch Number", "n2+an..20"),
  PROD_DATE("11", "Production Date", "n2+n6"),
  DUE_DATE("12", "Production Date", "n2+n6"),
  PACK_DATE("13", "Packaging Date", "n2+n6"),
  BEST_BEFORE("15", "Sell by Date (Quality Control) ", "n2+n6"),
  EXPIRY("17", "Expiration Date", "n2+n6"),
  VARIANT("20", "Product Variant", "n2+n2"),
  SERIAL("21", "Serial Number", "n2+an..20"),
  INTERNAL("91", "Company Internal Information", "n2+an..30"),
  ADDITIONAL_ID("240", "Additional Product Identification", "n3+an..30"),
  CUSTOMER_PART("241", "Customer Part Number", "n3+an..30"),
  VARIATION_NUMBER("242", "242 Made-to-Order variation no", "n2+n..30"),
  SECONDARY_SERIAL("250", "Secondary Serial Number", "n3+an..30"),
  REF_TO_SOURCE("251", "Reference to source entity", "n3+an..30"),
  DOC_ID("253", "Global Document Type Identifier", "n3+n13+n..17"),
  GLN_EXTENSION("254", "GLN extension component ", "n3+an..20"),
  VAR_COUNT("30", "Quantity Each", "n2+n..8"),
  NET_WEIGHT("310n", "Product Net Weight in kg", "n4+n6"),
  LENGTH("311n", "Product Length/1st Dimension, in meters", "n4+n6"),
  WIDTH("312n", "Product Width/Diameter/2nd Dimension, in meters", "n4+n6"),
  HEIGHT("313n", "Product Depth/Thickness/3rd Dimension, in meters", "n4+n6"),
  AREA("314n", "Product Area, in square meters", "n4+n6"),
  NET_VOLUME_LITERS("315n", " Product Volume, in liters", "n4+n6"),
  NET_VOLUME_CUBICS("316n", " product Volume, in cubic meters", "n4+n6"),
  NET_WEIGHT_POUNDS("320n", "Product Net Weight, in pounds", "n4+n6"),
  LENGTH_INCHES("321n", "Product Length/1st Dimension, in inches", "n4+n6"),
  LENGTH_PES("322n", "Product Length/1st Dimension, in feet", "n4+n6"),
  LENGTH_YARDS("323n", "Product Length/1st Dimension, in yards", "n4+n6"),
  WIDTH_INCHES("324n", "Product Width/Diameter/2nd Dimension, in inches", "n4+n6"),
  WIDTH_FEET("325n", "Product Width/Diameter/2nd Dimension, in feet", "n4+n6"),
  WIDTH_YARDS("326n", "Product Width/Diameter/2nd Dimension, in yards", "n4+n6"),
  HEIGHT_INCHES("327n", "Product Depth/Thickness/3rd Dimension, in inches", "n4+n6"),
  HEIGHT_FEET("328n", "Product Depth/Thickness/3rd Dimension, in feet", "n4+n6"),
  HEIGHT_YARDS("329n", "Product Depth/Thickness/3rd Dimension, in yards", "n4+n6"),
  PRODUCT_AREA_INCHES("350n", "Product Area (Square Inches)", "n4+n6"),
  PRODUCT_AREA_FEET("351n", "Product Area (Square Feet)", "n4+n6"),
  PRODUCT_AREA_YARDS("352n", "Product Area (Square Yards)", "n4+n6"),
  CONTAINER_AREA_INCHES("353n", "Container Area (Square Inches) ", "n4+n6"),
  CONTAINER_AREA_FEET("354n", "Container Area (Square Feet) ", "n4+n6"),
  CONTAINER_AREA_YARDS("355n", "Container Area (Square Yards) ", "n4+n6"),
  NET_WEIGHT_OUNCES("356n", "Net Weight (Troy Ounces)", "n4+n6"),
  PRODUCT_VOLUME_QUARTS("360n", " Product Volume (Quarts)", "n4+n6"),
  PRODUCT_VOLUME_GALLONS("361n", " Product Volume (Gallons)", "n4+n6"),
  CONTAINER_VOLUME_QUARTS("362n", " Container Gross Volume (Quarts)", "n4+n6"),
  CONTAINER_VOLUME_GALLONS("363n", " Container Gross Volume (Gallons)", "n4+n6"),
  PRODUCT_VOLUME_INCHES("364n", " Product Volume (Cubic Inches)", "n4+n6"),
  PRODUCT_VOLUME_FEET("365n", " Product Volume (Cubic Feet)", "n4+n6"),
  PRODUCT_VOLUME_YARDS("366n", " Product Volume (Cubic Yards)", "n4+n6"),
  CONTAINER_VOLUME_INCHES("367n", " Container Gross Volume (Cubic Inches)", "n4+n6"),
  CONTAINER_VOLUME_FEET("368n", " Container Gross Volume (Cubic Feet)", "n4+n6"),
  CONTAINER_VOLUME_YARDS("369n", " Container Gross Volume (Cubic Yards)", "n4+n6"),
  COUNT("37", " Number of Units Contained", "n2+n..8"),
  ORDER_NUMBER("400", "Customer Purchase Order Number", "n3+an..30"),
  SHIP_TO_DELIVER_TO_LOCATION_CODE("410", "Ship To/Deliver To Location Code (EAN13 or DUNS code)", "n3+n13"),
  BILL_TO_INVOICEE_TO_LOCATION_CODE("411", "Bill To/Invoice Location Code (EAN13 or DUNS code)", "n3+n13"),
  PURCHASE_FROM_LOCATION_CODE("412", "Purchase From Location Code (EAN13 or DUNS code)", "n3+n13"),
  SHIP_TO_DELIVER_TO_POSTALCODE("420", "Ship To/Deliver To Postal Code (Single Postal Authority)", "n3+an..9"),
  SHIP_TO_DELIVER_TO_MULTI_POSTALCODE("421", "Ship To/Deliver To Postal Code (Multiple Postal Authority)",
      "n3+n3+an..9"),
  ORIGIN("422", "Country orign", "n3+n3"),
  COUNTRY_INITIAL_PROCESS("423", "County initial processing", "n3+n3+n..12"),
  COUNTRY_PROCESS("424", "County processing", "n3+n3"),
  COUNTRY_DISASSEMBLY("425", "Country disassembly", "n3+n3"),
  COUNTRY_FULL_PROCESS("426", "Country pull processing", "n3+n3"),
  ROLL_PRODUCTS("8001", "Roll Products – Width/Length/Core Diameter", "n4+n14"),
  ESN("8002", "Electronic Serial Number (ESN) for Cellular Phone", "n4+an..20"),
  UPC_EAN_RETURNABLE_ASSET("8003", "UPC/EAN Number and Serial Number of Returnable Asset", "n4+an..30"),
  UPC_EAN_SERIAL_IDENTIFICATION("8004", "UPC/EAN Serial Identification", "n4+an..30"),
  PRICE_UNIT_MEASURE("8005", "Price per Unit of Measure", "n4+n6"),
  ;

  private final String code;
  private final String description;
  private final String format;

  private AIs(String code, String description, String format) {
    this.code = code;
    this.description = description;
    this.format = format;
  }

  @Override
  public String getCode() {
    return code;
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
