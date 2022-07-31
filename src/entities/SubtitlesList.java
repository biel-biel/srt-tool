package entities;

import java.util.ArrayList;
import java.util.List;

public class SubtitlesList {
	
	private List<Subtitle> subtitleList = new ArrayList<Subtitle>();
	private Double percentage;
	
	public SubtitlesList(Double percentage) {
		this.percentage = percentage;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public List<Subtitle> getSubtitleList() {
		return subtitleList;
	}
	
	public void addSubtitle(Subtitle subtitle) {
		subtitleList.add(subtitle);
	}
	
	public void remSubtitle(Subtitle subtitle) {
		subtitleList.remove(subtitle);
	}
	
}
