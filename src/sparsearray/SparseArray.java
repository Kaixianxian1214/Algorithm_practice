package sparsearray;

public class SparseArray {

	public static void main(String[] args) {
		// ??????????????
		// 0?????1???
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		// ?????????????
		for(int[] row : chessArr1) {
			for(int i : row) {
				System.out.printf("%d\t", i);
			}
			System.out.print("\n");
		}
		
		// ??????????????
		// 1?????????????? ?????0????????
		
		int sum = 0;
		for(int[] row : chessArr1) {
			for(int i : row) {
				if(i != 0) {
					sum++;
				}
			}
		}
		
		
		//??????????
		int sparseArr1[][] = new int [sum+1][3];
		sparseArr1[0][0] = chessArr1.length;            // row
		sparseArr1[0][1] = chessArr1[0].length;         // col
		sparseArr1[0][2] = sum;
		
		int count = 0;
		for(int i = 0; i < chessArr1.length; i++) {
			for(int j = 0; j < chessArr1[0].length; j++) {
				if(chessArr1[i][j] != 0) {
					count++;
					sparseArr1[count][0] = i;
					sparseArr1[count][1] = j;
					sparseArr1[count][2] = chessArr1[i][j];
				}
			}
		}
		
		for(int i = 0; i < sparseArr1.length; i++) {
			System.out.printf("%d\t%d\t%d\n", sparseArr1[i][0], sparseArr1[i][1], sparseArr1[i][2]);
		}
		
		//???????????????????
		
		int chessArr2[][] = new int[sparseArr1[0][0]][sparseArr1[0][1]];
		
		for(int i = 1; i < sparseArr1.length; i++) {
			chessArr2[sparseArr1[i][0]][sparseArr1[i][1]] = sparseArr1[i][2];
		}
		
		for(int[] row : chessArr2) {
			for(int i : row) {
				System.out.printf("%d\t", i);
			}
			System.out.print("\n");
		}
		
	}

}
