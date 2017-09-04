package com.bianjp.blog.dto;

public class JSONReplyDTO {
  private int code = 0;
  private String msg;
  private Object data;

  public JSONReplyDTO() {
  }

  public JSONReplyDTO(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public JSONReplyDTO(Object data) {
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
