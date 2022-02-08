package multithreading.threadLocal;

public class TestThreadLocal {

	public static void main(String[] args) {
		MonTraitementAvecTL monTraitementAvecTL = new MonTraitementAvecTL();
	    Thread thread1 = new Thread(monTraitementAvecTL);
	    Thread thread2 = new Thread(monTraitementAvecTL);
	    
	    Thread thread3 = new Thread(new MonTraitementAvecTL());
	    Thread thread4 = new Thread(new MonTraitementAvecTL());
	    
	    thread1.start();
	    thread2.start();
	    
	    thread3.start();
	    thread4.start();
	    
	}

}
