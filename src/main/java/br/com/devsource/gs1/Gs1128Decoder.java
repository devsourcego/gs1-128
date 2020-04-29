package br.com.devsource.gs1;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author guilherme.pacheco
 */
public final class Gs1128Decoder {

  private final Collection<AI> ais;

  Gs1128Decoder(Collection<AI> ais) {
    this.ais = ais;
  }

  public Map<AI, String> decode(final String barcode) {
    validateBarcode(barcode);
    String barcodeValue = StringUtils.remove(barcode, Gs1128Utils.PREFIX);
    return extract(barcodeValue);
  }

  private Map<AI, String> extract(String barcode) {
    final Iterator<Character> iterator = Gs1128Utils.iterator(barcode);
    final Map<AI, String> result = new WeakHashMap<>(4);
    StringBuilder builder = new StringBuilder();
    while (iterator.hasNext()) {
      builder.append(iterator.next());
      Optional<Map<AI, String>> mapAi = mapAi(iterator, builder.toString());
      if (mapAi.isPresent()) {
        result.putAll(mapAi.get());
        builder = new StringBuilder();
      }
    }
    return result;
  }

  private Optional<Map<AI, String>> mapAi(Iterator<Character> iterator, String builder) {
    return ais.stream().filter(v -> v.getCode().equals(builder)).findFirst().map(aiValue(iterator));
  }

  private Function<AI, Map<AI, String>> aiValue(Iterator<Character> iterator) {
    return ai -> {
      String value = Gs1128Utils.value(ai, iterator);
      return Map.of(ai, value);
    };
  }

  private void validateBarcode(String barcode) {
    Validate.notBlank(barcode, "Barcode cannot be blank");
    Validate.isTrue(StringUtils.startsWith(barcode, Gs1128Utils.PREFIX), "Barcode must start with prefix Gs1-128 \"]C1\"");
  }

}
