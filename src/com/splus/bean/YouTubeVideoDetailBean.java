/*******************************************************************************
 * Create By : Jaydip Makadia
 * Date/Time : 20-July-2010/06:16 PM
 * Organization : SPEC-INDIA, Ahmedabad 
 *******************************************************************************/
package com.splus.bean;

import java.util.Vector;

public class YouTubeVideoDetailBean {
	private String vedioID;
	private String vedioPublished;
	private String vedioUpdated;
	private String contentType;
	private String contentSrc;
	private String authorName;
	private String authorURI;
	private String mediaTitle;
	private String mediaDescription;
	private int statisticsViewCount;
	private int statisticsfavoriteCount;
	private int numOfRaters;	
	private float avgRaters;
	private int numberOfMediaContent;
	private Vector mediaContent = new Vector();
	
	public int getNumberOfMediaContent() {
		return numberOfMediaContent;
	}

	public void setNumberOfMediaContent(int numberOfMediaContent) {
		this.numberOfMediaContent = numberOfMediaContent;
	}

	public Vector getMediaContent() {
		return mediaContent;
	}

	public void setMediaContent(Vector mediaContent) {
		this.mediaContent = mediaContent;
	}

	public String getVedioID() {
		return vedioID;
	}

	public void setVedioID(String vedioID) {
		this.vedioID = vedioID;
	}

	public String getVedioPublished() {
		return vedioPublished;
	}

	public void setVedioPublished(String vedioPublished) {
		this.vedioPublished = vedioPublished;
	}

	public String getVedioUpdated() {
		return vedioUpdated;
	}

	public void setVedioUpdated(String vedioUpdated) {
		this.vedioUpdated = vedioUpdated;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentSrc() {
		return contentSrc;
	}

	public void setContentSrc(String contentSrc) {
		this.contentSrc = contentSrc;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorURI() {
		return authorURI;
	}

	public void setAuthorURI(String authorURI) {
		this.authorURI = authorURI;
	}

	public String getMediaTitle() {
		return mediaTitle;
	}

	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}

	public String getMediaDescription() {
		return mediaDescription;
	}

	public void setMediaDescription(String mediaDescription) {
		this.mediaDescription = mediaDescription;
	}

	public int getStatisticsViewCount() {
		return statisticsViewCount;
	}

	public void setStatisticsViewCount(int statisticsViewCount) {
		this.statisticsViewCount = statisticsViewCount;
	}

	public int getStatisticsfavoriteCount() {
		return statisticsfavoriteCount;
	}

	public void setStatisticsfavoriteCount(int statisticsfavoriteCount) {
		this.statisticsfavoriteCount = statisticsfavoriteCount;
	}

	public int getNumOfRaters() {
		return numOfRaters;
	}

	public void setNumOfRaters(int numOfRaters) {
		this.numOfRaters = numOfRaters;
	}

	public float getAvgRaters() {
		return avgRaters;
	}

	public void setAvgRaters(float avgRaters) {
		this.avgRaters = avgRaters;
	}


}
