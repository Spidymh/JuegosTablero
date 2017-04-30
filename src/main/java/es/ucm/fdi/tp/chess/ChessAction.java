 package es.ucm.fdi.tp.chess;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.view.pieza;

/**
 * An action for TickTackToe.
 */
public class ChessAction implements GameAction<ChessState, ChessAction> {

	private static final long serialVersionUID = -1889325373508412883L;
	
	private boolean movimientovacio;
	private int player;
    private int movimientoX;
    private int movimientoY;
    private int posPiezaX;//coordenadas de la ficha que qeuremos mover.
    private int posPiezaY;

    /**
     * Constructor que crea una accion vacia, en la que no se produce ningun movimiento.
     */
    public ChessAction(){
    	this.movimientovacio=true;
    	this.movimientoX=0;
    	this.movimientoY=0;
    	this.posPiezaX=0;
    	this.posPiezaY=0;
    	this.player=1;
    }
    /**
     * 
     * @param player Turno
     * @param movimientoX FilaDestino
     * @param movimientoY ColumnaDestino
     * @param posFichaX FilaOrigen
     * @param posFichaY ColumnaOrigen
     */
    public ChessAction(int player, int movimientoX, int movimientoY,int posFichaX, int posFichaY) {
        this.player = player;
        this.movimientoX = movimientoX;
        this.movimientoY = movimientoY;
        this.posPiezaX = posFichaX;
        this.posPiezaY = posFichaY;
        this.movimientovacio=false;
    }

    public int getPlayerNumber() {
        return player;
    }

    /**
     * Aplica una accion a un estado
     * @param state Estado anterior.
     * @return Proximo estado.
     */
    public ChessState applyTo(ChessState state) {
        if (player != state.getTurn()) {
            throw new IllegalArgumentException("Not the turn of this player");
        }

        // make move
        pieza[][] board = state.getBoard();
        if(board[posPiezaX][posPiezaY] != null && board[movimientoX][movimientoY] != null)
        	throw new IllegalArgumentException("Not valid movement");
        else {
        if(board[posPiezaX][posPiezaY].getJugador() != player && !this.movimientovacio)
        	throw new IllegalArgumentException("Not valid movement");
        pieza aux = board[movimientoX][movimientoY];
        board[movimientoX][movimientoY] = board[posPiezaX][posPiezaY];
        board[posPiezaX][posPiezaY] = aux;
        }
        // update state
        ChessState next = new ChessState(state, board, false, -1);
        if (next.isWinner())next = new ChessState(state, board, true, state.getTurn());
        

        //no hay empates.
        return next;
    }

    /**
     * 
     * @return FilaDestino
     */
    public int getmovimientoX() {
        return movimientoX;
    }

    /**
     * 
     * @return ColumnaDestino
     */
    public int getmovimientoY() {
        return movimientoY;
    }
    
    /**
     * 
     * @return FilaOrigen
     */
    public int getposFichaX() {
        return posPiezaX;
    }

    /**
     * 
     * @return ColumnaOrigen
     */
    public int getposFichaY() {
        return posPiezaY;
    }
    

    public String toString() {
    	StringBuilder sb=new StringBuilder();
    	if(!movimientovacio){
sb.append("Place ");
    			switch(player){
    			case 0:sb.append('O');break;
    			case 1:sb.append('X');break;
    			}
    			sb.append(" from (");
    			sb.append(this.posPiezaX +", "+this.posPiezaY+") to ");
        		sb.append("(" + movimientoX + ", " + movimientoY + ")");
        		return sb.toString();
        		}
    	else{
    		return "Pasar turno";
    	}
    }

}