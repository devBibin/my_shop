<%@page contentType="text/html" pageEncoding="UTF-8"%>

<hr>

<!-- Footer -->
<footer>
    <div class="row">
        <div class="col-lg-12">
            <p>Copyright &copy; MGTU 2016</p>
        </div>
    </div>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<% if ((session.getAttribute("registed") != null) && ((boolean)session.getAttribute("registed"))) {%>
<script> alert("Вы успешно зарегистрировались!")</script>
<% session.setAttribute("registed", null);}%>

<%
if (session.getAttribute("alert") != null)
{
int a= (int)session.getAttribute("alert");
switch (a) {
	case 1:%>
		<script> alert("Вам необходимо залогиниться!")</script>
	<%break;
	case 2:%>
		<script> alert("Спасибо! Ваш голос учтен!")</script>
	<%break;
	case 3:%>
		<script> alert("Вы уже голосовали за эту модель!")</script>
	<%break;
	case 4:%>
		<script> alert("Спасибо за оставленный отзыв!")</script>
	<%break;
	case 5:%>
		<script> alert("Спасибо за покупку!")</script>
	<%break;
	case 6:%>
		<script> alert("Неверный логин или пароль!")</script>
	<%break;
default:
}
session.setAttribute("alert", 0);
}
%>