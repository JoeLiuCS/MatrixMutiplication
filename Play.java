package matrixMulp;

public class Play {

	public static void main(String[] args) {
		Matrix m1 = new Matrix(32,"Fill");
		Matrix m2 = new Matrix(32,"Fill");
		m1.printMatrix();
		m2.printMatrix();
		
		System.out.println("Classical Mulp");
		ClassicalMulp c =  new ClassicalMulp(m1,m2);
		long startTime1 = System.nanoTime();
		c.Mulp();
		long endTime1   = System.nanoTime();
		long totalTime1 = endTime1 - startTime1;
		c.printResult();
		
		System.out.println("DivideAndConquer Mulp");
		DivideAndConquer d = new DivideAndConquer(m1,m2);
		long startTime2 = System.nanoTime();
		d.Mulp();
		long endTime2   = System.nanoTime();
		long totalTime2 = endTime2 - startTime2;
		d.getResult().printMatrix();
		
		System.out.println("Strassen Mulp");
		Strassen_matrix s = new Strassen_matrix(m1,m2);
		long startTime3 = System.nanoTime();
		s.Mulp();
		long endTime3   = System.nanoTime();
		long totalTime3 = endTime3 - startTime3;
		s.getResult().printMatrix();
		
		System.out.println("Classical: " +totalTime1+" Dvide and conquer: " + totalTime2+" Strassen : "+totalTime3 );

	}

}
