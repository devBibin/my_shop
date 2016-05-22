<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="pack.Good" %>
<%@ page import="pack.Stores" %>
<%@ page import="pack.DB_Connector" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%
	DB_Connector obj = new DB_Connector();
	out.print(obj.printHead("Корзина"));
	obj.connectDatabase();
%>
<script src="js/jquery-1.12.3.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/my_class.css" type="text/css"/>
</head>

<body>
	<%@ include file = 'top_bar.jsp'%>
	
	<% List<Good> goods = (List<Good>)session.getAttribute("basket");
		int i = 0; 
	%>
	<div class="container">
	<div class="row">
	<div class="col-md-9">
	<%if  (session.getAttribute("basket") == null) {%>
		<p>Ваша корзина пуста</p>
	<%} else 
		if (goods.size() == 0) {%>
		<p>Ваша корзина пуста</p>
	<%} else{%>
		<div class="table-responsive">
		  <table class="table">
		    <thead>
		      <tr>
		        <th>#</th>
		        <th>Товар</th>
		        <th>Модель</th>
		        <th>Компания</th>
		        <th>Цена</th>
		        <th>Количество</th>
		        <th>Удалить</th>
		        <th></th>
		      </tr>
		    </thead> 
		    <tbody>
		      <% for (Good good : goods) { %>
		      <tr>
		        <td><%out.print(++i); %></td>
		        <td><%out.print(good.get_name()); %></td>
		        <td><%out.print(good.get_model()); %></td>
		        <td><%out.print(good.get_company()); %></td>
		        <td class="price" id=<%out.print("price" + i); %>><%out.print(good.get_price() * (1 - good.get_discount()));%><span class="glyphicon glyphicon-ruble"></span></td>
		        <td>		              
					<div class="spinbox" data-min="1" data-max=<%out.print(good.get_count()); %> data-step="1">
					    <input id=<%out.print(i); %> class="form-control spinbox-input" type="text" value=<% out.print(good.get_order()); %>>
					    <div class="spinbox-buttons"> 
					        <button class="spinbox-up btn btn-default btn-xs" type="button">+</button>
					        <button class="spinbox-down btn btn-default btn-xs" type="button">-</button>
					    </div>
					</div>   
      			</td>
      			<td>
      			<div class="row">
      				<div class="col-md-4">
      				</div>
      				<div class="col-md-8">
      					<a href="DeletefromBasket?id=<%out.print(i);%>" class="button"><span class="glyphicon glyphicon-remove-circle"></span></a>
      				</div>
      			</div>	
      			</td>
		      </tr>
	<%} %>
		    
    </tbody>
    </table>
    </div>
   		<div class="row">
			<div class="col-md-offset-8 col-md-4">
				<div class="pull-right">
					<h4> Общая цена:<font size="5" color="green" name="total" id="total"></font><span class="glyphicon glyphicon-ruble"></span></h4>
				</div>
			</div>
		</div>
		<div class="row">
			<form action="ConfirmBasket">
				<%for (int j = 1; j <= i; j++){ String id=new String("hidden" + j);%>
					<input type="hidden" id=<%out.print(id); %> name=<% out.print(j); %> value="1">
				<%} %>
				<div class="form-group">
					<div class="col-md-10">
						<input type="submit" class="btn btn-success" name="self_delivery" value="Самовывоз">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-2">
						<input type="submit" class="btn btn-success" name="delivery" value="Доставка">
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>
	<%} %>
      

	
	<script type="text/javascript">
    // jQuery must be available before binding events
	    $(document).on('click', '.spinbox-up, .spinbox-down', function() {
		  var ListPrice = $('.price')
	      var $spinbox = $(this).closest('.spinbox');
		  var input = $('input',$spinbox);
	      var total = 0;
	      
		  if ($spinbox.length) {
			  var $input = $spinbox.find('input.spinbox-input');
	          if ($input.length) {
	              var max = parseInt($spinbox.data('max')) || false;
	              var min = parseInt($spinbox.data('min')) || false;
	              var val = parseInt($input.val()) || min || 0;
	              var sign = $(this).hasClass('spinbox-up') ? 1 : -1;
	              val += sign * (parseInt($spinbox.data('step')) || 1);
	              if (max && val > max) {
	                  val = max;
	              } else if (min && val < min) {
	                  val = min;
	              }
	              $input.val(val).trigger('change');
	          }
	          document.getElementById("hidden"+input[0].id).value=input[0].value;
	          var ListSpins= $('.spinbox');
		      var ListInp = $('input', ListSpins);
		      for (i = 0; i < ListInp.length; i++){
		    	  
		    	  total += ListInp[i].value * Number(ListPrice.eq(i).text());
		      }
		      $("#total").text(total);
	      }
	  });
	</script>
	
	<script type="text/javascript">
	$(function(){
		var total = 0;
		var ListPrice = $('.price')
	    var ListSpins= $('.spinbox');
	    var ListInp = $('input', ListSpins);
	    for (i = 0; i < ListInp.length; i++){
	  	  
	  	  total += ListInp[i].value * Number(ListPrice.eq(i).text());
	    }
	    $("#total").text(total);	
	})

	</script>
	
	<%@ include file = 'footer.jsp'%>
</body>
</html>