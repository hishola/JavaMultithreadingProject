package multithreading.completionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCompletionService {

	private static final int NB_TACHES = Runtime.getRuntime().availableProcessors();
	
	public static void main(String[] args) {
		/*
		 Pour faciliter la gestion de l'attente et de l'obtention des r�sultats de plusieurs t�ches ex�cut�es par un ExecutorService, l'API propose l'interface CompletionService.
		 Une instance de type CompletionService permet l'ex�cution de t�ches asynchrones et surtout facilite la r�cup�ration de leurs r�sultats au fur et � mesure de l'ach�vement de ces t�ches.
		 */
		ExecutorService executor = Executors.newFixedThreadPool(NB_TACHES);
	    CompletionService<Integer> completion = new ExecutorCompletionService<Integer>(executor);
	    for (int i = 0; i < NB_TACHES; i++) {
	      completion.submit(new MaTache(NB_TACHES - i));
	    }
	    for (int i = 0; i < NB_TACHES; i++) {
	      Integer resultat;
	      try {
	        resultat = completion.take().get();
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
