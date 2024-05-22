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
			<div class="panel-heading">Featured</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-10">
					<form action="/todo/modify" id="operForm" method="get">
						<input type="hidden" id="tno" name="tno" value="${todo.tno }">
						
						<div class="form-group">
							<label>TNO</label>
							<input class="form-control" name="tno" value="${todo.tno }" readonly>
						</div>
						
						<div class="form-group">
							<label>Title</label>
							<input class="form-control" name="title" value="${todo.title }" readonly>
						</div>
						
						<div class="form-group">
							<label>DueDate</label>
							<input type="date" class="form-control"
							 value="<fmt:formatDate pattern="yyyy-MM-dd" value="${todo.dueDate }" />" readonly>
						</div>
						
						<div class="form-group">
							<label>Writer</label>
							<input class="form-control" name="writer" value="${todo.writer }" readonly>
						</div>
						
						<div class="form-group">
                            <label>
                            <input type="checkbox" id="finished" class="form-control" name="finished" 
                            <c:if test="${todo.finished}">checked</c:if> disabled>
                            	Finished
                            </label>
                        </div>
						
						<button onclick="location.href='/todo/modify?tno={todo.tno}'" data-oper="modify" class="btn btn-primary">Modify</button>
						<button onclick="location.href='/todo/list'" data-oper="list" class="btn btn-success">List</button>
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
	const operForm = $("#operForm");

	$("button[data-oper='modify']").on("click", function(e) {
		operForm.attr("action", "/todo/modify").submit();	
	});

	$("button[data-oper='list']").on("click", function(e) {
		operForm.find("#tno").remove();
		operForm.attr("action", "/todo/list")
		operForm.submit();
	});
</script>

<%@ include file="../includes/footer.jsp"%>