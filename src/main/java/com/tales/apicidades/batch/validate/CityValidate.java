package com.tales.apicidades.batch.validate;

public class CityValidate {

	private String id;
	private String ibge_id;
	private String uf;
	private String name;
	private boolean capital;
	private String lon;
	private String lat;
	private String no_accents;
	private String alternative_names;
	private String microregion;
	private String mesoregion;
	
	public CityValidate() {
		
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
	public CityValidate(String ibge_id2, String uf2, String name2, boolean capital2, 
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
			return "Cidade [id=" + id + ", ibge_id=" + ibge_id + ", uf=" + uf + ", name=" + name + ", capital=" + capital					+ ", lon=" + lon + ", lat=" + lat + ", no_accents=" + no_accents + ", alternative_names="
				+ alternative_names + ", microregion=" + microregion + ", mesoregion=" + mesoregion + "]";
	}		
}
