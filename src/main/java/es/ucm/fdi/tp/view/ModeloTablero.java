package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameObserver;

public abstract class ModeloTablero <S extends GameState<S,A>, A extends GameAction<S,A>> implements GameObserver{

	private int row;
	private int col;
	private int turno;
	
	public ModeloTablero(){}
	
	//public abstract ArrayList<GameAction> accionesValidas(int player);
	
	
	public abstract boolean generarAccion(int posX, int posY);

	public abstract int getRow();
	
	public abstract int getCol();

	public abstract pieza getPiezaAt(int row, int col);
	
	public abstract void restart();
	
}
