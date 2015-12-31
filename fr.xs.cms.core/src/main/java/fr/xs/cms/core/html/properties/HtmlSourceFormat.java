package fr.xs.cms.core.html.properties;

public enum HtmlSourceFormat {
	APPLICATION("application/x-download"),
	AUDIO_WAV("audio/wav"), AUDIO_OGG("audio/ogg"), AUDIO_WEBM("audio/webm"), AUDIO_MP3("audio/mp3"),
							VIDEO_OGG("video/ogg"), VIDEO_WEBM("video/webm"), VIDEO_MPEG4("video/mp4"), VIDEO_3GP("video/3gp");
	
	String htmlTag;
	
	private HtmlSourceFormat(String _fmt) {
		htmlTag = _fmt;
	}
	
	public String toHtml() { return htmlTag; }

}
