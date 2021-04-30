import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Scanner;

public class Matrices {
	private static int x; //rows
	private static int y; //columns
	private static int row1, column1, row2, column2;
	private static int operationChoice;
	private static int[][] array1,array2,sum, product;

	private static Scanner input;
	private static SecureRandom rObj;

	public static void main(String[]args) throws NoSuchAlgorithmException, NoSuchProviderException {
		while(operationChoice()) {
			display();
		}
	}
	
	
	public static boolean operationChoice() throws NoSuchAlgorithmException, NoSuchProviderException {
		input = new Scanner(System.in);

		int choice;
		boolean value = false;
		System.out.print("Enter 1 to add two matrices to each other.\n"
				+ "Enter 2 to multiply two matrices.\n"
				+ "Enter 3 to exit.\n"
				+ "--> ");
		operationChoice = input.nextInt();

		switch(operationChoice) {
		case 1: 
			sum();
			value = true;
			break;
		case 2: 
			product();
			value = true;
			break;
		case 3:
			value = false;
			close();
			break;
		default:
			value = false;
			close();
		}
		return value;
	}

	public static void size() {
		input = new Scanner(System.in);

		if(operationChoice == 1) {
			System.out.println("The size of two matrices.");
			System.out.print("Enter the matrices' horizontal size: "
					+ "--> ");
			x = input.nextInt();

			System.out.print("Enter the matrices' vertical size: "
					+ "  --> ");
			y = input.nextInt();
			array1 = new int[x][y];
			array2 = new int[x][y];
			sum	   = new int[x][y];
		}  if (operationChoice == 2) {

			System.out.println("\nEnter the horizontal and vertical size of the matrices\n"
					+ "The number of columns of first matrix "
					+ "must be equal to the number of rows of second matrix\n"
					+ "If not, the program will terminate.");
			System.out.print("Enter the first matrix horizontal size: "
					+ " --> ");
			row1 = input.nextInt();
			System.out.print("Enter the first matrix vertical size: "
					+ "   --> ");
			column1 = input.nextInt();

			System.out.print("Enter the second matrix horizontal size: "
					+ "--> ");
			row2 = input.nextInt();
			System.out.print("Enter the second matrix vertical size: "
					+ "  --> ");
			column2 = input.nextInt();
			if (column1 != row2) {
				System.err.println("Wrong input...");
				close();
			} else {
				array1  = new int[row1][column1];
				array2  = new int[row2][column2];
				product = new int[row1][column2];
			}

		}
	}
	public static void fillChoice() throws NoSuchAlgorithmException, NoSuchProviderException {
		input = new Scanner(System.in);

		int choice;
		System.out.print("\n Enter 1 to fill the matrices with random numbers.\n"
				+ " Enter 2 to fill the matrices by yourself.\n"
				+ " In case of wrong answer, the default is 1.\n"
				+ " --> ");
		choice = input.nextInt();
		switch(choice) {
		case 1: 
			randoms();
			break;
		case 2: 
			inputs();
			break;
		default: 
			System.err.print("\nWrong input for filling the matrices.\n");
			System.err.println("\tChoice 1 is been chosen.");
			choice = 1;
			randoms();

		}
	}

