package exceptions;

@SuppressWarnings("serial")
public class SemanticException extends RuntimeException {
	public SemanticException(String msg) {
		super(msg);
	}
}
