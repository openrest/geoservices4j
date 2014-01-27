package com.openrest.geoservices;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A Geocode result.
 * @author DL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geocode implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Geocode(Address address, LatLng latLng, String timezone, String countryCode, String geocoder) {
    	this(address, latLng, timezone, countryCode, geocoder, System.currentTimeMillis());
    }
    
    public Geocode(Address address, LatLng latLng, String timezone, String countryCode,
    		String geocoder, Long timestamp) {
        this.address = address;
        this.latLng = latLng;
        this.timezone = timezone;
        this.countryCode = countryCode;
        this.geocoder = geocoder;
        this.timestamp = timestamp;
    }
    
    /** Default constructor for JSON deserialization. */
	public Geocode() {}

	@JsonInclude(Include.NON_NULL)
    public Address address;
    
	@JsonInclude(Include.NON_NULL)
    public LatLng latLng;

    /**
     * Timezone id in Olson naming convention.
     * @see http://en.wikipedia.org/wiki/Tz_database
     */
	@JsonInclude(Include.NON_NULL)
    public String timezone;
    
    public TimeZone timezone() {
    	return ((timezone != null) ? TimeZone.getTimeZone(timezone) : null);
    }

    /**
     * Country code (ISO 3166-1 alpha-2) 
     * @see http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
     */
	@JsonInclude(Include.NON_NULL)
    public String countryCode;
    
	@JsonInclude(Include.NON_NULL)
    public String geocoder;
    
	@JsonInclude(Include.NON_NULL)
    public Long timestamp;
    
    public Date timestamp() {
        return ((timestamp != null) ? new Date(timestamp.longValue()) : null);
    }
    
	@Override
	public String toString() {
		return "Geocode [address=" + address + ", latLng=" + latLng
				+ ", timezone=" + timezone + ", countryCode=" + countryCode
				+ ", geocoder=" + geocoder + ", timestamp=" + timestamp + "]";
	}
}
