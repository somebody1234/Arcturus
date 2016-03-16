//This is the main class where the program will be run.
//Created 03/13/16 by Trevor B. (Stack Exchange user Eridan)
//Last updated 03/15/16

package arcturus;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Map;
import kareltherobot.Directions.Direction;

//TODO: test everything
public class Arcturus extends LetterRobot {
	public Arcturus(String name, int avenue, int street, Direction direction, int beepers) {
		super(name, avenue, street, direction, beepers);
	}
	
	public static void main(String[] args) {
		//temporary
		ArrayList<String> code = new ArrayList<String>();
		for (String line : Files.readAllLines(Paths.get(args[0]))) {
		    code.add(line);
		}
		
		Arcturus bot = new Arcturus();
		Pattern rDouble = Pattern.compile("[\d,]+\.\d+");
		Pattern rInteger = Pattern.compile("[\d,]+");
		Scanner stdin = new Scanner(System.in);
		String program = stdin.nextLine();
		Type inputType = null; //TODO
		String input = stdin.nextLine(); //TODO: read only if there is input
		
		if(input.charAt(0) == '"') {
			inputType = Type.STRING;
		}
		else if(input.charAt(0) == '[') {
			inputType = Type.ARRAY; //TODO: differentiate between element type
		}
		else if(rDouble.Matcher(input).Matches()) {
			inputType = Type.DOUBLE;
		}
		else if(rInteger.Matcher(input).Matches()) {
			inputType = Type.INTEGER;
		}
		
		
		currentType = inputType;
		
		//Interpreting input
		
		if(currentType == Type.STRING) {
			for (char character: input) {
				bot.putBeeper((int) character);
			}
			/*for(int characterIndex = 0; characterIndex < input.length(); characterIndex++) {
				char currentCharacter = input.charAt(characterIndex);
				int ASCIIValue = (int) currentCharacter;
				bot.putBeeper(ASCIIValue); //can't make static reference to non-static method???
			}*/
		}
		if(currentType == Type.ARRAY) {
		}
		if(currentType == Type.DOUBLE) {
			//double d = Double.parseDouble(input);
		}
		if(currentType == Type.INTEGER) {
			//int i = Integer.parseInt(input);
		}
		//when double, set variable "a" equal double and give separate row.
		
		//commands have different functions depending on currentType, so can have 255*4 possible builtins
		
		//printing
	}
}