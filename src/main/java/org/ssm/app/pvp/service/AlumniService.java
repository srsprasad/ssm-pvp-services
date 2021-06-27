package org.ssm.app.pvp.service;

import java.util.List;

import org.ssm.app.pvp.data.BarChartData;
import org.ssm.app.pvp.data.GroupChoiceData;

public interface AlumniService {

	public List<GroupChoiceData> alumniMetadataByGroupType();
	
	public BarChartData processBarChartData(boolean includeStudents, boolean includeTeachers, String[] regions);
}
