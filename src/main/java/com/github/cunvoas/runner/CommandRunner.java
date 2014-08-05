package com.github.cunvoas.runner;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Executeur de commandes sur le systeme.
 */
public class CommandRunner extends Thread {

	private static Logger LOGGER = LoggerFactory.getLogger(CommandRunner.class);

    /** Internaly created process. */
    private Process process;

    /** A thread to capture standard output. */
    private StreamManager errorStreamManager;

    /** A thread to capture standard error. */
    private StreamManager outputStreamManager;

    /**
     * Construit la commande et l'ex�cute.
     * 
     * @param theCommand
     *            The command line specification.
     * @param theWorkDirectory
     *            The directory to set as execution directory.
     */
    public CommandRunner(final String[] theCommand, final File theWorkDirectory) throws Exception {
        process = null;

        final Runtime runTime = Runtime.getRuntime();

        final StringBuffer cmdLine = new StringBuffer();
        for (int idx = 0; idx < theCommand.length; ++idx) {
            cmdLine.append(theCommand[idx]);
            cmdLine.append(' ');
        }

        LOGGER.debug("path=" + theWorkDirectory + " , command=" + cmdLine.toString());
        process = runTime.exec(cmdLine.toString(), null, theWorkDirectory);

        // any error message?
        errorStreamManager = new StreamManager(process.getErrorStream());

        // any output?
        outputStreamManager = new StreamManager(process.getInputStream());

        // kick them off
        errorStreamManager.start();
        outputStreamManager.start();

    }

    /**
     * Test l'�tat de l'ex�cution en cours.
     * 
     * @throws Exception
     */
    public void waitForCompletion() throws Exception {
        if (null != process) {
            final int rc = process.waitFor();
            LOGGER.debug("Process return code = " + rc);

            if (outputStreamManager!=null && outputStreamManager.getResult()!=null && outputStreamManager.getResult().length()>0) {
	            LOGGER.debug("Process OUTPUT:");
	            LOGGER.debug(outputStreamManager.getResult());
            }

            if (errorStreamManager!=null && errorStreamManager.getResult()!=null && errorStreamManager.getResult().length()>0) {
	            LOGGER.debug("Process ERROR:");
	            LOGGER.debug(errorStreamManager.getResult());
	            throw new IllegalArgumentException(errorStreamManager.getResult());
            }

        }
    }

}