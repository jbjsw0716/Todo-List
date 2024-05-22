package com.keduit.service;

import java.util.List;

import com.keduit.domain.TodoVO;

//To-do 리스트를 다루기 위한 서비스 인터페이스를 정의
public interface TodoService {
	//To-do 리스트의 전체 목록을 가져오는 메서드
	public List<TodoVO> getList();
	
	//새로운 To-do 항목을 등록하는 메서드
	public void register(TodoVO todo);
	
	//주어진 식별자(tno)에 해당하는 To-do 항목을 가져오는 메서드
	public TodoVO get(Long tno);
	
	//기존의 To-do 항목을 수정하는 메서드 -> 수정이 성공했을 경우 true를 반환하고, 실패했을 경우 false를 반환
	public boolean modify(TodoVO todo);
	
	//주어진 식별자(tno)에 해당하는 To-do 항목을 삭제하는 메서드 -> 삭제가 성공했을 경우 true를 반환하고, 실패했을 경우 false를 반환
	public boolean remove(Long tno);
}
