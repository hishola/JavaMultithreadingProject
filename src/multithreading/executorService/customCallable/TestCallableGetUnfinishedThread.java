package multithreading.executorService.customCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestCallableGetUnfinishedThread {

	public static void main(String[] args) {
		Integer resultat;
	    ExecutorService executor = Executors.newSingleThreadExecutor();
	    Future<Integer> result = executor.submit(new Callable<Integer>() {
	      @Override
	      public Integer call() throws Exception {
	        try {
	          Thread.sleep(10000);
	        } catch (InterruptedException e) {
	          e.printStackTrace();
	        }
	        return Integer.valueOf(10);
	      }
	    });
	    executor.shutdown();
	    
	    long debut = System.currentTimeMillis();
	    try {
	      resultat = result.get(500L, TimeUnit.MILLISECONDS);
	      System.out.printf("Resultat %d (%d ms)%n", resultat,
	          System.currentTimeMillis() - debut);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    } catch (ExecutionException e) {
	      e.printStackTrace();
	    } catch (TimeoutException e) {
	      System.out
	          .printf("Timeout (%d ms)%n", System.currentTimeMillis() - debut);
	      System.out.printf("Execution terminee %b%n", result.isDone());
	    }
	}

}
