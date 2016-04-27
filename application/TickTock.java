package application;

public class TickTock {
	String state;
	 synchronized void tick(boolean running) {
		 if(!running){
			 state = "ticked";
			 notify();
			 return;
		 }
		 System.out.println("Tick ");
		 
		 state = "ticked";
		 notify();
		 try {
			 while(!state.equals("tocked")) {
				 wait();
			 }
		 }
		 catch(InterruptedException exc){
			 System.out.println("Thead interrupted");
		 }
	 }
	 synchronized void tock(boolean running) {
		 if(!running){
			 state = "tocked";
			 notify();
			 return;
		 }
		 System.out.println("Tock");
		 
		 state = "tocked";
		 notify();
		 try {
			 while(!state.equals("ticked")) {
				 wait();
			 }
		 }
		 catch(InterruptedException exc){
			 System.out.println("Thead interrupted");
		 }
	 }
	 
}
class MyThread1 implements Runnable{

	Thread thrd;	
	TickTock tk;
	
	MyThread1(String name, TickTock tt){
		thrd = new Thread(this, name);
		tk = tt;
		thrd.start();
	}
	
	public void run() {
		if(thrd.getName().compareTo("Tick")== 0){
			for (int i=0; i<5; i++) {
				tk.tick(true);
			}
			tk.tick(false);
		}	else{  
			for (int i=0; i<5; i++) {
				tk.tock(true);
			}
			tk.tock(false);
		}
	}
}

class ThreadCom{
	public static void main(String args[]) {
		TickTock tt = new TickTock();
		MyThread1 mt1 = new MyThread1("Tick", tt);
		MyThread1 mt2 = new MyThread1("Tock", tt);
		try{
			mt1.thrd.join();
			mt2.thrd.join();
		}
		catch(InterruptedException exc) {
			System.out.println("Main Thread interrupted.");
		}
	}
}