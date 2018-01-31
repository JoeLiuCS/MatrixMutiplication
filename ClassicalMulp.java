package matrixMulp;

/**
 * Classical Multiplication for matrix
 * @author shuoqiao liu
 *
 */
public class ClassicalMulp {

	private Matrix Result;
	private Matrix m1_input;
	private Matrix m2_input;
	private int result_size;
	
	public ClassicalMulp(Matrix m1,Matrix m2){
		Result = new Matrix(m1.size(),"unFill");
		result_size = m1.size();
		m1_input = m1;
		m2_input = m2;
	}
	
	/**
	 * Run to each position to calculate
	 */
	public void Mulp(){
		for(int row=0;row<result_size;row++){
			for(int coln=0;coln<result_size;coln++){
				Result.changeValue(row, coln, valueMulp(row,coln));
			}
		}
	}
	/**
	 * Formula for matrix calculation
	 * @param row
	 * @param coln
	 * @return the value for the specific position
	 */
	private int valueMulp(int row,int coln){
		int value = 0;
		for(int i=0;i<result_size;i++){
			value += m1_input.getValue(row, i) * m2_input.getValue(i, coln);
		}
		return value;
	}
	/**
	 * Get result
	 * @return the matrix for result
	 */
	public Matrix getResult(){
		return Result;
	}
	/**
	 * Print the Result
	 */
	public void printResult(){
		Result.printMatrix();
	}
	
	
	
}