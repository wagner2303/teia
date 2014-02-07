package br.ufmt.teia.model;

public class WiFiNetwork {

	private Integer codWiFi;
	private String BSSID;
	private String SSID;
	
	public WiFiNetwork() {
		super();
	}
	
	public WiFiNetwork(String sSID) {
		super();
		SSID = sSID;
	}
	
	public WiFiNetwork(String bSSID, String sSID) {
		super();
		BSSID = bSSID;
		SSID = sSID;
	}
	
	public WiFiNetwork(Integer codWiFi, String bSSID, String sSID) {
		super();
		this.codWiFi = codWiFi;
		BSSID = bSSID;
		SSID = sSID;
	}
	
	// Getters
	public Integer getCodWiFi() {
		return codWiFi;
	}
	
	public String getBSSID() {
		return BSSID;
	}
	
	public String getSSID() {
		return SSID;
	}
	
	// Setters
	public void setCodWiFi(Integer codWiFi) {
		this.codWiFi = codWiFi;
	}
	
	public void setBSSID(String bSSID) {
		BSSID = bSSID;
	}
	
	public void setSSID(String sSID) {
		SSID = sSID;
	}
	
	
}
