package com.bianjp.blog.dto;

public class JSONReplyDTO {
  private int code = HttpStatus.OK.getCode();
  private String msg = HttpStatus.OK.getMsg();
  private Object data;

  public static JSONReplyDTO success(String msg) {
    return new JSONReplyDTO(HttpStatus.OK.getCode(), msg);
  }

  public static JSONReplyDTO success(String msg, Object data) {
    return new JSONReplyDTO(HttpStatus.OK.getCode(), msg, data);
  }

  public static JSONReplyDTO success(Object data) {
    return new JSONReplyDTO(HttpStatus.OK.getCode(), HttpStatus.OK.getMsg(), data);
  }

  public static JSONReplyDTO fail(String msg) {
    return new JSONReplyDTO(HttpStatus.FAIL.getCode(), msg);
  }

  public JSONReplyDTO() {}

  public JSONReplyDTO(int code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
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

  public enum HttpStatus {
    OK(0, "OK"),
    FAIL(1, "Fail");

    private int code;
    private String msg;

    HttpStatus(int code, String msg) {
      this.code = code;
      this.msg = msg;
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
  }
}
