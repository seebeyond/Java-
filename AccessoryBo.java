package model.accessory;

public class AccessoryBo {
	private String autoID = "";
	private String owner = "";
	private String ownerID = "";
	private String ownerLevel = "";
	private String title = "";
	private String filepath = "";
	private String userID = "";
	private String inputDate= "";
	private String catalogID= "";
	private String docSize = "0";
	
	public String getDocSize() {
		return docSize;
	}
	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}
	public String getAutoID() {
		return autoID;
	}
	public void setAutoID(String autoID) {
		this.autoID = autoID;
	}
	public String getCatalogID() {
		return catalogID;
	}
	public void setCatalogID(String catalogID) {
		this.catalogID = catalogID;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
}
