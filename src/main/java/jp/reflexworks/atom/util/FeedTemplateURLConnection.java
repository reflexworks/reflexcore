package jp.reflexworks.atom.util;

import java.net.HttpURLConnection;

import jp.reflexworks.atom.entry.FeedBase;

public class FeedTemplateURLConnection  {
	
	public FeedBase feed;
	
	public HttpURLConnection http;
	
	FeedTemplateURLConnection(HttpURLConnection http, FeedBase feed) {
		this.http = http;
		this.feed = feed;
	}

	public FeedBase getFeed() {
		return feed;
	}

	public void setFeed(FeedBase feed) {
		this.feed = feed;
	}

	public HttpURLConnection getHttp() {
		return http;
	}

	public void setHttp(HttpURLConnection http) {
		this.http = http;
	}

	@Override
	public String toString() {
		return "FeedTemplateURLConnection [feed=" + feed + ", http=" + http
				+ "]";
	}

}
