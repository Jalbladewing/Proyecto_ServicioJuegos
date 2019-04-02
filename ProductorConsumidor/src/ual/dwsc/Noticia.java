package ual.dwsc;

public class Noticia {
	private String titulo;
	private String contenido;
	
	public Noticia(String titulo, String contenido) 
	{
		this.titulo = titulo;
		this.contenido = contenido;
	}

	public String getTitulo() 
	{
		return titulo;
	}
	
	public void setTitulo(String titulo) 
	{
		this.titulo = titulo;
	}
	
	public String getContenido() 
	{
		return contenido;
	}
	
	public void setContenido(String contenido) 
	{
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		return "Noticia [titulo=" + titulo + ", contenido=" + contenido + "]";
	}
}
