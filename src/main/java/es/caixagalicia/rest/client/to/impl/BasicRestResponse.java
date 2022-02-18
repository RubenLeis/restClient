package es.caixagalicia.rest.client.to.impl;

import es.caixagalicia.rest.client.to.RestResponse;
import es.caixagalicia.rest.client.to.StatusLine;


public class BasicRestResponse implements RestResponse{

	private StatusLine statusLine;
	private String entityAsString;

	
	public BasicRestResponse(StatusLine statusLine, String entityAsString) {
		super();
		this.statusLine = statusLine;
		this.entityAsString = entityAsString;
	}

	public BasicRestResponse() {
		
	}

	@Override
	public void setStatusLine(StatusLine statusLine) {
		this.statusLine = statusLine;
	}

	@Override
	public StatusLine getStatusLine() {
		return statusLine;
	}

	@Override
	public void setEntityAsString(String entityAsString) {
		this.entityAsString = entityAsString;
	}

	@Override
	public String getEntityAsString() {
		return entityAsString;
	}


}
