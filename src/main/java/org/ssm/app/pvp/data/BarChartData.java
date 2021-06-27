package org.ssm.app.pvp.data;

import java.util.List;

public class BarChartData {

	private String[] categories;
	
	private Data[] series;

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public Data[] getSeries() {
		return series;
	}

	public void setSeries(Data[] series) {
		this.series = series;
	}
	public Data newData() {
		return new Data();
	}
	
	public class Data {
		private String name;
		private List<Integer> data;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Integer> getData() {
			return data;
		}
		public void setData(List<Integer> data) {
			this.data = data;
		}
		
		

	}
}
