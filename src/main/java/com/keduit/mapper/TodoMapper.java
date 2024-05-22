package com.keduit.mapper;

import java.util.List;
import com.keduit.domain.TodoVO;

public interface TodoMapper {
//	@Select("select * from t_todo by tno desc")
	// 1.할 일 목록을 가져오기 2.TodoVO 객체의 리스트를 반환 3.sql 쿼리는 xml에서 처리(TodoMapper.xml)
	public List<TodoVO> getList();
	
	//xml의 아이디와 메소드의 이름은 같아야 작동이 가능
	// 1.새로운 할 일 추가 2.반환 타입은 void -> 삽입된 데이터에 대한 결과 반환x 3.삽입될 할 일의 정보를 포함하는 객체(TodoVO)를 매개변수로 받음
	public void insert(TodoVO todo);
	
	/* insert메서드와 역할은 거의 비슷하지만,
	데이터베이스에 새로운 할 일을 삽입할 때 해당 할 일의 식별자(primary key) 값을 자동으로 생성하여 TodoVO 객체에 설정하는 것 */
	public void insertSelectKey(TodoVO todo);
	
	//데이터베이스에서 주어진 식별자(tno)에 해당하는 할 일(Todo) 정보를 가져옴
	public TodoVO read(Long tno);
	
	//데이터베이스에서 주어진 식별자(tno)에 해당하는 할 일(Todo) 정보를 삭제
	//삭제된 행의 수나 성공 여부를 나타내는 정수 값을 반환 가능 -> 보통은 삭제된 행의 수를 반환
	public int delete(Long tno);
	
	//데이터베이스에서 주어진 할 일(Todo) 정보를 업데이트
	//업데이트가 성공하면 업데이트된 행의 수를 반환 가능
	public int update(TodoVO todo);
}
