package arcturus;

import java.util.HashMap;

//@quartata: If you revert, have a complaint, please comment here
public class Actions {
    // dictionary of operations, char being symbol
	public static final HashMap<char, Action> actions = new HashMap<char, Action>(){{
	    //WARNING: codePointer++ <- directly modifying a property is never a good practice in OOP, unless you have a very good reason.
	    put('<', new Action() {
			@Override
			public void operate(Arcturus arc) {
				arc.robot.absoluteMove(-1);
				arc.codePointer++;
			}
	    };
		put('>', new Action() {
			@Override
			public void operate(Arcturus arc) {
				arc.robot.absoluteMove(1);
				arc.codePointer++;
			}
		});
		//TODO: DRY
		put('+', new Action() {
			@Override
			public void operate(Arcturus arc) {
			    Value left = arc.robot.getLeft();
			    Value right = arc.robot.getRight();
			    Value value = Operations.operate(left, right, OperationType.ADD);
				//arc.tape.setPos(value);
				arc.codePointer++;
			}
		});
		put('-', new Action() {
			@Override
			public void operate(Arcturus arc) {
			    Value left = arc.robot.getLeft();
			    Value right = arc.robot.getRight();
			    Value value = Operations.operate(left, right, OperationType.SUBTRACT);
				//arc.tape.setPos(value);
				arc.codePointer++;
			}
		});
		put('*', new Action() {
			@Override
			public void operate(Arcturus arc) {
			    Value left = arc.robot.getLeft();
			    Value right = arc.robot.getRight();
			    Value value = Operations.operate(left, right, OperationType.MULTIPLY);
				//arc.tape.setPos(value);
				arc.codePointer++;
			}
		});
		put('/', new Action() {
			@Override
			public void operate(Arcturus arc) {
			    Value left = arc.robot.getLeft();
			    Value right = arc.robot.getRight();
			    Value value = Operations.operate(left, right, OperationType.DIVIDE);
				//arc.tape.setPos(value);
				arc.codePointer++;
			}
		});
	}};
}

interface Action {
	void act(Arcturus arc);
}