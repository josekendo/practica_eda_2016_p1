
public class Mediaplan {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		if(args[0] != null && args[1] != null && args[2] != null && args[3] != null)
		{
			Plano plano = new Plano(3,3);
			plano.leePlano(args[1]);
			
		}
	}

}
