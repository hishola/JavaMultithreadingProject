package multithreading.threadLocal;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			String value = "test";
			@Override
			public void run() {
				System.out.println("hello World");
				System.out.println("Current Thread name : "+Thread.currentThread().getName());
				System.out.println("Value = "+value);
			}
		}).start();
		
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("Current Thread name : "+Thread.currentThread().getName());
	}

}
