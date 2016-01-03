import java.util.concurrent.Exchanger;

public class Controller {

	public static void main(String[] args) throws InterruptedException{
		Room r1 = new Room();
		Exchanger<Integer> ex = new Exchanger<Integer>();
		
		for (int i = 0; i < 10; i ++){
			new Supplier(r1, ex,  i);
			new Consumer(r1, ex,  i);
		}
	}  
}
