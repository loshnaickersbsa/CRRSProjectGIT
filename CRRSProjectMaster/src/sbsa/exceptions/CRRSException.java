package sbsa.exceptions;

public class CRRSException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage = null;
	
	public CRRSException(String errorMessage)
	{
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return errorMessage;
	}
}
