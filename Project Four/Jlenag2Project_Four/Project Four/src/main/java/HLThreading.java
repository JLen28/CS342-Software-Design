
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;



public class HLThreading {
	
	String[] gameBoard;
	String turn;
	ExecutorService ex;
	int result = 0;
	int xdiffy = 0; 
	int odiffy = 0;
	int numGames = 0;

	public void init() {
		gameBoard = new String[] {"b","b","b","b","b","b","b","b","b"};	
		turn = "X";
		ex = Executors.newFixedThreadPool(5);
		for(int iter = 0; iter < 9; iter++) {
			
			Future<Integer> future = ex.submit(new MyCall(gameBoard, turn));
		
			try {
			Integer index = future.get();
			if(index == -1) {
				System.out.print("no more possible moves, DRAW!");
				result = 0;
				break;
			}
			else if(index == 20) {
				System.out.println("---------");
				System.out.println(turn + " Wins!");
				System.out.println("---------");
				if(turn == "X") {result = 1;}
				else {result = 2; }
			}
			if(didXWin(gameBoard) == true) {
				System.out.println("---------");
				System.out.println("X HAS WON");
				System.out.println("---------");
			}
			if(didOWin(gameBoard) == true) {
				System.out.println("---------");
				System.out.println("X HAS WON");
				System.out.println("---------");
			}
			gameBoard[index] = turn;

			}catch(Exception e){System.out.println(e.getMessage());}
			
			if(turn == "X") {
				turn = "O";
			}
			else {
				turn = "X";
			}
			
			
		}
		ex.shutdown();
		
	}

	public String[] getBoard() {
		return gameBoard;
	}
	
	public int getResult() {
		return result;
	}
	
	public void setXDiffy(int xD) {
		this.xdiffy = xD;
	}
	
	public void setODiffy(int oD) {
		this.odiffy = oD;
	}
	
	public boolean didXWin(String[] board) {
		if( board[0] == "X" && board[1] == "X" && board[2] == "X") { // top horiz row 
			return true;
		}
		else if(board[3] == "X" && board[4] == "X" && board[5] == "X") { // middle horiz row
			return true;
		}
		else if(board[6] == "X" && board[7] == "X" && board[8] == "X") { // bottom horiz row
			return true;
		}
		else if(board[0] == "X" && board[4] == "X" && board[8] == "X") { // left to right diag
			return true;
		}
		else if(board[2] == "X" && board[4] == "X" && board[6] == "X") { // right to left diag
			return true;
		}
		else if(board[0] == "X" && board[3] == "X" && board[6] == "X") { // 1st vert row
			return true;
		}
		else if(board[1] == "X" && board[4] == "X" && board[7] == "X") { // 2nd vert row
			return true;
		}
		else if(board[2] == "X" && board[5] == "X" && board[8] == "X") { // 3rd vert row
			return true;
		}
		else { return false;}
	}
	public boolean didOWin(String[] board) {
		if( board[0] == "O" && board[1] == "O" && board[2] == "O") { // top horiz row 
			return true;
		}
		else if(board[3] == "O" && board[4] == "O" && board[5] == "O") { // middle horiz row
			return true;
		}
		else if(board[6] == "O" && board[7] == "O" && board[8] == "O") { // bottom horiz row
			return true;
		}
		else if(board[0] == "O" && board[4] == "O" && board[8] == "O") { // left to right diag
			return true;
		}
		else if(board[2] == "O" && board[4] == "O" && board[6] == "O") { // right to left diag
			return true;
		}
		else if(board[0] == "O" && board[3] == "O" && board[6] == "O") { // 1st vert row
			return true;
		}
		else if(board[1] == "O" && board[4] == "O" && board[7] == "O") { // 2nd vert row
			return true;
		}
		else if(board[2] == "O" && board[5] == "O" && board[8] == "O") { // 3rd vert row
			return true;
		}
		else { return false;}
	}

}

class MyCall implements Callable<Integer>{

	String[] board = new String[] {};
	String move;
	
	MyCall(String[] game, String move){
		board = game;
		this.move = move;
	}
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		/*
		System.out.println("----BOARD INSIDE OF CALL-----");
		System.out.println(board[0] + " " + board[1] + " " + board[2]);
		System.out.println(board[3] + " " + board[4] + " " + board[5]);
		System.out.println(board[6] + " " + board[7] + " " + board[8]);
		System.out.println("----BOARD INSIDE OF CALL-----");
		*/
		if(move == "O") {
			board = transposeBoard(board);
			AI_MinMax minmaxAlg = new AI_MinMax(board);
			for(int i = 0; i < minmaxAlg.getMoveList().size() - 1; i++) {
				if(minmaxAlg.getMoveList().get(i).getMinMax() != -10) {
					continue;
				}
				else {
					return 20;
				}
			}
			Integer val = 0;
			for(int i = 0; i < minmaxAlg.getMoveList().size() - 1; i++) {
				val = minmaxAlg.getMoveList().get(i).getMovedTo();
				if(board[val] == "b" && ( minmaxAlg.getMoveList().get(i).getMinMax() == 10 || minmaxAlg.getMoveList().get(i).getMinMax() == 0)) {
					Thread.sleep(1000);
					System.out.println("\n" + "player: " + move + " chooses index: "+val);
					return val;
				}
				else {
					continue;
				}
				}
			return -1;
		}
		else {
		AI_MinMax minmaxAlg = new AI_MinMax(board);
		Integer val = 0;
		for(int i = 0; i < minmaxAlg.getMoveList().size() - 1; i++) {
		val = minmaxAlg.getMoveList().get(i).getMovedTo();
		if(board[val] == "b" && ( minmaxAlg.getMoveList().get(i).getMinMax() == 10 || minmaxAlg.getMoveList().get(i).getMinMax() == 0) ) {
			Thread.sleep(1000);
			System.out.println("\n" + "player: " + move + " chooses index: "+val);
			return val;
		}
		else {
			continue;
		}
		}
		return -1;
		}
	}
	// the minmax algorithm is only suited to handle X's turn, to avoid over-complicating things
	// I have taken professor Hallenbeck's advice and transposed the board to have O's turn into X's and vice versa.
	// I should only have to call this once on a turn of "O", since we are only returning the board space
	// number there is no need to preserve the board.
	public String[] transposeBoard(String[] curBoard) {
		String[] oBoard = new String[]{};
		for(int i = 0; i < curBoard.length; i++) {
			if(curBoard[i] == "X") {
				curBoard[i] = "O";
			}
			else if(curBoard[i] == "O") {
				curBoard[i] = "X";
			}
		}
		oBoard = curBoard;
		return oBoard;
	}
	
}
	

