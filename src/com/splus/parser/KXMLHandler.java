/*******************************************************************************
 * Create By : Jaydip Makadia
 * Date/Time : 20-July-2010/06:16 PM
 * Organization : SPEC-INDIA, Ahmedabad 
 *******************************************************************************/
package com.splus.parser;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import com.splus.util.Debug;
import com.splus.bean.*;
import com.splus.util.FeedURLConstant;

public class KXMLHandler
{
	private RecordStore recordStore = null;
	private KXmlParser parser = null;
	private HttpConnection connection = null;
	private InputStream in =null;
	private String youTubeFeedURL=null;
	public YouTubeFeedBean youTubeFeedBean = null;
	private YouTubeVideoDetailBean youTubeEntry = null;
	private MediaContentBean mediaContactBean = null;
	private boolean isFirstMediaContent = true;
	public KXMLHandler(String youTubeFeedURL)
	{
		this.youTubeFeedURL = youTubeFeedURL;
	}
	
	public void beginParse()
	{
		try
		{
			recordStore = RecordStore.openRecordStore(FeedURLConstant.YOUTUBE_VIDEO_DB, true);
	   	    connection = (HttpConnection) Connector.open(youTubeFeedURL);
	   	    in = connection.openInputStream();
		    parser = new KXmlParser();
		    parser.setInput(in,"UTF-8");
		    youTubeFeedBean = new YouTubeFeedBean();
			parse(parser);
			
			System.out.println("feedTotalResults:::"+youTubeFeedBean.getFeedTotalResults());
			System.out.println("Number Video:::"+youTubeFeedBean.getYouTubeVideoDetail().size());
			
			youTubeFeedBean.setNumberOfYouTubeEntry(youTubeFeedBean.getYouTubeVideoDetail().size());
			youTubeFeedBean.storeDataIntoRMS(recordStore);
			
			recordStore.closeRecordStore();
		}	
		catch (Exception error)
		{
			Debug.errorLog("KXMLHandler:beginParse:"+ error.getMessage().toString());
		}
		finally
		{
			try
			{
				recordStore.closeRecordStore();
			}
			catch(Exception error){}
		}
	}
	
