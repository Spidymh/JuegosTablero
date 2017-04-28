package es.ucm.fdi.tp.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class InfoJugadoresUI extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7621214367369841975L;
	
	private JTable tabla;
	private static final java.awt.Dimension defecto = new Dimension(50,50);//No olvidar borrar esto
	private int numjugadores;
	private static final Color BLANCO = Color.WHITE;
	
	private static class ModeloTabla extends AbstractTableModel {
		
		private int numJugadores;
		private Color[] colores;
		
		public ModeloTabla(int numJugadores, Color[] colores) {
			this.numJugadores = numJugadores;
			this.colores = colores;
		}
		
		@Override
		public int getRowCount() {
			return numJugadores;
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {			
			return columnIndex == 0 ? 
					"Jugador " + (rowIndex + 1) :
					colores[rowIndex];
		}

		@Override
		public String getColumnName(int column) {
			return column == 0 ? "#Jugador" : "Color";
		}		
	}
	
	
	@SuppressWarnings("deprecation")
	InfoJugadoresUI(int numJug){		
		this.setLayout(new FlowLayout());
		this.numjugadores=numJug;	
		Color[] colores = new Color[numJug];
		for (int i=0; i<numjugadores; i++){
			colores[i] = generarColorAleatorio();
		}
		this.tabla=new JTable(new ModeloTabla(numJug, colores));
		this.tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// TODO Auto-generated method stub
				JComponent jc = (JComponent)super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				if (column == 1) {
					jc.setBackground((Color)value);
				} else {
					jc.setBackground(tabla.getBackground());
				}
				return jc;
			}
			
		});
		this.tabla.setPreferredSize(new Dimension(100,100));
		JScrollPane scroll=new JScrollPane(tabla);
		//this.contenedor.add(tabla);
		this.add(scroll);
		this.setSize(new Dimension(300, 300));
	}
	

	private int generarNumeroAleatorio(){
		return (int) (Math.random()*255);
	}
	
	private Color generarColorAleatorio(){
		return new Color(generarNumeroAleatorio(),generarNumeroAleatorio(),generarNumeroAleatorio());
	}
	
	public static void main(String ... args) {
		JFrame jf = new JFrame("titulo");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new InfoJugadoresUI(2));
		jf.setSize(400, 400);
		jf.setVisible(true);
	}
}
