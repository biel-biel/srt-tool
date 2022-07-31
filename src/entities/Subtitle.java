package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Subtitle {
	
	private Date startTime;
	private Date endTime;
	private Date duration;
	private Date timeBetween;
	private Integer subtitleNumber;
	private List<String> subtitleStringList = new ArrayList<String>();
	
	public Subtitle(Date startTime, Date endTime, Date timeBetween, Integer subtitleNumber,
			List<String> subtitleStringList) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeBetween = timeBetween;
		this.subtitleNumber = subtitleNumber;
		this.subtitleStringList = subtitleStringList;
		this.duration = new Date(startTime.getTime() - endTime.getTime());
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getDuration() {
		return duration;
	}

	public Date getTimeBetween() {
		return timeBetween;
	}

	public Integer getSubtitleNumber() {
		return subtitleNumber;
	}

	public List<String> getSubtitleStringList() {
		return subtitleStringList;
	}
	
	public void addSubtitleStringList(String subtitle) {
		subtitleStringList.add(subtitle);
	}
	
	public void remSubtitleStringList(String subtitle) {
		subtitleStringList.remove(subtitle);
	}
 
	public List<Subtitle> generateEditedSubtitleList(Double percentage) {
		
	}
	
}
