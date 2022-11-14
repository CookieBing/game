package com.bing.game.constant;

import java.io.Serializable;

/**
 * 对外返回响应对象
 *
 * @author bingyaosi
 * @date 2021-12-24
 */
public class RespData<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 状态描述
	 */
	private String msg;

	/**
	 * 响应数据
	 */
	private T data;

	public RespData() {
	}

	private RespData(RespCodeEnum code) {
		this.code = code.getCode();
		this.msg = code.getMsg();
	}

	private RespData(RespCodeEnum code, T data) {
		this.code = code.getCode();
		this.msg = code.getMsg();
		this.data = data;
	}

	private RespData(Integer integer, String str) {
		this.code = integer;
		this.msg = str;
	}

	private RespData(Integer integer, String str, T data) {
		this.code = integer;
		this.msg = str;
		this.data = data;
	}

	public static <T> RespData<T> ok() {
		return new RespData<>(RespCodeEnum.OK);
	}

	public static <T> RespData<T> ok(T data) {
		return new RespData<>(RespCodeEnum.OK, data);
	}

	public static <T> RespData<T> ok(Integer integer, String str) {
		return new RespData<>(integer, str);
	}

	public static <T> RespData<T> ok(Integer integer, String str, T data) {
		return new RespData<>(integer, str, data);
	}

	public static <T> RespData<T> ok(RespCodeEnum responseCodeEnum) {
		return new RespData<>(responseCodeEnum);
	}

	public static <T> RespData<T> ok(RespCodeEnum responseCodeEnum, T data) {
		return new RespData<>(responseCodeEnum, data);
	}

	public static <T> RespData<T> error() {
		return new RespData<>(RespCodeEnum.ERROR);
	}

	public static <T> RespData<T> error(RespCodeEnum responseCodeEnum) {
		return new RespData<>(responseCodeEnum);
	}

	public static <T> RespData<T> error(Integer integer, String str) {
		return new RespData<>(integer, str);
	}

	public static <T> RespData<T> error(RespCodeEnum responseCodeEnum, T data) {
		return new RespData<>(responseCodeEnum, data);
	}

	public static <T> RespData<T> error(Integer integer, String str, T data) {
		return new RespData<>(integer, str, data);
	}

	public Integer getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}

	public T getData() {
		return this.data;
	}
}
