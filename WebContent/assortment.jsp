<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
${headTag}
</head>
<body>

	<%@ include file = 'top_bar.jsp' %>
	
	<%@ page import="pack.Good" %>
	<%@ page import="pack.DB_Connector" %>
	<%@ page import="java.util.*" %>
	
	<%
		DB_Connector obj = new DB_Connector();
		obj.connectDatabase();
		List<Good> goods = (List<Good>)session.getAttribute("goods");
		List<String> companies = obj.getCompanyList();
	%>
	
    <!-- Page Content -->
    <div class="container">
	    <!-- Title -->
        <div class="row">
            <div class="col-lg-12">
                <h3>Наши товары:</h3>
                <a href="#" data-toggle="modal" data-target="#Filter">Отфильтровать</a>
            </div>
        </div>

		<%if (goods.size() > 0) {%>
	        <!-- Page Features -->
	        <div class="row text-center">
				
				<% for (Good good : goods) { %>			
	            <div class="col-md-3 col-sm-6 hero-feature">
	                <div class="thumbnail">
	                    <img src=<%out.print(good.get_path()); %> alt=<%out.print(good.get_model()); %> style="width:304px;height:228px;">
	                    <div class="caption">
	                    	<form action="AddOrder" method="get">
		                        <h3><% out.print(good.get_name()); %></h3>
		                        <p><font color="blue"><% out.print(good.get_company()); %></font></p>
		                        <p> Модель: <font color="red"><% out.print(good.get_model()); %></font></p>
		                        <p> Рейтинг: <font color="red"><% out.print(good.get_rating()); %></font></p>
		                        <% if (good.get_discount() > 0) { %>
			                        <h4> Цена: <del><font color="red" size=2><%out.print(good.get_price()); %></font></del></h4>
			                        <h4><font color="green"><%out.print(good.get_price() * (1 - good.get_discount())); %></font></h4>
		                        <%}else {%>
		                        	<h4> Цена: </h4>
		                        	<h4><font color="green"><%out.print(good.get_price()); %><span class="glyphicon glyphicon-ruble"></span></font></h4>
		                        <%} %>
		                        <input type="hidden" name="id" value="<%out.print(good.get_id());%>">
		                        <input type="hidden" name="model" value="<%out.print(good.get_model());%>">
		                        <p>
			                        <input type="submit" name="more" class="btn btn-primary" value="Подробнее">
			                        <input type="submit" name="add" class="btn btn-primary" value="В корзину">
		                        </p>
	                        </form>
	                    </div>
	                </div>
	            </div>
           <%
               }
		   }
		   else
		   {%>
			   <br>
			   <font size=3 color="red">Товаров, удовлетворяющих условиям поиска не обнаружено</font>
		   <%} %>
		   
           

        </div>
        <!-- /.row -->
     </div>
     
    
	
      <div class="modal fade" id="Filter" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">Фильтрация изделий:</h4>
            </div>
            <div class="modal-body">           
			    <form class="form-horizontal" action="Filter">
			    	  <input type="hidden" value="<%out.print(session.getAttribute("name")); %>" name="type">
					  <div class="form-group">				  
						<label for="low_price" class="col-xs-2 control-label">Цена:</label>
				        <div class="row">
				        	<div class="col-xs-3">
				          		<input type="text" size=3 class="form-control" name="low_price" id="low_price" value=0> 
						    </div>
				        	<div class="col-xs-1">
				          		- 
						    </div>
						    <div class="col-xs-3">
						    	<input type="text" size=3 class="form-control" name="high_price" value=30000>
						    </div>
				        </div>
					  </div>
			
					  <div class="form-group">
						<label for="low_price" class="col-xs-2 control-label">Рейтинг:</label>
				        <div class="row">
				        	<div class="col-xs-3">
				          		<input type="text" size=3 class="form-control" name="low_rate" id="low_rate" value=0> 
						    </div>
				        	<div class="col-xs-1">
				          		- 
						    </div>
						    <div class="col-xs-3">
						    	<input type="text" size=3 class="form-control" name="high_rate" value=5>
						    </div>
				        </div>
					  </div>
					  
					 <div class="form-group"> 
						 <label for="company" class="col-xs-2 control-label">Фирма:</label> 
						 <div class="col-xs-7">
					      <select class="form-control" name="company">
					      	  <option value="any">Любая</option>
					      	  <%for (String name:companies) { %>
							  <option value=<%out.print(name); %>><%out.print(name); %></option>
							  <%} %>
					      </select>
					    </div>
				    </div>
				    <div class="row">
			        <div class="col-xs-offset-8 col-xs-3">
			            <div class="col-xs-10">
							<button type="submit" class="btn btn-success">Отфильтровать</button>
						</div>
			        </div>
			        </div>
			    </form>
			</div>
          </div>
        </div>
      </div>
     <%@ include file = 'footer.jsp'%>

</body>
</html>