package multithreading.executorService;

public class ExecutorServiceTest {

	public static void main(String[] args) {
		ThreadPoolExecutorTest threadPool = new ThreadPoolExecutorTest();
		
		//threadPool.launchTest1();
		
		/*try {
			threadPool.launchTest2();
		} catch (InterruptedException e) {
			System.err.println(e);
			e.printStackTrace();
		}*/
		
		//threadPool.launchTest3();
		//threadPool.launchTest4();
		threadPool.launchTest5();
		//threadPool.launchTest6();
		//threadPool.launchTest7();
		//threadPool.launchTest8();
	}

}
