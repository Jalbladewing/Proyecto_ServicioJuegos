package ual.dwsc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import ual.dwsc.Validador;

public class ValidatorTest {

	private InputStream xsd;
	private String xmlOk;
	private String xmlOrden;
	private String xmlContenido;
	
	@Before
	public void setUp() {
		try 
		{
			this.xsd= new FileInputStream("noticias.xsd");
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.xmlOk="noticiasCorrectas.xml";
		this.xmlOrden="noticiasOrden.xml";
		this.xmlContenido="noticiasContenido.xml";
	}

	@Test
	public void test()  {
		assertTrue(Validador.validate(xmlOk, xsd));
	}
	@Test
	public void testOrden() {
		assertFalse(Validador.validate(xmlOrden, xsd));
	}
	@Test
	public void testContenido() {
		assertFalse(Validador.validate(xmlContenido, xsd));
	}

}
