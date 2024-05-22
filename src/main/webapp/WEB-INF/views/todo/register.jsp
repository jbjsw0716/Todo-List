<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-4">
		<h1 class="page-header">Navbar</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-4">
		<div class="panel panel-default">
			<div class="panel-heading">Featured</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form role="form" action="/todo/register" method="post">
							<div class="form-group">
								<label>Title</label> <input class="form-control" name="title" required>
							</div>
							
							<div class="form-group">
								<label>DueDate</label>
								<input type="date" class="form-control" name="dueDate" required>
							</div>
							
							<div class="form-group">
								<label>Writer</label> <input class="form-control" name="writer" required>
							</div>
							
							<button onclick="location.href='/todo/list'" class="btn btn-success">List</button>
							<button type="submit" class="btn btn-info">Submit</button>
							<button type="reset" class="btn btn-danger">Reset</button>
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

<%@ include file="../includes/footer.jsp"%>