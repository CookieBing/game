package com.bing.game.controller;

import com.bing.game.constant.RespData;
import com.bing.game.entity.SnakeLadderGameDTO;
import com.bing.game.service.SnakeLadderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 蛇梯游戏控制器
 *
 * @author bing
 * @date 2022-11-14
 */
@RestController
@RequestMapping("/snake-ladder-game")
public class SnakeLadderController {

	private static final Logger logger = LoggerFactory.getLogger(SnakeLadderController.class);

	@Resource
	private SnakeLadderService snakeLadderService;

	@GetMapping("/start")
	public RespData<Void> start() {
		logger.info("进入开始游戏方法！");
		snakeLadderService.start();
		logger.info("初始化游戏数据完毕！");
		return RespData.ok();
	}

	@GetMapping("/roll-dice/{role}")
	public RespData<SnakeLadderGameDTO> rollDice(@PathVariable("role") String role) {
		logger.info("进入掷骰子方法！role = {}", role);
		RespData<SnakeLadderGameDTO> data = snakeLadderService.rollDice(role);
		logger.info("掷骰子方法执行完毕！data = {}", data.getData());
		return data;
	}
}
