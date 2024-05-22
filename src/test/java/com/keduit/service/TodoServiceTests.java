package com.keduit.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.TodoVO;

import lombok.extern.log4j.Log4j;

//JUnit 테스트를 실행할 때 Spring의 테스트 컨텍스트 프레임워크와 통합하여 테스트를 수행하도록 지시 -> 따라서 이 테스트 클래스는 Spring의 환경에서 실행
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
//테스트에 필요한 애플리케이션 컨텍스트를 로드하기 위해 사용 -> 여기서는 root-context.xml 파일을 사용하여 애플리케이션 컨텍스트를 설정
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TodoServiceTests {
	@Autowired
	private TodoService service;
	
	@Test
	//TodoService의 빈이 올바르게 주입되었는지를 테스트
	public void testExist() {
		log.info(service);
		
		//주입된 TodoService 객체가 null이 아닌지를 확인 -> 즉, 해당 빈이 정상적으로 생성되어 주입되었는지를 검증. 만약 객체가 null이면 테스트가 실패.
		assertNotNull(service);
	}
	
	@Test
	//register 메서드가 정상적으로 동작하는지 확인하는 데 사용
	public void testRegister() {
		//새로운 TodoVO 객체를 생성
		TodoVO todo = new TodoVO();
		//생성된 TodoVO 객체의 필드(title, dueDate, writer)를 설정
		todo.setTitle("4월 1일 숙제 중");
		todo.setDueDate(new Date());
		todo.setWriter("만우절");
		
		//새로운 To-do 항목을 등록
		service.register(todo);
		
		//등록된 To-do 항목의 번호(tno)를 로그에 출력
		log.info("생성된 할 일 번호: " + todo.getTno());
	}
	
	@Test
	//getList 메서드가 정상적으로 동작하는지 확인하는 데 사용
	public void testGetList() {
		//1.To-do 리스트의 전체 목록을 가져옴
		//2.가져온 목록에 대해 forEach를 사용하여 각각의 To-do 항목을 순회
		//3.각 To-do 항목을 로그에 출력
		service.getList().forEach(todo -> log.info(todo));
	}
	
	@Test
	//get 메서드가 주어진 식별자에 해당하는 To-do 항목을 정상적으로 가져오는지 확인하는 데 사용
	public void testGet() {
		//1.주어진 식별자(예: 43L)에 해당하는 To-do 항목을 가져옴
		//2.가져온 To-do 항목을 로그에 출력
		log.info("=====testGet=====" + service.get(43L));
	}
	
	@Test
	//modify 메서드가 To-do 항목을 정상적으로 업데이트하는지 확인하는 데 사용
	public void testUpdate() {
		//주어진 식별자(예: 25L)에 해당하는 To-do 항목을 가져옴
		TodoVO todo = service.get(25L);
		
		//가져온 To-do 항목이 null이면 메서드를 종료
		if (todo==null) {
			return;
		}
		
		//To-do 항목의 제목을 변경
		todo.setTitle("제목이지렁이");
		
		//변경된 To-do 항목을 modify 메서드를 통해 업데이트하고 그 결과를 로그에 출력
		log.info("=====testUpdate=====" + service.modify(todo));
		
		//변경된 To-do 항목 다시 보여주기
		service.get(25L);
	}
	
	@Test
	public void testDelete() {
		//삭제할 항목의 tno를 변수 tnoNm에 저장
	    Long tnoNm = 6L;

	    //해당 tno를 가진 항목을 삭제하고, 그 결과를 result 변수에 저장 -> 삭제가 성공하면 true, 실패하면 false가 반환
	    boolean result = service.remove(tnoNm);

	    //true라면 삭제한 tno 번호와 삭제가 성공했다는 메시지 출력, false라면 삭제가 실패했다는 메시지 출력
	    if (result) {
	        log.info(tnoNm + "번 삭제 성공");
	    } else {
	        log.info("삭제 실패: 해당하는 항목이 없습니다.");
	    }
	}
}
