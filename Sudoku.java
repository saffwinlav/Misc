package dataStructures;

public class Sudoku {

	//solves sudoku puzzle by inputting values and backtracking when no more valid inputs
	public static boolean solve(int [][] board){
		for (int row=0; row<board.length; row++) {
			for (int col=0; col<board.length; col++) {
				if(board[row][col] == 0) {
				for(int k = 1; k<=board.length; k++) {
					if(valid(board, row,col, k)){
				board[row][col] = k;
				if(solve(board) == true){
				return true;
			} else
				board[row][col] = 0;
			}	
		}
				 return false;	
		}
		
			}
		}
	return true;
		}
	//determines if a number is valid based on row/column rule of sodoku
	public static boolean valid(int board[][], int row, int col, int number) {
		for(int i = 0; i<board.length ; i++) {
			if(board[row][i] == number) {
				return false;
			} 
		}
		for(int j = 0; j<board.length ; j++) {
			if(board[j][col] == number) {
				return false;
			} 
		}
		int rbox = row - row%3;
		int cbox = col - col%3;
			for(int i = rbox; i<rbox+3; i++) {
				for (int j= cbox; j<cbox +3; j++) {
					if (board[i][j] == number) {
						return false;
					}
				}
			}
				
			return true;
			}
		
	public static void main(String[] args) {
		int [][] board = 
				{{8,0,0,0,0,0,0,0,0},
				{0,0,3,6,0,0,0,0,0},
				{0,7,0,0,9,0,2,0,0},
				{0,5,0,0,0,7,0,0,0},
				{0,0,0,0,4,5,7,0,0},
				{0,0,0,1,0,0,0,3,0},
				{0,0,1,0,0,0,0,6,8},
				{0,0,8,5,0,0,0,1,0},
				{0,0,9,0,0,0,4,0,0}};
	
		
		solve(board);
		
		for(int i = 0; i<board.length; i++)
		{
		    for(int j = 0; j<board.length; j++)
		    {
		        System.out.print(board[i][j] +" ");
		    }
		    System.out.println();
		}
	}
}

