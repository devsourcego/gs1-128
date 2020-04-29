package br.com.devsource.gs1;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @author guilherme.pacheco
 */
public final class Gs1128Engine {

  private final Set<AI> aisRegistred = new HashSet<>();

  public Gs1128Engine() {
    aisRegistred.addAll(EnumSet.allOf(AIs.class));
  }

  public void registerAi(AI ai) {
    aisRegistred.add(ai);
  }

  public Set<AI> getAisRegistred() {
    return Collections.unmodifiableSet(aisRegistred);
  }

  public Gs1128Encoder encoder() {
    return new Gs1128Encoder();
  }

  public Gs1128Decoder decoder() {
    return new Gs1128Decoder(aisRegistred);
  }

}
