package matrixMulp;
/**
 * Strassen's Multiplication for matrix
 * @author shuoqiaoliu
 *
 */
public class Strassen_matrix extends DivideAndConquer{

	private Matrix Result;
	private Matrix m1_input;
	private Matrix m2_input;
	private int result_size;
	
	public Strassen_matrix(Matrix m1, Matrix m2) {
		super(m1, m2);
		Result = new Matrix(m1.size(),"unFill");
		result_size = m1.size();
		m1_input = m1;
		m2_input = m2;
	}
	
	/**
	 * The multiplication follow the algorithm
	 */
	@Override
	public void Mulp(){
		Result = rec_Mulp_S(m1_input,m2_input,m2_input.size());
	}
	
	/**
	 * The recursively function for mulp()
	 * @param m1
	 * @param m2
	 * @param size
	 * @return The final matrix
	 */
	private Matrix rec_Mulp_S(Matrix m1,Matrix m2,int size){
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
		
		//P
		Matrix P = rec_Mulp_S(sumMatrix(ma11,ma22),sumMatrix(mb11,mb22),size/2);
		//Q
		Matrix Q = rec_Mulp_S(sumMatrix(ma21,ma22),mb11,size/2);
		//R
		Matrix R = rec_Mulp_S(ma11,subtractionMatrix(mb12,mb22),size/2);
		//S
		Matrix S = rec_Mulp_S(ma22,subtractionMatrix(mb21,mb11),size/2);
		//T
		Matrix T = rec_Mulp_S(sumMatrix(ma11,ma12),mb22,size/2);
		//U
		Matrix U = rec_Mulp_S(subtractionMatrix(ma21,ma11),sumMatrix(mb11,mb12),size/2);
		//V
		Matrix V = rec_Mulp_S(subtractionMatrix(ma12,ma22),sumMatrix(mb21,mb22),size/2);
		//C11
		Matrix C11 = subtractionMatrix(sumMatrix(P,S) ,subtractionMatrix(T,V));
		//C12
		Matrix C12 = sumMatrix(R,T);
		//C21
		Matrix C21 = sumMatrix(Q,S);
		//C22
		Matrix C22 = subtractionMatrix(sumMatrix(P,R) ,subtractionMatrix(Q,U));
		
		//return final matrix
		return combi_fourMatrix(C11,C12,C21,C22);
	}
	
	/**
	 * The result matrix
	 */
	@Override
	public Matrix getResult(){
		return Result;
	}
	/**
	 * The result size
	 */
	@Override
	public int getResultSize(){
		return result_size;
	}
	
}
