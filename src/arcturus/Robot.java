package arcturus;

import java.util.ArrayList;

public class Robot {
	private int position;
	private int startPosition;
	private Side facing; //popdir
	private Tape tape;
	private Map<int, int> positions; //TODO: change to Stack if want to remove
	private Map<int, Value> registers;
	private boolean record;
	
	public Robot(int start, Tape tape) {
		this(start, tape, Side.RIGHT);
	}

	public Robot(int start, Tape tape, Side facing) {
		this.startPosition = start;
		this.position = start;
		this.facing = facing;
		this.tape = tape;
	}

	public void face(Side side) {
		this.facing = side;
	}
	
	public void printAll() {
		this.record = false;
	}
	
	public void printEnd() {
		this.record = true;
	}
	
	public void togglePrint() {
		this.record = !this.record;
	}

	void absoluteMove(int steps) {
		int oldPosition = this.position;
		this.position = this.tape.calculatePosition(this.position + steps);
		if (this.record) {
			this.tape.print(oldPosition, this.position);
		}
		else {
			this.tape.print(this.position);
		}
	}

	public void move() {
		this.move(1);
	}
	
	public void move(int steps) { //named Side so Karel's Direction still works
		this.absoluteMove(this.facing.multiplier() * steps);
	}
	
	public void goHome() {
		this.move(this.startPosition);
	}
	
	public void swap() {
		Value temporary = this.tape.getValue(this.position + this.facing.multiplier());
		this.tape.setValue(this.position + this.facing.multiplier(), this.tape.getValue(this.position));
		this.tape.setValue(this.position, temporary);
	}

	public void rotate(int number, int shiftBy) {
		int multiplier = this.facing.multiplier();
		int position =  this.position + multiplier * number;
		ArrayList<Value> old = this.tape.getValues(position, number);
		for (int i = 0; i < old.length(); i++) {
			this.tape.setValue(position + (i + number + multiplier * shiftBy) % number, old[i]);
		}
	}
	
	//TODO: rotate
	
	public void duplicate() {
		this.tape.setValue(this.position + this.facing.multiplier(), this.tape.getValue(this.position));
	}
	
	public void saveLocation(int index, int position) {
		this.positions.put(index, position);
	}
	
	public void gotoLocation(int index) {
		this.position = this.positions[index];
	}
	
	public void copy(int index) {
		this.registers.put(index, this.tape.getValue(this.position));
	}
	
	public void reg(int index) {
		this.tape.setValue(this.position, this.register[index]);
	}

	public void getLeft() {
		switch (this.facing) {
			case Side.LEFT:
				return this.tape.getValue(this.position - 1);
			case Side.RIGHT:
				return this.tape.getValue(this.position);
		}
	}

	public void getRight() {
		switch (this.facing) {
			case Side.LEFT:
				return this.tape.getValue(this.position);
			case Side.RIGHT:
				return this.tape.getValue(this.position + 1);
		}
	}

	public void showOnTape() {
		int i = -origin; //TODO: check
		for (String value: tape) {
			System.out.print(i);
			if (i != this.position) {
				System.out.print("   ");
			}
			else {
				System.out.print(" R ");
			}
			System.out.println(value);
			i++;
		}
	}
}