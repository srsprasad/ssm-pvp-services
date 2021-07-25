package org.ssm.app.pvp.data;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FilterData {
	
	private String key;
	private String name;
	private List<Integer> schoolIdList;
	private boolean checked;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getSchoolIdList() {
		return schoolIdList;
	}
	public void setSchoolIdList(List<Integer> schoolIdList) {
		this.schoolIdList = schoolIdList;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	@Override
	public String toString() {		
		return ToStringBuilder.reflectionToString(this);
	}
}
