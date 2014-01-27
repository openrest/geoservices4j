package com.openrest.geoservices;

import java.io.IOException;

/**
 * The Geocoder interface.
 * @author DL
 */
public interface Geocoder {
	/**
	 * Geocodes an address.
	 * 
	 * @param locale   The requested locale, e.g. "en_US", "he_IL".
	 * @param query    The address to geocode.
	 * @return a Geocode result.
	 * @throws IOException on communication errors with external geocoding services.
	 */
    public Geocode find(String locale, String query) throws IOException;
}
