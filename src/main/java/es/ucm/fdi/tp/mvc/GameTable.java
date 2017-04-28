package es.ucm.fdi.tp.mvc;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;

/**
 * An event-driven game engine.
 * Keeps a list of players and a state, and notifies observers
 * of any changes to the game.
 */
public class GameTable<S extends GameState<S, A>, A extends GameAction<S, A>> implements GameObservable<S, A> {

    private S estadoInicial;
    private S estadoActual;
    private List<GameObserver<S,A>> observadores;
    private boolean parado;

    public GameTable(S initState) {
        this.estadoInicial = initState;
        this.estadoActual = null;
        this.parado = false;
        this.observadores = new ArrayList<GameObserver<S,A>>();
    }
    public void start() {
        estadoActual = estadoInicial;
    }
    
    public void stop() throws GameError {
        if(parado) throw new GameError("Juego ya parado");
        else parado = true;
        
    }
    
    public void execute(A action) {
    	this.estadoActual = action.applyTo(estadoActual);
    	this.notificarObservadores(new GameEvent(EventType.Change, null, estadoActual, null, "cambio"));
    }
    
    public S getState() {
    	return this.estadoActual;
    }
    
    public void notificarObservadores(GameEvent<S, A> e){
    	for (int i = 0; i < observadores.size(); ++i)
    		observadores.get(i).notifyEvent(e);
    }

    public void addObserver(GameObserver<S, A> o) {
        observadores.add(o);
    }
    public void removeObserver(GameObserver<S, A> o) {
        
    }
}
