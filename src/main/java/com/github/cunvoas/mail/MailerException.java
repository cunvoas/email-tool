package com.github.cunvoas.mail;

/**
 * Exception du mailer.
 * @author CUNVOAS
 */
public class MailerException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String message;
  /**
   * @param cause
   */
  public MailerException(Throwable cause) {
    super(cause);
    
    message = cause.getMessage();
    if (cause.getCause()!=null) {
      message = cause.getCause().getMessage(); 
    }
  }
  
  /**
   * @param msg
   */
  public MailerException(String msg) {
    message = msg;
  }
  
  /**
   * Getter for message.
   * @return the message
   */
  public String getMessage() {
    return message;
  }
  
  

}
