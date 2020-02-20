public class Table {
	public static void main(String[] args) {
		int i = 1;
		while (i <= 9) {
			int j = 1;
			while (j <= i) {
				System.out.printf("%d * %d = %2d  ", j, i, j * i);
				j++;
			}
			System.out.println();
			i++;
		}
	}
}