package es.ucm.fdi.tp.view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WasAction;
import es.ucm.fdi.tp.was.WasState;

public class JugadorUI<S extends GameState<S,A>, A extends GameAction<S,A>> extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6696978050638543432L;
	
	private static final int NUM_JUGADORES = 2;
	private static final int SMART_AI_DEPTH = 7;
	private BotoneraUI botonera;
	private TableroUI tablero;
	private InfoMensajesUI mensajes;
	private InfoJugadoresUI infoJugadores;
	private GameTable<S,A> juego;
	


	JugadorUI(String name, GameTable<S,A> juego){
		super(name);
		this.juego = juego;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setLayout(new BorderLayout());
		insertarElementosGUI(this.getContentPane()); 
		
		this.pack();
		setSize(800, 600);
		setVisible(true);   //muestra
		
	}
	public void insertarElementosGUI(Container pane){
		
		this.botonera =  new BotoneraUI(new ButtonsUIListener(){
			@Override
			public void generateRandom() {
			}
			@Override
			public void generateSmart() {
			}
			@Override
			public void reset() {
				
			}
			@Override
			public void apagar() {
				System.exit(0);
			}
		});
		pane.add(botonera, BorderLayout.NORTH);
		
		this.tablero = new TableroUI(new TableroWas());
		tablero.setPreferredSize(new Dimension (400,400));
		tablero.setMaximumSize(new Dimension(400, 400));
		pane.add(tablero, BorderLayout.CENTER);
		
		JPanel este = new JPanel();
		este.setLayout(new BoxLayout(este, BoxLayout.Y_AXIS));
		este.setPreferredSize(new Dimension(300, 300));
		este.setMaximumSize(new Dimension(300, 300));
		
		this.mensajes = new InfoMensajesUI();
		mensajes.insertarMensaje("Ventana que muestra los eventos del juego");
		este.add(mensajes);
		
		this.infoJugadores = new InfoJugadoresUI(2);
		este.add(this.infoJugadores);
		
		pane.add(este, BorderLayout.EAST);
	}
	
	 public static void main(String[] args) {
		 GameTable<WasState, WasAction> table = new GameTable<WasState, WasAction>(new WasState());
		 new JugadorUI("jugador1", table);
		 new JugadorUI("jugador2", table);
		 table.start();
	    }
	 /*  java.awt.EventQueue.invokeLater(new Runnable() {

	            public void run() {
	            	JugadorUI ven = new JugadorUI("titulo");
	            	ven.setVisible(true);
	            }
	        });*/
}
