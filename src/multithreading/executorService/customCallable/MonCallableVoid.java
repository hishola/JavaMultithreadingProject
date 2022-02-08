package multithreading.executorService.customCallable;

import java.util.concurrent.Callable;

public class MonCallableVoid  implements Callable<Void> {
	@Override
	public Void call() throws Exception {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new Exception("Thread interrompu", e);
	    }
		return null;
	}
}