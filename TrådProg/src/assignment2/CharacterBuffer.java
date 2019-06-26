package assignment2;

public class CharacterBuffer {

	private char[] charBuffer = new char[1];
	private boolean newChar = false;
	
	private boolean async = false;
	
	
	public boolean setChar(char character){
		
		if(async){
			charBuffer[0] = character;
			return true;
		}else{
			
			if(!newChar){
				charBuffer[0] = character;
				newChar = true;
				System.out.println("new character set!");
				return true;
			}
		}
		
		
		return false;
	}
	
	public boolean isEmpty(){
		if(charBuffer[0] == '\u0000'){
			return true;
		} else{
			return false;
		}
	}
	
	public char getChar(){
		
		if(async){
			return charBuffer[0];
		}else{
			if(newChar){
				newChar = false;
				return charBuffer[0];
			} else{
				return '\u0000';
			}
		}		
	}	
}
