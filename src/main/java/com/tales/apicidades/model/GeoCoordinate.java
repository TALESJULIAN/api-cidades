package com.tales.apicidades.model;

import com.tales.apicidades.utils.GeoUtils;

public class GeoCoordinate {

	private Double latitude;
	
	private Double longitude;

	/**
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public GeoCoordinate(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Recupera a latitude
	 * @return Latitude em graus
	 */
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 
	 * @param coordinate
	 * @return Distance between two geo points
	 */
	public Double distanceInKm(GeoCoordinate coordinate) {
		return GeoUtils.geoDistanceInKm(this, coordinate);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new GeoCoordinate(this.getLatitude(), this.getLatitude());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GeoCoordinate other = (GeoCoordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GeoCoordinate [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
