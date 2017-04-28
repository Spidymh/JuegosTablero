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
		case "random": {
			generateRandom();
		}break;
		case "smart":{
			generateSmart();
		}break;
		case "reset":{
			reset();
		}break;
		case "apagar": {
			apagar();
		}break;
		}

	}
}
