package com.is.textToSpeack.model;

public class TextToSpeachRequest {
	private String speachText;

	public TextToSpeachRequest(String speachText) {
		this.speachText=speachText;
	}
	
	public TextToSpeachRequest() {
		
	}
	

	public String getSpeachText() {
		return speachText;
	}

	public void setSpeachText(String speachText) {
		this.speachText = speachText;
	}

}
