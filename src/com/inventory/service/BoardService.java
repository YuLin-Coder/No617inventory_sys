package com.inventory.service;

import java.util.List;
import java.util.Map;

import com.inventory.entity.Board;

public interface BoardService {

	Board findBoardById(String id);

	void deleteBoard(String id);

	void addBoard(Board board);

	List<Board> getBoardPage(int pageNum, int pageSize, Map map);

	int queryBoardCount(Map map);

	void updateBoard(Board board);

}
