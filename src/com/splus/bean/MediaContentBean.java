/*******************************************************************************
 * Create By : Jaydip Makadia
 * Date/Time : 20-July-2010/06:16 PM
 * Organization : SPEC-INDIA, Ahmedabad 
 *******************************************************************************/
package com.splus.bean;

public class MediaContentBean {
	private String mediaContentUrl;
	private String mediaContentType;
	private String mediaContentMedium;
	private String mediaContentIsDefault;
	private String mediaContentExpression;
	private String mediaContentDuration;

	public String getMediaContentMedium() {
		return mediaContentMedium;
	}

	public void setMediaContentMedium(String mediaContentMedium) {
		this.mediaContentMedium = mediaContentMedium;
	}

	public String getMediaContentUrl() {
		return mediaContentUrl;
	}

	public void setMediaContentUrl(String mediaContentUrl) {
		this.mediaContentUrl = mediaContentUrl;
	}

	public String getMediaContentType() {
		return mediaContentType;
	}

	public void setMediaContentType(String mediaContentType) {
		this.mediaContentType = mediaContentType;
	}

	public String getMediaContentIsDefault() {
		return mediaContentIsDefault;
	}

	public void setMediaContentIsDefault(String mediaContentIsDefault) {
		this.mediaContentIsDefault = mediaContentIsDefault;
	}

	public String getMediaContentExpression() {
		return mediaContentExpression;
	}

	public void setMediaContentExpression(String mediaContentExpression) {
		this.mediaContentExpression = mediaContentExpression;
	}

	public String getMediaContentDuration() {
		return mediaContentDuration;
	}

	public void setMediaContentDuration(String mediaContentDuration) {
		this.mediaContentDuration = mediaContentDuration;
	}

	public String getMediaContentYTFormat() {
		return mediaContentYTFormat;
	}

	public void setMediaContentYTFormat(String mediaContentYTFormat) {
		this.mediaContentYTFormat = mediaContentYTFormat;
	}

	private String mediaContentYTFormat;

}
