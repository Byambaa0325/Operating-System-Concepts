import java.util.Random;

public class MatrixMul {
	public static void main(String[] args){
		int n = 10;
		int m = 5;
		int k = 60;
		int[][] A = new int[n][m];
		int[][] B = new int[m][k];
		Random rand = new Random();


		System.out.println("A Matrix "+n+"x"+m);
		for(int i = 0; i<n;i++){
			for (int j = 0; j < m; j++){
				A[i][j] = rand.nextInt();
				//System.out.print(A[i][j]+", ");
			}
			//System.out.println();
		}
		System.out.println("B Matrix "+m+"x"+k);
		for (int j = 0; j < m; j++){
			for(int l = 0; l<k;l++){
				B[j][l] = rand.nextInt();
				//System.out.print(B[j][l]+", ");
			}
			//System.out.println();
		}

		long startTime = System.currentTimeMillis();
		MatrixMulThread[][] multiplications = new MatrixMulThread[n][k];
		int C[][] = new int[n][k];
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				for (int l = 0; l < k; l++){
					multiplications[i][l] = new MatrixMulThread(A[i][j], B[j][l]);
					multiplications[i][l].start();
				}
			}
		}
		long initTime = System.currentTimeMillis();

		System.out.println("Average thread initialization time "+(initTime-startTime)/(n*k*1.0));
		System.out.println("Thread initializing Time: "+(initTime-startTime));

		System.out.println("C Matrix "+n+"x"+k);
		for(int i = 0; i<n;i++){
			for(int j =0; j<k; j++){
				try {
					multiplications[i][j].join();
					C[i][j] = multiplications[i][j].result;
					//System.out.print(C[i][j]+", ");
				}
				catch (Exception e){
					System.out.println("Error");
				}
			}
			//System.out.println();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Elapsed Time: "+(endTime-startTime));

		multiply(A,B);


	}
	private static void multiply(int[][] A, int[][] B){
		long startTime = System.currentTimeMillis();
			int[][] product = new int[A.length][B[0].length];
			for(int i = 0; i < A.length; i++) {
				for (int j = 0; j < B[0].length; j++) {
					for (int k = 0; k < B.length; k++) {
						product[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		long endTime = System.currentTimeMillis();
		System.out.println("Elapsed Time: "+(endTime-startTime));
	}

}
class MatrixMulThread extends  Thread{
	public int A;
	public int B;
	public int result = 0;
	MatrixMulThread(int A_, int B_){
		A = A_;
		B = B_;
	}

	public void run(){
		try{
			result = A*B;
		}catch (Exception e){
			System.out.println("Error");
		}
	}
}