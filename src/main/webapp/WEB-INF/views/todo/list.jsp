<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<form action="/todo/list" method="get">
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Featured</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h1>Special title treatment</h1>
			</div>

			<div style="text-align: center;">
				<a href="/todo/register">등록</a>
			</div>
			<!-- /.panel-heading -->
			
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>Tno</th>
							<th>Title</th>
							<th>Writer</th>
							<th>DueDate</th>
							<th>Finished</th>
						</tr>
					</thead>

					<c:forEach var="todo" items="${list }">
						<tr>
							<td>${todo.tno }</td>
							<td><a href="/todo/get?tno=${todo.tno }">${todo.title }</a></td>
							<td>${todo.writer }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${todo.dueDate }" /></td>
							<td>${todo.finished }</td>
						</tr>
					</c:forEach>
				</table>

				<div style="text-align: center;">
    				<h3>Footer</h3>
				</div>
				
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Modal title</h4>
							</div>
							<div class="modal-body">처리가 완료되었습니다.</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<!-- <button type="button" class="btn btn-primary">Save
									changes</button> -->
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
</form>

<script type="text/javascript">
	$(document).ready(
			function() {
				const result = '<c:out value="${result}"/>'
				checkModal(result);
				
				function checkModal(result) {
					if (result === '') {
						return;
					}

					if (parseInt(result) > 0) {
						$(".modal-body").html("할 일 " + parseInt(result) + "번 등록 완료.")
					}

					$("#myModal").modal("show");
				}
			});
</script>

<%@ include file="../includes/footer.jsp"%>