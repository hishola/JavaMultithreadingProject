package multithreading.threadLocal;

public class TestInheritableThreadLocal3 {
	public static void main(String[] args) throws InterruptedException {
		
		/*Attention : lors de l'utilisation d'un InheritableThreadLocal, 
		il ne faut stocker que des objets immuables ou thread-safe car ces objets sont partagés entre le thread parent et ses threads fils.
		*/
		
		final ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>() {
			@Override
			protected String childValue(String parentValue) {
				return parentValue + " fils";
			}
		};
	    threadLocal.set("valeur");
	    afficherValeur(threadLocal);
	    Thread t = new Thread() {
	    	public void run() {
	    		afficherValeur(threadLocal);
	    	}
	    };
	    t.start();
	    t.join();
	    
	    threadLocal.set("valeur2");
	    Thread t2 = new Thread() {
	    	public void run() {
	    		afficherValeur(threadLocal);
	    	}
	    };
	    t2.start();
	    t2.join();
	}
  
  private static void afficherValeur(final ThreadLocal<String> threadLocal) {
    System.out.println(Thread.currentThread().getName() + " : "
        + threadLocal.get());
  }
}