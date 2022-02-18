package es.caixagalicia.rest.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import es.caixagalicia.rest.client.to.RestClientException;
import es.caixagalicia.rest.client.to.RestResponse;
import es.caixagalicia.rest.client.to.StatusLine;
import es.caixagalicia.rest.client.to.impl.BasicRestResponse;
import es.caixagalicia.rest.client.to.impl.BasicStatusLine;

/**
 * Wrapper de una llamada de un cliente rest
 * 
 * @author rleis
 *
 */
public class RestClient {


	public RestResponse doGet(String uri) throws RestClientException {
		HttpGet httpGet = new HttpGet(uri);
		return execute(httpGet);
	}

	public RestResponse doPatch(String uri, String json) throws RestClientException {
		HttpPatch httpPatch = new HttpPatch(uri);
		httpPatch.addHeader("content-type", "application/json");

		StringEntity stringEntity = getStringEntity(json);
		httpPatch.setEntity(stringEntity);
		return execute(httpPatch);
	}

	private StringEntity getStringEntity(String json) {
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(json.toString());
		} catch (UnsupportedEncodingException e) {
			new RestClientException(e);
		}
		return stringEntity;
	}

	// Podria haber sobrecarga con un mapa para el header, timeout, securizacion....
	public RestResponse doPost(String uri, String json) throws RestClientException  {
		HttpPost httpPost = new HttpPost(uri);
		httpPost.addHeader("content-type", "application/json");
		StringEntity stringEntity = getStringEntity(json);
		httpPost.setEntity(stringEntity);

		return execute(httpPost);
	}
	
	// Podria haber sobrecarga con un mapa para el header, timeout, securizacion....
//	public <T> RestResponse doPost(String uri, T clazz) throws RestClientException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		String jsonAsString;
//		try {
//			jsonAsString = objectMapper.writeValueAsString(clazz);
//		} catch (JsonProcessingException e) {
//			throw new RestClientException(e);
//		}
//		
//		return this.doPost(uri, jsonAsString);
//	}

	/**
	 * Metodo generico para llamar al API
	 * 
	 * En principio todos van a devolver un json (String) y el statusCode, parece
	 * comun esta info
	 * 
	 * @param httpRequest
	 * @return
	 * @throws RestClientException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private RestResponse execute(HttpRequestBase httpRequest) throws RestClientException {

		// throw new RestClientException("Error");

		// https://stackoverflow.com/questions/21576414/setting-time-out-in-apache-http-client
//		RequestConfig config = RequestConfig.custom()
//				  .setConnectTimeout(timeout * 1000)
//				  .setConnectionRequestTimeout(timeout * 1000)
//				  .setSocketTimeout(timeout * 1000).build();		
//		CloseableHttpClient client =  HttpClientBuilder.create().setDefaultRequestConfig(config).build();

		try (CloseableHttpClient httpclient = HttpClients.createDefault();
				CloseableHttpResponse response = httpclient.execute(httpRequest)) {
			RestResponse restResponse = null;
			restResponse = new BasicRestResponse();

			// Status Line
			if (response.getStatusLine() != null) {
				StatusLine statusLine = new BasicStatusLine();
				statusLine.setStatusCode(response.getStatusLine().getStatusCode());
				statusLine.setReasonPhrase(response.getStatusLine().getReasonPhrase());
				restResponse.setStatusLine(statusLine);
			}

			// Response
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String entityAsString = EntityUtils.toString(entity);
				if (entityAsString != null) {
					restResponse.setEntityAsString(entityAsString);
				}
			}

			return restResponse;

		} catch (ClientProtocolException e) {
			throw new RestClientException(e);
		} catch (IOException e) {
			throw new RestClientException(e);
		}
	}

}
