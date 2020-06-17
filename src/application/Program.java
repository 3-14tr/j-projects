package application;

import java.util.Random;
import java.util.Scanner;

public class Program {
	public static String field[][] = new String[10][10];
	public static String pcField[][] = new String[10][10];
	public static int userShips=0;
	public static int compShips=0;
	public static Random randPos = new Random();
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		System.out.println("##############");		
		System.out.println("# Game Start #");
		System.out.println("##############\n\n");
		System.out.println("FIELD IS YET EMPTY\n\n");

		promptField(field); //prints a blank field.
		
		
	     System.out.println("\nDeploy your ships:");
	     userDeploy(field); //User chooses coordinates for deploys
//	     promptField(field); prints updated field
	     System.out.println("The computer is deploying it's ships!\n\n");
	     pcDeploy(field);
	     promptField(pcField); //caso eu queira ver o field do pc.
	     
	     bombard();

		sc.close();
	}
	public static void promptField(String[][] field)
	{
		System.out.println("\n  0123456789  ");
		
		for (int line = 0; line < field.length; line++)
		{
			System.out.print(line + "|");
			for(int col = 0; col < field[line].length; col++)
			{
				if(field[line][col] == null)
				{
					System.out.print(" ");
				}
				else
				{
					System.out.print(field[line][col]); //will print "@" when updating the field.
				}
			}
			System.out.print("|" + line);
			System.out.println();
		}
		 System.out.println("  0123456789  ");
	      
	}
	public static void userDeploy(String[][] field)
	{
		while (userShips<5)
        {
            System.out.print("Enter X coordinate for your "+(userShips+1)+" ship: ");
            int col=sc.nextInt();
            System.out.print("Enter Y coordinate for your "+(userShips+1)+" ship: ");
            int line= sc.nextInt();
            
            if (line > 9 || col > 9)//the field is 10by10,Can't place beyond the 9 index.
            {
                System.out.println("The coordinate you entered is out of range, please try again");
            } 
            else if(field[line][col] != null) //Means, if not empty spot;	            	
            { 
                System.out.println("The coordinate you entered is already used, please try again");
            } 
            else 
            {
                field[line][col]= "@";
                userShips++;
            }	            
        }
	}
	public static void pcDeploy(String[][] field)
	{
		
		while(compShips < 5)
		{			 
	         int col=randPos.nextInt(10);	   //generates random number between 0 and n-1     
	         int line= randPos.nextInt(10);	
//	         System.out.println("Cheat [" + line+", " + col+"]"); pc coordinates, to help testing
	         if(field[line][col] == null && pcField[line][col] == null)
	            {
	            	System.out.printf("%d. ship deployed.\n",(compShips+1));
	                pcField[line][col]= "@";	                
	                compShips++;
	            }
	    }
	}
	public static void userTurn()
	{
		System.out.println("\nIts your Turn!\n ");
		int turn = 0;
		while (turn == 0)
		{
			System.out.print("Enter the X coordinate: ");
			int col = sc.nextInt();
			System.out.print("Enter the Y coordinate: ");
			int line = sc.nextInt();
			
			if(line > 9 || col > 9) //out of the field
			{
				System.out.println("\nOut of the field. Try again!");
			}
			else if(field[line][col] == "!" || field[line][col] == "-" || field[line][col] =="-")
			{
				System.out.println("\nThis coordinate was already used! Try again!");
			}
			else if(field[line][col] == "@")
			{
				System.out.println("\nOh no!, you sunk your own ship! :(");
				field[line][col] ="x";
				userShips--;
				turn++;
			}
			else if(pcField[line][col] == "@")
			{
				System.out.println("\nBoom! You sunk ther computer's ship");
				compShips--;
				field[line][col] ="!";
				pcField[line][col]="!";
				turn++;
			}
			else
			{
				System.out.println("\nSorry, you missed!");
				field[line][col]="-";
				turn++;
			}			
		}	
	}
	
	public static void pcTurn()
	{
		System.out.println("\n\nCOMPUTER'S TURN");
		int turn = 0;
		
		while(turn == 0)
		{
			int row = randPos.nextInt(10);
			int colmn = randPos.nextInt(10);
			
			if(pcField[row][colmn] == "@")
			{
				System.out.println("\nThe Computer sunk one of its own ships!");
				compShips--;
				pcField[row][colmn] ="!";
				field[row][colmn]="!";
				turn++;
			}
			else if(field[row][colmn] =="@")
			{
				System.out.println("\nThe Computer sunk one of your ships!");
				userShips--;
				field[row][colmn] ="x";
//				pcField[row][colmn] = "x";
				turn++;				
			}
			else if(field[row][colmn] =="!" || field[row][colmn] =="x"||field[row][colmn]=="-")
			{
				//already used. Will repeat.
			}
			else
			{
				System.out.println("\nComputer Missed!");
				turn++;
				pcField[row][colmn] = "-";
				turn++;
			}
		}		

	}
	public static void bombard()
	{
		promptField(field); //updated field
		System.out.println();
		System.out.println("Your  Ships: " +userShips+"| PC Ships: "+compShips);
		while (userShips > 0 && compShips > 0) 
		{
			userTurn();
			pcTurn();
			promptField(field);
			System.out.println("Your Ships: " +userShips+"| PC Ships: "+compShips);
//			updating ships remaining
		}
		System.out.println();
		if(userShips == 0)
		{
			System.out.println("Game Over: ");
			System.out.print("Computer have defeated you!\n");
			System.out.println("Your Ships: " +userShips+"| PC Ships: "+compShips);
		}
		else if(compShips == 0)
		{
			System.out.println("Hooray! You win the battle :)");
		}		
	}	
}


