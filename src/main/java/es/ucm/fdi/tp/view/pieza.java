package es.ucm.fdi.tp.view;

public class pieza {
	private Shape forma;
	private int jugador;
	
	public pieza (Shape f, int j){
		this.forma = f;
		this.jugador = j;
	}
	
	public Shape getShape(){
		return this.forma;
	}
	
	public int getJugador(){
		return this.jugador;
	}
}
