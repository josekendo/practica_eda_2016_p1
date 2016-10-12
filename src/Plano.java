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
		this.dimension = new Coordenadas(ip,jp);
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
				if(numerodecolumnasmapa != this.dimension.getFila() || numerodefilasmapa != dimension.getColumna())
				{
					boolean redimensionarx = false;
					boolean redimensionary = false;
					
					//redimensionamos en el caso de que sea mayor
					if(numerodecolumnasmapa > this.dimension.getColumna())
					{
						 redimensionary = true;
					}
					
					//redimensionamos en el caso de que sea mayor
					if(numerodefilasmapa > dimension.getFila())
					{
						redimensionarx = true;
					}
					
					//el metodo redimensionar se encarga de redimensionar la matrix dejando todos los valores existentes iguales
					if(redimensionarx && redimensionary)
					{
						redimensionar(numerodefilasmapa,numerodecolumnasmapa);
					}
					else if(redimensionarx)
					{
						redimensionar(numerodefilasmapa,this.dimension.getColumna()+1);
					}
					else if(redimensionary)
					{
						redimensionar(this.dimension.getFila()+1,numerodecolumnasmapa);
					}
				}
			}
			//aqui ya solo nos faltaria meter los datos en la matrix
			boolean dondeterminamapa=true;
			int numerodelecturasiguiente=-1;
			for(int k=1; k < planoleido.length;k++)
			{
				if(planoleido[k].toString().charAt(0) != '<' && dondeterminamapa)
				{
					for(int j=0;j < this.pl[k-1].length;j++)
					{
						if(j < (planoleido[k].toString().length()))
						{
							if(pl[k-1][j] == null || pl[k-1][j].getTipo() == 'l')
							{
								if(planoleido[k].toString().charAt(j) == 'l' || planoleido[k].toString().charAt(j) == 'o')
								{
									this.pl[k-1][j] = new Casilla(planoleido[k].toString().charAt(j));
								}
								else
								{
									this.pl[k-1][j] = new Casilla('l');
								}
							}
						}
						else
						{
							if(pl[k-1][j] == null || pl[k-1][j].getTipo() == 'l')
							{
								this.pl[k-1][j] = new Casilla('l');
							}
						}
					}
				}
				else
				{
					dondeterminamapa = false;
					numerodelecturasiguiente= k;
				}
			}
			//ahora si hay mas datos los vamos creando
			if(numerodelecturasiguiente != -1)
			{
				for(int l=numerodelecturasiguiente; l < planoleido.length;l++)
				{	
					if(planoleido[l].compareToIgnoreCase("<DESTINO>") == 0)
					{
						//dos lineas
						if((l+1) < planoleido.length && (l+2) < planoleido.length && planoleido[l+1].matches("^[a-zA-Z]+$") && planoleido[l+2].matches("^[0-9]+"+" "+"[0-9]+$"))
						{
							if(Integer.parseInt(planoleido[l+2].substring(0,0)) < dimension.getFila() && Integer.parseInt(planoleido[l+2].substring(2,2)) < dimension.getColumna() && (pl[Integer.parseInt(planoleido[l+2].substring(0,0))][Integer.parseInt(planoleido[l+2].substring(2,2))].getTipo() == 'l' || pl[Integer.parseInt(planoleido[l+2].substring(0,0))][Integer.parseInt(planoleido[l+2].substring(2,2))] == null))
							{
								Casilla nueva = new Casilla('d');
								nueva.setNombre(planoleido[l+1]);
								nueva.setCoordenadas(planoleido[l+2].charAt(0),planoleido[l+2].charAt(2),this);
							}
						}		
					}
					else if(planoleido[l].compareToIgnoreCase("<PUERTA>") == 0)
					{
						//tres lineas
						if((l+1) < planoleido.length && (l+2) < planoleido.length && (l+3) < planoleido.length && planoleido[l+1].matches("^[0-9]+$") && planoleido[l+2].matches("^[0-9]+"+" "+"[0-9]+$") && planoleido[l+3].matches("^[a-zA-Z]$"))
						{
							if(Integer.parseInt(planoleido[l+2].substring(0,0)) < dimension.getFila() && Integer.parseInt(planoleido[l+2].substring(2,2)) < dimension.getColumna() && (pl[Integer.parseInt(planoleido[l+2].substring(0,0))][Integer.parseInt(planoleido[l+2].substring(2,2))].getTipo() == 'l' || pl[Integer.parseInt(planoleido[l+2].substring(0,0))][Integer.parseInt(planoleido[l+2].substring(2,2))] == null))
							{
								Casilla nueva = new Casilla('d');
								nueva.setPuerta(Integer.parseInt(planoleido[l+1]),planoleido[l+3].charAt(0));
								nueva.setCoordenadas(planoleido[l+2].charAt(0),planoleido[l+2].charAt(2),this);
							}
						}		
					}
					else if(planoleido[l].compareToIgnoreCase("<COMIENZO>") == 0)
					{
						//dos lineas
						if((l+1) < planoleido.length && (l+2) < planoleido.length && planoleido[l+1].matches("^[a-zA-Z]+$") && planoleido[l+2].matches("^[0-9]+"+" "+"[0-9]+$"))
						{
							
							if(Integer.parseInt(planoleido[l+2].substring(0,0)) < dimension.getFila() && Integer.parseInt(planoleido[l+2].substring(2,2)) < dimension.getColumna() && (pl[Integer.parseInt(planoleido[l+2].substring(0,0))][Integer.parseInt(planoleido[l+2].substring(2,2))].getTipo() == 'l' || pl[Integer.parseInt(planoleido[l+2].substring(0,0))][Integer.parseInt(planoleido[l+2].substring(2,2))] == null))
							{
								Casilla nueva = new Casilla('c');
								nueva.setNombre(planoleido[l+1]);
								nueva.setCoordenadas(planoleido[l+2].charAt(0),planoleido[l+2].charAt(2),this);
							}
						}								
					}
				}
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
	
	//este metodo se encarga de redimensionar la matrix sin perder datos utilizando un doble for para  recorrer el viejo y el nuevo
	public void redimensionar(int x, int y)
	{
		int ax=0,by=0;
		if(x != -1 && y != -1)
		{
			ax=x;
			by=y;
		}
		else if(x != -1)
		{
			ax=x;
			by=this.pl[0].length;
		}
		else if(y != -1)
		{
			by=y;
			ax=this.pl.length;	
		}
		
		Casilla[][] nuevamatrix = new Casilla[ax][by];
		
		for(int h=0;h < this.pl.length;h++)
		{
			for(int i=0;i < this.pl[h].length;i++)
			{
				nuevamatrix[h][i] = pl[h][i]; 
			}	
		}
		
		pl = nuevamatrix;
	}
}
