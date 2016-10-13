import java.util.Vector;

//DNI 48767995z ANTON COY, JOSE VICENTE

public class Mediaplan {

	public static void main(String[] args) 
	{
		
		// TODO Auto-generated method stub
		if(args.length > 3)
		{
			if(args[0] != null && args[1] != null && args[2] != null && args[3] != null && args[3].matches("^[a|b]$") && args[2].matches("^[-|0-9]?[0-9]+$") && args[1].matches("^[-|0-9]?[0-9]+$") && args[0].length() > 0)
			{
				Plano planonuevo = new Plano(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
				planonuevo.leePlano(args[0]);
				//------------------------------ Ahora segun el metodo a o b
				if(args[3].compareToIgnoreCase("a") == 0)
				{
					metodoA(planonuevo);
				}
				else if(args[3].compareToIgnoreCase("b") == 0)
				{
					metodoB(planonuevo);
				}
			}
		}
	}
	
	public static void metodoA(Plano p)
	{
		//llamamos a buscar destinos
		//llamamos a calcular distancias enviando los destinos
		Vector<Casilla> destinos;
		Vector<String> resultados;
		Vector<String> resultadosdos;
		destinos = p.buscardestinos();
		p.comparardestinos(destinos);
		p.compararplano(destinos, 1);
	}
	
	public static void metodoB(Plano p)
	{
		Vector<Casilla> destinos;
		Vector<String> resultadosdos;
		destinos = p.buscardestinos();
		p.compararplano(destinos, 2);
	}

}