	public void parse(KXmlParser parser)
	{
		try
		{
			String currentTagName = "";
			while (parser.next() != XmlPullParser.END_DOCUMENT)
			{
				currentTagName = parser.getName();
				System.out.println("tagName::" + currentTagName);
				if(parser.getEventType() == XmlPullParser.START_TAG && currentTagName.equals("entry"))
				{
					youTubeEntry = new YouTubeVideoDetailBean();
					while(parser.next() != XmlPullParser.END_DOCUMENT)
					{
						currentTagName = parser.getName();
						System.out.println("tagName in inner while::" + currentTagName);
						if(parser.getEventType() == XmlPullParser.END_TAG && currentTagName.equals("feed"))
						{
							youTubeFeedBean.getYouTubeVideoDetail().addElement(youTubeEntry);
							parser = null;
							currentTagName = null;
							return;
						}
						else if(parser.getEventType() == XmlPullParser.START_TAG && currentTagName.equals("entry"))
						{
							isFirstMediaContent = true;
							youTubeFeedBean.getYouTubeVideoDetail().addElement(youTubeEntry);
							youTubeEntry = new YouTubeVideoDetailBean();
						}
						else
						{
							if(parser.getEventType() == XmlPullParser.START_TAG)
							{
								if(currentTagName.equals("id"))
								{
									parser.next();
									if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
									{
										youTubeEntry.setVedioID(parser.getText().trim());
									}
								}
								else if(currentTagName.equals("published"))
								{
									parser.next();
									if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
									{
										youTubeEntry.setVedioPublished(parser.getText().trim());
									}
								}
								else if(currentTagName.equals("updated"))
								{
									parser.next();
									if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
									{
										youTubeEntry.setVedioUpdated(parser.getText().trim());
									}
								}
								else if(currentTagName.equals("content"))
								{
									youTubeEntry.setContentType(parser.getAttributeValue(0));
									if(parser.getAttributeValue(0).trim().equals("text")){
										parser.next();
										if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
										{
											youTubeEntry.setContentSrc(parser.getText().trim());
										}
									}
								}
								else if(currentTagName.equals("name"))
								{
									parser.next();
									if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
									{
										youTubeEntry.setAuthorName(parser.getText().trim());
									}
								}
								else if(currentTagName.equals("uri"))
								{
									parser.next();
									if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
									{
										youTubeEntry.setAuthorURI(parser.getText().trim());
									}
								}
								else if(currentTagName.equals("title"))
								{
									parser.next();
									if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
									{
										youTubeEntry.setMediaTitle(parser.getText().trim());
									}
								}
								else if(currentTagName.equals("media:description"))
								{
									parser.next();
									if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
									{
										youTubeEntry.setMediaDescription(parser.getText().trim());
									}
								}
								else if(currentTagName.equals("yt:statistics"))
								{
									youTubeEntry.setStatisticsViewCount(Integer.parseInt(parser.getAttributeValue(0)));
									youTubeEntry.setStatisticsfavoriteCount(Integer.parseInt(parser.getAttributeValue(1)));
								}
								else if(currentTagName.equals("gd:rating"))
								{
									youTubeEntry.setAvgRaters(Float.parseFloat(parser.getAttributeValue(0)));
									youTubeEntry.setNumOfRaters(Integer.parseInt(parser.getAttributeValue(3)));
								}
								else if(currentTagName.equals("media:content"))
								{
									if(isFirstMediaContent)
									{
										mediaContactBean = new MediaContentBean();
										mediaContactBean.setMediaContentUrl(parser.getAttributeValue(0));
										mediaContactBean.setMediaContentType(parser.getAttributeValue(1));
										mediaContactBean.setMediaContentMedium(parser.getAttributeValue(2));
										mediaContactBean.setMediaContentIsDefault(parser.getAttributeValue(3));
										mediaContactBean.setMediaContentExpression(parser.getAttributeValue(4));
										mediaContactBean.setMediaContentDuration(parser.getAttributeValue(5));
										mediaContactBean.setMediaContentYTFormat(parser.getAttributeValue(6));
										youTubeEntry.getMediaContent().addElement(mediaContactBean);
										isFirstMediaContent = false;
									}
									else if(!isFirstMediaContent)
									{
										mediaContactBean = new MediaContentBean();
										mediaContactBean.setMediaContentUrl(parser.getAttributeValue(0));
										mediaContactBean.setMediaContentType(parser.getAttributeValue(1));
										mediaContactBean.setMediaContentMedium(parser.getAttributeValue(2));
										mediaContactBean.setMediaContentIsDefault("false");
										mediaContactBean.setMediaContentExpression(parser.getAttributeValue(3));
										mediaContactBean.setMediaContentDuration(parser.getAttributeValue(4));
										mediaContactBean.setMediaContentYTFormat(parser.getAttributeValue(5));
										youTubeEntry.getMediaContent().addElement(mediaContactBean);
									}
								}
							}
						}
					}
				}
				else if(parser.getEventType() == XmlPullParser.START_TAG)
				{
					if(currentTagName.equals("id"))
					{
						parser.next();
						if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
						{
							youTubeFeedBean.setFeedID(parser.getText().trim());
						}
					}
					else if(currentTagName.equals("updated"))
					{
						parser.next();
						if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
						{
							youTubeFeedBean.setFeedUpdated(parser.getText().trim());
						}
					}
					else if(currentTagName.equals("title"))
					{
						parser.next();
						if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
						{
							youTubeFeedBean.setFeedTitle(parser.getText().trim());
						}
					}
					else if(currentTagName.equals("logo"))
					{
						parser.next();
						if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
						{
							youTubeFeedBean.setFeedLogo(parser.getText().trim());
						}
					}
					else if(currentTagName.equals("openSearch:totalResults"))
					{
						parser.next();
						System.out.println("parser.getText().trim():openSearch:totalResults:"+parser.getText().trim());
						if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
						{
							youTubeFeedBean.setFeedTotalResults(parser.getText().trim());
						}
					}
					else if(currentTagName.equals("openSearch:startIndex"))
					{
						parser.next();
						if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
						{
							youTubeFeedBean.setFeedStartIndex(parser.getText().trim());
						}
					}
					else if(currentTagName.equals("openSearch:itemsPerPage"))
					{
						parser.next();
						if(parser.getEventType() == XmlPullParser.TEXT && parser.getText().trim().length() > 0)
						{
							youTubeFeedBean.setFeedItemsPerPage(parser.getText().trim());
						}
					}
				}
			}
		}
		catch(Exception error)
		{
			Debug.errorLog("KXMLHandler:parse:"+ error.getMessage().toString());
		}
	}

