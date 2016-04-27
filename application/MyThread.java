package application;

public class MyThread implements Runnable{
	Thread myThread;
	
	MyThread(String name){
		myThread = new Thread(this, name);
		myThread.start();
	}
	@Override
	public void run() {
		System.out.println(myThread.getName() + " starting");
		try {
			for (int count = 0; count < 10; count++){
				Thread.sleep(400);
				System.out.println("In " + myThread.getName() + ", count is " + count);
			}
		} 
		catch(InterruptedException exc){
			System.out.println(myThread.getName() + "interrupted");	
		}
		System.out.println(myThread.getName() + " terminated");
	}
}

class UseThreads {
	public static void main(String args[]){
		System.out.println("Main thread starting");
		MyThread mt1 = new MyThread("I am number 1");
		MyThread mt2 = new MyThread("I am number 2");
		MyThread mt3 = new MyThread("I am number 3");
		for (int i= 0; i < 50; i++) {
			System.out.println(".");
			try{
				Thread.sleep(100);
			}
			catch(InterruptedException exc){
				System.out.println("Main thread interrupted.");
			}
		}
		System.out.println("Main thread ending.");
	}
	
}
