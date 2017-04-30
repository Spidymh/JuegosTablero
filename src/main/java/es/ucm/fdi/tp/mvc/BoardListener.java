package es.ucm.fdi.tp.mvc;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public interface BoardListener {
    /**
     * Notifies the observer of an event. Typically called by a GameObservable
     * that this observer has registered with
     * @param e the event
     */
    void notifyBoardEvent(BoardEvent e);
}