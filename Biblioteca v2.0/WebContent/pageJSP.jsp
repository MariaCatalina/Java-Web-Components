<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="Header.jsp"%>

<%-- jsp-ul contine prima pagină --%>

<br>
<br>
<br>
<br>
<style>
* {
	margin: 0;
	padding: 0;
	outline: none;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;

}

body {
	-webkit-font-smoothing: antialiased;
	font-family: 'Helvetica Neue', sans-serif;
	font-size: 15px;
	font-weight: 400;
	height: auto !important;
	height: 100%;
	min-height: 100%;
	text-rendering: optimizeLegibility
}

div.wrapper {

	width: 250px;
	position:fixed;
}

nav.vertical {
	border-radius: 4px;
	box-shadow: 0 0 10px rgba(0, 0, 0, .15);
	overflow: hidden;
	text-align: center;
}

nav.vertical>ul {
	list-style-type: none;
}

nav.vertical>ul>li {
	display: block;
}

nav.vertical>ul>li>label, nav.vertical>ul>li>a {
	background-color: rgb(157, 34, 60);
	background-image: -webkit-linear-gradient(135deg, rgb(204, 102, 0),
		rgb(204, 102, 0));
	background-image: -moz-linear-gradient(135deg, rgb(204, 102, 0),
		rgb(204, 102, 0));
	background-image: -o-linear-gradient(135deg, rgb(204, 102, 0),
		rgb(204, 102, 0));
	background-image: linear-gradient(135deg, rgb(204, 102, 0),
		rgb(204, 102, 0));
	border-bottom: 1px solid rgba(255, 255, 255, .1);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .1), 0 1px 1px
		rgba(0, 0, 0, .1);
	color: rgb(255, 255, 255);
	display: block;
	font-size: .85rem;
	font-weight: 500;
	height: 50px;
	letter-spacing: .5rem;
	line-height: 50px;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .1);
	text-transform: uppercase;
	transition: all .1s ease;
}

nav.vertical>ul>li>label:hover, nav.vertical>ul>li>a:hover {
	background-color: rgb(114, 51, 98);
	background-image: -webkit-linear-gradient(150deg, rgb(204, 102, 0),
		rgb(204, 102, 0));
	background-image: -moz-linear-gradient(150deg, rgb(204, 102, 0),
		rgb(204, 102, 0));
	background-image: -o-linear-gradient(150deg, rgb(204, 102, 0),
		rgb(204, 102, 0));
	background-image: linear-gradient(150deg, rgb(204, 102, 0),
		rgb(204, 102, 0));
	cursor: pointer;
}

nav.vertical>ul>li>label+input {
	display: none;
	visability: hidden;
}

nav.vertical>ul>li>div {
	background-color: rgb(255, 255, 255);
	max-height: 0;
	overflow: hidden;
	transition: all .5s linear;
}

nav.vertical>ul>li>label+input:checked+div {
	max-height: 500px;
}

nav.vertical>ul>li>div>ul {
	list-style-type: none;
}

nav.vertical>ul>li>div>ul>li>a {
	background-color: rgb(255, 255, 255);
	border-bottom: 1px solid rgba(0, 0, 0, .05);
	color: #333331;
	display: block;
	font-size: 1.1rem;
	padding: 10px 0;
	text-decoration: none;
	transition: all 0.15s linear;
}

nav.vertical>ul>li>div>ul>li:hover>a {
	background-color: #994600;
	color: rgb(255, 255, 255);
	padding: 10px 0 10px 50px;
}
</style>

<% if( request.getAttribute("role").equals("bibliotecar")) { %>

<div class="wrapper">
		<nav class="vertical">

			<ul>
				<li><label for="home">Add books</label> <input type="radio"
					name="verticalMenu" id="home" />
					<div>
						<ul>
							<li><a href="AddAuthor.jsp">Add an author</a></li>
							<li><a href="AddBook">Add a book</a></li>
						</ul>
					</div></li>
				<li><label for="blog">List of books</label> <input type="radio"
					name="verticalMenu" id="blog" />
					<div>
						<ul>
							<li><a href="ListAuthors">List of authors</a></li>
							<li><a href="ListaCarti">List of books</a></li>
							<li><a href="ListaCartiImprumutate">Borrowed books</a></li>
					   </ul>
					</div></li>
			</ul>
		</nav>
</div>
	
<%} else { %>

<div class="wrapper">
		<nav class="vertical">

			<ul>
				<li><label for="home">List of books</label> <input type="radio"
					name="verticalMenu" id="home" />
					<div>
						<ul>
							<li><a href="ListaCartiUser">List all books</a></li>
							<li><a href="ListaCartiImprumutateUser">List borrowed books</a></li>
						</ul>
					</div></li>
					
				<li><label for="profile">Profile</label> <input type="radio"
					name="verticalMenu" id="profile" />
					<div>
						<ul>
							<li><a href="ClientSetings.jsp">Edit profile</a></li>
						</ul>
					</div></li>
			</ul>
		</nav>
</div>
<%} %>
