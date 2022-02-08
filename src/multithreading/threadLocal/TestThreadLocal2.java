package multithreading.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadLocal2 {
  private static final MonCompteurTL monContextTL = new MonCompteurTL();
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(new Runnable() {
      public void run() {
    	  monContextTL.incrementer();
    	  System.out.println(monContextTL.get());
      }
    });
    executerGC();
    executor.execute(new Runnable() {
      public void run() {
    	monContextTL.retirer();
        System.out.println(monContextTL.get());
      }
    });
    executor.shutdown();
    executerGC();
    System.out.println("Fin");
  }
  
  private static void executerGC() throws InterruptedException {
    Thread.sleep(1000);
    System.gc();
    Thread.sleep(1000);
    System.gc();
    Thread.sleep(1000);
    System.gc();
  }
}