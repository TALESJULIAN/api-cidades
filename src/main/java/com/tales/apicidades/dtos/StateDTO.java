package com.tales.apicidades.dtos;

/**
 * 
 * @author Tales
 *
 */
public class StateDTO {

	private String uf;
	private Integer numberOfCities;
	
	public StateDTO() {
		
	}
	
	/**
	 * 
	 * @param uf
	 * @param numberOfCities
	 */
	public StateDTO(String uf, Integer numberOfCities) {
		super();
		this.uf = uf;
		this.numberOfCities = numberOfCities;
	}
	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}
	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}
	/**
	 * @return the numberOfCities
	 */
	public Integer getNumberOfCities() {
		return numberOfCities;
	}
	/**
	 * @param numberOfCities the numberOfCities to set
	 */
	public void setNumberOfCities(Integer numberOfCities) {
		this.numberOfCities = numberOfCities;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numberOfCities == null) ? 0 : numberOfCities.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateDTO other = (StateDTO) obj;
		if (numberOfCities == null) {
			if (other.numberOfCities != null)
				return false;
		} else if (!numberOfCities.equals(other.numberOfCities))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "State [uf=" + uf + ", numberOfCities=" + numberOfCities + "]";
	}
	
	
}
