<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
${headTag}
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/star-rating.css" media="all" type="text/css"/>
<link rel="stylesheet" href="css/theme-krajee-fa.css" media="all" type="text/css"/>
<link rel="stylesheet" href="css/theme-krajee-svg.css" media="all" type="text/css"/>
<link rel="stylesheet" href="css/theme-krajee-uni.css" media="all" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="js/star-rating.js" type="text/javascript"></script>
</head>
<body>

	<%@ include file = 'top_bar.jsp'%>
	<%@ page import="pack.Good" %>
	<%@ page import="pack.Reports" %>
	<%@ page import="pack.DB_Connector" %>
	<%@ page import="java.util.*" %>
	
    <% Good good = (Good)session.getAttribute("item"); %>
    <% List<Reports> report_list = (List<Reports>)session.getAttribute("report_list");%>
    
    <div class="container">
    	<br>
        <div class="row">
            <div class="col-md-9">
				<div class="thumbnail">
                    <img src=<%out.print(good.get_path()); %> alt=<%out.print(good.get_model()); %> style="width:334px;height:255px;">
                        <div class="text-center">
	                        <h4><font size=5 color=red><%out.print(good.get_name()); out.print("-"); out.print(good.get_model()); %></font></h4>                     
	                        <h4><font size=4 color=blue><%out.print(good.get_company()); %></font></h4>
	                       	<% if (good.get_discount() > 0) { %>
		                        <h4> Цена: <del><font color="red" size=2><%out.print(good.get_price()); %></font></del></h4>
		                        <h4><font color="green"><%out.print(good.get_price() * (1 - good.get_discount())); %></font></h4>
	                        <%}else {%>
	                        	<h4> Цена: </h4>
	                        	<h4><font color="green"><%out.print(good.get_price()); %><span class="glyphicon glyphicon-ruble"></span></font></h4>
	                        <%} %>
	                        <% if (good.get_count() > 0) {%>
	                        	<h4><font size=3 color=green>Есть в наличии</font></h4> 
	                        <%}else {%>
	                        	<h4><font size=3 color=red>Нет в наличии</font></h4>
	                        <%}%>
                        </div>  
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum
                        </p>
                        
	                        <form action="MakeVote">
						        <input type="text" class="rating rating-loading" value=<%out.print(good.get_rating()); %> data-size="xs" title="" name="rate">
								<div class="row">
									<div class="col-md-8"><button type="submit" class="btn btn-success">Проголосовать</button></div>
									<div class="col-md-4">
								          <div class="text-right">
								          <% if (good.get_count() > 0) {%>
								           	 <a href="AddBasket" class="btn btn-success btn-md" >Добавить в корзину</a>
								          <%}%>   	
								          </div>									
									</div>

								</div>                  	
	                        </form>							
                  	                      
				</div>
			</div>	
		</div>
				
		<div class="row">
			<div class="col-md-9">
	                <div class="thumbnail">
						<div class="caption-full">
		                    <div class="text-right">
		                        <a class="btn btn-success btn-md" href="#" data-toggle="modal" data-target="#Reporting">Оставить отзыв</a>
		                    </div>
		
		                    <hr>
							<% if (report_list.size() == 0) {%>
		                    <div class="row">
		                        <div class="col-md-12">
		                            <p>Нет отзывов</p>
		                        </div>
		                    </div>
		                    <%} else {
		                    	for (Reports report : report_list) {
		                    %>
		                    <div class="row">
		                        <div class="col-md-12">
		                            <%out.print(report.getAuthor()); %>
		                            <span class="pull-right"><% out.print(report.getDate()); %></span>
		                            <p><%out.print(report.getReport()); %></p>	                                                      
		                        </div>
		                    </div>
		
		                    <hr>
		                    <%}} %>
	                    </div>
	                </div>
	         </div>
         </div>
         


        </div>
    <!-- /.container -->
    
    
      <!-- Modal2 - login -->
      <div class="modal fade" id="Reporting" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">Оставьте Ваш отзыв!</h4>
            </div>
            <div class="modal-body">
			    <form class="form-horizontal" action="SendReport">	      
			      <div class="form-group">
			        <div class="col-xs-10">
      					<textarea class="form-control" name="report"></textarea>
			        </div>
			      </div>
			      <div class="form-group">
			        <div class="col-xs-offset-8 col-xs-3">
			            <div class="col-xs-10">
							<button type="submit" class="btn btn-success">Отправить</button>
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