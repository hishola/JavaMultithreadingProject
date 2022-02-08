package multithreading.executorService.customCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable {

	public static void main(String[] args) {
		Integer resultat;
	    ExecutorService executor = Executors.newSingleThreadExecutor();
	    Future<Integer> result = executor.submit(new Callable<Integer>() {
	      @Override
	      public Integer call() throws Exception {
	        try {
	          Thread.sleep(1000);
	        } catch (InterruptedException e) {
	          e.printStackTrace();
	        }
	        return Integer.valueOf(10);
	      }
	    });
	    executor.shutdown();
	    
	    System.out.println("debut");
	    long debut = System.currentTimeMillis();
	    try {
	      resultat = result.get();
	      System.out.println("fin");
	      System.out.printf("Resultat %d (%d ms)%n", resultat,
	          System.currentTimeMillis() - debut);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    } catch (ExecutionException e) {
	      e.printStackTrace();
	    }
	}

}
