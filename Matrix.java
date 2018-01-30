package matrixMulp;

import java.util.Random;
/**
 * The class is for creating the original matrix
 * @author shuoqiao liu
 */
public class Matrix {

	private int size;
	private int [] [] matrix;
	
	/**
	 * Give the number of size, and fill or not fill the random numbers
	 * @param size
	 * @param fill type string fill or Fill to fill up random numbers to the matrix
	 */
	public Matrix(int size,String fill){
		this.size = size;
		matrix = new int [size][size];
		if (fill.equals("Fill")||fill.equals("fill"))
			MatrixFillup();
	}
	
	/**
	 * Fill up the matrix with random numbers
	 */
	private void MatrixFillup(){
		for(int row=0;row<size;row++){
			for(int coln=0;coln<size;coln++){
				Random rand = new Random();
				matrix[row][coln] = rand.nextInt(9)+1;
			}
		}
	}
	
	/**
	 * Size of the Matrix
	 * @return number of size
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Get the matrix
	 * @return matrix
	 */
	public int [][] getMatrix(){
		return matrix;
	}
	/**
	 * Change the value for the specific position
	 * @param row
	 * @param coln
	 * @param value
	 * @return true or false if the value was changed
	 */
	public boolean changeValue(int row,int coln,int value){
		if(row<size && coln<size){
			matrix[row][coln] = value;
			return true;
		}
		else{
			return false;
		}
	}
	
	public int getValue(int row,int coln){
		return matrix[row][coln];
	}
	/**
	 * Print the matrix
	 */
	public void printMatrix(){
		System.out.println("This is "+size+" by "+size+" matrix.\n");
		for(int [] x: matrix){
			for(int y:x){
				System.out.print(formatPrint(y));
			}
			System.out.println("\n");
		}
	}
	/**
	 * Fix the print format for each value 
	 * @param num
	 * @return String with length = 4 
	 */
	private String formatPrint(int num){
		String numString = Integer.toString(num);
		while(numString.length() < 5){
			numString += " ";
		}
		return numString;
	}
	
}
