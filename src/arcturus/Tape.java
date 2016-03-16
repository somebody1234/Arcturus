package arcturus;

public class Tape {
	private String[] tape;
	//private Value[] tape;
	private Type[] types;
	private int size;
	private int origin;
	
	public Tape(int size, int offset) {
		this.tape = new Value[size];
		this.types = new Type[size];
		this.size = size;
		this.origin = offset;
	}
	
	public void setValue(int position, String s) {
		this.tape[this.calculatePosition(position + this.origin)] = new Value(s);
	}

	public void setValue(int position, int i) {
		this.tape[this.calculatePosition(position + this.origin)] = new Value(i);
	}

	public void setValue(int position, double d) {
		this.tape[this.calculatePosition(position + this.origin)] = new Value(d);
	}
	
	public void setValue(int position, Value v) {
		this.tape[this.calculatePosition(position + this.origin)] = v;
	}
	
	public int calculatePosition(int position) {
		return (position + this.size) % this.size; //So negative values will work
	}
	
	public String getValue(int position) {
		return this.tape[this.calculatePosition(position)];
	}
	
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