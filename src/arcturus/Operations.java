package arcturus;

//TODO: check operation has correct number of arguments? or just Java handle it?

/**
 * Key type for Operations
 * 
 * @since 0.0
 * @see Operations
 */
class OperationType {
	public char operator;
	public Type[] types;

	/**
	 * Creates an OperationType using the specified character and arrya of parameter types.
	 * 
	 * @see OperationType
	 */
	OperationType(char operator, Type[] types) {
		this.operator = operator;
		this.types = types;
	}

	@Override
	public int hashCode() { //arity is just Type[]'s length, meaning no conflicts
		int typeLength = Type.LENGTH;
		int hash = types.reduce(0, (total, type) -> total * typeLength + type.index());
		return hash * 256 + 
			(int) this.operator; //assuming full-byte encoding
	}

	@Override
	public boolean equals(Object obj) {
		if (!obj instanceof OperationType) {
			return false;
		}
		return this.operator == obj.operator && 
			this.types.equals(other.types);
	}
}

//If you have suggestions on how this can be more DRY, please add a comment
//@quartata @eridan what do the other operations do? don't forget array operations
//@quartata @eridan does array * double multiply length and fill with items from original array?
//or does it multiply each item?

//@quartata I can add arities back if you want.
/**
 * Contains all operations used by Arcturus, and what they do.
 * 
 * @since 0.0
 * @see   OperationType
 */
public class Operations {
	public static final HashMap<OperationType, Function<Value[], Value>> operations = new HashMap<OperationType,Function<Value[], Value>>(256, 0) {
		//TODO: make sure 0 works, otherwise use 0.01 (min time, assuming plenty of space)
		put(new OperationType('+', new Type[]{Type.STRING, Type.STRING}),
			values -> new Value(values[0].getString() + values[1].getString()));
		put(new OperationType('+, new Type[]{Type.STRING, Type.INTEGER}),
			values -> new Value(values[0].getString() + values[1].getAsString()));
		put(new OperationType('+, new Type[]{Type.STRING, Type.DOUBLE}),
			values -> new Value(values[0].getString() + values[1].getAsString()));

		put(new OperationType('+', new Type[]{Type.INTEGER, Type.STRING}),
			values -> new Value(values[0].getAsString() + values[1].getString()));
		put(new OperationType('+', new Type[]{Type.INTEGER, Type.INTEGER}),
			values -> new Value(values[0].getInteger() + values[1].getInteger()));
		put(new OperationType('+', new Type[]{Type.INTEGER, Type.DOUBLE}),
			values -> new Value(values[0].getInteger() + values[1].getDouble()));

		put(new OperationType('+', new Type[]{Type.DOUBLE, Type.STRING}),
			values -> new Value(values[0].getAsString() + values[1].getString()));
		put(new OperationType('+', new Type[]{Type.DOUBLE, Type.INTEGER}),
			values -> new Value(values[0].getDouble() + values[1].getInteger()));
		put(new OperationType('+', new Type[]{Type.DOUBLE, Type.DOUBLE}),
			values -> new Value(values[0].getDouble() + values[1].getDouble()));


		put(new OperationType('-', new Type[]{Type.INTEGER, Type.INTEGER}),
			values -> new Value(values[0].getInteger() - values[1].getInteger()));
		put(new OperationType('-', new Type[]{Type.INTEGER, Type.DOUBLE}),
			values -> new Value(values[0].getInteger() - values[1].getDouble()));

		put(new OperationType('-', new Type[]{Type.DOUBLE, Type.INTEGER}),
			values -> new Value(values[0].getDouble() - values[1].getInteger()));
		put(new OperationType('-', new Type[]{Type.DOUBLE, Type.DOUBLE}),
			values -> new Value(values[0].getDouble() - values[1].getDouble()));


		put(new OperationType('*', new Type[]{Type.STRING, Type.INTEGER}),
			values -> new Value(Utils.stringTimes(values[0].getString(), values[1].getAsDouble())));
		put(new OperationType('*', new Type[]{Type.STRING, Type.DOUBLE}),
			values -> new Value(Utils.stringTimes(values[0].getString(), values[1].getDouble())));

		put(new OperationType('*', new Type[]{Type.INTEGER, Type.STRING}),
			values -> new Value(Utils.stringTimes(values[1].getString(), values[0].getAsDouble())));
		put(new OperationType('*', new Type[]{Type.INTEGER, Type.INTEGER}),
			values -> new Value(values[0].getInteger() * values[1].getInteger()));
		put(new OperationType('*', new Type[]{Type.INTEGER, Type.DOUBLE}),
			values -> new Value(values[0].getInteger() * values[1].getDouble()));

		put(new OperationType('*', new Type[]{Type.DOUBLE, Type.STRING}),
			values -> new Value(Utils.stringTimes(values[1].getString(), values[0].getDouble())));
		put(new OperationType('*', new Type[]{Type.DOUBLE, Type.INTEGER}),
			values -> new Value(values[0].getDouble() * values[1].getInteger()));
		put(new OperationType('*', new Type[]{Type.DOUBLE, Type.DOUBLE}),
			values -> new Value(values[0].getDouble() * values[1].getDouble()));


		put(new OperationType('/', new Type[]{Type.STRING, Type.INTEGER}),
			values -> new Value(Utils.stringTimes(values[0].getString(), 1/values[1].getAsDouble())));
		put(new OperationType('/', new Type[]{Type.STRING, Type.DOUBLE}),
			values -> new Value(Utils.stringTimes(values[0].getString() + 1/values[1].getDouble())));

		put(new OperationType('/', new Type[]{Type.INTEGER, Type.INTEGER}),
			values -> new Value(values[0].getInteger() / values[1].getInteger())); //TODO: this performs integer division, not sue if this behavior is wanted, or if it will be for '\' instead
		put(new OperationType('/', new Type[]{Type.INTEGER, Type.DOUBLE}),
			values -> new Value(values[0].getInteger() / values[1].getDouble()));

		put(new OperationType('/', new Type[]{Type.DOUBLE, Type.INTEGER}),
			values -> new Value(values[0].getDouble() / values[1].getInteger()));
		put(new OperationType('/', new Type[]{Type.DOUBLE, Type.DOUBLE}),
			values -> new Value(values[0].getDouble() / values[1].getDouble()));
	};
}

