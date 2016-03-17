package arcturus;

enum OperationType {
	ADD,
	SUBTRACT,
	MULTIPLY,
	DIVIDE,
	POWER
}

//Seriously, this is a lot more concise than the format in Actions
//Please don't delete.
//@quartata If you don't understand or have any complaints, please add a comment
//If you have suggestions on how this can be more DRY, please add a comment

//@quartata @eridan what do the other operations do? don't forget array operations
//@quartata @eridan does array * double multiply length and fill with items from original array?
//or does it multiply each item?
public class Operations {
	public static final HashMap<Type, HashMap<Type, HashMap<OperationType, Operation>>> operations = new HashMap<Type, HashMap<Type, HashMap<OperationType, Operation>>>() {{
		put(Type.STRING, new HashMap<Type, HashMap<OperationType, Operation>>() {{
			put(Type.STRING, new HashMap<OperationType, Operation>>() {{
				put(OperationType.ADD, (left, right) -> new Value(left.getString() + right.getString()));
			}});
			put(Type.INTEGER, new HashMap<OperationType, Operation>>() {{
				put(OperationType.ADD, (v1, v2) -> new Value(left.getString() + right.getAsString()));
				put(OperationType.MULTIPLY, (v1, v2) -> new Value(Utils.stringTimes(left.getString(), right.getAsDouble())));
			}});
			put(Type.DOUBLE, new HashMap<OperationType, Operation>>() {{
				put(OperationType.ADD, (v1, v2) -> new Value(left.getString() + right.getAsString()));
				put(OperationType.MULTIPLY, (v1, v2) -> new Value(Utils.stringTimes(left.getString(), right.getDouble())));
			}});
		}});
		put(Type.INTEGER, new HashMap<Type, HashMap<OperationType, Operation>>() {{
			put(Type.STRING, new HashMap<OperationType, Operation>>() {{
				put(OperationType.ADD, (left, right) -> new Value(left.getAsString() + right.getString()));
				put(OperationType.MULTIPLY, (left, right) -> new Value(Utils.stringTimes(right.getString(), left.getAsDouble())));
			}});
			put(Type.INTEGER, new HashMap<OperationType, Operation>>() {{
				put(OperationType.ADD, (v1, v2) -> new Value(left.getInteger() + right.getInteger()));
				put(OperationType.SUBTRACT, (v1, v2) -> new Value(left.getInteger() - right.getInteger()));
				put(OperationType.MULTIPLY, (v1, v2) -> new Value(left.getInteger() * right.getInteger()));
				put(OperationType.DIVIDE, (v1, v2) -> new Value(left.getInteger() / right.getInteger()));
				put(OperationType.POWER, (v1, v2) -> new Value(Math.pow(left.getInteger() , right.getInteger())));
			}});
			put(Type.DOUBLE, new HashMap<OperationType, Operation>>() {{
				put(OperationType.ADD, (v1, v2) -> new Value(left.getAsDouble() + right.getDouble()));
				put(OperationType.SUBTRACT, (v1, v2) -> new Value(left.getAsDouble() - right.getDouble()));
				put(OperationType.MULTIPLY, (v1, v2) -> new Value(left.getAsDouble() * right.getDouble()));
				put(OperationType.DIVIDE, (v1, v2) -> new Value(left.getAsDouble() / right.getDouble()));
				put(OperationType.POWER, (v1, v2) -> new Value(Math.pow(left.getAsDouble() , right.getDouble())));
			}});
		}});
		put(Type.DOUBLE, new HashMap<Type, HashMap<OperationType, Operation>>() {{
			put(Type.STRING, new HashMap<OperationType, Operation>>() {{
				put(OperationType.ADD, (left, right) -> new Value(left.getAsString() + right.getString()));
				put(OperationType.MULTIPLY, (left, right) -> new Value(Utils.stringTimes(right.getString(), left.getDouble())));
			}});
			put(Type.INTEGER, new HashMap<OperationType, Operation>>() {{
				put(OperationType.ADD, (v1, v2) -> new Value(left.getDouble() + right.getAsDouble()));
				put(OperationType.SUBTRACT, (v1, v2) -> new Value(left.getDouble() - right.getAsDouble()));
				put(OperationType.MULTIPLY, (v1, v2) -> new Value(left.getDouble() * right.getAsDouble()));
				put(OperationType.DIVIDE, (v1, v2) -> new Value(left.getDouble() / right.getAsDouble()));
				put(OperationType.POWER, (v1, v2) -> new Value(Math.pow(left.getDouble() , right.getAsDouble())));
			}});
			put(Type.DOUBLE, new HashMap<OperationType, Operation>>() {{
				put(OperationType.ADD, (v1, v2) -> new Value(left.getDouble() + right.getDouble()));
				put(OperationType.SUBTRACT, (v1, v2) -> new Value(left.getDouble() - right.getDouble()));
				put(OperationType.MULTIPLY, (v1, v2) -> new Value(left.getDouble() * right.getDouble()));
				put(OperationType.DIVIDE, (v1, v2) -> new Value(left.getDouble() / right.getDouble()));
				put(OperationType.POWER, (v1, v2) -> new Value(Math.pow(left.getDouble() , right.getDouble())));
			}});
		}});
	}};

	//TODO: DRY
	static Value operate(Value left, Value right, OperationType operationType) {
		leftType = left.getType();
		if (operations.containsKey(leftType)) {
			leftMap = operations.get(leftType);

			rightType = right.getType();
			if (leftMap.containsKey(rightType)) {
				rightMap = operations.get(lrightType);

				if (rightMap.containsKey(operationType)) {
					return rightMap.get(operationType)(left, right);
				}
				else {
					throw new NoOperationException(leftType, rightType, operationType);
				}
			}
			else {
				throw new NoOperationException(leftType, rightType);
			}
		}
		else {
			throw new NoOperationException(leftType);
		}
	}
}

@FunctionalInterface
interface Operation {
    public Value operate(Value left, Value right);
}