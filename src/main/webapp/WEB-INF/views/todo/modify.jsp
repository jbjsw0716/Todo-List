<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Navbar</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-10">
		<div class="panel panel-default">
			<div class="panel-heading">Todo Modify</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-10">
						<form role="form" action="/todo/modify" method="post">
							<div class="form-group">
								<label>TNO</label>
								<input class="form-control" name="tno" value="${todo.tno }" readonly>
							</div>
							
							<div class="form-group">
								<label>Title</label>
								<input class="form-control" name="title" value="${todo.title }" required>
							</div>
							
							<div class="form-group">
								<label>DueDate</label>
								<input name="dueDate" type="date" class="form-control"
								value='<fmt:formatDate pattern="yyyy-MM-dd" value="${todo.dueDate }"/>' required>
							</div>
							
							<div class="form-group">
								<label>Writer</label>
								<input class="form-control" name="writer" value="${todo.writer }" readonly>
							</div>
							
							<div class="form-group">
                            	<label>
                            	<input type="checkbox" id="finished" class="form-control" name="finished" 
                            	<c:if test="${todo.finished}">checked</c:if>>
                            		Finished
                            	</label>
                        	</div>
							
							<button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
							<button type="submit" data-oper="modify" class="btn btn-primary">Modify</button>
							<button type="submit" data-oper="list" class="btn btn-info">List</button>
						</form>
					</div>
					<!-- /.col-lg-6 (nested) -->
				</div>
				<!-- /.row (nested) -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<script type="text/javascript">
	//jQuery를 사용하여 문서가 준비되었을 때($(document).ready) 실행되는 함수를 정의
	$(document).ready(function() {
		const formObj = $("form");
		
		$("button").on("click", function(e) {
			//클릭 이벤트가 발생하면 해당 버튼의 기본 동작인 폼 제출을 막기 위해(submit의 기본 동작 해제) e.preventDefault();를 호출
			e.preventDefault();
			//클릭된 버튼의 data-oper 속성 값을 읽어와 operation 변수에 저장하고, 이 값을 콘솔에 출력
			const operation = $(this).data("oper")
			
			//문서가 로드되면, 버튼이 클릭될 때 그 클릭된 버튼의 data-oper 속성 값을 콘솔에 출력하는 기능
			console.log(operation);
			
			//이전에 정의한 operation 변수의 값을 기반으로 <form>의 속성을 동적으로 변경하고, 변경된 속성을 사용하여 폼을 제출
			if (operation === 'remove') {
				formObj.attr("action", "/todo/remove")
					   .attr("method", "POST")
			} else if (operation === 'modify') {
				formObj.attr("action", "/todo/modify")
					   .attr("method", "POST")
			} else if (operation === 'list') {
				formObj.attr("action", "/todo/list")
					   .attr("method", "GET")
			}
			
			//변경된 속성을 사용하여 폼을 제출
			formObj.submit();
		})
		
		//JavaScript 변수에 todo.finished 값을 할당하여 사용
        const isFinished = ${todo.finished ? 'true' : 'false'};
        
        if (isFinished) {
            $("#finished").prop("checked", true); //true이면 체크
        } else {
            $("#finished").prop("checked", false); //false이면 체크 해제
        }
	})
</script>

<%@ include file="../includes/footer.jsp"%>