//DNI 48767995z ANTON COY, JOSE VICENTE


public class Coordenadas {
	
	private int fila;
	private int columna;

	public Coordenadas(int g,int m) 
	{
		// TODO Auto-generated constructor stub
		fila = g;
		columna = m;
	}
	
	public int getFila()
	{
		return fila;
	}
	
	public int getColumna()
	{
		return columna;
	}
	
	public String getInfo()
	{
		return "("+fila+","+columna+")";
	}

}
