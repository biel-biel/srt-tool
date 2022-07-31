package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Subtitle {
	
	private Date startTime;
	private Date endTime;
	private Date duration;
	private Date timeBefore;
	private Integer subtitleNumber;
	private List<String> subtitleStringList = new ArrayList<String>();
	public Subtitle(Date startTime, Date endTime, Date duration, Integer subtitleNumber,
			List<String> subtitleStringList) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.subtitleNumber = subtitleNumber;
		this.subtitleStringList = subtitleStringList;
		this.timeBefore = new Date(startTime.getTime() - endTime.getTime());
	}
	
	
	public List<Subtitle> generateEditedSubtitleList(Double percentage) {
		
	}
	
}
