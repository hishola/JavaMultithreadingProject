package multithreading.executorService.customCallable;

import java.util.concurrent.Callable;

public class MonCallableString implements Callable<String> {
 
	@Override
	public String call() throws Exception {
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e){
			throw new Exception("Thread interrompu",e);
		}
		return "test";
  }
}