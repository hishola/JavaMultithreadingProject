package multithreading.threadLocal;

public class TestInheritableThreadLocal {
	  public static void main(String[] args) throws InterruptedException {
		 
	    final ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	    
	    threadLocal.set("valeur1");
	    afficherValeur(threadLocal);
	    Thread t = new Thread() {
	      public void run() {
	        afficherValeur(threadLocal);
	        threadLocal.set("valeur2");
	        afficherValeur(threadLocal);
	      }
	    };
	    t.start();
	    t.join();
	    afficherValeur(threadLocal);
	  }
	  
	  private static void afficherValeur(final ThreadLocal<String> threadLocal) {
	    System.out.println(Thread.currentThread().getName() + " : "
	        + threadLocal.get());
	  }
	}