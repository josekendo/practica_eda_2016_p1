import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Plano 
{
	private Casilla[][] pl;
	private Coordenadas dimension;
	
	//si es menor que cero ponemos tres en su lugar, si no es asi le ponemos su numero, i para las filas y j para las columnas
	public Plano(int i, int j) 
	{
		// TODO Auto-generated constructor stub
		int ip,jp;
		
		if(i < 0)
		{
			ip = 3;
		}
		else
		{
			ip = i;
		}
		
		if(j < 0)
		{
			jp = 3;
		}
		else
		{
			jp = j;
		}
		
		pl = new Casilla[ip][jp];
	}
	
	public boolean setCasilla(Casilla x)
	{
		
		if(pl[x.getCoordenadas().getFila()][x.getCoordenadas().getColumna()] != null)
		{
			return false;
		}
		else
		{
			pl[x.getCoordenadas().getFila()][x.getCoordenadas().getColumna()] = x;
			return true;
		}
	}
	
	public void leePlano(String f)
	{
		if(f == null || f.length() < 1)
		{
			///vacio
		}
		else
		{
			String [] planoleido = this.leerdicc(f);
			int numerodecolumnasmapa = this.pl[0].length;
			int numerodefilasmapa = this.pl.length;
			if(planoleido[0].compareToIgnoreCase("<MAPA>") == 0)
			{
				for(int c=1;c < (planoleido.length);c++)
				{
					if(c == 1)
					{
						if(planoleido[c].toString().charAt(0) != '<')
						{
							numerodecolumnasmapa = planoleido[c].toString().length();//esto determinara la longitud de columnas	
							numerodefilasmapa=1;//hemos leido la fila 1
						}
						else
						{
								break;//prevenimos de que no se siga leyendo si se acaba la lectura de la zona <MAPA>,procederiamos a dejarlo todo en libre	
						}
					}
					else
					{
						if(planoleido[c].toString().charAt(0) != '<')
						{
							numerodefilasmapa++;//leemos la fila c
						}
						else
						{
							break;//prevenimos de que no se siga leyendo si se acaba la lectura de la zona <MAPA>,redimensionariamos la matriz de pl con las columnas y filas recogidas asi sabriamos exactamente cuantas filas hay y empezar a introducir datos limpiamente
						}
					}
				}
				//tenemos que redimensionar si las columnas son mayores o si las filas lo son.
			}
		}
	}
	
	public char consultaCasilla(Coordenadas x)
	{
		if(x != null && x.getFila() >= 0 && x.getColumna() >= 0 && x.getFila() < this.pl.length && x.getColumna() < this.pl[0].length && this.pl[x.getFila()][x.getColumna()] != null)
		{
			return pl[x.getFila()][x.getColumna()].getTipo();
		}
		return 'F';
	}
	
	public char[] consultaVecinas(Coordenadas x)
	{
		if(x != null && x.getFila() >= 0 && x.getColumna() >= 0 && x.getFila() < this.pl.length && x.getColumna() < this.pl[0].length && this.pl[x.getFila()][x.getColumna()] != null)
		{
			//esto hay que mirar que valores cambian
		}
		return null;
	}
	
	public void muestraPlano()
	{
		if(this.pl != null && pl.length > 0)
		{
			for(int d=0; d < pl.length;d++)
			{
				String linea="";
				for(int e=0; e < pl[d].length;e++)
				{
					if(pl[d][e] != null)
					{
						linea=linea+pl[d][e].getTipo();
					}
					else
					{
						linea=linea+"F";
					}
				}
				System.out.println(linea);
			}
		}
	}
	
	public boolean equals(Plano compara)
	{
		//comprobar si tienen las mismas dimensiones
		//luego comprobar una a una si la casilla es igual en funcion del tipo que sea
		//en el momento que una no sea igual se sale del for retornado false si termina el recorrido se devuelve true
		return true;
	}
	
	public String[] leerdicc(String nombrefichero)//ok
	{
		if(nombrefichero == " " || nombrefichero == "" || nombrefichero == null)
		{
			return null;
		}
		
		String [] diccionario;
		FileReader fichero=null;
		BufferedReader lectura=null;
		String diccencadena=null;
		try
		{
			fichero=new FileReader(nombrefichero);
			lectura =new BufferedReader(fichero);
			String linea = lectura.readLine();
			while(linea != null)
			{
				if(linea.length() > 0)
				{
					if(linea.length() > 0)
					{
						if(diccencadena == null)
						{
							diccencadena=linea;
						}
						else
						{
							diccencadena=diccencadena+"@dicc"+linea;
						}
					}
				}
				linea = lectura.readLine();
			}
			diccionario= diccencadena.split("@dicc");
		}
		catch(IOException e)
		{
			System.out.println(e);
			return null;
		}
		
		try
		{
			if (fichero!=null)
			{
				fichero.close();
			}
			if (lectura!=null)
			{
				lectura.close();
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
		return diccionario;
	}
}
