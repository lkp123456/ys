package com.xxx.base;

public class ResponseResult {
	
	/**
	 * general
	 */
	public static final ResponseResult OK = new ResponseResult(200, "SUCCESS");

	public static final ResponseResult ERROR = new ResponseResult(500, "ERROR");

	public static final ResponseResult INVALID_ARGUMENT= new ResponseResult(500, "INVALID_ARGUMENT");

	public static final ResponseResult NOT_FOUND = new ResponseResult(404, "NOT_FOUND");

	/**
	 * 权限相关状态码 100~199
	 */
	public static final ResponseResult CAPTCHA_INVALID= new ResponseResult(100, "CAPTCHA_INVALID");
	public static final ResponseResult LOGINID_PASSWORD_ERROR= new ResponseResult(101, "LOGINID_PASSWORD_ERROR");
	public static final ResponseResult LOGINID_INVALID= new ResponseResult(102, "LOGINID_INVALID");
	public static final ResponseResult PASSWORD_INVALID= new ResponseResult(103, "PASSWORD_INVALID");
	public static final ResponseResult PASSWORDS_DIFFER_INVALID= new ResponseResult(104, "ENTERED PASSWORDS DIFFER");
	public static final ResponseResult USER_EXIST= new ResponseResult(105, "USER_EXIST");
	public static final ResponseResult NOT_LOGIN= new ResponseResult(106, "NOT_LOGIN");
	public static final ResponseResult ACCESS_FORBIDDEN= new ResponseResult(107, "ACCESS_FORBIDDEN");
	public static final ResponseResult VERIFY_CODE_SENT= new ResponseResult(108, "VERIFY_CODE_SENT");
	public static final ResponseResult VERIFY_CODE_NEED_CAPTCHA= new ResponseResult(109, "VERIFY_CODE_NEED_CAPTCHA");
	public static final ResponseResult MSGCODE_INVALID= new ResponseResult(110, "MSGCODE_INVALID");

	
	
	public int code;
	public String msg;
	public Object data;

	public int getCode() {
		return code;
	}

	public ResponseResult setCode(int code) {

		this.code = code;
		return this;
	}


	public ResponseResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResponseResult(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResponseResult(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseResult() {
		super();
	}

	public static ResponseResult data(String msg, Object data){
		ResponseResult responseResult = new ResponseResult();
		responseResult.setCode(200);
		responseResult.setMsg(msg);
		responseResult.setData(data);
		return responseResult;
	}

}