	public void checkInRMS()
	{
		try
		{
			System.gc();
			recordStore = RecordStore.openRecordStore(FeedURLConstant.YOUTUBE_VIDEO_DB, false);
			RecordEnumeration recordEnumeration = recordStore.enumerateRecords(null, null, false);
			if (recordEnumeration.numRecords() > 0)
			{
				if(recordEnumeration.hasNextElement())
				{
					int recordID = recordEnumeration.nextRecordId();
					if (recordID >= 1)
					{
						byte[] data = recordStore.getRecord(recordID);
						ByteArrayInputStream bin = new ByteArrayInputStream(data);
						DataInputStream din = new DataInputStream(bin);
						youTubeFeedBean = new YouTubeFeedBean();
						youTubeFeedBean.setFeedID(din.readUTF());
						youTubeFeedBean.setFeedUpdated(din.readUTF());
						youTubeFeedBean.setFeedTitle(din.readUTF());
						youTubeFeedBean.setFeedLogo(din.readUTF());
						youTubeFeedBean.setFeedTotalResults(din.readUTF());
						youTubeFeedBean.setFeedStartIndex(din.readUTF());
						youTubeFeedBean.setFeedItemsPerPage(din.readUTF());
						youTubeFeedBean.setNumberOfYouTubeEntry(din.readInt());
						for(int i=0;i<youTubeFeedBean.getNumberOfYouTubeEntry();i++)
						{
							System.gc();
							youTubeEntry = new YouTubeVideoDetailBean();
							youTubeEntry.setVedioID(din.readUTF());
							youTubeEntry.setVedioPublished(din.readUTF());
							youTubeEntry.setVedioUpdated(din.readUTF());
							youTubeEntry.setContentType(din.readUTF());
							youTubeEntry.setContentSrc(din.readUTF());
							youTubeEntry.setAuthorName(din.readUTF());
							youTubeEntry.setAuthorURI(din.readUTF());
							youTubeEntry.setMediaTitle(din.readUTF());
							youTubeEntry.setMediaDescription(din.readUTF());
							youTubeEntry.setStatisticsViewCount(din.readInt());
							youTubeEntry.setStatisticsfavoriteCount(din.readInt());
							youTubeEntry.setNumOfRaters(din.readInt());
							youTubeEntry.setAvgRaters(din.readFloat());
							youTubeEntry.setNumberOfMediaContent(din.readInt());
							for(int j=0;j<youTubeEntry.getNumberOfMediaContent();j++)
							{
								System.gc();
								mediaContactBean = new MediaContentBean();
								mediaContactBean.setMediaContentUrl(din.readUTF());
								mediaContactBean.setMediaContentType(din.readUTF());
								mediaContactBean.setMediaContentMedium(din.readUTF());
								mediaContactBean.setMediaContentIsDefault(din.readUTF());
								mediaContactBean.setMediaContentExpression(din.readUTF());
								mediaContactBean.setMediaContentDuration(din.readUTF());
								mediaContactBean.setMediaContentYTFormat(din.readUTF());
								youTubeEntry.getMediaContent().addElement(mediaContactBean);
								System.gc();
							}
							youTubeFeedBean.getYouTubeVideoDetail().addElement(youTubeEntry);
							System.gc();
						}
					}
				}
			}
		}
		catch(Exception error)
		{
			Debug.errorLog("KXMLHandler:checkInRMS:"+ error.getMessage().toString());
		}
		finally
		{
			try
			{
				recordStore.closeRecordStore();
			}
			catch(Exception error){}
		}
	}
}
