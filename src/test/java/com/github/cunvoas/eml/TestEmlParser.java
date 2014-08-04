package com.github.cunvoas.eml;

import java.io.File;
import java.net.URL;

import javax.mail.internet.MimeMessage;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestEmlParser {
	
	private EmlParser tested = null;
	private String emlTestFile = null;

	@Before
	public void setUp() throws Exception {
		tested = new EmlParser();
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("eml/test.eml");
		emlTestFile = url.getPath();
	}

	@Test
	public void testParse() {
		
		MimeMessage msg = tested.parse(new File(emlTestFile));
		
		Assert.assertNotNull("MimeMessage", msg);
	}

}
