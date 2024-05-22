package com.keduit.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.TodoVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TodoMapperTests {
	//mapper 필드에 대한 Setter 메서드를 생성
	//@Autowired 어노테이션을 적용하여 Spring이 TodoMapper 인터페이스에 대한 구현체를 자동으로 주입하도록 함
	@Setter(onMethod_ = @Autowired)
	private TodoMapper mapper;
	
	@Test
	public void testGetList() {
		log.info(mapper.getList());
		
		//할 일(Todo) 목록의 각 항목을 개별적으로 확인하기 위한 용도로 사용
		mapper.getList().forEach(todo -> log.info("하나씩 확인해보자!==> " + todo));
	}
	
	@Test
	public void testInsert() {
		TodoVO todo = new TodoVO();
		todo.setTitle("할일 인서트");
        todo.setWriter("작성자");
        todo.setDueDate(new Date());
        todo.setFinished(false);
        
		mapper.insert(todo);
		log.info(".....insert....." + todo);
	}
	
	@Test
	public void testInsertSelectKey() {
		TodoVO todo = new TodoVO();
		todo.setTitle("제목이랍니다");
        todo.setWriter("zi존ㅁr법ㅅr");
        todo.setDueDate(new Date());
        todo.setFinished(false);
		
		mapper.insertSelectKey(todo);
		log.info(".....insertSelectKey....." + todo);
	}
	
	@Test
	//tno 번호에 해당하는 데이터 1건 읽어오기
	public void testRead() {
		TodoVO todo = mapper.read(23L);
		
		log.info("Read에 관하여===> " + todo);
	}
	
	@Test
	public void testDelete() {
		int result = mapper.delete(5L);
		
	    if (result > 0) {
	        log.info("삭제 완료 => 삭제된 행 수: " + result);
	    } else {
	        log.info("삭제 실패");
	    }
	}
	
	@Test
	public void testUpdate() {
		TodoVO todo = new TodoVO();
		long tnoNm = 27L;
		
		todo.setTno(tnoNm);
		todo.setTitle("스프링 과제하기");
		todo.setWriter("Jamix");
		todo.setFinished(true);
		
		int result = mapper.update(todo);
		
		mapper.read(tnoNm);
		
		if (result > 0) {
			log.info("수정 완료 => 수정된 행 수: " + result);
		} else {
			log.info("수정 실패");
		}
	}
}
