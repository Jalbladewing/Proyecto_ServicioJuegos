<%@ page import ="java.util.ArrayList"%>
<%@ page import ="ual.dwsc.Noticia"%>

<html>
    <head>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"/>
		<link rel="stylesheet" href="css/style.css">
		<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    
       <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
       <meta name="Author" content="Luis Iribarne">
       <meta name="Description" content="University of Almeria (Spain)">
       <title>Prototipo HTML para el Productor-Consumidor</title>
    </head>
    <body>
    <%@include  file="includes/header.html" %>
    
       <div class="container">
			<div class="row">
		
				<section class="content">
					<h1>Noticias</h1>
					<div class="col-md-8 col-md-offset-2">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="pull-right">
									<div class="btn-group">
										
									</div>
								</div>
								<div class="table-container">
									<table class="table table-filter">
										<tbody>
											<%
												  ArrayList<Noticia> noticias = (ArrayList<Noticia>) request.getAttribute("noticias"); 
												  for (Noticia noticia: noticias) {   
											%>
											<tr>
												<td style="width: 69.7%;">
													<div class="media">
														<a href="#" class="pull-left">
															<img src="images/news.jpg" class="media-photo">
														</a>
														<div class="media-body">
															<span class="media-meta pull-right"><%=noticia.getDate()%></span>
															<h4 class="title">
																<%=noticia.getShortDescription()%>
															</h4>
															<p class="summary"><%=noticia.getLongDescription()%></p>
														</div>
													</div>
												</td>
											</tr>
											<%}%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="content-footer">
							<p>
								Page © - 2019 <br>
								Powered By <a href="" target="_blank">------</a>
							</p>
						</div>
					</div>
				</section>
				
			</div>
		</div>
    </body>
</html>