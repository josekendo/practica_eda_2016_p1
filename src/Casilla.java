
public class Casilla {

	private char tipo;//el tipo de casilla que es
	private Coordenadas coordenada_s;//las coordenadas se inicializa en null
	private Plano plano;//el plano al que pertenece
	private String nombre;//su nombre en el caso de que sea destino o comienzo
	private int puerta_n;//numero de puerta
	private char puerta_p;//propiedad de la puerta por defecto F
	
	//se encarga de inicializar las variables de instancia del objeto y de darle el tipo
	public Casilla(char a) 
	{
		if(a == 'l') //es libre
		{
			tipo='l';
		}
		else if(a == 'o') //esta ocupado
		{
			tipo='o';
		}
		else if(a == 'p') //es una puerta
		{
			tipo='p';
		}
		else if(a == 'd') //es un destino
		{
			tipo='d';
		}
		else if(a == 'c') //es un comienzo
		{
			tipo='c';
		}
		else //es erronea
		{
			tipo='F';
		}
		
		coordenada_s = null;
		nombre = null;
		puerta_p = 'F';
		puerta_n = -1;
		// TODO Auto-generated constructor stub
	}
	
	//comprobamos de que los valores no sean menores de 0 y que las coordenadas no esten ya definidas en caso contrario se devuelve false si no true.
	public boolean setCoordenadas(int g, int n, Plano m)
	{
		if(!(g < 0 || n < 0 || coordenada_s != null))
		{
			coordenada_s=new Coordenadas(g,n);
			plano = m;
			return true;
		}
		
		return false;
	}
	
	//comprobamos de que el valor pasado no sea nulo y de que sea el tipo correcto para darle nombre
	public void setNombre(String n)
	{
		if(n != null && (tipo == 'd' || tipo == 'c'))
		{
			nombre = n;
		}
	}
	
	//le asignamos numero y propiedad en el caso de que sea una puerta y el numero sea mayor o igual que 0
	public void setPuerta(int y, char p)
	{
		if(y < 0 && (tipo == 'p'))
		{
			puerta_n = y;
			puerta_p = p;
		}
	}
	
	//si es un comienzo se devuelve true si no false
	public boolean esComienzo()
	{
		if(tipo == 'c')
		{
			return true;
		}
		
		return false;
		
	}
	
	//si es un destino se devuelve true si no false
	public boolean esDestino()
	{
		if(tipo == 'd')
		{
			return true;
		}
		
		return false;
	}
	
	//si es un obstaculo se devuelve true si no false
	public boolean esObstaculo()
	{
		if(tipo == 'o')
		{
			return true;
		}
		
		return false;
	}
	
	//si es libre se devuelve true si no false
	public boolean esLibre()
	{
		if(tipo == 'l')
		{
			return true;
		}
		
		return false;
	}
	
	//si es una puerta se devuelve true si no false
	public boolean esPuerta()
	{
		if(tipo == 'p')
		{
			return true;
		}
		
		return false;
	}
	
	//devolvemos las coordenadas, si no existen se devuelve null
	public Coordenadas getCoordenadas()
	{
		return this.coordenada_s;
	}
	
	//devolvemos el nombre si no existe se devuelve el null
	public String getNombre()
	{
		return this.nombre;
	}
	
	//devolvemos el numero si no existe devolvemos -1
	public int getNumero()
	{
		return this.puerta_n;
	}
	
	//devolvemos la propiedad de la puerta en el caso de que no exista se devuelve F
	public char getPropiedad()
	{
		return this.puerta_p;
	}
	
	//devolvemos el tipo,si no exista se devuelve F
		public char getTipo()
		{
			return this.tipo;
		}
	
	//sacamos por pantalla la informacion segun el tipo que sea, si no es asi no sacamos nada
	public void escribeInfo()
	{
		if(this.esLibre() || this.esComienzo() || this.esDestino() || this.esObstaculo() || this.esPuerta())
		{
			String cadena = "";
			
			if(tipo == 'p')
			{
				cadena=cadena+this.puerta_p+":";
			}
			
			if(tipo == 'c' || tipo == 'd')
			{
				cadena=cadena+this.nombre+":";
			}
			
			if(tipo == 'l')
			{
				cadena="libre"+":";
			}
			
			if(tipo == 'o')
			{
				cadena="obstaculo"+":";
			}
			
			cadena=cadena+this.coordenada_s.getInfo();
			
			if(tipo == 'p')
			{
				cadena=":"+this.puerta_p;
			}
			
			System.out.println(cadena);
		}
	}
	
	//devolvemos la informacion segun el tipo que sea en formato string, si no es asi devolvemos null
	public String devolverInfo()
	{
		if(this.esLibre() || this.esComienzo() || this.esDestino() || this.esObstaculo() || this.esPuerta())
		{
			String cadena = "";
			
			if(tipo == 'p')
			{
				cadena=cadena+this.puerta_p+":";
			}
			
			if(tipo == 'c' || tipo == 'd')
			{
				cadena=cadena+this.nombre+":";
			}
			
			if(tipo == 'l')
			{
				cadena="libre"+":";
			}
			
			if(tipo == 'o')
			{
				cadena="obstaculo"+":";
			}
			
			cadena=cadena+this.coordenada_s.getInfo();
			
			if(tipo == 'p')
			{
				cadena=":"+this.puerta_p;
			}
			
			return cadena;
		}
		
		return null;
	}
	
	//comprobamos de que no sea nula la informacion y de que sean del mismo tipo, luego comparamos la informacion que devuelve en el caso de que sea la misma se devuelve true si no false
	public boolean equals(Casilla c)
	{
		
		if((!(c.devolverInfo() == null || this.devolverInfo() == null)) && c.tipo == this.tipo)
		{
			if(c.devolverInfo().compareToIgnoreCase(this.devolverInfo()) == 0)
			{
				return true;
			}
		}
		return false;
	}
	
}

