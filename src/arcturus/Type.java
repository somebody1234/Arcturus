package arcturus;

public enum Type {
	STRING        (       "string"),
	DOUBLE        (       "double"),
	INTEGER       (      "integer"),
	STRING_ARRAY  ( "string array"),
	DOUBLE_ARRAY  ( "double array"),
	INTEGER_ARRAY ("integer array");

	private final int string;
	
	Type(int string) {
		this.string = string;
	}
	
	public int toString() { return string; }
}