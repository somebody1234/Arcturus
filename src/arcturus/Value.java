package arcturus;

//TODO: make javadocs more informative

/**
 * An Arcturus value.
 */
public class Value {
	private String value;
	private ArrayList<String> arrayValue;
	private boolean isArray;
	private Type type;
	//TODO: convert arrays to strings as well?

    /**
     * Creates a Value object from a string.
     * 
     * @param string The string value of the Value.
     * @see   Value
     */
	public Value(String string) {
		this.type = Type.STRING;
		this.value = string;
	}

    /**
     * Creates a Value object from an integer.
     * 
     * @param integer The integer value of the Value.
     * @see   Value
     */
	public Value(int integer) {
		this.type = Type.INTEGER;
		this.value = integer.toString();
	}

    /**
     * Creates a Value object from a double.
     * 
     * @param doubl The double value of the Value.
     * @see   Value
     */
	public Value(double doubl) {
		this.type = Type.DOUBLE;
		this.value = doubl.toString();
	}

    /**
     * Creates a Value object from a string array.
     * 
     * @param strings The string array value of the Value.
     * @see   Value
     */
	public Value(ArrayList<string> strings) {
		this.arrayValue = strings;
		this.isArray = true;
		this.type = Type.STRING_ARRAY;
	}

    /**
     * Creates a Value object from an integer array.
     * 
     * @param strings The integer array value of the Value.
     * @see   Value
     */
	public Value(ArrayList<int> integers) {
		this.arrayValue = new ArrayList<String>(integers.length());
		for (int integer: integers) {
			this.arrayValue.add(integer.toString());
		}
		this.isArray = true;
		this.type = Type.INTEGER_ARRAY;
	}

    /**
     * Creates a Value object from a double array.
     * 
     * @param strings The double array value of the Value.
     * @see   Value
     */
	public Value(ArrayList<double> doubles) {
		this.arrayValue = new ArrayList<String>(doubles.length());
		for (double doubl: doubles) {
			this.arrayValue.add(doubl.toString());
		}
		this.isArray = true;
		this.type = Type.DOUBLE_ARRAY;
	}

    /**
     * Gets the value of a Value as a string.
     * 
     * @return Value of the string Value.
     * @see    Value
     */
	String getAsString() {
		return this.value;
	}

    /**
     * Gets the value of a Value as a integer.
     * 
     * @return Value.
     * @see    Value
     */
	int getAsInteger() {
		return Integer.parseInt(this.value);
	}

    /**
     * Gets the value of a Value as a double.
     * 
     * @return Value.
     * @see    Value
     */
	double getAsDouble() {
		return Double.parseDouble(this.value);
	}

    /**
     * Gets the value of a string Value.
     * 
     * @return Value of the string Value.
     * @see    Value
     */
	String getString() {
		if (this.type = Type.STRING) {
			return this.getAsString();
		}
	}

    /**
     * Gets the value of an integer Value.
     * 
     * @return Value of the integer Value.
     * @see    Value
     */
	int getInteger() {
		if (this.type = Type.INTEGER) {
			return this.getAsInteger();
		}
	}

    /**
     * Gets the value of a double Value.
     * 
     * @return Value of the double Value.
     * @see    Value
     */
	public double getDouble() {
		if (this.type = Type.DOUBLE) {
			return this.getAsDouble();
		}
	}

    /**
     * Gets the value of a string array Value.
     * 
     * @return Value of the string array Value.
     * @see    Value
     */
	public ArrayList<String> getStrings() {
		if (this.type = Type.STRING_ARRAY) {
			return this.arrayValue;
		}
	}

    /**
     * Gets the value of an integer array Value.
     * 
     * @return Value of the integer array Value.
     * @see    Value
     */
	public ArrayList<int> getIntegers() {
		if (this.type = Type.INTEGER_ARRAY) {
			ArrayList<int> returns = new ArrayList<int>(this.arrayValue.length());
			for (String s: this.arrayValue) {
				returns.add(Int.parseInt(s));
			}
			return returns;
		}
	}

    /**
     * Gets the value of a double array Value.
     * 
     * @return Value of the double array Value.
     * @see    Value
     */
	public ArrayList<double> getDoubles() {
		if (this.type = Type.DOUBLE_ARRAY) {
			ArrayList<double> returns = new ArrayList<double>(this.arrayValue.length());
			for (String s: this.arrayValue) {
				returns.add(Double.parseDouble(s));
			}
			return returns;
		}
	}

    /**
     * Gets the type of a Value.
     * 
     * @return Type of the Value.
     * @see    Value
     */
	public Type getType() {
		return this.type;
	}

	public String toString() {
		if (!this.isArray {
			return this.value;
		}
		else {
			return this.arrayValue.toString();
		}
	}
}