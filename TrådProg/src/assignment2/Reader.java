package assignment2;

public class Reader extends Thread{

	private CharacterBuffer charBuffer;
	volatile private boolean running = true;
	private char newChar;
	private String newString = "";
	private GUIMutex gui;
	private boolean newTrans = true;
	
	public Reader(CharacterBuffer charBuffer, GUIMutex gui){
		this.charBuffer = charBuffer;
		this.gui = gui;
	}
	
	public void createString(char character){
		newString += character;
		gui.addReadText(newString);
	}
	
	
	public void run(){
		
		
		while(running){
			newChar = charBuffer.getChar();
			if(newChar != '\u0000'){
				createString(newChar);
				newTrans = false;
			}else if(newString.length() > gui.getTransLength()){
				newString = "";
			}else if((newString.length() == gui.getTransLength()) && !newTrans){
				newString = "";
				gui.transDone();
				newTrans = true;
			}
			
		}
	}
	
}
