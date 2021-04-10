package edu.unipampa.es.rp2.marco1;

public class Contador {
  private String name;
  private int count;

  public Contador(String name) {
    this.name = name.substring(27);
    count = 0;
  }

  public String getName() {
    return name;
  }

  public int getCount() {
    return count;
  }

  public void increment() {
    count++;
  }

  public void reset() {
    count = 0;
  }
}
