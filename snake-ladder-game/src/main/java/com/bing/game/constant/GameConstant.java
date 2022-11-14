package com.bing.game.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 游戏常量类
 *
 * @author abing
 * @date 2022-11-14
 */
public class GameConstant {

	/**
	 * 遭遇蛇吻，退到蛇尾
	 */
	public static final Map<Integer, Integer> SNAKE = new HashMap<Integer, Integer>() {
		{
			put(16, 6);
			put(47, 26);
			put(49, 11);
			put(56, 53);
			put(62, 19);
			put(64, 60);
			put(87, 24);
			put(93, 73);
			put(95, 75);
			put(98, 78);
		}
	};

	/**
	 * 遇到梯底，爬上梯子
	 */
	public static final Map<Integer, Integer> LADDER = new HashMap<Integer, Integer>() {
		{
			put(1, 38);
			put(4, 14);
			put(9, 31);
			put(21, 42);
			put(28, 84);
			put(36, 44);
			put(51, 67);
			put(71, 91);
			put(80, 100);
		}
	};

	/**
	 * 角色枚举类
	 */
	public enum RoleEnum {
		/**
		 * 红方角色名称
		 */
		RED("red"),

		/**
		 * 蓝方角色名称
		 */
		BLUE("blue");

		private final String value;

		RoleEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}
}
