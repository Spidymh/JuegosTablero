package es.ucm.fdi.tp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class ButtonsUIListener implements ActionListener {
	
	public enum MoveType{
		RANDOM, SMART
	}

	public abstract void generateRandom();
	public abstract void generateSmart();
	public abstract void reset();
	public abstract void apagar();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "Random": {
			generateRandom();
		}break;
		case "Smart":{
			generateSmart();
		}break;
		case "Reiniciar":{
			reset();
		}break;
		case "Apagar": {
			apagar();
		}break;
		}

	}
}
