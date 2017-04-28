package es.ucm.fdi.tp.view;

import javax.swing.JComboBox;

public class ModoJugador extends JComboBox<String>{
	
	static String OPCIONES[] = {"Manual", "Random", "Smart"};
	
	ModoJugador(){
		super(OPCIONES);
	}
}
