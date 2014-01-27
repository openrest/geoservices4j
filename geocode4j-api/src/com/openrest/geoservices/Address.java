package com.openrest.geoservices;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	    
    /** Default constructor for JSON deserialization. */
	public Address() {}

	public Address(String locale, String country, String locality, String route,
			String streetNumber, String postalCode, String formatted) {
		this.locale = locale;
		this.country = country;
		this.locality = locality;
		this.route = route;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
		this.formatted = formatted;
	}
	
	@Override
	public Object clone() {
		return new Address(locale, country, locality, route, streetNumber, postalCode, formatted);
	}
	
	@JsonInclude(Include.NON_NULL)
	public String locale;
	
	@JsonInclude(Include.NON_NULL)
	public String country;
    
	@JsonInclude(Include.NON_NULL)
	public String locality;
    
	@JsonInclude(Include.NON_NULL)
	public String route;
    
	@JsonInclude(Include.NON_NULL)
	public String streetNumber;
    
	@JsonInclude(Include.NON_NULL)
	public String postalCode;
    
	@JsonInclude(Include.NON_NULL)
	public String formatted;
    
    public boolean fullyQualified() {
    	return ((locale != null) && (country != null) && (locality != null) &&
    			(route != null) && (streetNumber != null));
    }
    
    @Override
	public String toString() {
		return "Address [locale=" + locale + ", country=" + country
				+ ", locality=" + locality + ", route=" + route
				+ ", streetNumber=" + streetNumber + ", postalCode=" + postalCode
				+ ", formatted=" + formatted + "]";
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((formatted == null) ? 0 : formatted.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result
				+ ((locality == null) ? 0 : locality.hashCode());
		result = prime * result
				+ ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		result = prime * result
				+ ((streetNumber == null) ? 0 : streetNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (formatted == null) {
			if (other.formatted != null)
				return false;
		} else if (!formatted.equals(other.formatted))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (locality == null) {
			if (other.locality != null)
				return false;
		} else if (!locality.equals(other.locality))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (streetNumber == null) {
			if (other.streetNumber != null)
				return false;
		} else if (!streetNumber.equals(other.streetNumber))
			return false;
		return true;
	}
}
