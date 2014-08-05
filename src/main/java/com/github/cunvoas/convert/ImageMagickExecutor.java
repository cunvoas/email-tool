package com.github.cunvoas.convert;

import java.io.File;

import com.github.cunvoas.runner.CommandRunner;


/**
 * @author CUNVOAS
 * @see http://www.imagemagick.org/script/formats.php
 * suuport txt, html, jpg, png, 
 */
public class ImageMagickExecutor {
	
	public void convert(File workDir, String source, String type, String dest) throws Exception {
		
		String[] cmdLine = new String[] {"convert",  source , dest};
		
		CommandRunner runner= new CommandRunner(cmdLine, workDir);
		runner.waitForCompletion();
	}
}
