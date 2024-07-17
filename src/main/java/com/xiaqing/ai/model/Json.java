package com.xiaqing.ai.model;

import java.io.Serializable;

public class Json implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 信息
	 */
	private String msg;
	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 数据
	 */
	private Object obj;
	/**
	 * 旧对象数据
	 */
	private Object oldObj;

	public Object getOldObj() {
		return oldObj;
	}

	public void setOldObj(Object oldObj) {
		this.oldObj = oldObj;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Json data(Object obj) {
		this.obj = obj;
		return this;
	}

	public Json msg(String msg) {
		this.msg = msg;
		return this;
	}

	public Json success(boolean success) {
		this.success = success;
		return this;
	}

	public Json success() {
		this.success = true;
		return this;
	}

	public Json error() {
		this.success = false;
		return this;
	}

	/**
	 * 没有登录
	 * */
	public Json noLogin(){
		this.success = false;
		this.code = 401;
		return this;
	}

	public Json code(Integer code) {
		this.code = code;
		return this;
	}
}
