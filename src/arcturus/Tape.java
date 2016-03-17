package arcturus;

public class Tape {
	private Value[] tape; //somebody: it's a string internally, but that would become ugly if it was a string eternally as well IMO
	private int size;
	private int origin;
	//private int pos; //somebody: not very OOP-like
	
	public Tape(int size, int origin) {
		this.tape = new Value[size];
		this.size = size;
		this.origin = origin;
		this.pos = 0;
	}

	public Value getValueAt(int position) {
		return this.tape[this.calculatePosition(position)];
	}
	
	public void setValueAt(int position, Value v) {
		this.tape[this.calculatePosition(position + this.origin)] = v;
	}

	public int calculatePosition(int position) {
		return (position + this.size) % this.size; //So negative values will work
	}
	
	public Value getValue(int position) { return getValueAt(position); }
	
	public void setValue(int position, Value v) { setValueAt(position, v); }

	ArrayList<Value> getValues(int start, int length) { return new ArrayList(tape.subList(start, start + length + 1)); }

	//void setValues(int start, ArrayList<Value> v) { setValueAt(position, v); }

	/*public int getPos() { return pos; }
	
	public void setPos(int i) { pos = calculatePosition(i); }*/
	
	public void print(int position) {
		System.out.println(this.getValue(position));
	}
	
	public void print(int start, int end) {
		int step = 0;
		if (start <= end) {
			step = 1;
		}
		else {
			step = -1;
		}
		for (int i = start; i * step <= end * step; i += step) { //* step because negative comparisons are reversed
			System.out.println(this.getValue(i));
		}
	}

	public void print() {
		for (String value: tape) {
			System.out.println(value);
		}
	}

	public void printDebug() {
		int i = -origin; //TODO: check
		for (String value: tape) {
			System.out.print(i);
			System.out.print(' ');
			System.out.println(value);
			i++;
		}
	}
}