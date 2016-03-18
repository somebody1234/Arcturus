package arcturus;

/**
 * An enum representi type of a Value.
 * 
 * @see   Value
 * @since 0.0
 */
public enum Type {
	STRING        (       "string", 1),
	DOUBLE        (       "double", 2),
	INTEGER       (      "integer", 3),
	STRING_ARRAY  ( "string array", 4),
	DOUBLE_ARRAY  ( "double array", 5),
	INTEGER_ARRAY ("integer array", 6);

	private final String string;
	private final int index;

	Type(String string, int index) {
		this.string = string;
		this.index = index;
	}

	public static LENGTH = 6; //faster than values().length;

	public int index() { return index; }
	public String toString() { return string; }
}