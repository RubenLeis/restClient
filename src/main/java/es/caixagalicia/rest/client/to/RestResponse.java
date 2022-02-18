package es.caixagalicia.rest.client.to;


/**
 * Wrapper de la informacion de respuesta
 * @author rleis
 *
 */
public interface RestResponse {

	StatusLine getStatusLine();
	void setStatusLine(StatusLine statusLine);
	
	String getEntityAsString();
	void setEntityAsString(String entityAsString);
	
}
