package multithreading.completionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCompletionService_ExecutorServiceLimits {

	private static final int NB_TACHES = Runtime.getRuntime().availableProcessors();
	
	public static void main(String[] args) {
		/*
		 L'inconv�nient de cette solution est qu'elle r�cup�re les r�sultats dans l'ordre dans lequel les t�ches sont soumises. 
		 Ainsi, si le temps d'ex�cution de la premi�re t�che est le plus long, 
		 il faudra attendre sa fin avant de commencer � obtenir un r�sultat m�me si toutes les autres t�ches sont termin�es.	 
		 */
		ExecutorService executor = Executors.newFixedThreadPool(NB_TACHES);
	    List<Future<Integer>> futures = new ArrayList<Future<Integer>>(NB_TACHES);
	    
	    for (int i = 0; i < NB_TACHES; i++) {
	      futures.add(executor.submit(new MaTache(NB_TACHES - i)));
	    }
	    
	    for (Future<Integer> future : futures) {
	    	Integer resultat;
	    	try {
	    		resultat = future.get();
	    		System.out.println("resultat = " + resultat);
	    	} catch (InterruptedException e) {
	    		e.printStackTrace();
	    	} catch (ExecutionException e) {
	    		e.printStackTrace();
	    	}
	    }
	    executor.shutdown();
	}
}
