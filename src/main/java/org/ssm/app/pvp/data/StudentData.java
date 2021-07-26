package org.ssm.app.pvp.data;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentData extends CommonDataFields {

	protected String alumniName;
	protected String studentName;
	protected String highestClass;
	protected String passoutYear;

	public String getAlumniName() {
		return alumniName;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getHighestClass() {
		return highestClass;
	}

	public String getPassoutYear() {
		return passoutYear;
	}

	@Override
	public int hashCode() {
		if (alumniName != null) {
			return alumniName.replaceAll("\\s", "").toLowerCase().hashCode() + schoolId.hashCode();
		}else {
			return studentName.replaceAll("\\s", "").toLowerCase().hashCode() + schoolId.hashCode();
		}
		
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
	@Override
	public boolean equals(Object other) {
		if ((other instanceof StudentData)) {
			StudentData otherData = (StudentData) other;
			String firstName;
			if (otherData.alumniName != null) {
				firstName = otherData.getAlumniName();
			}else {
				firstName = otherData.getStudentName();
			}
			String secondName;
			if (this.getAlumniName() != null) {
				secondName = this.getAlumniName();
			}{
				secondName = this.getStudentName();
			}
			return (firstName != null && secondName != null) && firstName.replaceAll("\\s", "").toLowerCase()
					.equals(secondName.replaceAll("\\s", "").toLowerCase())
					&& otherData.getSchoolId().equals(this.getSchoolId());
		}
		return false;
	}
}
