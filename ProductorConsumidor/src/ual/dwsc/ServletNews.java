package ual.dwsc;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.omg.CORBA.ORB;
import org.omg.CORBA.StringHolder;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import BufferApp.Buffer;
import BufferApp.BufferHelper;

import java.net.*;
public class ServletNews extends HttpServlet {
	
	/** The buffer impl. */
	static Buffer bufferImpl;
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
       StringHolder aux = new StringHolder();
       response.setContentType("text/html");
       PrintWriter out = response.getWriter();
       out.println("<html>");
       out.println("<head>");
       out.println("<title>Desarrollo Web Basado en Servicios y Componentes. UAL 2018</title>");
       out.println("</head>");
       out.println("<body>");
       
       if(Validador.validate("D:\\Descargas\\Informatica\\Master Informatica\\Segundo Cuatrimestre\\Desarrollo Web\\noticias.xml", "D:\\Descargas\\Informatica\\Master Informatica\\Segundo Cuatrimestre\\Desarrollo Web\\noticias.xsd") && Parser.validate("D:\\Descargas\\Informatica\\Master Informatica\\Segundo Cuatrimestre\\Desarrollo Web\\noticias.xml"))
       {
    	   try 
    	   {
    		   getreference();

    		   /*if (request.getParameter("action").compareTo(" Leer noticia ") == 0)
    		   {
    			   
    			   if(bufferImpl.get(aux))
        		   {
      					
      					List<Noticia> listaNoticias = XMLCoder.decodeXML("noticias");
    	            	out.println(listaNoticias.get(0).toString() +"<br>");
    	            	
    	            	listaNoticias.remove(0);
    	            	XMLCoder.codeXML("noticias", listaNoticias);
      	               
      				}else
      				{
      					out.println("No hay noticias disponibles");
      				}
    		   }else
    		   {
    			   if(bufferImpl.read(aux))
      				{
      					
      					List<Noticia> listaNoticias = XMLCoder.decodeXML("noticias");
      					out.println(listaNoticias.get(0).toString() +"<br>");
      	               
      				}else
      				{
      					out.println("No hay noticias disponibles");
      				}
    		   }   */
    		   
    		   /*if(bufferImpl.read(aux))
 				{
 					
 					List<Noticia> listaNoticias = XMLCoder.decodeXML("noticias");
 					out.println(listaNoticias.get(0).toString() +"<br>");
 	               
 				}else
 				{
 					out.println("No hay noticias disponibles");
 				}*/
    		   
    		   List<Noticia> noticias = XMLCoder.decodeXML("noticias");
    		   RequestDispatcher dispatcher = request.getRequestDispatcher("playerNews.jsp");
    		   request.setAttribute("noticias", noticias); // set your String value in the attribute
    		   dispatcher.forward( request, response );
			
    	   } catch (Exception e) 
    	   {
    		   
    		   out.println("Error al obtener la noticia");
    	   }
    	      
       }else
       {
    	   out.println("Error en la validación del documento");
       }

       
       out.println("</body></html>");
    }
    public void doPost (HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
       Enumeration elements = request.getParameterNames();
       String name1 = (String)elements.nextElement(); /** Fecha */
       String value_name1 = request.getParameter(name1);
       String name2 = (String)elements.nextElement(); /** Descripción Corta */
       String value_name2 = request.getParameter(name2);
       String name3 = (String)elements.nextElement(); /** Descripción Larga */
       String value_name3 = request.getParameter(name3);
       
       response.setContentType("text/html");
       PrintWriter out = response.getWriter();
       out.println("<html>");
       out.println("<head>");
       out.println("<title>Desarrollo Web Basado en Servicios y Componentes. UAL 2018</title>");
       out.println("</head>");
       out.println("<body>");
       
       try 
       {
    	   getreference();
		
    	   List noticias = XMLCoder.decodeXML("noticias");
	       noticias.add(new Noticia(value_name1,value_name2, value_name3));
	       boolean estado = false;
	       
	       if(bufferImpl.canPut())
	       {
		       try 
		       {
		    	   estado = XMLCoder.codeXML("noticias", noticias);
		       } catch (Exception e) 
		       {
		    	   out.println("Error al codificar el texto");
		       }
		       
		       if (estado == true) 
		       {
		           if(Validador.validate("D:\\Descargas\\Informatica\\Master Informatica\\Segundo Cuatrimestre\\Desarrollo Web\\noticias.xml", "D:\\Descargas\\Informatica\\Master Informatica\\Segundo Cuatrimestre\\Desarrollo Web\\noticias.xsd") && Parser.validate("D:\\Descargas\\Informatica\\Master Informatica\\Segundo Cuatrimestre\\Desarrollo Web\\noticias.xml"))
		           {
		        	   if (!bufferImpl.put(noticias.get(noticias.size()-1).toString()))
							throw new Exception("No se ha podido insertar el elemento en el buffer");

						//nelementos = bufferImpl.getSize();
			    	   
				       /** Se genera la salida HTML para mostrar en el cliente */
				       out.println("Se ha guardado la noticia " + value_name1 + " y se ha generado el XML correctamente");
				       
		           }else
		           {
		        	   out.println("Error en la validación del documento");
		           }
		    	  
		       } else 
		       {
			       /** Se genera la salida HTML para mostrar en el cliente */
			       out.println("La noticia no ha podido generarse");
		       }
	       }else
	       {
		       out.println("No se pueden añadir más noticias, la lista está llena.");
	       }
	       
	
       } catch (Exception e) 
       {
	       /** Se genera la salida HTML para mostrar en el cliente */
	       out.println("Error al intentar añadir la noticia.");
       }
       
       out.println("</body></html>");

       
     }
    
    private void getreference() throws	org.omg.CORBA.ORBPackage.InvalidName,
							    		org.omg.CosNaming.NamingContextPackage.NotFound,
							    		org.omg.CosNaming.NamingContextPackage.CannotProceed,
							    		org.omg.CosNaming.NamingContextPackage.InvalidName
    {
    		// create and initialize the ORB
    		String args[]=new String[4];
    		args[0]="-ORBInitialPort";
    		args[1]="1050";
    		args[2]="-ORBInitialHost";
    		args[3]="localhost";
    		ORB orb = ORB.init(args, null);
    		// get the root naming context
    		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
    		// Use NamingContextExt instead of NamingContext. This is
    		// part of the Interoperable naming Service.
    		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
    		// resolve the Object Reference in Naming
    		String name = "Buffer";
    		bufferImpl = BufferHelper.narrow(ncRef.resolve_str(name));
    }
}