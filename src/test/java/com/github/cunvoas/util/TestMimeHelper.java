package com.github.cunvoas.util;


import org.junit.Assert;
import org.junit.Test;

public class TestMimeHelper {

	@Test
	public void testIsAutorized() {
		Assert.assertTrue("jpg", MimeHelper.isAutorized("image/jpeg"));
		Assert.assertTrue("docx", !MimeHelper.isAutorized("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
	}

	@Test
	public void testGetExtention() {
		Assert.assertEquals("jpg", MimeHelper.getExtention("image/jpeg"));
	}

	@Test
	public void testGetContentType() {
		Assert.assertEquals("image/jpeg", MimeHelper.getContentType("jpeg"));
		Assert.assertEquals("image/jpeg", MimeHelper.getContentType("jpg"));
	}

}
