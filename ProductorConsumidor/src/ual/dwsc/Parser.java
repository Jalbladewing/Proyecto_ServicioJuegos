package ual.dwsc;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Parser {
    public static boolean validate(String filename){
       try {
          File inputFile = new File(filename);
          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
          Document doc = dBuilder.parse(inputFile);
          doc.getDocumentElement().normalize();
          //System.out.println("Elemento raiz :" + doc.getDocumentElement().getNodeName());
          NodeList nList = doc.getElementsByTagName("noticia");

          for (int temp = 0; temp < nList.getLength(); temp++) 
          {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) 
            {
                    Element eElement = (Element) nNode;

                    eElement.getElementsByTagName("date").item(0).getTextContent();
                    eElement.getElementsByTagName("shortDescription").item(0).getTextContent();
                    eElement.getElementsByTagName("longDescription").item(0).getTextContent();
            }
         }
       } catch (Exception e) 
       {
	       System.out.println("Error encontrado, " + e.getMessage());
           return false;
       }
       
       return true;
    }
}