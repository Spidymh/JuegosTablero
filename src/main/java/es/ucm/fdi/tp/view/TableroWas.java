package es.ucm.fdi.tp.view;

import java.util.ArrayList;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.was.*;

public class TableroWas extends ModeloTablero{
	
	static final int LOBO = 0;
	static final int OVEJA = 1;
	static final int DIM = 8;
	
	private WasState estado;
	private int row;
	private int col;
	private int numJugadores;
	private boolean isSelectedLobo;
	private boolean isSelectedOveja;
	private punto selectedOveja;
	
	public TableroWas(){
		this.estado = new WasState();
		this.row = DIM;
		this.col = DIM;
		this.numJugadores = 2;
		this.isSelectedLobo = false;
		this.isSelectedOveja = false;
		this.selectedOveja = new punto(-1,-1);
	}
	

	//@Override
	//public ArrayList<GameAction> accionesValidas(int player) {
		//return null;
	//}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public int getCol() {
		return this.col;
	}

	@Override
	public pieza getPiezaAt(int row, int col) {
		return estado.at(row, col);
	}
	
	public boolean isSelected(){
		if (estado.getTurn() == LOBO) return this.isSelectedLobo;
		else return this.isSelectedOveja;
	}
	
	@Override
	public boolean generarAccion(int posX, int posY) {
		switch (estado.getTurn()){
		case LOBO:{
			punto posLobo = estado.buscaLobo();
			if(!isSelectedLobo){
				if(posLobo.getX() == posX && posLobo.getY() == posY) isSelectedLobo = true;
				return false;
			}
			else{
				isSelectedLobo = false;
				WasAction accion = new WasAction(LOBO, posX, posY, posLobo.getX(), posLobo.getY());
				if(this.estado.isValid(accion)){
					estado = accion.applyTo(estado);
					return true;
				}
				else return false;
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
				return false;
			}
			else{
				isSelectedOveja = false;
				WasAction accion = new WasAction(OVEJA, posX, posY, selectedOveja.getX(), selectedOveja.getY());
				if(this.estado.isValid(accion)) {
					estado = accion.applyTo(estado);
					return true;
				}
				else return false;
			}
		}
		}
		return false;
	}


	@Override
	public void restart() {
		this.estado = new WasState();
		this.isSelectedLobo = false;
		this.isSelectedOveja = false;
		this.selectedOveja = new punto(-1,-1);
	}


	@Override
	public void notifyEvent(GameEvent e) {
		// TODO Auto-generated method stub
		
	}

}
