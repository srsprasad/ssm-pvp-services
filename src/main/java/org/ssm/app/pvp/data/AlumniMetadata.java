package org.ssm.app.pvp.data;

import java.util.List;

public class AlumniMetadata {

	private boolean teachersSelected;
	
	private boolean studentsSelected=true;
	
	private String[] selectedGroupNames;
	
	private List<GroupChoiceData> groupChoiceData;
	
	private BarChartData bartChartData;

	public boolean isTeachersSelected() {
		return teachersSelected;
	}

	public void setTeachersSelected(boolean teachersSelected) {
		this.teachersSelected = teachersSelected;
	}

	public boolean isStudentsSelected() {
		return studentsSelected;
	}

	public void setStudentsSelected(boolean studentsSelected) {
		this.studentsSelected = studentsSelected;
	}

	public String[] getSelectedGroupNames() {
		return selectedGroupNames;
	}

	public void setSelectedGroupNames(String[] selectedGroupName) {
		this.selectedGroupNames = selectedGroupName;
	}

	public List<GroupChoiceData> getGroupChoiceData() {
		return groupChoiceData;
	}

	public void setGroupChoiceData(List<GroupChoiceData> groupChoiceData) {
		this.groupChoiceData = groupChoiceData;
	}

	public BarChartData getBartChartData() {
		return bartChartData;
	}

	public void setBartChartData(BarChartData bartChartData) {
		this.bartChartData = bartChartData;
	}
	
	
}
