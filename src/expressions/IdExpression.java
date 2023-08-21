package expressions;

public class IdExpression {
	private String id;
	private int    value;
	
	public int eval() {
		return this.value;
	}
	
	public String toString() {
		return this.id;
	}
}
