package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Subtitle {
	
	private Date startTime;
	private Date endTime;
	private Date duration;
	private Integer subtitleNumber;
	private List<String> subtitleStringList = new ArrayList<String>();
	
	public Subtitle(Date startTime, Date endTime, Integer subtitleNumber, List<String> subtitleStringList) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.subtitleNumber = subtitleNumber;
		this.subtitleStringList = subtitleStringList;
	}
	
	
}
