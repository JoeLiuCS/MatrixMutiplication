package matrixMulp;

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
		Mulp();
	}
	
	private void Mulp(){
		for(int row=0;row<result_size;row++){
			for(int coln=0;coln<result_size;coln++){
				Result.changeValue(row, coln, valueMulp(row,coln));
			}
		}
	}
	private int valueMulp(int row,int coln){
		int value = 0;
		for(int i=0;i<result_size;i++){
			value += m1_input.getValue(row, i) * m2_input.getValue(i, coln);
		}
		return value;
	}
	
	public Matrix getResult(){
		return Result;
	}
	
	public void printResult(){
		Result.printMatrix();
	}
	
	
	
}
