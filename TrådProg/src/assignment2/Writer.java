package assignment2;

public class Writer extends Thread{

	
	private CharacterBuffer charBuffer;
	private String strToSend = "";
	volatile private boolean running = false;
	
	public Writer(CharacterBuffer charBuffer){
		this.charBuffer = charBuffer;
	}
	
	public void newString(String string){
		System.out.println("newString - Writer");
		strToSend = string;
	}
	
	public void startWrite(){
		System.out.println("startWrite - Writer");

		running = true;
	}
	
	public void stopWrite(){
		running = false;
	}
	
	public void run(){
		while(true){
			if(running){				
				if(strToSend != "" && charBuffer.isEmpty()){
					for(int i=0;i<strToSend.length();i++){
						while(!charBuffer.setChar(strToSend.charAt(i)));
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
					while(!charBuffer.setChar('\u0000'));
					running = false;
				}
			}
		}		
	}	
}
