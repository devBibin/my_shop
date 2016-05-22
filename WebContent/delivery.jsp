<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="pack.Good" %>
<%@ page import="pack.Stores" %>
<%@ page import="pack.DB_Connector" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
${headTag}
<script src="js/jquery-1.12.3.js" type="text/javascript"></script>
</head>

<body>
	<% DB_Connector obj = new DB_Connector();
	   obj.connectDatabase();
	%>
    
	<%@ include file = 'top_bar.jsp'%>
	
	<br>
    <div class="container">
    	<div class="col-md-5">
	   		<h4 class="modal-title" id="myModalLabel">Доставка</h4>
	   		<div class="row">
		   		<form action="ConfirmOrder">
		   			<div class = "form-group">
							<textarea rows="3" cols="72" name="location" placeholder="Введите адрес"></textarea>
							<br>
							<br>
							<input type="checkbox" id="isCardSelected" checked="checked"> Оплата по карте
							<br>
							<input type="text" size="74" id="card" name="card" placeholder="Введите реквизиты карты">
			    	</div>
		    		<div class = "form-group">
		    			<div class="row">
		    				<div class="pull-right">
								<input type="submit" class="btn btn-success" name="delivery" value="Оплатить">
							</div>  
				    	</div> 
		    		</div>
		    	</form>
	    	</div>
    	</div>
    	
   	</div> 
	
	<%@ include file = 'footer.jsp'%>

	<script type="text/javascript">
	$(function(){
		$('#isCardSelected').click(function() {
		    $("#card").toggle(this.checked);
		    document.getElementById("card").value = "";
		});	
	})
	</script>
</body>
</html>