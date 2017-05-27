package br.com.devsource.gs1;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
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
    Iterator<Character> iterator = Gs1128Utils.iterator(barcodeValue);
    return extract(iterator);
  }

  private Map<AI, String> extract(Iterator<Character> iterator) {
    Map<AI, String> result = new HashMap<>(4);
    StringBuilder builder = new StringBuilder();
    while (iterator.hasNext()) {
      builder.append(iterator.next());
      Optional<Map<AI, String>> mapAi = mapAi(iterator, builder);
      if (mapAi.isPresent()) {
        result.putAll(mapAi.get());
        builder = new StringBuilder();
      }
    }
    return result;
  }

  private Optional<Map<AI, String>> mapAi(Iterator<Character> iterator, StringBuilder builder) {
    Optional<AI> ai = ais.stream().filter(v -> v.getCode().equals(builder.toString())).findFirst();
    return ai.map(aiValue(iterator));
  }

  private Function<AI, Map<AI, String>> aiValue(Iterator<Character> iterator) {
    return ai -> {
      String value = Gs1128Utils.value(ai, iterator);
      return Collections.singletonMap(ai, value);
    };
  }

  private void validateBarcode(String codigo) {
    Validate.notBlank(codigo, "Código de barra é inválido");
    Validate.isTrue(StringUtils.startsWithIgnoreCase(codigo, Gs1128Utils.PREFIX),
      "Código de barra inválido");
  }

}
