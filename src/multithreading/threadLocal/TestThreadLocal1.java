package multithreading.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 Si le thread est dans un pool, le ThreadLocal n'est pas récupéré par le ramasse-miettes puisque le thread ne se termine pas à la fin de l'exécution des traitements.
 */
public class TestThreadLocal1 {
  private static final MonCompteurTL monContextTL = new MonCompteurTL();
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(new Runnable() {
      public void run() {
        System.out.println(monContextTL.get());
      }
    });
    executerGC();
    executor.execute(new Runnable() {
      public void run() {
        System.out.println(monContextTL.get());
      }
    });
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