package ual.dwsc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ual.dwsc.Validador;

public class ValidatorTest {

	private String xsd;
	private String xmlOk;
	private String xmlOrden;
	private String xmlContenido;
	
	@Before
	public void setUp() {
		this.xsd="noticias.xsd";
		this.xmlOk="noticias.xml";
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
