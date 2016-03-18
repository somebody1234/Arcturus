package arcturus;


/**
 * The data storage class for Arcturus.
 * 
 * @since 0.0
 */
public class Tape {
	private Value[] tape; //somebody: it's a string internally, but that would become ugly if it was a string eternally as well IMO
	private int size;
	private int origin;

	/**
	 * Creates a Tape using the specified size and origin.
	 * 
	 * @param size   The length of the Tape.
	 * @param origin The position the Tape interprets as 0.
	 */
	public Tape(int size, int origin) {
		this.tape = new Value[size];
		this.size = size;
		this.origin = origin;
	}

	/**
	 * Gets the Value at the specified position in the tape.
	 * 
	 * @param   position The position of the desired Value.
	 * @returns The Value at the specified position.
	 * @see     Value
	 */
	public Value getValueAt(int position) {
		return this.tape[this.calculatePosition(position)];
	}

	/**
	 * Sets the value at the specified position in the tape.
	 * 
	 * @param position The position of the Value to set.
	 * @param value    The Value to set the position to.
	 * @see   Value
	 */
	public void setValueAt(int position, Value value) {
		this.tape[this.calculatePosition(position + this.origin)] = value;
	}

	/**
	 * Calculates the actual position of a position, 
	 * taking into account looping around.
	 * 
	 * @param   position The position to calculate.
	 * @returns The corrected position.
	 */
	public int calculatePosition(int position) {
		return (position + this.size) % this.size; //So negative values will work
	}

	/**
	 * Gets the Value at a specified position in the Tape.
	 * 
	 * @param   position The position of the Value to get.
	 * @returns The Value at the position.
	 * @see     Value
	 */
	public Value getValue(int position) { return getValueAt(position); }

	/**
	 * Sets the Value at a specified position in the Tape.
	 * 
	 * @param position The position of the Value to get.
	 * @param value    The Value to set.
	 * @see   Value
	 */
	public void setValue(int position, Value v) { setValueAt(position, v); }

	/**
	 * Gets a number of Values from the Tape.
	 * 
	 * @param   start  The starting position of the range.
	 * @param   length The length of the range.
	 * @returns A list of the values in the range.
	 * @see     Value
	 */
	ArrayList<Value> getValues(int start, int length) { return new ArrayList(tape.subList(start, start + length + 1)); }

	//void setValues(int start, ArrayList<Value> v) { setValueAt(position, v); }

	/**
	 * Prints the Value at a specified position in the Tape.
	 * 
	 * @param position The position of the Value to print.
	 * @see   Value
	 */
	public void print(int position) {
		System.out.print(this.getValue(position));
	}

	/**
	 * Prints the Values of a range of positions in the Tape.
	 * 
	 * @param start The start position of the range.
	 * @param end   The end position of the range.
	 */
	public void print(int start, int end) {
		int step = 0;
		if (start <= end) {
			step = 1;
		}
		else {
			step = -1;
		}
		for (int i = start; i * step <= end * step; i += step) {
			//multiply by step because negative comparisons are reversed
			System.out.print(this.getValue(i));
		}
	}

	/**
	 * Prints the Values in every index in the tape.
	 */
	public void print() {
		for (String value: tape) {
			System.out.print(value);
		}
	}

	/**
	 * Prints the positions and Values in every index of the tape.
	 */
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