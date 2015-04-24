package example02;

import support.NullPrintStream;
import support.Output;
import support.RunWith;

@RunWith({
  "-XX:-TieredCompilation", "-XX:+PrintCompilation",
  "-XX:-BackgroundCompilation",
  "-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining"
})
@Output(highlight={
  "DirectInlining::hotMethod",
  "DirectInlining::square"
})
public class DirectInlining {
  public static void main(String[] args)
    throws InterruptedException
  {
    System.setOut(new NullPrintStream());
    for ( int i = 0; i < 20_000; ++i ) {
      hotMethod();
    }
    
    // No need to sleep with -XX:-BackgroundCompilation
    // Thread.sleep(5_000);
  }

  public static void hotMethod() {
    System.out.println(square(7));
    System.out.println(square(9));
  }

  static int square(int x) {
    return x * x;
  }
}