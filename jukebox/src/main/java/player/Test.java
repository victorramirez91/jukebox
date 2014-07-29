package player;

public class Test {
	
	
	public String PlayerOn()
	{

		try {
		 // Reproductor mi_reproductor = new Reproductor();
		  BasicPlayerTest test = new BasicPlayerTest();
		 // mi_reproductor.AbrirFichero("src/main/lib/HOLA.mp3");
		 // mi_reproductor.Play();
		  test.play("src/main/lib/HOLA.mp3");
		  //test.Stopsong();
		 
		} catch (Exception ex) {
		  System.out.println("Error: " + ex.getMessage());
		}
		return "";
		
	}
	
	
}
