package es.ucm.fdi.tp.view;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BotoneraUI extends JPanel{
	public JButton random;
	public JButton smart;
	public JButton reset;
	public JButton apagar;
	public ButtonsUIListener listener;
	
	public ModoJugador modo;
	
	private ImageIcon loadIcon(String iconName) {
		try {
			return new ImageIcon(ImageIO.read(
				 getClass().getResource("/" + iconName + ".png")));
		} catch (IOException ioe) {
			System.err.println("NO ICON for " + iconName);
			return null;
		}
	}
	
	BotoneraUI(ButtonsUIListener list){
		super();
		this.listener = list;
	
		this.random = new JButton("random");
		random.setIcon(loadIcon("dice"));
		this.smart = new JButton("smart");
		smart.setIcon(loadIcon("nerd"));
		this.reset = new JButton("reset");
		reset.setIcon(loadIcon("restart"));
		this.apagar = new JButton("apagar");
		apagar.setIcon(loadIcon("exit"));
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.modo = new ModoJugador();
		

	    this.add(random);
	    random.addActionListener(listener);
	    this.add(smart);
	    smart.addActionListener(listener);
	    this.add(reset);
	    reset.addActionListener(listener);
		this.add(apagar);
		apagar.addActionListener(listener);
}
}
