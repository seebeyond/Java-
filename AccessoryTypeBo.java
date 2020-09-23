package model.accessory;

public class AccessoryTypeBo {
	private String autoID = "";
	private String owner = "";
	private String ownerID = "";
	private String ownerLevel = "";
	private String catalogValue = "";
	private String flag = "0";
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getAutoID() {
		return autoID;
	}
	public void setAutoID(String autoID) {
		this.autoID = autoID;
	}
	public String getCatalogValue() {
		return catalogValue;
	}
	public void setCatalogValue(String catalogValue) {
		this.catalogValue = catalogValue;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getOwnerLevel() {
		return ownerLevel;
	}
	public void setOwnerLevel(String ownerLevel) {
		this.ownerLevel = ownerLevel;
	}
}
