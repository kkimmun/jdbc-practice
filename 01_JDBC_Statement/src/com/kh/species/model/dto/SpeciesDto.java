package com.kh.species.model.dto;

public class SpeciesDto {
	private String speciesId;
	private String speciesName;
	private String speicesClass;
	
	
	public SpeciesDto() {}

	public SpeciesDto(String speciesName, String speicesClass) {
		this.speciesName = speciesName;
		this.speicesClass = speicesClass;
	}

	public SpeciesDto(String speciesId, String speciesName, String speicesClass) {
		this.speciesId = speciesId;
		this.speciesName = speciesName;
		this.speicesClass = speicesClass;
	}

	public String getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}

	public String getSpeciesName() {
		return speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}

	public String getSpeicesClass() {
		return speicesClass;
	}

	public void setSpeicesClass(String speicesClass) {
		this.speicesClass = speicesClass;
	}

	@Override
	public String toString() {
		return "SpeciesDto [speciesId=" + speciesId + ", speciesName=" + speciesName + ", speicesClass=" + speicesClass
				+ "]";
	}
	
	
	

}
