package jp.reflexworks.atom.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

import org.junit.Test;

import jp.reflexworks.atom.mapper.FeedTemplateMapper;
import jp.sourceforge.reflex.util.FileUtil;

public class TestMsgpackMapperGenerateFiles_mypackage {

	@Test
	public void testGenerateFiles() throws FileNotFoundException, ParseException {
		
		String[] args = new String[5];
		
		args[0] = "mypackage";
		args[1] = FileUtil.getResourceFilename("template_mypackage.txt");
		args[2] = "./";
		args[3] = "out" + File.separator + "mykey";  // secretkey
		args[4] = FileUtil.getResourceFilename("prop_acls_mypackage.txt");
		
		FeedTemplateMapper.main(args);
		
	}

}
