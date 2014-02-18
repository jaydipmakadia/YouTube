/*******************************************************************************
 * Create By : Jaydip Makadia
 * Date/Time : 20-July-2010/06:16 PM
 * Organization : SPEC-INDIA, Ahmedabad 
 *******************************************************************************/
package com.splus.renderer;

import com.splus.bean.YouTubeVideoDetailBean;
import com.sun.lwuit.Component;
import com.sun.lwuit.Container;
import com.sun.lwuit.Label;
import com.sun.lwuit.List;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.list.ListCellRenderer;

public class VideoListRenderer extends Container implements ListCellRenderer {
	private Label youTubeLogoLbl = new Label("");
	private Label videoTitleLbl = new Label("");
	private Label videoDescLbl = new Label("");
	private Label videoAuthorLbl = new Label("");
	private Label videoRateLbl = new Label("");
	private Label focus = new Label("");
	
	public VideoListRenderer()
	{
		System.gc();
		setLayout(new BorderLayout());
		Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container cnt1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container cnt2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container cnt3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
		
		/*Style style = new  Style();
		Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
		style.setFont(font);
		
		videoTitleLbl.setStyle(style);
		videoDescLbl.setStyle(style);
		videoAuthorLbl.setStyle(style);*/
		youTubeLogoLbl.getStyle().setBgTransparency(0);
		videoTitleLbl.getStyle().setBgTransparency(0);
		videoDescLbl.getStyle().setBgTransparency(0);
		videoAuthorLbl.getStyle().setBgTransparency(0);
		videoRateLbl.getStyle().setBgTransparency(0);
		
		cnt1.addComponent(youTubeLogoLbl);
		cnt2.addComponent(videoTitleLbl);
		cnt2.addComponent(videoDescLbl);
		cnt2.addComponent(videoAuthorLbl);
		cnt2.addComponent(videoRateLbl);
		cnt3.addComponent(cnt1);
		cnt3.addComponent(cnt2);
		cnt.addComponent(cnt3);
		addComponent(BorderLayout.CENTER, cnt);
	}
	
	public Component getListCellRendererComponent(com.sun.lwuit.List list, Object value, int index, boolean isSelected)
	{
		try
		{
			System.gc();
			YouTubeVideoDetailBean youTubeVideoEntry = (YouTubeVideoDetailBean) value;
			youTubeLogoLbl.setIcon(com.sun.lwuit.Image.createImage("/YouTubeLogo.png"));
			videoTitleLbl.setText(youTubeVideoEntry.getMediaTitle());
			videoDescLbl.setText(youTubeVideoEntry.getMediaDescription());
			videoAuthorLbl.setText(youTubeVideoEntry.getAuthorName());
			if(youTubeVideoEntry.getAvgRaters() >= 1 && youTubeVideoEntry.getAvgRaters() < 2)
			{
				videoRateLbl.setIcon(com.sun.lwuit.Image.createImage("/1Star.png"));
			}
			else if(youTubeVideoEntry.getAvgRaters() >= 2 && youTubeVideoEntry.getAvgRaters() < 3)
			{
				videoRateLbl.setIcon(com.sun.lwuit.Image.createImage("/2Star.png"));
			}
			else if(youTubeVideoEntry.getAvgRaters() >= 3 && youTubeVideoEntry.getAvgRaters() < 4)
			{
				videoRateLbl.setIcon(com.sun.lwuit.Image.createImage("/3Star.png"));
			}
			else if(youTubeVideoEntry.getAvgRaters() >= 4 && youTubeVideoEntry.getAvgRaters() < 5)
			{
				videoRateLbl.setIcon(com.sun.lwuit.Image.createImage("/4Star.png"));
			}
			else if(youTubeVideoEntry.getAvgRaters() >= 5 && youTubeVideoEntry.getAvgRaters() < 6)
			{
				videoRateLbl.setIcon(com.sun.lwuit.Image.createImage("/5Star.png"));
			}
			if (isSelected) 
			{
				this.setFocus(true);
				this.getStyle().setBgTransparency(100);
			}
			else 
			{
				this.setFocus(false);
				this.getStyle().setBgTransparency(0);
			}
			System.gc();
		}
		catch(Exception error)
		{
			error.printStackTrace();
		}
		return this;
	}

	public Component getListFocusComponent(List arg0) 
	{
		System.gc();
		return focus;
	}

}
