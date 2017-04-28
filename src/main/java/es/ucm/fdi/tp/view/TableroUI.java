package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.image.ColorModel;

import javax.swing.JFrame;

public class TableroUI extends JBoard{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4433603032523198502L;

	
	private ModeloTablero model;

	public TableroUI(ModeloTablero model){
		this.model = model;	
	}

	@Override
	protected void keyTyped(int keyCode) {
		// TODO Auto-generated method stub
	}

	public static void prueba(){}
	@Override
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
		System.out.println("Mouse: " + clickCount + "clicks at position (" + row + "," + col + ") with Button "
				+ mouseButton);
		if(model.generarAccion(row, col)) this.repaint();
	}

	@Override
	protected Shape getShape(int player) {
		return player == 0 ? Shape.BKNIGHT : Shape.WKNIGHT;
	}

	@Override
	protected Color getColor(int player) {
		return player == 0 ? Color.BLUE : Color.RED;
	}

	@Override
	protected pieza getPieza(int row, int col) {
		return this.model.getPiezaAt(row, col);
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
	
	
	
	
	
	public static void main (String ... args){
		JFrame jf = new JFrame("titulo");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TableroUI prueba = new TableroUI(new TableroWas());
		jf.add(prueba);
		jf.setSize(400, 400);
		jf.setVisible(true);
	}
	

}


/**
 * 	private static class ModeloColor extends ColorModel{

		public ModeloColor(int bits) {
			super(bits);
			// TODO Auto-generated constructor stub
		}

		@Override
		public int getRed(int pixel) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getGreen(int pixel) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getBlue(int pixel) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getAlpha(int pixel) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
*/