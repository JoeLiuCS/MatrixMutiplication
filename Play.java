package matrixMulp;

public class Play {

	public static void main(String[] args) {
		Matrix m1 = new Matrix(16,"Fill");
		Matrix m2 = new Matrix(16,"Fill");
		m1.printMatrix();
		m2.printMatrix();
		ClassicalMulp c =  new ClassicalMulp(m1,m2);
		c.printResult();
		
		DivideAndConquer d = new DivideAndConquer(m1,m2);
		d.getResult().printMatrix();

	}

}
