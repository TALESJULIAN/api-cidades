package com.tales.apicidades.batch.model;

import com.opencsv.bean.CsvBindByPosition;

public class CityIO {

	@CsvBindByPosition(position = 0)
	private String id;
	@CsvBindByPosition(position = 1)
	private String ibge_id;
	@CsvBindByPosition(position = 2)
	private String uf;
	@CsvBindByPosition(position = 3)
	private String name;
	@CsvBindByPosition(position = 4)
	private boolean capital;
	@CsvBindByPosition(position = 5)
	private String lon;
	@CsvBindByPosition(position = 6)
	private String lat;
	@CsvBindByPosition(position = 7)
	private String no_accents;
	@CsvBindByPosition(position = 8)
	private String alternative_names;
	@CsvBindByPosition(position = 9)
	private String microregion;
	@CsvBindByPosition(position = 10)
	private String mesoregion;
	
	public CityIO() {
		
	}
	
	/**
	 * 
	 * @param ibge_id2
	 * @param uf2
	 * @param name2
	 * @param capital2
	 * @param lon2
	 * @param lat2
	 * @param no_accents2
	 * @param alternative_names2
	 * @param microregion2
	 * @param mesoregion2
	 */
	public CityIO(String ibge_id2, String uf2, String name2, boolean capital2, 
			String lon2, String lat2, String no_accents2, String alternative_names2, String microregion2, String mesoregion2) {
	}
	
	public String getId() {
		return id;
	}
	
	public String getIbge_id() {
		return ibge_id;
	}
	
	public String getUf() {
		return uf;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getCapital() {
		return capital;
	}
	
	public String getLon() {
		return lon;
	}
	
	public String getLat() {
		return lat;
	}
	
	public String getNo_accents() {
		return no_accents;
	}
	
	public String getAlternative_names() {
		return alternative_names;
	}
	
	public String getMicroregion() {
		return microregion;
	}
	
	public String getMesoregion() {
		return mesoregion;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIbge_id(String ibge_id) {
		this.ibge_id = ibge_id;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapital(String capital) {
		this.capital = false;
		if(!capital.isEmpty()) {
			this.capital = true;
		}
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public void setNo_accents(String no_accents) {
		this.no_accents = no_accents;
	}
		
	public void setAlternative_names(String alternative_names) {
		this.alternative_names = alternative_names;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}

	@Override
	public String toString() {
			return "CityIO [id=" + id + ", ibge_id=" + ibge_id + ", uf=" + uf + ", name=" + name + ", capital=" + capital					+ ", lon=" + lon + ", lat=" + lat + ", no_accents=" + no_accents + ", alternative_names="
				+ alternative_names + ", microregion=" + microregion + ", mesoregion=" + mesoregion + "]";
	}		
}
