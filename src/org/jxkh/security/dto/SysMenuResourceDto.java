package org.jxkh.security.dto;

public class SysMenuResourceDto {

	private String menuId;
	private String parentId;
	private String display;
    private String resourceString;
    private String description;
    private String dispindex;
    private String iconuri;
    
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getResourceString() {
		return resourceString;
	}
	public void setResourceString(String resourceString) {
		this.resourceString = resourceString;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDispindex() {
		return dispindex;
	}
	public void setDispindex(String dispindex) {
		this.dispindex = dispindex;
	}
	public String getIconuri() {
		return iconuri;
	}
	public void setIconuri(String iconuri) {
		this.iconuri = iconuri;
	}
    
}
