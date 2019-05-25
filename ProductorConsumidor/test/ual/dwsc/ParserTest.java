package ual.dwsc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ual.dwsc.Parser;

public class ParserTest {

	private String xmlOk;
	private String xmlTag;
	private String xmlContenido;
	
	@Before
	public void setUp() throws Exception {
		this.xmlOk="noticiasCorrectas.xml";
		this.xmlTag="noticiasTag.xml";
		this.xmlContenido="noticiasContenido.xml";
	}

	@Test
	public void test() throws Exception {
		assertTrue(Parser.validate(xmlOk));
	}
	@Test
	public void testOrden() throws Exception {
		assertFalse(Parser.validate(xmlTag));
	}
	@Test
	public void testContenido() throws Exception {
		assertFalse(Parser.validate(xmlContenido));
	}

}
