package ual.dwsc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XMLCoder 
{
	public static boolean codeXML(String fileUrl, String fileName, List<Noticia> noticias) throws Exception
	{
	       if(noticias.isEmpty())
	       {
	          return false;
	      }else{
	              DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	              DocumentBuilder builder = factory.newDocumentBuilder();
	              DOMImplementation implementation = builder.getDOMImplementation();
	              Document document = implementation.createDocument(null, fileName, null);
	              document.setXmlVersion("1.0");
	             //Main Node
	              Element raiz = document.getDocumentElement();
	             //Por cada noticia creamos un registro noticia que contendra el email, el titulo de la noticia y su contenido
	             for(int i=0; i<noticias.size();i++)
	             {
	                     Element notNode = document.createElement("noticia");
	                     Element dateNode = document.createElement("date");
	                     Element shortNode = document.createElement("shortDescription");
	                     Element longNode = document.createElement("longDescription");
	                     
	                     Text nodeDateValue = document.createTextNode(noticias.get(i).getDate());
	                     Text nodeShortValue = document.createTextNode(noticias.get(i).getShortDescription());
	                     Text nodeLongValue = document.createTextNode(noticias.get(i).getLongDescription());
	                     
	                     dateNode.appendChild(nodeDateValue);
	                     shortNode.appendChild(nodeShortValue);
	                     longNode.appendChild(nodeLongValue);
	                     
	                     notNode.appendChild(dateNode);
	                     notNode.appendChild(shortNode);
	                     notNode.appendChild(longNode);
	                     
	                     raiz.appendChild(notNode); //pegamos el elemento a la raiz "Documento"
	              }
	            //Generate XML
	             Source source = new DOMSource(document);
	            //Indicamos donde lo queremos almacenar
	             Result result = new StreamResult(new java.io.File(fileUrl + fileName+".xml")); //nombre del archivo
	             Transformer transformer = TransformerFactory.newInstance().newTransformer();
	             transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");//Saltos de linea
	             transformer.setOutputProperty(OutputKeys.INDENT, "yes");//Saltos de linea
	             transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); //Tabulaciones
	             transformer.transform(source, result);
	          }
	       
	       return true;
	  }
	
	public static List<Noticia> decodeXML(String fileUrl, String fileName)
	{
		 try {
	          File inputFile = new File(fileUrl + fileName +".xml");
	          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	          Document doc = dBuilder.parse(inputFile);
	          doc.getDocumentElement().normalize();
	          List<Noticia> listaNoticias = new ArrayList<Noticia>();
	          //System.out.println("Elemento raiz :" + doc.getDocumentElement().getNodeName());
	          NodeList nList = doc.getElementsByTagName("noticia");
	         System.out.println("----------------------------");
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            //System.out.println("\nCurrent Elemento :" + nNode.getNodeName());
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                    Element eElement = (Element) nNode;
	                    //Control de errores
	                    if(eElement.getElementsByTagName("date").item(0) == null)
	                    {
	                    	System.out.println("Falta el elemento Fecha"); 
	                    	continue;
	                    }
	                    if(eElement.getElementsByTagName("shortDescription").item(0) == null)
	                    {
	                    	System.out.println("Falta el elemento Descripción Corta"); 
	                    	continue;
	                    }
	                    
	                    if(eElement.getElementsByTagName("longDescription").item(0) == null)
	                    {
	                    	System.out.println("Falta el elemento Descripción Larga"); 
	                    	continue;
	                    }
	                    
	                    listaNoticias.add(new Noticia(eElement.getElementsByTagName("date").item(0).getTextContent(),eElement.getElementsByTagName("shortDescription").item(0).getTextContent(), eElement.getElementsByTagName("longDescription").item(0).getTextContent()));
	             }
	         }
	         
	         return listaNoticias;
	     }catch (Exception e) 
		 {
	        System.out.println("Error encontrado, " + e.getMessage());
	     }
		 
		 return new ArrayList<Noticia>(); 
	}
}
