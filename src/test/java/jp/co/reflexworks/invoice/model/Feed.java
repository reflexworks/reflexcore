package jp.co.reflexworks.invoice.model;

import java.io.Serializable;

import jp.reflexworks.atom.feed.FeedBase;

public class Feed extends FeedBase implements Serializable {

	@Override
	public boolean isValid() {
		return false;
	}



}
