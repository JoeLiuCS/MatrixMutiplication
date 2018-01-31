package matrixMulp;

/**
 * Divide and conquer for matrix
 * @author shuoqiao liu
 */

public class DivideAndConquer {
	
	protected final int startPosition = 0;
	private Matrix Result;
	private Matrix m1_input;
	private Matrix m2_input;
	private int result_size;
	
	public DivideAndConquer(Matrix m1,Matrix m2){
		Result = new Matrix(m1.size(),"unFill");
		result_size = m1.size();
		m1_input = m1;
		m2_input = m2;
	}
	/**
	 * Recursion prepare
	 * @return the final matrix 
	 */
	public void Mulp(){
		Result = rec_Mulp(m1_input,m2_input,m1_input.size());
	}
	/**
	 * Recursion divide and conquer
	 * @param m1
	 * @param m2
	 * @param size
	 * @return the final matrix
	 */
	private Matrix rec_Mulp(Matrix m1,Matrix m2,int size){
		if(size<=2){
			Matrix base = general_mulp(m1,m2);
			return base;
		}
		//m11
		Matrix ma11 = cutMatrix(m1,startPosition,startPosition,size/2);
		//m12
		Matrix ma12 = cutMatrix(m1,startPosition,startPosition+size/2,size/2);
		//m21
		Matrix ma21 = cutMatrix(m1,startPosition+size/2,startPosition,size/2);
		//m22
		Matrix ma22 = cutMatrix(m1,startPosition+size/2,startPosition+size/2,size/2);
		//m11
		Matrix mb11 = cutMatrix(m2,startPosition,startPosition,size/2);
		//m12
		Matrix mb12 = cutMatrix(m2,startPosition,startPosition+size/2,size/2);
		//m21
		Matrix mb21 = cutMatrix(m2,startPosition+size/2,startPosition,size/2);
		//m22
		Matrix mb22 = cutMatrix(m2,startPosition+size/2,startPosition+size/2,size/2);
		//mulp
		Matrix c11 = sumMatrix(rec_Mulp(ma11,mb11,ma11.size()),rec_Mulp(ma12,mb21,ma12.size()));
		Matrix c12 = sumMatrix(rec_Mulp(ma11,mb12,ma11.size()),rec_Mulp(ma12,mb22,ma12.size()));
		Matrix c21 = sumMatrix(rec_Mulp(ma21,mb11,ma21.size()),rec_Mulp(ma22,mb21,ma22.size()));
		Matrix c22 = sumMatrix(rec_Mulp(ma21,mb12,ma21.size()),rec_Mulp(ma22,mb22,ma22.size()));
		//combi
		return combi_fourMatrix(c11,c12,c21,c22);
	}
	/**
	 * Mulp two matrix
	 * @param m1
	 * @param m2
	 * @return the final matrix after mulp
	 */
	protected Matrix general_mulp(Matrix m1,Matrix m2){
		Matrix newMatrix = new Matrix(m1.size(),"unFill");
		for(int row=0;row<m1.size();row++){
			for(int coln=0;coln<m1.size();coln++){
				newMatrix.changeValue(row, coln, valueMulp(row,coln,m1,m2));
			}
		}
		return newMatrix;
	}
	/**
	 *  Formula for matrix calculation
	 * @param row
	 * @param coln
	 * @param m1
	 * @param m2
	 * @return the value for the specific position
	 */
	protected int valueMulp(int row,int coln,Matrix m1,Matrix m2){
		int value = 0;
		for(int i=0;i<m1.size();i++){
			value += m1.getValue(row, i) * m2.getValue(i, coln);
		}
		return value;
	}
	/**
	 * Sum two matrix together
	 * @param m1
	 * @param m2
	 * @return The matrix by summed 
	 */
	protected Matrix sumMatrix(Matrix m1,Matrix m2){
		Matrix newMatrix = new Matrix(m1.size(),"unFill");
		for(int row=0;row<m1.size();row++){
			for(int coln=0;coln<m1.size();coln++){
				int newValue = m1.getValue(row, coln)+m2.getValue(row, coln);
				newMatrix.changeValue(row, coln, newValue);
			}
		}
		return newMatrix;
	}
	/**
	 * subtract two matrix
	 * @param m1
	 * @param m2
	 * @return The matrix by subtraction
	 */
	protected Matrix subtractionMatrix(Matrix m1,Matrix m2){
		Matrix newMatrix = new Matrix(m1.size(),"unFill");
		for(int row=0;row<m1.size();row++){
			for(int coln=0;coln<m1.size();coln++){
				int newValue = m1.getValue(row, coln) - m2.getValue(row, coln);
				newMatrix.changeValue(row, coln, newValue);
			}
		}
		return newMatrix;
	}
	
	/**
	 * Cut a sub matrix with given position
	 * @param m1
	 * @param start_y
	 * @param start_x
	 * @param cut_length
	 * @return the piece of the matrix by given
	 */
	protected Matrix cutMatrix(Matrix m1,int start_y,int start_x,int cut_length){
		Matrix newMatrix = new Matrix(cut_length,"unFill");
		
		for(int row=0,copy_row = start_y; copy_row < start_y+cut_length ; row++,copy_row++){
			for(int coln=0,copy_coln = start_x;copy_coln < start_x+cut_length;coln++,copy_coln++){
				int value = m1.getValue(copy_row, copy_coln);
				newMatrix.changeValue(row, coln, value);
			}
		}
		
		return newMatrix;
	}
	
	/**
	 * Combine 4 matrix to 1
	 * @param m1
	 * @param m2
	 * @param m3
	 * @param m4
	 * @return the final matrix combine with 4 matrix
	 */
	protected Matrix combi_fourMatrix(Matrix m1,Matrix m2,Matrix m3,Matrix m4){
		
		Matrix matrixResult = new Matrix(m1.size()*2,"notfill");
		int halfSize = matrixResult.size()/2;
		
		for(int row=0;row<matrixResult.size();row++){
			for(int coln=0;coln<matrixResult.size();coln++){
				int value = 0;
				if(row < halfSize && coln < halfSize){
					value = m1.getValue(row, coln);
				}
				else if(row < halfSize && coln >= halfSize){
					value = m2.getValue(row, coln - halfSize);
				}
				else if(row >= halfSize && coln < halfSize){
					value = m3.getValue(row-halfSize, coln);
				}
				else if(row >= halfSize && coln >= halfSize){
					value = m4.getValue(row-halfSize, coln-halfSize);
				}
				else{
					throw new IllegalArgumentException("Wrong Area");
				}
				matrixResult.changeValue(row, coln, value);
			}
		}
		return matrixResult;
	}
	
	/**
	 * Get Result matrix
	 * @return result matrix
	 */
	public Matrix getResult(){
		return Result;
	}
	
	/**
	 * Get result matrix size
	 * @return matrix size
	 */
	public int getResultSize(){
		return result_size;
	}
	
}