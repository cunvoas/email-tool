package com.github.cunvoas.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gestion de flux retourné par l'ex�cuteur de commande système.
 */
public class StreamManager extends Thread {

    private static Logger LOGGER = LoggerFactory.getLogger(StreamManager.class);

    /** The stream to read from. */
    private InputStream inputStream;

    /** Output grabber */
    private StringBuffer result;

    /**
     * Constructor.
     * 
     * @param aStream
     *            The stream to read from.
     */
    StreamManager(final InputStream aStream) {
        inputStream = aStream;
        result = new StringBuffer();
    }

    /**
     * Get the final output log.
     */
    public String getResult() {
        return result.toString();
    }

    /**
     * Read until the end of stream.
     */
    public void run() {
        try {
            final InputStreamReader reader = new InputStreamReader(inputStream);
            final BufferedReader buffReader = new BufferedReader(reader);

            String line = null;

            line = buffReader.readLine();
            while (null != line) {
                result.append(line);
                result.append("\n");

                line = buffReader.readLine();
            }

        } catch (final IOException e) {
            LOGGER.error("run", e);
        }
    }
}