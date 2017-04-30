package model;

import java.awt.Color;
import java.util.ArrayList;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.view.punto;
import es.ucm.fdi.tp.was.WasAction;
import es.ucm.fdi.tp.was.WasState;

public class TableroWas<S extends GameState<S, A>, A extends GameAction<S, A>> extends ModeloTablero{

	static final int DIM = 8;
	
	private final int LOBO = 0;
	private final int OVEJA = 1;
	private boolean isSelectedLobo;
	private boolean isSelectedOveja;
	private punto selectedOveja;
	private GameTable<S, A> table;
	
	public TableroWas(GameTable<S, A> juego){
		this.row = DIM;
		this.col = DIM;
		this.table = juego;
		this.isSelectedLobo = false;
		this.isSelectedOveja = false;
		this.selectedOveja = new punto(-1,-1);
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public int getCol() {
		return this.col;
	}

	@Override
	public WasAction generateAction(int posX, int posY) {
		WasState estado = (WasState)table.getState();
		switch (table.getState().getTurn()){
		case LOBO:{
			punto posLobo = estado.buscaLobo();
			if(!isSelectedLobo){
				if(posLobo.getX() == posX && posLobo.getY() == posY) isSelectedLobo = true;
				return null;
			}
			else{
				isSelectedLobo = false;
				WasAction accion = new WasAction(LOBO, posX, posY, posLobo.getX(), posLobo.getY());
				if(estado.isValid(accion)){
					return accion;
				}
				else return null;
			}
		}
		case OVEJA:{
			ArrayList<punto> posOvejas = estado.buscaOvejas();
			if(!isSelectedOveja){
				for(int i = 0; i < 4; ++i){ // 4 es NUMOVEJAS
					if(posOvejas.get(i).getX() == posX && posOvejas.get(i).getY() == posY){
						isSelectedOveja = true;
						selectedOveja = new punto(posX, posY);
					}
				}
				return null;
			}
			else{
				isSelectedOveja = false;
				WasAction accion = new WasAction(OVEJA, posX, posY, selectedOveja.getX(), selectedOveja.getY());
				if(estado.isValid(accion)) {
					return accion;
				}
				else return null;
			}
		}
		}
		return null;
	}

}
