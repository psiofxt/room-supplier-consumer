public class Container {
	public int containerID;
	boolean occupied = false;
	static int containerNum = 1;
	int number;
	int contData;
	
	public Container(){
		this.number = containerNum++;
	}
	
	public synchronized void setData(Supplier s) throws InterruptedException{
		contData = s.produce();
		containerID = s.supID;
		occupied = true;
		System.out.println("Supplier #" + s.supID + " set data in container #" + number);
		s.ex.exchange(contData);
	}
	
	public synchronized void getData(Consumer c) throws InterruptedException{
		c.data = c.ex.exchange(contData);
		//System.out.println("Both Consumers have arrived.");
		System.out.println("Consumer #" + c.consID + " retrieved data value " + c.data + " from the container #" + number + " originating from Supplier #" + containerID);
		occupied = false;
	}
	
	public boolean isOccupied(){
		return occupied;
	}
	
	public int getdataID(){
		return containerID;
	}
	
}




