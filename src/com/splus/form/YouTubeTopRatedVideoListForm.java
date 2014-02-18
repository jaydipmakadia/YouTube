/*******************************************************************************
 * Create By : Jaydip Makadia
 * Date/Time : 20-July-2010/06:16 PM
 * Organization : SPEC-INDIA, Ahmedabad 
 *******************************************************************************/
package com.splus.form;

import com.splus.bean.YouTubeFeedBean;
import com.splus.bean.YouTubeVideoDetailBean;
import com.splus.renderer.VideoListRenderer;
import com.sun.lwuit.Command;
import com.sun.lwuit.Form;
import com.sun.lwuit.List;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;

public class YouTubeTopRatedVideoListForm implements ActionListener  {
	private Form youTubeVideoForm = null;
	private List youTubeVideoList = null;
	private Command playCmd = new Command("Play");
	private Command exitCmd = new Command("Exit");
	
	public YouTubeTopRatedVideoListForm(YouTubeFeedBean youTubeFeed)
	{
		youTubeVideoForm = new Form(youTubeFeed.getFeedTitle());
		
		youTubeVideoForm.setLayout(new BorderLayout());
		
		youTubeVideoList = new List();
		
		for (int i = 0; i < youTubeFeed.getYouTubeVideoDetail().size(); i++)
		{
			YouTubeVideoDetailBean youTubeVideEntry = (YouTubeVideoDetailBean) youTubeFeed.getYouTubeVideoDetail().elementAt(i);
			youTubeVideoList.addItem(youTubeVideEntry);
		}
		
		youTubeVideoList.setFixedSelection(List.FIXED_NONE_CYCLIC);
		youTubeVideoList.setListCellRenderer(new VideoListRenderer());
		
		youTubeVideoForm.addComponent(BorderLayout.CENTER,youTubeVideoList);
		
		youTubeVideoForm.addCommand(exitCmd);
		youTubeVideoForm.addCommand(playCmd);
		
		youTubeVideoForm.addCommandListener(this);
		
		youTubeVideoForm.show();
	}
	
	public void actionPerformed(ActionEvent arg0) {
	}
}
