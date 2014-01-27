package com.openrest.geoservices;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.openrest.geoservices.GeoServicesClient.Endpoint;

public class GeoServicesClientTest {
	private static final HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
	private static final GeoServicesClient client = new GeoServicesClient(requestFactory, 5000, 5000, Endpoint.PRODUCTION, null);
	
    @Test
    public void testGeocode() throws Exception {
    	final Geocode geocode = client.geocode("en_US", "Arlozorov 8, Tel Aviv, Israel");
    	assertNotNull(geocode);
    	assertNotNull(geocode.latLng);
    }

}
