package br.com.devsource.gs1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author guilherme.pacheco
 */
public final class Gs1128Engine {

  private Set<AI> aisRegistred;

  Gs1128Engine() {
    aisRegistred = new HashSet<>();
    aisRegistred.addAll(Arrays.asList(AIs.values()));
  }

  public void registerAi(AI ai) {
    aisRegistred.add(ai);
  }

  public void registerAi(String code, String title, String description, String format) {
    aisRegistred.add(AiFactory.create(code, title, description, format));
  }

  public List<AI> getAisRegistred() {
    return new ArrayList<>(aisRegistred);
  }

  public Gs1128Encoder encoder() {
    return new Gs1128Encoder();
  }

  public Gs1128Decoder decoder() {
    return new Gs1128Decoder(aisRegistred);
  }

}
