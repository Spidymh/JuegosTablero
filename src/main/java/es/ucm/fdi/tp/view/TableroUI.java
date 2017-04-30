package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.BoardEvent;
import es.ucm.fdi.tp.mvc.BoardListenable;
import es.ucm.fdi.tp.mvc.BoardListener;
import es.ucm.fdi.tp.mvc.ColorChangeEvent;
import es.ucm.fdi.tp.mvc.GameTable;
import model.ModeloTablero;
import model.TableroWas;

public class TableroUI<S extends GameState<S, A>, A extends GameAction<S, A>> extends JBoard implements BoardListenable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4433603032523198502L;

	
	private ModeloTablero<S,A> model;
	private GameTable<S,A> table;
	private List<BoardListener> observadores;

	public TableroUI(ModeloTablero<S,A> model, GameTable<S,A> table){
		this.model = model;	
		this.table = table;
		this.observadores = new ArrayList<BoardListener>();
	}

	@Override
	protected void keyTyped(int keyCode) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
		System.out.println("Mouse: " + clickCount + "clicks at position (" + row + "," + col + ") with Button "	+ mouseButton);
		
		for (int i = 0; i < observadores.size(); ++i)
    		observadores.get(i).notifyBoardEvent(new BoardEvent(row, col, mouseButton));
	}
	
	public A generateAct(int x, int y){
		return model.generateAction(x, y);
	}

	@Override
	protected Shape getShape(int player) {
		return player == 0 ? Shape.RECTANGLE : Shape.CIRCLE;/**/
	}

	@Override
	protected Color getColor(pieza p) {
		return p.getJugador() == 0 ? Color.BLUE : Color.RED;
	}

	@Override
	protected Color getBackground(int row, int col) {
		return (row+col) % 2 == 0 ? new Color(240, 217, 181) : new Color(181, 136, 99); //Marron claro y oscuro
	}

	@Override
	protected int getNumRows() {
		return this.model.getRow();
	}

	@Override
	protected int getNumCols() {
		return this.model.getCol();
	}
	
	protected int getSepPixels() {
		return 0; 
	}

	@Override
	protected pieza getPieza(int row, int col) {
		return table.getPiezaAt(row, col);
	}


	@Override
	public void addObserver(BoardListener o) {
		observadores.add(o);
	}
	
	public void notifyColorChange(ColorChangeEvent e){
	}
	
	
	
	/*public static void main (String ... args){
		JFrame jf = new JFrame("titulo");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TableroUI prueba = new TableroUI(new TableroWas(), new GameTable<WasState, WasAction>(new WasState()));
		jf.add(prueba);
		jf.setSize(400, 400);
		jf.setVisible(true);
	}*/


	

}