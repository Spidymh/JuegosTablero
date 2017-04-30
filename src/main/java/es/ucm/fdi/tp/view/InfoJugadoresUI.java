package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.base.Utils;

public class InfoJugadoresUI extends JPanel{
	private JTable tabla;
	private int numJugadores;
	private static final Color BLANCO=Color.WHITE;
	private ModeloTabla modelo;

	@SuppressWarnings("deprecation")
	InfoJugadoresUI(int numJug){
		this.setLayout(new FlowLayout());
		this.numJugadores=numJug;
		this.modelo=new ModeloTabla(2);
		JTable tabla=new JTable(modelo){
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				if (col == 1)
					comp.setBackground(modelo.getColorenpos(row));
				else
					comp.setBackground(Color.WHITE);
				return comp;
			}
		};
		tabla.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent evento){
				int row = tabla.rowAtPoint(evento.getPoint());
				int col = tabla.columnAtPoint(evento.getPoint());
				if (row >= 0 && col >= 0) {
					changeColor(row);
				}
			}
		});
		tabla.setSize(tabla.getAutoResizeMode(), tabla.getAutoResizeMode());
		tabla.setAutoResizeMode(WHEN_IN_FOCUSED_WINDOW);
		JScrollPane scroll=new JScrollPane(tabla);
		this.add(scroll);
		this.setSize(new Dimension(300,300));
	}

	private void changeColor(int row) {
		Color c=JColorChooser.showDialog(null, "Selecciona otro color", Color.white);
		modelo.setColor(row, c);
		repaint();
	}

	
	public static void main(String ... args) {
		JFrame jf = new JFrame("prueba");
		JPanel jp=new JPanel();
		jp.add(new InfoJugadoresUI(2));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf.setContentPane(jp);
		jf.setSize(400, 400);
		jf.setVisible(true);
	}
	
}