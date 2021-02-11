package model;

import java.awt.Color;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.view.pieza;

public abstract class ModeloTablero <S extends GameState<S, A>, A extends GameAction<S, A>>{

	protected int row;
	protected int col;
	protected int turno;
	
	public ModeloTablero(){}

	public abstract int getRow();
	
	public abstract int getCol();

	public abstract A generateAction(int posX, int posY);
	
}
