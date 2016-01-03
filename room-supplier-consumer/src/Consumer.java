import java.util.concurrent.Exchanger;

class Consumer implements Runnable {
		Room r;
		int consID;
		int data = 0;
		Exchanger<Integer> ex = new Exchanger<Integer>();
		
		Consumer(Room r, Exchanger<Integer> e, int id){
			this.r = r;
			this.consID = id;
			ex = e;
			new Thread(this, "consumer #" + consID).start();
		}
		
		public void run(){
				try{
					long sleeper = (long) (Math.random() * 1000);
					Thread.sleep(sleeper);
					r.getCons(this); 
					System.out.println("Consumer #" + consID + " sleeping for " + sleeper);
					Thread.sleep(sleeper);
				}
				catch(InterruptedException e){		
			}
			run();	
		}	
	}