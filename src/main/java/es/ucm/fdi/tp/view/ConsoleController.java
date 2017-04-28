package es.ucm.fdi.tp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.was.WasAction;
import es.ucm.fdi.tp.was.WasState;

public class ConsoleController <S extends GameState<S,A>, A extends GameAction<S,A>> implements Runnable {

	private List<GamePlayer> jugadores;
	private GameTable<S,A> juego;
	
	
	public ConsoleController(List<GamePlayer> players, GameTable<S,A> game) {
			this.jugadores = players;
			this.juego = game;
	}
	
	@Override
	public void run() {
		int playerCount = 0;
		for (GamePlayer p : jugadores) {
			p.join(playerCount++); // welcome each player, and assign
									// playerNumber
		}
		this.juego.start();

		while (!juego.getState().isFinished()) {
			// request move
			A action = jugadores.get(juego.getState().getTurn()).requestAction(juego.getState());
			// apply move
			juego.execute(action);

			if (juego.getState().isFinished()) {
				// game over
				String endText = "The game ended: ";
				int winner = juego.getState().getWinner();
				if (winner == -1) {
					endText += "draw!";
				} else {
					endText += "player " + (winner + 1) + " (" + jugadores.get(winner).getName() + ") won!";
				}
				System.out.println(endText);
			}
		}
		System.out.println(juego.getState().getWinner());
		
		
	}
	
	public static void main(String... args) {
		
		try (Scanner s = new Scanner(System.in)) {
			List<GamePlayer> players = new ArrayList<GamePlayer>();
			GameState<?, ?> estadoJuego = new WasState();
			GameTable<?, ?> game = new GameTable(estadoJuego);
			players.add(new ConsolePlayer("Alice", s));
			players.add(new RandomPlayer("AiBob"));
			ConsoleController<WasState, WasAction> controladorJuego = new ConsoleController (players, game);
			new ConsoleView(game);
			controladorJuego.run();
		} 
	}

	
}
