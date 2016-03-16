package arcturus;

/**
 * A Function with two arguments.
 * 
 * @see Function
 */
@FunctionalInterface
interface Function<One, Two, Three> {
    public Three apply(One one, Two two);
}

public static class Operations {
	private String stringTimes(String s, double d) {
		int rounded = Math.ceil(d);
		return new String(new char[rounded]).replace("\0", s)).substring(0, Math.round(s.length() * d));
	}
	//TODO: add array types
	private Map<Type, Map<Type, Function<Value, Value, Value>>> addDictionary = new HashMap() {{
		put(Type.STRING, new HashMap() {{
			put(Type.STRING, (left, right) -> new Value(left.getString() + right.getString()));
			put(Type.INTEGER, (left, right) -> new Value(left.getString() + right.getString()));
			put(Type.DOUBLE, (left, right) -> new Value(left.getString() + right.getString()));
		}});
		put(Type.INTEGER, new HashMap() {{
			put(Type.STRING, (left, right) -> new Value(left.getString() + right.getString()));
			put(Type.INTEGER, (left, right) -> new Value(left.getInteger() + right.getInteger()));
			put(Type.DOUBLE, (left, right) -> new Value(left.getDouble() + right.getDouble()));
		}});
		put(Type.DOUBLE, new HashMap() {{
			put(Type.STRING, (left, right) -> new Value(left.getString() + right.getString()));
			put(Type.INTEGER, (left, right) -> new Value(left.getDouble() + right.getDouble()));
			put(Type.DOUBLE, (left, right) -> new Value(left.getDouble() + right.getDouble()));
		}});
	}}
	private Map<Type, Map<Type, Function<Value, Value, Value>>> subtractDictonary = new HashMap() {{
		put(Type.INTEGER, new HashMap() {{
			put(Type.INTEGER, (left, right) -> new Value(left.getInteger() - right.getInteger()));
			put(Type.DOUBLE, (left, right) -> new Value(left.getDouble() - right.getDouble()));
		}});
		put(Type.DOUBLE, new HashMap() {{
			put(Type.INTEGER, (left, right) -> new Value(left.getDouble() - right.getDouble()));
			put(Type.DOUBLE, (left, right) -> new Value(left.getDouble() - right.getDouble()));
		}});
	}}
	private Map<Type, Map<Type, Function<Value, Value, Value>>> multiplyDictionary = new HashMap() {{
		put(Type.STRING, new HashMap() {{
			put(Type.INTEGER, (left, right) -> new Value(stringTimes(left.getString(), right.getDouble())));
			put(Type.DOUBLE, (left, right) -> new Value(stringTimes(left.getString(), right.getDouble())));
		}});
		put(Type.INTEGER, new HashMap() {{
			put(Type.STRING, (left, right) -> new Value(stringTimes(left.getDouble(), right.getString())));
			put(Type.INTEGER, (left, right) -> new Value(left.getInteger() * right.getInteger()));
			put(Type.DOUBLE, (left, right) -> new Value(left.getDouble() * right.getDouble()));
		}});
		put(Type.DOUBLE, new HashMap() {{
			put(Type.STRING, (left, right) -> new Value(stringTimes(left.getDouble(), right.getString())));
			put(Type.INTEGER, (left, right) -> new Value(left.getDouble() * right.getDouble()));
			put(Type.DOUBLE, (left, right) -> new Value(left.getDouble() * right.getDouble()));
		}});
	}}
	private Map<Type, Map<Type, Function<Value, Value, Value>>> divideDictionary = new HashMap() {{
		put(Type.STRING, new HashMap() {{
			put(Type.INTEGER, (left, right) -> new Value(stringTimes(left.getString(), 1 / right.getDouble())));
			put(Type.DOUBLE, (left, right) -> new Value(stringTimes(left.getString(), 1 / right.getDouble())));
		}});
		put(Type.INTEGER, new HashMap() {{
			put(Type.INTEGER, (left, right) -> new Value(left.getInteger() / right.getInteger()));
			put(Type.DOUBLE, (left, right) -> new Value(left.getDouble() / right.getDouble()));
		}});
		put(Type.DOUBLE, new HashMap() {{
			put(Type.INTEGER, (left, right) -> new Value(left.getDouble() / right.getDouble()));
			put(Type.DOUBLE, (left, right) -> new Value(left.getDouble() / right.getDouble()));
		}});
	}}
	private Value performOperation(Map<Type, Map<Type, Function<Value, Value, Value>>> dictionary, Value left, Value right) throws NoOperationException {
		Type leftType = left.getType();
		Type rightType = right.getType();
		if (dictionary.containsKey(leftType) && dictionary.get(leftType).containsKey(rightType)) {
			return dictionary.get(leftType).get(rightType)(left, right);
		}
		else {
			throw new NoOperationException(leftType, rightType);
		}
	}
	public Value add(Value left, Value right) {
		return performOperation(addDictionary, left, right);
	}
	public Value subtract(Value left, Value right) {
		return performOperation(subtractDictonary, left, right);
	}
	public Value multiply(Value left, Value right) {
		return performOperation(multiplyDictionary, left, right);
	}
	public Value divide(Value left, Value right) {
		return performOperation(divideDictionary, left, right);
	}
}