
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
				planonuevo.muestraPlano();
				Coordenadas coordenaprueba= new Coordenadas(3,8);
				
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
		
	}
	
	public static void metodoB(Plano p)
	{
		
	}

}
