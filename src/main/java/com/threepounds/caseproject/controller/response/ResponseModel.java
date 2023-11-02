package com.threepounds.caseproject.controller.response;



public class ResponseModel<T> {

  private int statusCode;

  private T body;

  public ResponseModel() {
  }

  public ResponseModel(int statusCode, T body) {
    this.statusCode = statusCode;
    this.body = body;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public T getBody() {
    return body;
  }

  public void setBody(T body) {
    this.body = body;
  }
}
