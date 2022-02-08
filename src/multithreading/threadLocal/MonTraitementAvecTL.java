package multithreading.threadLocal;

public class MonTraitementAvecTL implements Runnable {

	private static ThreadLocal<String> monThreadLocal = new ThreadLocal<String>() {
		protected String initialValue(){
			return "initialValue";
		}
	};	
	
	public void run() {
		System.out.println("Mon traitement " + Thread.currentThread().getName()+ " monThreadLocal=" + monThreadLocal);
	   
	    try {
	    	monThreadLocal.set("Valeur pour " + Thread.currentThread().getName());
	    	Thread.sleep(1000);
	      	String maValeur = monThreadLocal.get();
	      	System.out.println("Valeur = " + maValeur);
	    } catch (InterruptedException e) {
	    	e.printStackTrace();
	    }finally {
			monThreadLocal.remove();
			System.out.println("Valeur = " + monThreadLocal.get());
		}
	  }
}
