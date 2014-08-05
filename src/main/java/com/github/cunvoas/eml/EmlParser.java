package com.github.cunvoas.eml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmlParser {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmlParser.class);

	private List<String> grantedContentType = new ArrayList<String>();

	public EmlParser() {
		super();
		grantedContentType.add("text/plain");
		grantedContentType.add("text/html");
		grantedContentType.add("application/msword");
	}

	public MimeMessage parse(File emlFile) {
		Properties props = System.getProperties();
		props.put("mail.host", "localhost");
		props.put("mail.transport.protocol", "smtp");
		Session mailSession = Session.getDefaultInstance(props, null);

		MimeMessage message = null;
		InputStream source = null;
		FileOutputStream fos = null;
		try {
			source = new FileInputStream(emlFile);
			message = new MimeMessage(mailSession, source);

			String subject = message.getSubject();
			LOGGER.info(subject);

			MimeMultipart content = (MimeMultipart) message.getContent();
			int nbItem = content.getCount();
			
			Pattern contentTypePattern = Pattern.compile("([a-z\\/]+); (.*)");
			
			for (int i = 0; i < nbItem; i++) {
				BodyPart body = content.getBodyPart(i);
				
				Matcher m = contentTypePattern.matcher(body.getContentType());
				
				LOGGER.info(body.getContentType());
				LOGGER.info(body.getFileName());

				if (body.getFileName() != null && m.find() && grantedContentType.contains(m.group(1))) {
					LOGGER.info("process "+ m.group(2));
					// attachements extractions
					byte[] data = new byte[body.getDataHandler().getInputStream().available()];
					body.getDataHandler().getInputStream().read(data);

					fos = new FileOutputStream("d:/tmp/pg_" + body.getFileName(), false);
					fos.write(data);
					fos.close();
				} else {

					if (body.getContent() instanceof MimeMultipart) {
						MimeMultipart bodyContent = (MimeMultipart) body
								.getContent();
						LOGGER.info(bodyContent.getContentType());

						if (bodyContent.getContentType().startsWith(
								"multipart/alternative")) {
							String[] boundary = bodyContent.getContentType()
									.split("boundary=");
							if (boundary != null && boundary.length == 2) {
								String pattern = "--" + boundary[1];

								byte[] data = new byte[body.getDataHandler().getInputStream().available()];
								body.getDataHandler().getInputStream().read(data);

								String mailContent = new String(data);
								String[] items = mailContent.split(pattern);

								List<EmlContentElement> elements = new ArrayList<EmlContentElement>();
								if (items != null && items.length > 0) {
									for (int j = 0; j < items.length; j++) {
										String item = items[j];

										if (item.contains("Content-Type")) {
											LOGGER.info(item);
											m = Pattern.compile("(.*)Content-Type: (.*); charset=(.*)\\n([.\\s]*)")
													.matcher(item);//

											EmlContentElement element = null;
											if (m.find()) {
												element = new EmlContentElement();
												element.setContentType(m.group(2));
												element.setEncoding(m.group(3));
												element.setContent(item.replaceAll(m.group(), ""));

												elements.add(element);
											}
										}
									}
								}

								if (!elements.isEmpty()) {
									Collections.sort(elements); // html before
																// plain
									EmlContentElement element = elements.get(0);

									String[] fn = element.getContentType().split("\\/");
									fos = new FileOutputStream("d:/tmp/pg_" + fn[0] + "." + fn[1], false);
									fos.write(element.getContent().getBytes());
									fos.close();
								}
							}
						}
					}

				}

				// LOGGER.info(body.toString());
			}

		} catch (FileNotFoundException e) {
			LOGGER.error("EML file nort found", e);
		} catch (MessagingException e) {
			LOGGER.error("Bad Message", e);
		} catch (IOException e) {
			LOGGER.error("Bad Message I/O", e);
		} catch (Exception e) {
			LOGGER.error("Unknonw", e);
		} finally {
			if (fos!=null) {
				try {
					fos.close();
				} catch (IOException ignore) {
					LOGGER.debug("safe close");
				}
			}
			if (source != null) {
				try {
					source.close();
				} catch (IOException ignore) {
					LOGGER.info("close EML file", ignore);
				}
			}
		}

		return message;
	}

	/**
	 * Setter for grantedContentType.
	 * 
	 * @param grantedContentType
	 *            the grantedContentType to set
	 */
	protected void setGrantedContentType(List<String> grantedContentType) {
		this.grantedContentType = grantedContentType;
	}

}
