package com.bird.demo.infrastructure.bpmn.xml.exception;

/**
 * @author Tijs Rademakers
 */
public class XMLException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public XMLException(String message) {
    super(message);
  }
  
  public XMLException(String message, Throwable t) {
    super(message, t);
  }
}
