/*******************************************************************************
 * Create By : Jaydip Makadia
 * Date/Time : 20-July-2010/06:16 PM
 * Organization : SPEC-INDIA, Ahmedabad 
 *******************************************************************************/
package com.splus.bean;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Vector;

import javax.microedition.rms.RecordStore;

public class YouTubeFeedBean {
	private String feedID;
	private String feedUpdated;
	private String feedTitle;
	private String feedLogo;
	private String feedTotalResults;
	private String feedStartIndex;
	private String feedItemsPerPage;
	private int numberOfYouTubeEntry;
	private Vector youTubeVideoDetail = new Vector();
	
	public int getNumberOfYouTubeEntry() {
		return numberOfYouTubeEntry;
	}

	public void setNumberOfYouTubeEntry(int numberOfYouTubeEntry) {
		this.numberOfYouTubeEntry = numberOfYouTubeEntry;
	}

	public String getFeedID() {
		return feedID;
	}

	public void setFeedID(String feedID) {
		this.feedID = feedID;
	}

	public String getFeedUpdated() {
		return feedUpdated;
	}

	public void setFeedUpdated(String feedUpdated) {
		this.feedUpdated = feedUpdated;
	}

	public String getFeedTitle() {
		return feedTitle;
	}

	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	public String getFeedLogo() {
		return feedLogo;
	}

	public void setFeedLogo(String feedLogo) {
		this.feedLogo = feedLogo;
	}

	public String getFeedTotalResults() {
		return feedTotalResults;
	}

	public void setFeedTotalResults(String feedTotalResults) {
		this.feedTotalResults = feedTotalResults;
	}

	public String getFeedStartIndex() {
		return feedStartIndex;
	}

	public void setFeedStartIndex(String feedStartIndex) {
		this.feedStartIndex = feedStartIndex;
	}

	public String getFeedItemsPerPage() {
		return feedItemsPerPage;
	}

	public void setFeedItemsPerPage(String feedItemsPerPage) {
		this.feedItemsPerPage = feedItemsPerPage;
	}

	public Vector getYouTubeVideoDetail() {
		return youTubeVideoDetail;
	}

	public void setYouTubeVideoDetail(Vector youTubeVideoDetail) {
		this.youTubeVideoDetail = youTubeVideoDetail;
	}
	
	public void storeDataIntoRMS(RecordStore recordStore) throws Exception
	{
		System.gc();
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		DataOutputStream dataout = new DataOutputStream(byteout);
		dataout.writeUTF(feedID);
		dataout.writeUTF(feedUpdated);
		dataout.writeUTF(feedTitle);
		dataout.writeUTF(feedLogo);
		dataout.writeUTF(feedTotalResults);
		dataout.writeUTF(feedStartIndex);
		dataout.writeUTF(feedItemsPerPage);
		dataout.writeInt(numberOfYouTubeEntry);
		for(int i=0;i<numberOfYouTubeEntry;i++)
		{
			System.gc();
			YouTubeVideoDetailBean youTubeVideoEntry = (YouTubeVideoDetailBean) youTubeVideoDetail.elementAt(i);
			System.out.println("youTubeVideoEntry.getMediaTitle():::"+youTubeVideoEntry.getMediaTitle());
			dataout.writeUTF(youTubeVideoEntry.getVedioID());
			dataout.writeUTF(youTubeVideoEntry.getVedioPublished());
			dataout.writeUTF(youTubeVideoEntry.getVedioUpdated());
			dataout.writeUTF(youTubeVideoEntry.getContentType());
			dataout.writeUTF(youTubeVideoEntry.getContentSrc());
			dataout.writeUTF(youTubeVideoEntry.getAuthorName());
			dataout.writeUTF(youTubeVideoEntry.getAuthorURI());
			dataout.writeUTF(youTubeVideoEntry.getMediaTitle());
			dataout.writeUTF(youTubeVideoEntry.getMediaDescription());
			dataout.writeInt(youTubeVideoEntry.getStatisticsViewCount());
			dataout.writeInt(youTubeVideoEntry.getStatisticsfavoriteCount());
			dataout.writeInt(youTubeVideoEntry.getNumOfRaters());
			dataout.writeFloat(youTubeVideoEntry.getAvgRaters());
			dataout.writeInt(youTubeVideoEntry.getMediaContent().size());
			System.out.println("youTubeVideoEntry.getMediaContent().size():::"+youTubeVideoEntry.getMediaContent().size());
			for(int j = 0; j < youTubeVideoEntry.getMediaContent().size();j++)
			{
				System.gc();
				MediaContentBean mediaContentBean = (MediaContentBean) youTubeVideoEntry.getMediaContent().elementAt(j);
				dataout.writeUTF(mediaContentBean.getMediaContentUrl());
				dataout.writeUTF(mediaContentBean.getMediaContentType());
				dataout.writeUTF(mediaContentBean.getMediaContentMedium());
				dataout.writeUTF(mediaContentBean.getMediaContentIsDefault());
				dataout.writeUTF(mediaContentBean.getMediaContentExpression());
				dataout.writeUTF(mediaContentBean.getMediaContentDuration());
				dataout.writeUTF(mediaContentBean.getMediaContentYTFormat());
				System.gc();
			}
			System.gc();
		}
		System.gc();
		byteout.flush();
		dataout.flush();
		dataout.close();
		byteout.close();
		recordStore.addRecord(byteout.toByteArray(), 0,byteout.toByteArray().length);
	}
}
