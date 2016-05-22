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
	<%@ include file = 'top_bar.jsp'%>
	<% DB_Connector obj = new DB_Connector();
	   obj.connectDatabase();
	   int index;
	%>
	
    <% List<Good> goods = (List<Good>)session.getAttribute("basket");
       List<Stores> stores = new ArrayList<Stores>();
	   if (session.getAttribute("stores") == null){
		   stores = obj.getStoresList(goods.get(0).get_id());
		   index =  goods.get(0).get_id();
	   }	
	   else{
		    stores = (List<Stores>)session.getAttribute("stores");
		    index =  (int)session.getAttribute("chosen_good");
	   }
	%>
	
	<br>
	<div class="container">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-5">
					<font size="5" color="green"><%out.print(obj.getGood(index).get_name()+ " " + obj.getGood(index).get_model()); %></font>
					<br>
					<font size="3" color="blue"><%out.print(obj.getGood(index).get_company()); %></font>
				</div>
			</div>
		
			
			
			<div class="row">
				<div class="col-md-9">
				
		 		<table class="table">
		 		<thead>
		 			<tr>
		 				<th>Метро</th>
		 				<th>Адрес</th>
		 				<th>В наличии</th>
		 			</tr>
		 		</thead>
		 		<tbody>
		 			<%for (Stores store: stores){%>
		 			<tr>
						  <td><%out.print(store.get_subway()); %></td>
						  <td><%out.print(store.get_adress()); %></td>
						  <td><%out.print(store.get_count()); %></td>
		 			</tr>
		 			<%} %>
		 		</tbody>
		 		</table>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<form action="ConfirmOrder">
		   			<div class = "form-group">
							<input type="checkbox" id="isCardSelected" checked="checked" name="controller" value="checked"> Оплата по карте
							<br>
							<input type="text" size="74" id="card" name="card" placeholder="Введите реквизиты карты">
			    	</div>
		    		<div class = "form-group">
		    			<div class="row">
		    				<div class="col-md-9">
								<input type="submit" class="btn btn-success" name="auto" value="Оплатить">
							</div>  
				    	</div> 
		    		</div>
		    	</form>
				</div>
				<div class="col-md-3">
					<br>
					<form action="ChooseGood">
					     <select class="form-control" name="company">
				     	  <%for (Good good:goods) { %>
					  			<option value=<%out.print(good.get_id()); %>><%out.print(good.get_name() +" "+ good.get_model()); %></option>
					  	  <%} %>
				     	</select>
				     	<div class="pull-right">
				     		<input type="submit" class="btn-primary" value="Отобразить">
				     	</div>
					</form>	
				</div>
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