package multithreading.threadLocal;

public class TestInheritableThreadLocal2 {
	  public static void main(String[] args) throws InterruptedException {
		
		//Il peut �tre utile d'avoir par d�faut les valeurs du thread parent : c'est le r�le de la classe InheritableThreadLocal.
	    final ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();
	    
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