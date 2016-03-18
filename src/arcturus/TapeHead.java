package arcturus;

import java.util.ArrayList;

/**
 * The direction in which the TapeHead points.
 * 
 * @see   TapeHead
 * @since 0.0
 */
enum Side {
	LEFT  (-1),
	RIGHT ( 1);

	private final int multiplier;

	Side(int multiplier) {
		this.multiplier = multiplier;
	}
	
	public int multiplier() { return multiplier; }
}

/**
 * The tape head for the Tape class.
 * 
 * @see   Tape
 */
public class TapeHead {
	private int position;
	private int initialPosition;
	private Side facing; //popdir
	private Tape tape;
	private HashMap<int, int> positions; //TODO: implement removing
	private HashMap<int, Value> registers;
	private boolean record;

	/**
	 * Creates a TapeHead with the specified initial position and Tape.
	 * 
	 * @param initial The initial position of the TapeHead.
	 * @param tape    The Tape the TapeHead is on.
	 * @see   Tape
	 */
	public TapeHead(int initial, Tape tape) {
		this(intial, tape, Side.LEFT);
		//@quartata Check the starting side
	}

	/**
	 * Creates a TapeHead with the specified initial position, Tape, 
	 * and direction.
	 * 
	 * @param initial The initial position of the TapeHead.
	 * @param tape    The Tape the TapeHead is on.
	 * @param facing  The direction in which the TapeHead initially points.
	 * @see   Tape
	 * @see   Side
	 */
	public TapeHead(int initial, Tape tape, Side facing) {
		this.initialPosition = initial;
		this.position = initial;
		this.tape = tape;
		this.facing = facing;
	}

	/**
	 * Makes the TapeHead face a certain direction.
	 * 
	 * @param side The desired direction in which the TapeHead will point.
	 * @see   Side
	 */
	public void face(Side side) {
		this.facing = side;
	}

	/**
	 * Sets the record mode of the TapeHead.
	 * 
	 * @param record Whether the TapeHead will record the values in all 
	 *               positions it travels over
	 */
	public void setRecord(boolean record) {
		this.record = record;
	}

	/**
	 * Toggles the record mode of the TapeHead.
	 */
	public void toggleRecord() {
		this.record = !this.record;
	}

	/**
	 * Moves the TapeHead a specified number of steps right.
	 * 
	 * @param steps The number of steps right to move the TapeHead.
	 */
	void absoluteMove(int steps) {
		int oldPosition = this.position;
		this.position = this.tape.calculatePosition(this.position + steps);
		if (this.record) {
			this.tape.print(oldPosition, this.position);
		} else {
			this.tape.print(this.position);
		}
	}

	/**
	 * Moves the TapeHead one unit forward.
	 */
	public void move() {
		this.move(1);
	}

	/**
	 * Moves the TapeHead a specified number of steps forward.
	 * 
	 * @param steps The number of steps to move forward.
	 */
	public void move(int steps) {
		this.absoluteMove(this.facing.multiplier() * steps);
	}

	/**
	 * Moves the TapeHead to its initial position.
	 */
	public void goHome() {
		this.move(this.initialPosition);
	}

	/**
	 * Swaps the Value in the cell the TapeHead is on 
	 * and the cell one step in front of it.
	 */
	public void swap() {
		Value temporary = this.tape.getValue(this.position + this.facing.multiplier());
		this.tape.setValue(this.position + this.facing.multiplier(), this.tape.getValue(this.position));
		this.tape.setValue(this.position, temporary);
	}

	/**
	 * Rotates a specified number of cells  specified number of cells right.
	 * 
	 * @param number  The number of cells to shift.
	 * @param shiftBy The number of cells to shift right by.
	 */
	public void rotate(int number, int shiftBy) {
		int multiplier = this.facing.multiplier();
		int position =  this.position + multiplier * number;
		ArrayList<Value> old = this.tape.getValues(position, number);
		for (int i = 0; i < old.length(); i++) {
			this.tape.setValue(position + (i + number + shiftBy) % number, old[i]);
		}
	}

	/**
	 * Duplicates the Value in the cell the TapeHead is on to the cell one unit 
	 * in front of the TapeHead.
	 */
	public void duplicate() {
		this.tape.setValue(this.position + this.facing.multiplier(), this.tape.getValue(this.position));
	}

	/**
	 * Saves the current location to the specified index of the 
	 * location register of the TapeHead.
	 * 
	 * @param index The index of the in which the location will be stored.
	 */
	public void saveLocation(int index) {
		this.positions.put(index, this.position);
	}
	
	/**
	 * Goes to the location stored in a specified index of the location 
	 * register of the TapeHead.
	 * 
	 * @param index The index in which the location will be obtained from.
	 */
	public void gotoLocation(int index) {
		this.position = this.positions[index];
	}

	/**
	 * Copies the Value in the current position into the location registers 
	 * of the TapeHead at a specified index.
	 * 
	 * @param index The index in whch to place the value.
	 */
	public void copy(int index) {
		this.registers.put(index, this.tape.getValue(this.position));
	}

	/**
	 * Copies the Value at a specified index in the location registers 
	 * to the cel at the current position.
	 */
	public void reg(int index) {
		this.tape.setValue(this.position, this.register.get(index));
	}

	//@quartata Maybe adapt for variadic function?
	//meaning no arguments = overload defaulting to 1
	/**
	 * Gets the value of the left operand of a dyadic function.
	 * 
	 * @returns Value of the left operand. This depends on the direction 
	 *          in which the TapeHead is facing.
	 */
	public Value getLeft() {
		int offset = 0;
		switch (this.facing) {
			case Side.LEFT:
				offset = -1;
				break;
			case Side.RIGHT: //just in case it is needed later
				break;
		}
		return this.tape.getValue(this.position + offset);
	}

	/**
	 * Gets the value of the right operand of a dyadic function.
	 * 
	 * @returns Value of the right operand. This depends on the direction 
	 *          in which the TapeHead is facing.
	 */
	public Value getRight() {
		int offset = 0;
		switch (this.facing) {
			case Side.LEFT:
				break;
			case Side.RIGHT:
				offset = 1;
				break;
		}
		return this.tape.getValue(this.position + offset;
	}

	/**
	 * Displays the entire tape and the position of every cell, 
	 * showing the TapeHead as a '>' character.
	 */
	public void showOnTape() {
		int i = -origin; //TODO: check
		for (String value: tape) {
			System.out.print(i);
			if (i != this.position) {
				System.out.print("   ");
			}
			else {
				System.out.print(" > ");
			}
			System.out.println(value);
			i++;
		}
	}
}