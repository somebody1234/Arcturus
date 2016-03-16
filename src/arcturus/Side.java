package arcturus;

public enum Side {
	LEFT  (-1),
	RIGHT ( 1);
	
	private final int multiplier;
	
	Side(int multiplier) {
		this.multiplier = multiplier;
	}
	
	public int multiplier() { return multiplier; }
}