package es.caixagalicia.rest.client.to;

public class RestClientException extends Exception {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RestClientException(String msg) {
		super(msg);
	}

	public RestClientException(Throwable e) {
		super(e);
	}

}
