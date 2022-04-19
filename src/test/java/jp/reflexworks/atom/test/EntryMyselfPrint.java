package jp.reflexworks.atom.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.reflexworks.atom.api.Condition;
import jp.reflexworks.atom.entry.Author;
import jp.reflexworks.atom.entry.EntryBase;

public class EntryMyselfPrint extends EntryBase{

	public static void main(String[] args) {
		
		EntryMyselfPrint entry = new EntryMyselfPrint();
		List<Author> authors = new ArrayList<Author>();
		Author anAuthor = new Author();
		anAuthor.uri = "urn:vte.cx:created:461";
		authors.add(anAuthor);
		anAuthor = new Author();
		anAuthor.uri = "urn:vte.cx:created:555";
		authors.add(anAuthor);
		
		entry.setAuthor(authors);
		
		System.out.println("id="+entry.getCreatorUid());

	}

	@Override
	public Object getValue(String fieldname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void encrypt(Object cipher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrypt(Object cipher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMatch(Condition[] conditions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getsize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean validate(String uid, List<String> groups) throws ParseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void maskprop(String uid, List<String> groups) {
		// TODO Auto-generated method stub
		
	}

}
