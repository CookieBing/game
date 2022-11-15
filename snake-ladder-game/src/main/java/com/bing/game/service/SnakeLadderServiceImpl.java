package com.bing.game.service;

import com.bing.game.constant.GameConstant;
import com.bing.game.constant.RespCodeEnum;
import com.bing.game.constant.RespData;
import com.bing.game.entity.SnakeLadderGameDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 蛇梯游戏服务实现类
 *
 * @author bing
 * @date 2022-11-14
 */
@Service
public class SnakeLadderServiceImpl implements SnakeLadderService {

	private static final Logger logger = LoggerFactory.getLogger(SnakeLadderServiceImpl.class);

	/**
	 * 是否开启蛇吻与梯子的功能
	 */
	@Value("${spring.open-snake-ladder}")
	private Boolean openSnakeLadder;

	/**
	 * 缓存红方骰子点数
	 */
	private static Integer RED_PIPS;

	/**
	 * 缓存蓝方骰子点数
	 */
	private static Integer BLUE_PIPS;

	/**
	 * 最近一次掷骰子的角色
	 */
	private static String LAST_ROLE;


	@Override
	public void start() {
		RED_PIPS = 0;
		BLUE_PIPS = 0;
		LAST_ROLE = null;
	}

	@Override
	public RespData<SnakeLadderGameDTO> rollDice(String role) {
		if (!GameConstant.RoleEnum.RED.getValue().equals(role) && !GameConstant.RoleEnum.BLUE.getValue().equals(role)) {
			logger.warn("角色传入有误！");
			return RespData.error(-1, "角色传入有误！");
		}
		// 防止同一角色多次掷骰子
		if (LAST_ROLE != null && LAST_ROLE.equals(role)) {
			logger.warn("同一角色不可连续多次掷骰子！");
			return RespData.error(-1, "同一角色不可连续多次掷骰子！");
		}
		LAST_ROLE = role;

		logger.info("red = {}, blue = {}", RED_PIPS, BLUE_PIPS);
		if (RED_PIPS == null || BLUE_PIPS == null) {
			start();
		}
		int pips = (int) (Math.random() * 6 + 1);
		SnakeLadderGameDTO dto = SnakeLadderGameDTO.builder().role(role).pips(pips).redPips(RED_PIPS).bluePips(BLUE_PIPS).build();
		boolean isRedRole = GameConstant.RoleEnum.RED.getValue().equals(dto.getRole());
		boolean meetSnakeOrLadder = false;
		boolean exceedEnd = false;
		if (isRedRole) {
			// 如果是红方
			RED_PIPS += pips;
			logger.info("red = {}, blue = {}", RED_PIPS, BLUE_PIPS);
			if (RED_PIPS > 100) {
				RED_PIPS = 200 - RED_PIPS;
				logger.info("red = {}, blue = {}", RED_PIPS, BLUE_PIPS);
				exceedEnd = true;
			}
			dto.setRedPips(RED_PIPS);
		} else {
			// 如果是蓝方
			BLUE_PIPS += pips;
			logger.info("red = {}, blue = {}", RED_PIPS, BLUE_PIPS);
			if (BLUE_PIPS > 100) {
				BLUE_PIPS = 200 - BLUE_PIPS;
				logger.info("red = {}, blue = {}", RED_PIPS, BLUE_PIPS);
				exceedEnd = true;
			}
			dto.setBluePips(BLUE_PIPS);
		}
		if (openSnakeLadder) {
			// 如果开启游戏道具，执行以下方法
			meetSnakeOrLadder = isMeetSnakeOrLadder(isRedRole, dto);
		}
		// 将最新的数据更新到红蓝方的点数中
		RED_PIPS = dto.getRedPips();
		BLUE_PIPS = dto.getBluePips();
		if (RED_PIPS == 100) {
			// 结束对局，清空缓存
			end();
			return RespData.ok(RespCodeEnum.RED_WIN, dto);
		} else if (BLUE_PIPS == 100) {
			// 结束对局，清空缓存
			end();
			return RespData.ok(RespCodeEnum.BLUE_WIN, dto);
		}
		if (exceedEnd) {
			if (meetSnakeOrLadder) {
				return RespData.ok(RespCodeEnum.EXCEED_END_AND_MEET_LADDER_OR_SNAKE, dto);
			}
			return RespData.ok(RespCodeEnum.EXCEED_END, dto);
		}
		if (meetSnakeOrLadder) {
			return RespData.ok(RespCodeEnum.MEET_LADDER_OR_SNAKE, dto);
		}
		return RespData.ok(dto);
	}

	private void end() {
		RED_PIPS = null;
		BLUE_PIPS = null;
		LAST_ROLE = null;
	}

	/**
	 * 判断是否遇到道具
	 */
	private boolean isMeetSnakeOrLadder(boolean isRedRole, SnakeLadderGameDTO dto) {
		Integer pips = isRedRole ? dto.getRedPips() : dto.getBluePips();
		Integer value = GameConstant.SNAKE.get(pips);
		if (value != null) {
			// 遇到蛇吻
			logger.info("遇到蛇吻，old_pips = {}, new_pips = {}", pips, value);
			pips = value;
			updatePips(isRedRole, dto, pips);
			return true;
		}
		value = GameConstant.LADDER.get(pips);
		if (value != null) {
			// 遇到梯子
			logger.info("遇到蛇吻，old_pips = {}, new_pips = {}", pips, value);
			pips = value;
			updatePips(isRedRole, dto, pips);
			return true;
		}
		return false;
	}

	/**
	 * 更新点数
	 */
	private void updatePips(boolean isRedRole, SnakeLadderGameDTO dto, Integer pips) {
		if (isRedRole) {
			dto.setRedPips(pips);
		} else {
			dto.setBluePips(pips);
		}
	}

}
