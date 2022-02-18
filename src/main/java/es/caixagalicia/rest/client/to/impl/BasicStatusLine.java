package es.caixagalicia.rest.client.to.impl;

import es.caixagalicia.rest.client.to.StatusLine;

public class BasicStatusLine implements StatusLine {

	public BasicStatusLine() {

	}

	private int statusCode;
	private String reasonPhrase;

	public BasicStatusLine(int statusCode, String reasonPhrase) {
		super();
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
	}

	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String getReasonPhrase() {
		return reasonPhrase;
	}

}
