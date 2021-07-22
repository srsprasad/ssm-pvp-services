package org.ssm.app.pvp.service;

import java.util.List;

import org.ssm.app.pvp.data.BarChartData;
import org.ssm.app.pvp.data.GroupChoiceData;

public interface SchoolDataService {

	public List<GroupChoiceData> schoolMetadataByGroupType();
	
	public BarChartData processBarChartData(boolean includeStudents, boolean includeTeachers, String[] regions);
}
