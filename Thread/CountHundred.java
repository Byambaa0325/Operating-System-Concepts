public class CountHundred {
	public static void main(String[] args){
		CountingThread object = new CountingThread();
		object.start();
	}
}
class CountingThread extends Thread{
	public static int COUNT_LIMIT = 100;
	public static int COUNT = 1;
	public void run(){
		try{
			System.out.println(COUNT++);
			if (COUNT<=100) {
				CountingThread object = new CountingThread();
				object.start();
			}
			else{
				COUNT = 1;
			}
		}catch (Exception e){
				System.out.println("Error");
		}
	}
}