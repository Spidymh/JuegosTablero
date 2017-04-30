package es.ucm.fdi.tp.view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.AiPlayer;
import es.ucm.fdi.tp.mvc.BoardEvent;
import es.ucm.fdi.tp.mvc.BoardListener;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.was.WasAction;
import es.ucm.fdi.tp.was.WasState;
import model.TableroWas;

public class JugadorUI<S extends GameState<S,A>, A extends GameAction<S,A>> extends JFrame implements BoardListener, GameObserver<S,A>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6696978050638543432L;
	
	private static final int NUM_JUGADORES = 2;
	private static final int SMART_AI_DEPTH = 7;
	private BotoneraUI botonera;
	private InfoMensajesUI mensajes;
	private InfoJugadoresUI infoJugadores;
	private TableroUI<S,A> tablero;
	private GameTable<S,A> juego;



	JugadorUI(String name, GameTable<S,A> juego){
		super(name);
		this.juego = juego;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setLayout(new BorderLayout());
		insertarElementosGUI(this.getContentPane()); 
		juego.addObserver(this);
		
		this.pack();
		setSize(800, 600);
		setVisible(true); 
	}
	public void insertarElementosGUI(Container pane){
		
		this.botonera =  new BotoneraUI(new ButtonsUIListener(){
			@Override
			public void generateRandom() {
				juego.applyRandom();
				repaint();
			}
			@Override
			public void generateSmart() {
				juego.applySmart(SMART_AI_DEPTH);
			}
			@Override
			public void reset() {
				juego.reset();
				repaint();
			}
			@Override
			public void apagar() {
				System.exit(0);
			}
		});
		pane.add(botonera, BorderLayout.NORTH);
		
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
		
		this.tablero = new TableroUI<S,A>(new TableroWas<S,A>(juego), juego);
		tablero.setPreferredSize(new Dimension (400,400));
		tablero.setMaximumSize(new Dimension(400, 400));
		pane.add(tablero, BorderLayout.CENTER);
		tablero.addObserver(this);
	}

	@Override
	public void notifyBoardEvent(BoardEvent e) {
		A accion = tablero.generateAct(e.getX(), e.getY());
		if(accion != null) juego.execute(accion);
	}
	
	
	
	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		switch(e.getType()){
		case Change: {
			tablero.repaint();
		}break;
		}
	}


	
	public static void main(String[] args) {
		 GameTable<WasState, WasAction> table = new GameTable<WasState, WasAction>(new WasState());
		 table.start();
		 JugadorUI<WasState, WasAction> Jugador1 = new JugadorUI<WasState, WasAction>("jugador1", table);
		 JugadorUI<WasState, WasAction> Jugador2 = new JugadorUI<WasState, WasAction>("jugador2", table);
	}

}
