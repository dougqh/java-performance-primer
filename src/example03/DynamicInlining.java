package example03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import support.Output;
import support.RunWith;
import example03.support.Summation;

@RunWith({
  "-XX:-TieredCompilation", "-XX:+PrintCompilation",
  "-XX:-BackgroundCompilation",
  "-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining"
})
@Output(highlight={
  "ArrayList::forEach",
  "Summation::accept",
  "TypeProfile",
  "Summation"
})
public class DynamicInlining {
  static int blackhole;
  
  public static void main(final String[] args)
  	throws InterruptedException
  {
	List<Integer> ints = randomList(100);
  
	for ( int i = 0; i < 20_000; ++i ) {
	  Summation summation = new Summation();
	  ints.forEach(summation);
	  
	  blackhole = summation.result();
	}
	
    // No need to sleep with -XX:-BackgroundCompilation
    // Thread.sleep(5_000);
  }
  
  private static List<Integer> randomList(final int size) {
	ThreadLocalRandom random = ThreadLocalRandom.current();
		
	ArrayList<Integer> ints = new ArrayList<>(size);
	for ( int i = 0; i < size; ++i ) {
	  ints.add(random.nextInt());
	}
	return ints;
  }
}
