package multithreading.executorService.customCallable;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestCallableThreadInterruptionHandled {

	public static void main(String[] args) {
		
		System.out.println("Number of cores : "+Runtime.getRuntime().availableProcessors());
		
		BigInteger resultat;
		ExecutorService executor = Executors.newSingleThreadExecutor();
	    Future<BigInteger> result = executor.submit(new Callable<BigInteger>() {
	      @Override
	      public BigInteger call() throws Exception {
	    	BigInteger compteur = BigInteger.ZERO;
	        while (compteur.compareTo(BigInteger.valueOf(100000000)) < 0  && (!Thread.currentThread().isInterrupted()) ) {
	          compteur = compteur.add(BigInteger.ONE);
	          //System.out.println("compteur=" + compteur);
	        }
	        System.out.println("compteur=" + compteur + " "
	            + Thread.currentThread().isInterrupted());
	        return compteur;
	      }
	    });
	    executor.shutdown();
	    
	    long debut = System.currentTimeMillis();
	    try {
	      resultat = result.get(50L, TimeUnit.MILLISECONDS);
	      System.out.printf("Resultat %d (%d ms)%n", resultat,
	          System.currentTimeMillis() - debut);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    } catch (ExecutionException e) {
	      e.printStackTrace();
	    } catch (TimeoutException e) {
	      System.out
	          .printf("Timeout (%d ms)%n", System.currentTimeMillis() - debut);
	      result.cancel(true);
	      System.out.printf("Execution terminee %b%n", result.isDone());
	    }
	}

}
