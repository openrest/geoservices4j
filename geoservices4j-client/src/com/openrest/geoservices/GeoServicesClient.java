package com.openrest.geoservices;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;

public class GeoServicesClient {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static enum Endpoint {
    	DEVELOPMENT("https://geoservices-develop.appspot.com/v1.1/"),
    	PRODUCTION("https://geoservices.openrest.com/v1.1/");
    	
    	public final String url;
    	
    	private Endpoint(String url) {
    		this.url = url;
    	}
    }
    private final HttpRequestFactory requestFactory;
    private final Integer connectTimeout;
    private final Integer readTimeout;
    private final Endpoint endpoint;
    private final String key;
    
    public GeoServicesClient(HttpRequestFactory requestFactory, Integer connectTimeout, Integer readTimeout,
    		Endpoint endpoint, String key) {
    	this.requestFactory = requestFactory;
    	this.connectTimeout = connectTimeout;
    	this.readTimeout = readTimeout;
    	this.endpoint = endpoint;
    	this.key = key;
    }
    
    public Geocoder getGeocoder() {
    	return new Geocoder() {
			@Override
			public Geocode find(String locale, String query) throws IOException {
				return geocode(locale, query);
			}
    	};
    }

	public Geocode geocode(String locale, String query) throws IOException {
		final QueryStringBuilder queryStr = new QueryStringBuilder();
		queryStr.append("locale", locale);
		queryStr.append("query", query);
		queryStr.append("key", key);

    	final HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(endpoint.url + "geocode" + queryStr.toString()));
        if (connectTimeout != null) {
        	request.setConnectTimeout(connectTimeout.intValue());
        }
        if (readTimeout != null) {
        	request.setReadTimeout(readTimeout.intValue());
        }

        final HttpResponse response = request.execute();
        try {
	        final Reader reader = new InputStreamReader(response.getContent(), "UTF-8");
	        try {
	        	return mapper.readValue(reader, Geocode.class);
	        } finally {
	        	reader.close();
	        }
        } finally {
        	response.ignore();
        }
    }
	
	public List<Address> getAddresses() throws IOException {
		final QueryStringBuilder queryStr = new QueryStringBuilder();
		queryStr.append("key", key);
		
    	final HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(endpoint.url + "addresses/" + queryStr.toString()));
        if (connectTimeout != null) {
        	request.setConnectTimeout(connectTimeout.intValue());
        }
        if (readTimeout != null) {
        	request.setReadTimeout(readTimeout.intValue());
        }
		

        final HttpResponse response = request.execute();
        try {
	        final Reader reader = new InputStreamReader(response.getContent(), "UTF-8");
	        try {
	        	return mapper.readValue(reader, new TypeReference<List<Address>>() {});
	        } finally {
	        	reader.close();
	        }
        } finally {
        	response.ignore();
        }
    }
}
