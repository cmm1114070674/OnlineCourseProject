package Service;

public class InvalidExtNameException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidExtNameException(String msg) {
		super(msg);
	}
}
