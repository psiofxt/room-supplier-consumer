import java.util.ArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class Room {
	
	int data;
	Supplier one;
	boolean isThere = false;
	Semaphore sema;
	Container first = new Container(), second = new Container();
	ArrayList<Container> containers = new ArrayList<>();
	Exchanger<Integer> ex = new Exchanger<Integer>();
	
	Room(){
		this.sema = new Semaphore(2);
		containers.add(first);
		containers.add(second);
	}
	
	public void supplierOccupy(Supplier s) throws InterruptedException{
		//sema.acquire();
		for(Container bucket : containers){
			if(bucket.isOccupied() == false){
				bucket.setData(s);
				break;
			}
		}
		//wait();
		//sema.release();
	}
	
	public void getCons(Consumer c) throws InterruptedException{
		//sema.acquire();
		for(Container bucket : containers){
			if(bucket.isOccupied() == true && bucket.getdataID() == c.consID){
				System.out.println("Consumer #" + c.consID + " attempting to take data from supplier #" + bucket.containerID);
				bucket.getData(c);
				//System.out.println(c.data);
				//notifyAll();
				break;
			}
		}
		//sema.release();
	}
	
	public void exchanging(Supplier s) throws InterruptedException{
		ex.exchange(s.data);
	}
	
}



