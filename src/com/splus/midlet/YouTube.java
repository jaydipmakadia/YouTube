/*******************************************************************************
 * Create By : Jaydip Makadia
 * Date/Time : 20-July-2010/06:16 PM
 * Organization : SPEC-INDIA, Ahmedabad 
 *******************************************************************************/
package com.splus.midlet;

import java.io.InputStream;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

import com.splus.form.YouTubeTopRatedVideoListForm;
import com.splus.parser.KXMLHandler;
import com.splus.util.Debug;
import com.splus.util.FeedURLConstant;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.plaf.Border;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;

public class YouTube extends MIDlet {
	private static Resources resources = null;
	private boolean firstTime= false;
	private RecordStore displayRecordStore = null;
	private KXMLHandler kxmlHandler = null;
	protected com.sun.lwuit.Dialog progress =null;
	
	public YouTube()
	{
		firstTime=true;
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException
	{
	}

	protected void pauseApp()
	{
	}

	protected void startApp() throws MIDletStateChangeException 
	{
		try
		{
			if(firstTime)
			{
				System.gc();
				
				Display.init(this);
				
				InputStream stream = getClass().getResourceAsStream("/GUITheme.res");
				
				resources = Resources.open(stream);
				
				UIManager.getInstance().setThemeProps(resources.getTheme("SplusThemes"));
				
				kxmlHandler = new KXMLHandler(FeedURLConstant.YOUTUBE_TOP_RATED_STANDARD_FEED);
				
				startParsingYouTubeStandardFeed();
				
	            System.gc();
			}
		}
		catch(Exception error)
		{
			
		}
	}
	
	private void startParsingYouTubeStandardFeed()
	{
		try
		{
			System.gc();
			//UIManager.getInstance().setThemeProps(resources.getTheme("SplusThemes"));
			setLoadingScreen();
			new Thread()
			{
				public void run()
				{
					try
					{
						System.gc();
						deleteOldYouTubeFeedData();
						kxmlHandler.beginParse();
						kxmlHandler.checkInRMS();
						new YouTubeTopRatedVideoListForm(kxmlHandler.youTubeFeedBean);
						System.gc();
						
					}
					catch(Exception error)
					{
						System.gc();
					}
				}
			}.start();
		}
		catch(Exception error)
		{
			System.gc();
		}
	}
	
	public void setLoadingScreen()
	{
		try
		{
			System.gc();
			if(progress!=null){
				int height = com.sun.lwuit.Display.getInstance().getDisplayHeight()- (progress.getContentPane().getPreferredH() + progress.getTitleComponent().getPreferredH());
				height /= 2;
				progress.show(height, height, 20, 20, true, false);
			}else{
				progress = new com.sun.lwuit.Dialog();
				progress.getDialogStyle().setBorder(Border.createRoundBorder(10, 10, 0xcc0033));
				progress.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, true, 400));
				progress.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_VERTICAL, false, 400));
				progress.addComponent(new Label("Please Wait"));
				progress.addComponent(new com.splus.util.InfiniteProgressIndicator(Image.createImage("/wait-circle.png")));
				int height = com.sun.lwuit.Display.getInstance().getDisplayHeight()- (progress.getContentPane().getPreferredH() + progress.getTitleComponent().getPreferredH());
				height /= 2;
				progress.show(height, height, 20, 20, true, false);
			}
			System.gc();
		}
		catch (Exception error)
		{
			System.gc();
			com.splus.util.Debug.errorLog("aPhone:setLoadingScreen:"+ error.getMessage().toString());
		}
	}
	
	public RecordStore openDisplayRecordStore()
	{
		try
		{
			System.gc();
			displayRecordStore = RecordStore.openRecordStore(FeedURLConstant.YOUTUBE_VIDEO_DB, true);
			System.gc();
		}
		catch (Exception error)
		{
			System.gc();
			Debug.errorLog("aPhone:RecordStore:" + error.getMessage().toString());
		}
		return displayRecordStore;
	}

	public void closeDisplayRecordStore() 
	{
		try
		{
			System.gc();
			displayRecordStore.closeRecordStore();
			System.gc();
		}
		catch (Exception error)
		{
			System.gc();
			Debug.errorLog("aPhone:closeRecordStore:" + error.getMessage().toString());
		}
	}

	public void deleteOldYouTubeFeedData()
	{
		try
		{
			System.gc();
			openDisplayRecordStore();
			RecordEnumeration recordEnumeration = displayRecordStore.enumerateRecords(null, null, false);
			if (recordEnumeration.numRecords() > 0)
			{
				while (recordEnumeration.hasNextElement())
				{
					int recordID = recordEnumeration.nextRecordId();
					if (recordID >= 1)
					{
						displayRecordStore.deleteRecord(recordID);
					}
				}
			}
			closeDisplayRecordStore();
			System.gc();
		}
		catch(Exception error)
		{
			System.gc();
			Debug.errorLog("deleteOldYouTubeFeedData:" + error.getMessage().toString());
		}
	}

	public void testForm(){
		Form testForm = new Form("YOUTUBE TOP RATED VIDEO");
		testForm.show();
	}
}
