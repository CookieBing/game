package com.bing.game.service;

import com.bing.game.constant.RespData;
import com.bing.game.entity.SnakeLadderGameDTO;

/**
 * 蛇梯游戏服务类
 *
 * @author bing
 * @date 2022-11-14
 */
public interface SnakeLadderService {

	/**
	 * 开始游戏
	 */
	void start();

	/**
	 * 掷骰子
	 */
	RespData<SnakeLadderGameDTO> rollDice(String role);
}
