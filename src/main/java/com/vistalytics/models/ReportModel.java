package com.vistalytics.models;

public class ReportModel {
	private String key;

	private Double absoluteDiff;

	private Double percentageDiff;

	public ReportModel() {
	}

	public ReportModel(String key, Double absoluteDiff, Double percentageDiff) {
		super();
		this.key = key;
		this.absoluteDiff = absoluteDiff;
		this.percentageDiff = percentageDiff;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String keys) {
		this.key = keys;
	}

	public Double getAbsoluteDiff() {
		return absoluteDiff;
	}

	public void setAbsoluteDiff(Double absoluteDiff) {
		this.absoluteDiff = absoluteDiff;
	}

	public Double getPercentageDiff() {
		return percentageDiff;
	}

	public void setPercentageDiff(Double percentageDiff) {
		this.percentageDiff = percentageDiff;
	}

	@Override
	public String toString() {
		return "ReportModal [key=" + key + ", absoluteDiff=" + absoluteDiff
				+ ", percentageDiff=" + percentageDiff + "]";
	}
	
}
