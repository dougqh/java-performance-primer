package example03.support;

import java.util.function.Consumer;

public class Summation implements Consumer<Integer> {
  private int result = 0;

  @Override
  public void accept(Integer x) {
	result += x;
  }
	
  public final int result() {
	return result;
  }
}