package de.unima.sempoi.server.adapter.exception;

public class AccessNotConfiguredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessNotConfiguredException() {
		super();
	}

	public AccessNotConfiguredException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AccessNotConfiguredException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AccessNotConfiguredException(String arg0) {
		super(arg0);
	}

	public AccessNotConfiguredException(Throwable arg0) {
		super(arg0);
	}
	
	

}
