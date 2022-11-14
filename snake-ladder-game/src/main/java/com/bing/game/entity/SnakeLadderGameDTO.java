package com.bing.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 蛇梯游戏传输类
 *
 * @author abing
 * @date 2022-11-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SnakeLadderGameDTO implements Serializable {

	/**
	 * 骰子的点数
	 */
	private Integer pips;

	/**
	 * 当前轮次的角色
	 */
	private String role;

	/**
	 * 红方当前点数
	 */
	private Integer redPips;

	/**
	 * 蓝方当前点数
	 */
	private Integer bluePips;

}
