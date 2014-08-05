package com.github.cunvoas.util;

import junit.framework.Assert;

import org.junit.Test;

public class TestFileHelper {

	@Test
	public void test() {
		Assert.assertEquals("A________A", FileHelper.sanitizeFileName("A:\\/?|<>*A"));
	}

}
