package com.tales.apicidades.utils;

import org.springframework.stereotype.Component;

import com.tales.apicidades.model.GeoCoordinate;

@Component
public class GeoUtils {

	/**
	 * Raio médio da terra em quilômetros Ref:
	 * http://en.wikipedia.org/wiki/Earth_radius
	 */
	public static int EARTH_RADIUS_KM = 6371;

	/**
	 * 
	 * @param firstLatitude
	 * @param firstLongitude
	 * @param secondLatitude
	 * @param secondLongitude
	 * @return Distance between two geo points
	 */
	public static Double geoDistanceInKm(Double firstLatitude,
			Double firstLongitude, Double secondLatitude, Double secondLongitude) {

		// Conversão de graus pra radianos das latitudes
		Double firstLatToRad = Math.toRadians(firstLatitude);
		Double secondLatToRad = Math.toRadians(secondLatitude);

		// Diferença das longitudes
		Double deltaLongitudeInRad = Math.toRadians(secondLongitude
				- firstLongitude);

		// Cálcula da distância entre os pontos
		return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
				* Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
				* Math.sin(secondLatToRad))
				* EARTH_RADIUS_KM;
	}

	/**
	 * 
	 * @param first
	 * @param second
	 * @return Distance between two geo points
	 */
	public static Double geoDistanceInKm(GeoCoordinate first,
			GeoCoordinate second) {
		return geoDistanceInKm(first.getLatitude(), first.getLongitude(),
				second.getLatitude(), second.getLongitude());
	}

}
