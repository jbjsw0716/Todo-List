package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.TodoVO;
import com.keduit.mapper.TodoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/* 1.비즈니스 로직이 구현된 클래스를 나타냄
   2.해당 클래스가 서비스 컴포넌트로 인식 -> Spring의 컨테이너에서 빈으로 관리
   3.의존성 주입(Dependency Injection) 및 AOP(Aspect-Oriented Programming) 기능을 활용할 수 있도록 함 */
@Service
//클래스에 로깅 기능을 추가
@Log4j
/* 1.필수 인자를 갖는 생성자를 자동으로 생성
   2.클래스의 모든 필수(non-null, final) 인자를 가지고 있는 생성자를 자동으로 생성 */
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
	/* 1.생성자 주입은 의존성을 주입받기 위한 방법 중 하나로, 클래스의 생성자를 통해 필요한 의존성을 주입하는 것을 말함.
	   2.TodoMapper 타입의 인스턴스를 생성할 때 생성자를 호출하여 의존성을 주입해야 함
	   	 -> 이렇게 하면 클래스가 생성될 때 의존성이 필수적으로 주입되므로 NullPointerException과 같은 오류를 방지 가능
	   3.코드 상에서 생성자가 정의되어 있지 않기 때문에, Lombok의 @RequiredArgsConstructor 어노테이션을 적용해야 함.
	   	 -> 이를 통해 컴파일 시점에 자동으로 생성자가 추가되어 의존성을 주입받을 수 있도록 해줌 */
	private final TodoMapper mapper;	//생성자 주입을 받겠다.

	@Override
	//To-do 리스트의 전체 목록을 가져오는 메서드 -> mapper 객체를 사용하여 데이터베이스에서 전체 목록을 조회하고 반환
	public List<TodoVO> getList() {
		log.info("=====getList=====");
		
		return mapper.getList();
	}

	@Override
	//새로운 To-do 항목을 등록하는 메서드 -> 매개변수로 전달된 TodoVO 객체를 데이터베이스에 추가
	public void register(TodoVO todo) {
		log.info("=====register=====" + todo);
		
		//todo를 받아서 mapper에게 시킨다.
		mapper.insertSelectKey(todo);
	}

	@Override
	//주어진 식별자(tno)에 해당하는 To-do 항목을 가져오는 메서드 -> mapper 객체를 사용하여 데이터베이스에서 해당 To-do 항목을 조회하고 반환
	public TodoVO get(Long tno) {
		log.info("=====get=====" + tno);
		
		return mapper.read(tno);
	}

	@Override
	//기존의 To-do 항목을 수정하는 메서드 -> 수정이 성공했을 경우 true를 반환하고, 실패했을 경우 false를 반환
	public boolean modify(TodoVO todo) {
		log.info("=====todo=====" + todo);
		
		//1이면 true 아니면 false
		return mapper.update(todo)==1;
	}

	@Override
	//주어진 식별자(tno)에 해당하는 To-do 항목을 삭제하는 메서드 -> 삭제가 성공했을 경우 true를 반환하고, 실패했을 경우 false를 반환
	public boolean remove(Long tno) {
		log.info("=====remove=====" + tno);
		
		//1이면 true 아니면 false
		return mapper.delete(tno)==1;
	}

}
