package com.github.cunvoas.eml;

import java.io.File;
import java.net.URL;
import java.util.Collection;

import junit.framework.Assert;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class TestEmlParser {
	
	private EmlParser tested = null;
	private String emlTestFile = null;
	private String emlPlainTestFile = null;

	@Before
	public void setUp() throws Exception {
		tested = new EmlParser();
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("eml/test.eml");
		emlTestFile = url.getPath();
		url = Thread.currentThread().getContextClassLoader().getResource("eml/test_plain.eml");
		emlPlainTestFile = url.getPath();
	}

	@Test
	public void testParse() {
		EmlContent msg = null;
		msg = tested.parse(new File(emlTestFile));
		Assert.assertNotNull("MimeMessage", msg);
		
		MatcherAssert.assertThat("body", (Collection<?>)msg.getBody(), Matchers.is(Matchers.not(Matchers.empty())));
		MatcherAssert.assertThat("attach", (Collection<?>)msg.getAttachements(), Matchers.is(Matchers.not(Matchers.empty())));
		
		
		msg = tested.parse(new File(emlPlainTestFile));
		Assert.assertNotNull("MimeMessage", msg);
		MatcherAssert.assertThat("body", (Collection<?>)msg.getBody(), Matchers.is(Matchers.not(Matchers.empty())));
		MatcherAssert.assertThat("attach", (Collection<?>)msg.getAttachements(), Matchers.is(Matchers.not(Matchers.empty())));
		
	}

}
