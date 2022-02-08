package multithreading.executorService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	/**
	 * Permet de retourner le nombre de coeurs/processeur pour l'execution des threads.
	 * Le nombre de coeurs d�termine le nombre de threads optimal � cr�er
	 * @return
	 */
	int getNumberOfProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	public void launchTest1() {
		ExecutorService executorService = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

		Future future = executorService.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("debut tache " + Thread.currentThread().getName());
		        try {
		        	Thread.sleep(10000);
		        } catch (InterruptedException e) {
		          e.printStackTrace();
		        }
		        System.out.println("fin tache");
		      }
		    });
		
		System.out.println("Autre traitement");
		
		try {
			System.out.println("resultat=" + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		executorService.shutdown();
		System.out.println("Fin thread principal");
	}
	
	public void launchTest2() throws InterruptedException {
		ExecutorService executorService = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Runnable() {
				@Override
		        public void run() {
		        	System.out.println("debut tache " + Thread.currentThread().getName());
		        	try {
		        		Thread.sleep(1000);
		        	} catch (InterruptedException e) {
		        		e.printStackTrace();
		        	}
		        	System.out.println("fin tache");
				}
			});
		}
		
		System.out.println("Autre traitement");

		executorService.shutdown();
		executorService.awaitTermination(300, TimeUnit.SECONDS);

		System.out.println("Fin thread principal");
	}
	
	public void launchTest3() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		 
	    executorService.execute(new Runnable() {
	      public void run() {
	        System.out.println("debut tache");
	        try {
	          Thread.sleep(10000);
	        } catch (InterruptedException e) {
	          e.printStackTrace();
	        }
	        System.out.println("fin tache");
	      }
	    });
	 
	    executorService.shutdown();
	 
	    System.out.println("Fin thread principal");
	}
	
	public void launchTest4() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		 
	    Future future = executorService.submit(new Runnable() {
	      @Override
	      public void run() {
	        System.out.println("debut tache " + Thread.currentThread().getName());
	        try {
	          Thread.sleep(10000);
	        } catch (InterruptedException e) {
	          e.printStackTrace();
	        }
	        System.out.println("fin tache");
	      }
	    });
	 
	    System.out.println("Autre traitement");
	 
	    try {
	      System.out.println("resultat=" + future.get());
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    } catch (ExecutionException e) {
	      e.printStackTrace();
	    }
	 
	    executorService.shutdown();
	 
	    System.out.println("Fin thread principal");
	}
	
	public void launchTest5() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		 
	    Future<String> future2 = executorService.submit(new Callable<String>() {
	      public String call() throws Exception {
	        int i = 0;
	        System.out.println("debut tache 1");
	        System.out.println(Thread.currentThread().getName());
	        while (i < 10 && !Thread.currentThread().isInterrupted()) {
	          Thread.sleep(1000);
	          i++;
	        }
	        System.out.println("fin tache 1");
	        return "Tache 1";
	      }
	    });

	    Future<String> future1 = executorService.submit(new Callable<String>() {
	      public String call() throws Exception {
	        int i = 0;
	        System.out.println("debut tache 2 ");
	        System.out.println(Thread.currentThread().getName());
	        while (i < 10 && !Thread.currentThread().isInterrupted()) {
	          Thread.sleep(500);
	          i++;
	        }
	        System.out.println("fin tache 2");
	        return "Tache 2";
	      }
	    });
	 
	    executorService.shutdown();
	 
	    try {
	      executorService.awaitTermination(1, TimeUnit.HOURS);
	      System.out.println("result1 = " + future1.get());
	      System.out.println("result2 = " + future2.get());
	    } catch (InterruptedException ie) {
	      ie.printStackTrace();
	    } catch (ExecutionException ee) {
	      ee.printStackTrace();
	    }
	}
	
	public void launchTest6() {
	    ExecutorService executorService = Executors.newFixedThreadPool(3);
	    
	    Set<Callable<String>> callables = new HashSet<Callable<String>>();
	 
	    callables.add(new Callable<String>() {
	      public String call() throws Exception {
	        int i = 0;
	        System.out.println("debut tache 1");
	        while (i < 100 && !Thread.currentThread().isInterrupted()) {
	          Thread.sleep(10000);
	          i++;
	        }
	        System.out.println("fin tache 1");
	        return "Tache 1";
	      }
	    });

	    callables.add(new Callable<String>() {
	      public String call() throws Exception {
	        int i = 0;
	        System.out.println("debut tache 2 ");
	        while (i < 50 && !Thread.currentThread().isInterrupted()) {
	          Thread.sleep(10);
	          i++;
	        }
	        System.out.println("fin tache 2");
	        return "Tache 2";
	      }
	    });

	    callables.add(new Callable<String>() {
	      public String call() throws Exception {
	        int i = 0;
	        System.out.println("debut tache 3 ");
	        while (i < 200 && !Thread.currentThread().isInterrupted()) {
	          Thread.sleep(100);
	          i++;
	        }
	        System.out.println("fin tache 3");
	        return "Tache 3";
	      }
	    });
	 
	    try {
	      String result = executorService.invokeAny(callables);
	      System.out.println("result = " + result);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    } catch (ExecutionException e) {
	      e.printStackTrace();
	    }
	    executorService.shutdown();
	}
	
	public void launchTest7() {
	    ExecutorService executorService = Executors.newFixedThreadPool(2);
	    
	    Set<Callable<String>> callables = new HashSet<Callable<String>>();
	 
	    callables.add(new Callable<String>() {
	      public String call() throws Exception {
	        int i = 0;
	        System.out.println("debut tache 1");
	        while (i < 10 && !Thread.currentThread().isInterrupted()) {
	          Thread.sleep(100);
	          i++;
	        }
	        System.out.println("fin tache 1");
	        return "Tache 1";
	      }
	    });

	    callables.add(new Callable<String>() {
	      public String call() throws Exception {
	        int i = 0;
	        System.out.println("debut tache 2 ");
	        while (i < 10 && !Thread.currentThread().isInterrupted()) {
	          Thread.sleep(500);
	          i++;
	        }
	        System.out.println("fin tache 2");
	        return "Tache 2";
	      }
	    });
	 
	    try {
	      List<Future<String>> futures = executorService.invokeAll(callables);
	 
	      executorService.shutdown();
	 
	      executorService.awaitTermination(1, TimeUnit.HOURS);
	 
	      for (Future<String> future : futures) {
	        System.out.println("resultat = " + future.get());
	      }
	    } catch (InterruptedException ie) {
	      ie.printStackTrace();
	    } catch (ExecutionException ee) {
	      ee.printStackTrace();
	    }
	}
	
	public void launchTest8() {
		/*
		 La m�thode awaitTermination() permet d'attendre de mani�re bloquante la fin de l'ex�cution de toutes les t�ches soumises.
		 Les threads du pool ne sont pas des d�mons : si la m�thode shutdown() n'est pas invoqu�e alors la JVM continuera ind�finiment 
		 de s'ex�cuter m�me si les traitements du thread principal sont termin�s.
		*/
		
		/*
		 La m�thode shutdownNow() va tenter de stopper l'ex�cution des t�ches en cours et va retirer les t�ches dont l'ex�cution n'est pas encore d�marr�e. 
		 Aucune garantie n'est offerte concernant les t�ches qui sont stopp�es : elles peuvent �tre interrompues ou leur ex�cution peut se poursuivre jusqu'� sa fin.
		 */
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		 
	    Future<String> future = executorService.submit(new Callable<String>() {
	      public String call() throws Exception {
	        System.out.println("debut tache");
	        Thread.sleep(1000);
	        System.out.println("fin tache");
	        return "Tache";
	      }
	    });
	 
	    try {
	      System.out.println("resultat = " + future.get());
	    } catch (InterruptedException ie) {
	      ie.printStackTrace();
	    } catch (ExecutionException ee) {
	      ee.printStackTrace();
	    }
	    System.out.println("Fin thread principal");
	}
}
