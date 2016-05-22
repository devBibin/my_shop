  <%@page contentType="text/html" pageEncoding="UTF-8"%>
  
    <!--- You code here -->
    <nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="MainPage">BuyTheWay</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="MainPage">Домашняя</a></li>
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	      <% if (session.getAttribute("login") == null) { %>
				<li><a href="#" data-toggle="modal" data-target="#myModal1"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
	    		<li><a href="#" data-toggle="modal" data-target="#myModal2"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			<% } else { %>
				<li><H5> <font color=blue> You entered as <%=(String)session.getAttribute("login") %></font> </H5></li>		
				<li><a href="basket.jsp" class="button"><span class="glyphicon glyphicon-circle-arrow-down"></span> Корзина</a></li>		
				<li><a href="Logout" class="button"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
			<% } %>
	    </ul>
	  </div>
	</nav>

 	  <!-- Modal1 - register -->
      <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">Регистрация</h4>
            </div>
            <div class="modal-body">           
			    <form class="form-horizontal" action="Register" method="post">
			      <div class="form-group">
			        <label for="surname" class="col-xs-2 control-label" >Фамилия:</label>
			        <div class="col-xs-10">
			          <input type="text" class="form-control" name="surname" placeholder="Введите фамилию" required>
			        </div>
			      </div>
				  <div class="form-group">
			        <label for="name" class="col-xs-2 control-label">Имя:</label>
			        <div class="col-xs-10">
			          <input type="text" class="form-control" name="name" placeholder="Введите имя" required>
			        </div>
			      </div>
				  <div class="form-group">
			        <label for="sec_name" class="col-xs-2 control-label">Отчество:</label>
			        <div class="col-xs-10">
			          <input type="text" class="form-control" name="sec_name" placeholder="Введите отчество" required>
			        </div>
			      </div>			      
			      <div class="form-group">
			        <label for="inputEmail" class="col-xs-2 control-label">Адрес email:</label>
			        <div class="col-xs-10">
			          <input type="email" class="form-control" name="mail" placeholder="Введите email" required>
			        </div>
			      </div>
			      <div class="form-group">
			        <label for="inputPassword" class="col-xs-2 control-label">Пароль:</label>
			        <div class="col-xs-10">
			          <input type="password" class="form-control" name="password" placeholder="Введите пароль" required>
			        </div>
			      </div>
			      <div class="form-group">
				    <label for="inputPassword" class="col-xs-2 control-label">Телефон:</label>
				    <div class="col-xs-10">
				      <input type="text" class="form-control bfh-phone" name= "phone" data-format="+1 (ddd) ddd-dddd" placeholder="Введите телефон" required>
				    </div>
			      </div>
			      <div class="form-group">
			        <div class="col-xs-offset-2 col-xs-10">
			            <div class="checkbox">
						  <label><input name="sex" type="checkbox" value="male" checked>М</label>
						  <label><input name="sex" type="checkbox" value="female">Ж</label>
						</div>
			        </div>
			      </div>
			      <div class="form-group">
			        <div class="col-xs-offset-8 col-xs-3">
			            <div class="col-xs-10">
							<button type="submit" class="btn btn-success">Зарегистрироваться</button>
						</div>
			        </div>
			      </div>
			    </form>
			</div>
          </div>
        </div>
      </div>
      
      <!-- Modal2 - login -->
      <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">Войти</h4>
            </div>
            <div class="modal-body">
			    <form class="form-horizontal" action="Authorization" method="post">	      
			      <div class="form-group">
			        <label for="login" class="col-xs-2 control-label">Логин:</label>
			        <div class="col-xs-10">
			          <input type="email" class="form-control" id="login" name="login" placeholder="Введите email" required>
			        </div>
			      </div>
			      <div class="form-group">
			        <label for="inputPassword" class="col-xs-2 control-label">Пароль:</label>
			        <div class="col-xs-10">
			          <input type="password" class="form-control" id="password" name="password" placeholder="Введите пароль" required>
			        </div>
			      </div>
			      <div class="form-group">
			        <div class="col-xs-offset-8 col-xs-3">
			            <div class="col-xs-10">
							<button type="submit" class="btn btn-success">Войти</button>
						</div>
			        </div>
			      </div>
			    </form>
            </div>
          </div>
        </div>
      </div>
      
      <form>
      <div class="container">
	  <ul class="nav nav-tabs">
	    <li class="dropdown">
	      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Кухня <span class="caret"></span></a>
	      <ul class="dropdown-menu">
		        <li><a href="Assortment_Controller?type=Блендер">Блендеры</a></li>
		        <li><a href="Assortment_Controller?type=Блинница">Блинницы</a></li>
		        <li><a href="Assortment_Controller?type=Миксер">Миксеры</a></li>
		        <li><a href="Assortment_Controller?type=Мультиварка">Мультиварки</a></li>
		        <li><a href="Assortment_Controller?type=Мясорубка">Мясорубки</a></li>
		        <li><a href="Assortment_Controller?type=Холодильник">Холодильники</a></li>                          
	      </ul>
	    </li>
	    <li class="dropdown">
	      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Развлечения <span class="caret"></span></a>
	      <ul class="dropdown-menu">
	        <li><a href="Assortment_Controller?type=Телевизор">Телевизоры</a></li>
	        <li><a href="Assortment_Controller?type=Смартфон">Смартфоны</a></li>                        
	      </ul>
	    </li>
	    <li class="dropdown">
	      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Разное <span class="caret"></span></a>
	      <ul class="dropdown-menu">
	        <li><a href="Assortment_Controller?type=Весы">Весы</a></li>
	        <li><a href="Assortment_Controller?type=Пылесос">Пылесосы</a></li>
	        <li><a href="Assortment_Controller?type=Утюг">Утюги</a></li>                        
	      </ul>
	    </li>
	  </ul>
	</div>
	</form>
	