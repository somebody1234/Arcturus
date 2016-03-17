//This is the main class where the program will be run.
//Created 03/13/16 by Trevor B. (Stack Exchange user Eridan)
//Last updated 03/15/16

package arcturus;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


//TODO: test everything
public class Arcturus {
	
	public Tape tape = new Tape(256, 128);
	public Robot robot = new Robot(0);
	public String code;
	public Scanner input = new Scanner(System.in);
	public int codePointer = 0;
	
	public Arcturus(String code, String input) {
		this.code = code;
		//this.tape.setValue(input); // set origin cell to input
		//somebody: this is a bad idea, especially if you aim to support multiple inputs like many esolangs
	}
	
	public void execute() {
		while(codePointer < code.length()) { 
			Operations.operations.get(code.charAt(codePointer));
		}
		if(code.length() == 0) {
			double random = Math.random();
			if (random < 0.01) {
				System.out.println("ಠ_ಠ");
			}
			else if (random < 0.02) {
				System.out.println("Write a program, you fool ");
			}
			else {
				System.out.println("Because the people who are crazy enough to think they can change the world are the ones who do.");
			}
		}
	}

	public Value getInput() {
		String input = this.input.stdin.nextLine(); //TODO: parse type
		return new Value(input);
	}

	public static void main(String[] args) throws IOException {
		String program = new String(Files.readAllBytes(Paths.get(args[0])), "CP437"); // read code from file and interpret as CP437

		Arcturus arc = new Arcturus(program).execute(); // run
	}
}