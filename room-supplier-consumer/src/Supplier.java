import java.util.concurrent.Exchanger;

class Supplier implements Runnable {
		Room r;
		int supID;
		int data;
		Exchanger<Integer> ex = new Exchanger<Integer>();
		
		Supplier(Room r, Exchanger<Integer> e, int id){
			this.r = r;
			this.supID = id;
			ex = e;
			new Thread(this, "supplier #" + supID).start();
		}
		
		public int produce(){
			double data1 = Math.random() * 10000;
			int data = (int) (data1/1);
			return data;
		}
		
		public void run(){
			try{
				long sleeper = (long) (Math.random() * 1000);
					this.data = produce();
					Thread.sleep(sleeper);
					r.supplierOccupy(this);
					System.out.println("Supplier #" + supID + " sleeping for " + sleeper);
					Thread.sleep(sleeper);	
			}
			catch(InterruptedException e){	
			}
			run();
		}
	}