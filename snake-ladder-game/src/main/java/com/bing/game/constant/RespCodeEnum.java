package com.bing.game.constant;

/**
 * 响应状态码
 *
 * @author bingyaosi
 * @date 2021-12-24
 */
public enum RespCodeEnum {

	/**
	 * 成功
	 */
	OK(0, "操作成功"),

	/**
	 * 失败
	 */
	ERROR(-1, "系统繁忙"),

	/**
	 * 超过终点
	 */
	EXCEED_END(1, "超过终点，往回走"),

	/**
	 * 遇到梯底，爬梯子；遇到蛇头，遭到蛇吻
	 */
	MEET_LADDER_OR_SNAKE(2, "遇到蛇吻或梯底"),

	/**
	 * 超过终点，往回走，且遇到蛇吻或梯底
	 */
	EXCEED_END_AND_MEET_LADDER_OR_SNAKE(3, "超过终点，往回走，且遇到蛇吻或梯底"),

	/**
	 * 游戏结束，红方胜利
	 */
	RED_WIN(4, "游戏结束，红方胜利"),

	/**
	 * 游戏结束，蓝方胜利
	 */
	BLUE_WIN(4, "游戏结束，蓝方胜利");

	private final Integer code;
	private final String msg;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	RespCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
