package es.ucm.fdi.tp.view;

import java.util.ArrayList;

import javax.swing.JFrame;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.mvc.GameEvent;

public class TableroChess extends ModeloTablero{

	static final int BLANCAS = 0;
	static final int NEGRAS = 1;
	static final int DIM = 8;
	
	private pieza[][] tablero;
	private int row;
	private int col;
	private int numJugadores;
	
	public TableroChess(){
		this.tablero = new pieza[DIM][DIM];
		this.row = DIM;
		this.col = DIM;
		this.numJugadores = 2;
		
        for (int i=0; i<DIM; i++) {
            for (int j=0; j<DIM; j++){ 
            	if(i == 1) tablero[i][j] = new pieza(Shape.BPAWN, NEGRAS);
            	else if (i == 6) tablero[i][j] = new pieza(Shape.WPAWN, BLANCAS);
            	else tablero[i][j] = null;
            	}
        }
        tablero[0][0] = new pieza(Shape.BROOK, NEGRAS);
        tablero[0][7] = new pieza(Shape.BROOK, NEGRAS);
        tablero[0][1] = new pieza(Shape.BKNIGHT, NEGRAS);
        tablero[0][6] = new pieza(Shape.BKNIGHT, NEGRAS);
        tablero[0][2] = new pieza(Shape.BBISHOP, NEGRAS);
        tablero[0][5] = new pieza(Shape.BBISHOP, NEGRAS);
        tablero[0][3] = new pieza(Shape.BQUEEN, NEGRAS);
        tablero[0][4] = new pieza(Shape.BKING, NEGRAS);
        
        tablero[7][0] = new pieza(Shape.WROOK, BLANCAS);
        tablero[7][7] = new pieza(Shape.WROOK, BLANCAS);
        tablero[7][1] = new pieza(Shape.WKNIGHT, BLANCAS);
        tablero[7][6] = new pieza(Shape.WKNIGHT, BLANCAS);
        tablero[7][2] = new pieza(Shape.WBISHOP, BLANCAS);
        tablero[7][5] = new pieza(Shape.WBISHOP, BLANCAS);
        tablero[7][3] = new pieza(Shape.WQUEEN, BLANCAS);
        tablero[7][4] = new pieza(Shape.WKING, BLANCAS);
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
	public pieza getPiezaAt(int row, int col) {
		return tablero[row][col];
	}
	@Override
	public boolean generarAccion(int posX, int posY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main (String ... args){
		JFrame jf = new JFrame("titulo");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TableroUI prueba = new TableroUI(new TableroChess());
		jf.add(prueba);
		jf.setSize(400, 400);
		jf.setVisible(true);
	}


	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyEvent(GameEvent e) {
		// TODO Auto-generated method stub
		
	}

}
