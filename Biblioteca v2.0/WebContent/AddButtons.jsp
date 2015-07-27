<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="Header.jsp"%>

<br>
<br>
<br>
<style>
* {
	margin: 10;
	padding: 0;
	outline: none;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

body {
	-webkit-font-smoothing: antialiased;
	font-family: 'Helvetica Neue', sans-serif;
	font-size: 10px;
	font-weight: 300;
	height: auto !important;
	height: 50%;
	min-height: 50%;
	text-rendering: optimizeLegibility;
	position: letf;
}

div.wrapper {
	margin: 10px;
	width: 230px;
}

p {
	font-family: georgia;
	font-size: 1rem;
	line-height: 25px;
	margin: 24px 0;
	text-align: left;
}

nav.vertical {
	border-radius: 2px;
	box-shadow: 0 0 10px 336600;
	overflow: hidden;
	text-align: center;
}

nav.vertical>ul {
	list-style-type: none;
}

nav.vertical>ul>li {
	display: block;
}

nav.vertical>ul>li>a {
	background-color: 339933;
	background-image: -webkit-linear-gradient(135deg, rgb(0, 153, 0),
		rgb(0, 153, 0));
	background-image: -moz-linear-gradient(135deg, rgb(0, 153, 0),
		rgb(0, 153, 0));
	background-image: -o-linear-gradient(135deg, rgb(0, 153, 0),
		rgb(0, 153, 0));
	background-image: linear-gradient(135deg, rgb(0, 153, 0), rgb(0, 153, 0));
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
	text-decoration: none;
}

nav.vertical>ul>li>a:hover {
	background-color: 336600;
	background-image: -webkit-linear-gradient(150deg, 336600, 336600);
	background-image: -moz-linear-gradient(150deg, rgb(114, 51, 98), 336600);
	background-image: -o-linear-gradient(150deg, rgb(114, 51, 98), 336600);
	background-image: linear-gradient(150deg, rgb(114, 51, 98), 336600);
	cursor: pointer;
}

nav.vertical>ul>li>div {
	background-color: 336600;
}

nav.vertical>ul>li>div>ul {
	list-style-type: none;
}

nav.vertical>ul>li>div>ul>li>a {
	background-color: 339933;
	border-bottom: 1px solid rgba(0, 0, 0, .05);
	color: #333331;
	display: block;
	font-size: 1.1rem;
	padding: 10px 0;
	text-decoration: none;
	transition: all 0.15s linear;
}

nav.vertical>ul>li>div>ul>li:hover>a {
	background-color: lightGreen;
	color: rgb(255, 255, 255);
	padding: 10px 0 10px 50px;
}
</style>

<div class="wrapper">
	<nav class="vertical">
		<ul>
			<li><a href="#">Menu</a>
				<div>
					<ul>
						<li><a href="ListAuthors">List of authors</a></li>
						<li><a href="#">Add an author</a></li>
						<li><a href="#">Add a book</a></li>
						<li><a href="index">Return to main page</a></li>
					</ul>
				</div></li>
		</ul>
	</nav>
</div>
</body>
<html>