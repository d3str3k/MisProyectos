package proyecto;




public class NotRegisteredException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotRegisteredException () 
	{
		super();
	}
	
	public NotRegisteredException(String e) 
	{
		super(e);
	}
}
