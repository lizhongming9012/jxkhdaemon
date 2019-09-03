package org.jxkh.common.tree;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CheckTreeNode implements Serializable{

	private int id;            
    private String text;          
    private String cls;            
    private boolean leaf;        
    private String href;           
    private String hrefTarget;     
    private boolean expandable;   
    private String description;
    private boolean checked;
    
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {//确定树显示字体大小，解决IE8显示问题
		this.text = "<span style='font-size:11px'>"+text+"</span>";
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getHrefTarget() {
		return hrefTarget;
	}
	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}
	public boolean isExpandable() {
		return expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}
