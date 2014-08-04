package com.github.cunvoas.eml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmlParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmlParser.class);
	
	private List<String> grantedContentType = new ArrayList<String>();
	
	public EmlParser() {
		super();
		grantedContentType.add("text/plain");
		grantedContentType.add("application/msword");
	}
	

	public MimeMessage parse(File emlFile) {
		Properties props = System.getProperties();
        props.put("mail.host", "localhost");
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = Session.getDefaultInstance(props, null);
        
        
		MimeMessage message=null;
		InputStream source = null;
		
		try {
			source = new FileInputStream(emlFile);
			message = new MimeMessage(mailSession, source);
			
			String subject= message.getSubject();
			LOGGER.info(subject);
			
			
			MimeMultipart content = (MimeMultipart)message.getContent();
			int nbItem = content.getCount();
			for (int i = 0; i < nbItem; i++) {
				BodyPart body = content.getBodyPart(i);
				LOGGER.info(body.getContentType());
				LOGGER.info(body.getFileName());
				
				if (body.getFileName()!=null && grantedContentType.contains(body.getContentType())) {
					// attachements
					byte[] data = new byte[body.getDataHandler().getInputStream().available()];
					body.getDataHandler().getInputStream().read(data);
					
					FileOutputStream fos = new FileOutputStream("d:/tmp/pg_"+body.getFileName(), false);
					fos.write(data);
					fos.close();
				} else {
					MimeMultipart bodyContent = (MimeMultipart)body.getContent();
					LOGGER.info(bodyContent.getContentType());
				}
				
				
				
				

				//LOGGER.info(body.toString());
			}
	
			
			
			
		} catch (FileNotFoundException e) {
			LOGGER.error("EML file nort found", e );
		} catch (MessagingException e) {
			LOGGER.error("Bad Message", e );
		} catch (IOException e) {
			LOGGER.error("Bad Message I/O", e );
		} finally {
			if (source!=null) {
				try {
					source.close();
				} catch (IOException ignore) {
					LOGGER.info("close EML file", ignore );
				}
			}
		}
        
        
        
        
		
		return message;
	}
}
