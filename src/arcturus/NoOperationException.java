package arcturus;

/**
 * An exception thrown when an operation does not exist for the two specified types.
 * 
 * @see Function
 */
class NoOperationException extends Exception {
    /**
     * Creates a NoOperationException with the specified message.
     * 
     * @param message The message displayed by the NoOperationException.
     * @see   NoOperationException
     */
	public NoOperationException(String message) {
		super(message);
	}

    /**
     * Creates a NoOperationException using the specified types of the left and right arguments.
     * 
     * @param leftType  The type of the left argument.
     * @param rightType The type of the right argument.
     * @see   NoOperationException
     */
	public NoOperationException(Type leftType, Type rightType) {
		super("No operator found for types " + leftType.toString() + " and " + rightType.toString ".");
	}
}