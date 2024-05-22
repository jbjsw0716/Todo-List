package com.keduit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.TodoVO;
import com.keduit.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
// /todo/*" 패턴에 매칭되는 모든 HTTP 요청은 이 컨트롤러로 라우팅
@RequestMapping("/todo/*")
@RequiredArgsConstructor
public class TodoController {
	//TodoService 객체를 주입받아서 Todo 관련 작업을 수행
	private final TodoService service;	//@RequiredArgsConstructor로 인해 생성자 자동 생성

	//HTTP GET 요청이 "/list" 경로로 올 때 이 메서드가 호출 -> 따라서 "/list" 경로로의 GET 요청을 처리하는 역할
	@GetMapping("/list")
	//GET 메서드를 처리하여 할 일 목록을 불러오는 기능을 구현
	public void list(Model model) {
		log.info("=====list=====");
		
		//모델에 "list"라는 속성을 추가 -> 속성 이름은 "list"이며, 해당 속성 값은 service.getList() 메서드를 호출하여 얻은 할 일 목록
		model.addAttribute("list", service.getList());
	}
	
	@GetMapping("/register")
	public void register() {}
	
	//클라이언트로부터 받은 Todo 항목을 등록하고, 등록된 항목의 고유 번호를 플래시 속성으로 전달한 후 "/todo/list" 경로로 리다이렉트하여 등록된 항목을 확인할 수 있도록 함
	@PostMapping("/register")
	//RedirectAttributes: Spring MVC에서 리다이렉트 시 데이터를 전달하기 위한 용도로 사용되는 인터페이스. 주로 리다이렉트된 후에도 데이터를 유지하고 다음 요청에 전달할 때 사용.
	public String register(String dueDate, TodoVO todo, RedirectAttributes rttr) {
		log.info("=====register=====" + todo);
		
	    //날짜 문자열을 java.util.Date 객체로 변환
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        Date parsedDueDate = dateFormat.parse(dueDate);
	        
	        todo.setDueDate(parsedDueDate);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		//TodoService 객체를 사용하여 Todo 항목을 등록 -> TodoService 클래스의 register 메서드를 호출하여 실제로 데이터베이스에 Todo 항목을 추가
		service.register(todo);
		
		//리다이렉트 시 플래시 속성을 추가 -> result"라는 속성에 새로 등록된 Todo 항목의 고유 번호인 tno를 전달
		rttr.addFlashAttribute("result", todo.getTno());
		
		//"/todo/list" 경로로 리다이렉트를 수행 -> Todo 항목이 등록된 후에는 해당 경로로 이동하여 등록된 항목을 확인 가능
		return "redirect:/todo/list";
	}
	
	//"/get" 또는 "/modify" 경로로 GET 요청이 들어왔을 때, 해당 할 일(Todo) 항목을 가져오거나 수정
	@GetMapping({"/get", "/modify"})
	//@RequestParam 어노테이션을 사용하여 요청 파라미터 중 "tno" 값을 Long 형식으로 받아옴
	public void get(@RequestParam("tno") Long tno, Model model) {
		log.info("=====get or modify=====");
		
		model.addAttribute("todo", service.get(tno));
	}
	
	/* 클라이언트로부터 받은 Todo 항목을 수정하고, 수정 결과에 따라 플래시 속성을 추가한 후
	   "/todo/list" 경로로 리다이렉트하여 수정된 항목 확인 가능 */
	@PostMapping("/modify")
	public String modify(@RequestParam("dueDate") String dueDate, TodoVO todo, RedirectAttributes rttr) {
		log.info("=====modify=====" + todo);
		
		//날짜 문자열을 java.util.Date 객체로 변환
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        Date parsedDueDate = dateFormat.parse(dueDate);
	        
	        todo.setDueDate(parsedDueDate);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		//TodoService 객체를 사용하여 Todo 항목을 수정하는 if조건문 -> TodoService의 modify 메서드를 호출하여 실제로 데이터베이스에 Todo 항목을 수정
		if (service.modify(todo)) {
			//수정에 성공하면 "success"라는 플래시 속성을 RedirectAttributes에 추가
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/todo/list";
	}
	
	/* 클라이언트로부터 받은 할 일(Todo) 항목의 고유 번호를 사용하여 해당 항목을 삭제하고,
	   삭제 결과에 따라 플래시 속성을 추가한 후 "/todo/list" 경로로 리다이렉트하여 변경된 목록 확인 가능 */
	@PostMapping("/remove")
	//@RequestParam 어노테이션을 사용하여 요청 파라미터 중 "tno" 값을 Long 형식으로 받아옴
	public String remove(@RequestParam("tno") Long tno, RedirectAttributes rttr) {
		log.info("=====remove=====" + tno);
		
		//TodoService 객체를 사용하여 Todo 항목을 삭제하는 if조건문 -> TodoService의 remove 메서드를 호출하여 실제로 데이터베이스에서 해당 항목을 삭제
		if (service.remove(tno)) {
			//삭제에 성공하면 "success"라는 플래시 속성을 RedirectAttributes에 추가
			rttr.addFlashAttribute("result", "success");
		} else {
			//실패하면 로그에 "==fail==" 메시지를 출력
			log.info("==fail==");
		}
		
		return "redirect:/todo/list";
	}
}
