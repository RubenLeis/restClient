
package es.caixagalicia.rest.client.to;

/**
 * Wrapper de la informacion del estado de la peticion
 * @author rleis
 *
 */
public interface StatusLine {


    int getStatusCode();
    
    void setStatusCode(int statusCode);

    String getReasonPhrase();
    
    void setReasonPhrase(String reasonPhrase);

}