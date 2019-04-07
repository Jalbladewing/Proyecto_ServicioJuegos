package ual.dwsc;

public class Noticia {
	private String date;
	private String shortDescription;
	private String longDescription;
	
	public Noticia(String date, String shortDescription, String longDescription) 
	{
		this.date = date;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}

	public String getDate() 
	{
		return date;
	}
	
	public void setDate(String date) 
	{
		this.date = date;
	}
	
	public String getShortDescription() 
	{
		return shortDescription;
	}
	
	public void setShortDescription(String shortDescription) 
	{
		this.shortDescription = shortDescription;
	}
	
	public String getLongDescription() 
	{
		return longDescription;
	}
	
	public void setLongDescription(String longDescription) 
	{
		this.longDescription = longDescription;
	}

	@Override
	public String toString() {
		return "Noticia [Fecha=" + date + ", descripción corta=" + shortDescription + ", descripción larga=" + longDescription + "]";
	}
}
