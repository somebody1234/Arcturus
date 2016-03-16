package arcturus;

import java.util.ArrayList;

public class Robot {
	private int position;
	private int startPosition;
	private Side facing; //popdir
	private Tape tape;
	private ArrayList<int> positions; //TODO: change to Stack if want to remove
	private ArrayList<Value> registers;
	private boolean record;
	
	public Robot(int start) {
		this.startPosition = start;
		this.position = start;
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
	
	public void move() {
		this.move(this.facing, 1);
	}
	
	public void move(int steps) { //named Side so Karel's Direction still works
		int oldPosition = this.position;
		this.position = this.tape.calculatePosition(this.position + this.facing.multiplier() * steps);
		if (this.record) {
			this.tape.print(oldPosition, this.position);
		}
		else {
			this.tape.print(this.position);
		}
	}
	
	public void goHome() {
		this.move(this.startPosition);
	}
	
	public void swap() {
		Value temporary = this.tape.getValue(this.position + this.facing.multiplier());
		this.tape.setValue(this.position + this.facing.multiplier(), this.tape.getValue(this.position));
		this.tape.setValue(this.position, temporary);
	}
	
	//TODO: rotate
	
	public void duplicate() {
		this.tape.setValue(this.position + this.facing.multiplier(), this.tape.getValue(this.position));
	}
	
	public void saveLocation(int position) {
		this.positions.add(position);
	}
	
	public void gotoLocation(int index) {
		this.position = this.positions[index];
	}
	
	public void copy() {
		registers.add(tape.getValue(this.position));
	}
	
	public void reg(int index) {
		tape.setValue(this.position, this.register[index]);
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