package assignment2;

public class Main {

	
	public static void main(String[] args){
		CharacterBuffer charBuffer = new CharacterBuffer();
		Writer writer = new Writer(charBuffer);
		GUIMutex gui = new GUIMutex(writer, charBuffer);
		Reader reader = new Reader(charBuffer, gui);
		gui.Start();
		reader.start();
		writer.start();
	}
}