	private static void inputs() {
		// TODO Auto-generated method stub
		input = new Scanner(System.in);
		if(operationChoice == 1) {
			System.out.print("Enter the elements of the two matrices...\n"
					+ "Enter the elements of the first one: \n");
			for (int xCheck=0; xCheck<x; xCheck++) {
				for (int yCheck=0; yCheck<y; yCheck++) {
					System.out.print("--> ");
					array1[xCheck][yCheck] = input.nextInt();
				}
			}

			System.out.print("\nEnter the elements of the second one: \n");
			for (int xCheck=0; xCheck<x; xCheck++) {
				for (int yCheck=0; yCheck<y; yCheck++) {
					System.out.print("--> ");
					array2[xCheck][yCheck] = input.nextInt();
				}
			}
		} if(operationChoice == 2) {
			System.out.print("Enter the elements of the two matrices...\n"
					+ "Enter the elements of the first one: \n");
			for (int xCheck=0; xCheck<row1; xCheck++) {
				for (int yCheck=0; yCheck<column1; yCheck++) {
					System.out.print("--> ");
					array1[xCheck][yCheck] = input.nextInt();
				}
			}

			System.out.print("\nEnter the elements of the second one: \n");
			for (int xCheck=0; xCheck<row2; xCheck++) {
				for (int yCheck=0; yCheck<column2; yCheck++) {
					System.out.print("--> ");
					array2[xCheck][yCheck] = input.nextInt();
				}
			}
		}
		System.out.println("Arrays filled");
	}
	private static void randoms() throws NoSuchAlgorithmException, NoSuchProviderException {
		/* here I preferred to use getInstance() method that returns a SecureRandom object that
		 *  implements the specified Random Number Generator (RNG) algorithm.
		 *  instead of typing    SecureRandom rObj = new SecureRandom(); 
		 */
		rObj = SecureRandom.getInstance("SHA1PRNG", "SUN");
		 
		if(operationChoice == 1) {
			for (int xCheck=0; xCheck<x; xCheck++) 
				for (int yCheck=0; yCheck<y; yCheck++) 
					array1[xCheck][yCheck] = rObj.nextInt(10); //districted with small numbers to avoid big calculations

			for (int xCheck=0; xCheck<x; xCheck++) 
				for (int yCheck=0; yCheck<y; yCheck++) 
					array2[xCheck][yCheck] = rObj.nextInt(10); //districted with small numbers to avoid big calculations
			System.out.println("Arrays filled");
		} if (operationChoice == 2) {
			for (int xCheck=0; xCheck<row1; xCheck++) 
				for (int yCheck=0; yCheck<column1; yCheck++) 
					array1[xCheck][yCheck] = rObj.nextInt(10); //districted with small numbers to avoid big calculations

			for (int xCheck=0; xCheck<row2; xCheck++) 
				for (int yCheck=0; yCheck<column2; yCheck++) 
					array2[xCheck][yCheck] = rObj.nextInt(10); //districted with small numbers to avoid big calculations
			System.out.println("Arrays filled");
		}
	}
	public static  void sum () throws NoSuchAlgorithmException, NoSuchProviderException {
		size();
		fillChoice();

		for (int xCheck=0; xCheck<x; xCheck++) {
			for (int yCheck=0; yCheck<y; yCheck++) {
				sum[xCheck][yCheck] = array1[xCheck][yCheck] + array2[xCheck][yCheck];
			}
		}

	}

	public static void product() throws NoSuchAlgorithmException, NoSuchProviderException {
		size();
		fillChoice();
		for(int xCheck = 0; xCheck < row1; xCheck++) {
			for (int yCheck = 0; yCheck < column2; yCheck++) {
				for (int i = 0; i < column1; i++) {
					product[xCheck][yCheck] += array1[xCheck][i] * array2[i][yCheck];
				}
			}
		}
	}
	public static void display() {

		if(operationChoice == 1) {
			System.out.println("The first matrix. ");
			for (int xCheck=0; xCheck<x; xCheck++) {
				System.out.print(" ");
				for (int yCheck=0; yCheck<y; yCheck++) {
					System.out.print(array1[xCheck][yCheck] + "\t");
				}
				System.out.println();
			} System.out.println();


			System.out.println("The second matrix. ");
			for (int xCheck=0; xCheck<x; xCheck++) {
				System.out.print(" ");
				for (int yCheck=0; yCheck<y; yCheck++) {
					System.out.print(array2[xCheck][yCheck] + "\t");
				}
				System.out.println();
			} System.out.println();


			System.out.println("The sum of the two matrices. ");
			for (int xCheck=0; xCheck<x; xCheck++) {
				System.out.print(" ");
				for (int yCheck=0; yCheck<y; yCheck++) {
					System.out.print(sum[xCheck][yCheck] + "\t");
				}
				System.out.println();
			}
			System.out.println();
		} if(operationChoice == 2) {
			System.out.println("The first matrix. ");
			for (int xCheck=0; xCheck<row1; xCheck++) {
				System.out.print(" ");
				for (int yCheck=0; yCheck<column1; yCheck++) {
					System.out.print(array1[xCheck][yCheck] + "\t");
				}
				System.out.println();
			} System.out.println();


			System.out.println("The second matrix. ");
			for (int xCheck=0; xCheck<row2; xCheck++) {
				System.out.print(" ");
				for (int yCheck=0; yCheck<column2; yCheck++) {
					System.out.print(array2[xCheck][yCheck] + "\t");
				}
				System.out.println();
			} System.out.println();


			System.out.println("The product of the two matrices. ");    //here
			for (int xCheck=0; xCheck<row1; xCheck++) {
				System.out.print(" ");
				for (int yCheck=0; yCheck<column2; yCheck++) {
					System.out.print(product[xCheck][yCheck] + "\t");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	private static void close() {
		System.out.println("BYE...");
		input.close();
		System.exit(0);
	}
}
