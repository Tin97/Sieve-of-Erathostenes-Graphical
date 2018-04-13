
import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;

public class Sieve2 {
	public static final float MAX_SIDE = 0.1f;
	public static final int FONT_SIZE_PRIME = 20;
	public static final int NUMBERS_IN_ROW_PRIME = 8;
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 1000;
	public static final float MARGIN_TOP = 0.05f;
	public static final float MARGIN_LEFT = 0.05f;
	public static final float MARGIN_RIGHT = 0.4f;
	
	public static void main(String[] args) {
		
		StdDraw.setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		StdDraw.text(1-(MARGIN_RIGHT/2), 1 - MARGIN_TOP, "Prime Numbers");
		
		Scanner input = new Scanner(System.in);
		
		StdDraw.enableDoubleBuffering();
		
		int number = input.nextInt();
		
		displayNumbers2ToN( number );
		
		boolean[] sequence = sieve( number );
		
		System.out.println(nonCrossedOutSubseqToString( sequence ));
		
		input.close();
	}
	
	public static boolean[] createSequence( int number )
	{
		boolean[] sequence = new boolean[number - 1];
		
		for ( int index = 0; index < sequence.length ; index++ )
		{
			sequence[index] = false;
		}
		
		return sequence;
	}
	
	public static void crossOutHigherMultiples( boolean[] sequence , int number, int primeCount )
	{
		for ( int index = number - 1 ; index < sequence.length ; index++ )
		{
			if ( (index + 2) % number == 0 && !sequence[index])
			{
				displayComposite(index + 2, primeCount, sequence.length + 1);
				
				sequence[index] = true;
			}
		}
	}
	
	public static boolean[] sieve( int number )
	{
		boolean[] sequence = createSequence( number );
		
		System.out.println(sequenceToString( sequence ));
		
		int primeCount = 0;
		
		for ( int index = 0 ; index < sequence.length ; index++ )
		{
			if ( !sequence[index] )
			{
				if (index < Math.sqrt( number ) - 1)
				{
					primeCount++;
					
					displayPrime( index + 2, primeCount);
					
					crossOutHigherMultiples( sequence , (index + 2), primeCount );
				
					System.out.println(sequenceToString( sequence ));
				}
				
				else
				{
					primeCount++;
					
					displayPrime( index + 2, primeCount);
				}
			}
		}
		
		return sequence;
	}
	
	public static String sequenceToString( boolean[] sequence )
	{
		String stringSequence = "2";
		
		for ( int index = 1 ; index < sequence.length ; index++ )
		{
			if ( sequence[index] == false )
			{
				stringSequence = stringSequence + ( ", " + Integer.toString(index + 2) ); 
			}
			
			else
			{
				stringSequence = stringSequence + ( ", [" + Integer.toString(index + 2) + "]" );
			}
		}
		
		return stringSequence;
	}
	
	public static String nonCrossedOutSubseqToString( boolean[] sequence )
	{
		String stringSequence = "2";
		
		for ( int index = 1 ; index < sequence.length ; index++ )
		{
			if ( sequence[index] == false )
			{
				stringSequence += ( ", " + Integer.toString(index + 2) ); 
			}
		}
		
		return stringSequence;
	}
	
	public static void displayNumber( int number, Color c, int n)
	{
		int numbersInARow = (int)Math.sqrt(n);
		double sideRect = (( 1 - MARGIN_LEFT - MARGIN_RIGHT ) / numbersInARow) / 2;
		
		if ( sideRect > MAX_SIDE )
		{
			sideRect = MAX_SIDE;
		}
		
		int row = (number - 1) / numbersInARow;
		int column = (number - 1) % numbersInARow;
		
		double positionY = 1 - ( sideRect + MARGIN_TOP + ( sideRect * row ) * 2 );
		double positionX = MARGIN_LEFT + sideRect + ( sideRect * column ) * 2;
		
		Font font = new Font("Arial", Font.BOLD, (int)(sideRect * SCREEN_WIDTH));
		StdDraw.setFont(font);
		StdDraw.setPenColor(c);
		StdDraw.filledRectangle(positionX, positionY, sideRect, sideRect);
		StdDraw.setPenColor();
		StdDraw.text(positionX, positionY, Integer.toString(number));
		StdDraw.show();
	}
	
	public static void displayNumbers2ToN( int n )
	{
		for ( int number = 2 ; number <= n ; number++ )
		{
			displayNumber( number, StdDraw.GRAY, n);
		}
	}
	
	public static void displayComposite( int m, int primeCount, int n )
	{
		Color c;
		c = Color.BLUE;
	
		switch((primeCount - 1) % 8)
		{
			case 0 :  
				c = Color.BLUE;
				break;
			case 1 : 
				c = Color.CYAN;
				break;
			case 2 :
				c = Color.RED;
				break;
			case 3 : 
				c = Color.MAGENTA;
				break;
			case 4 : 
				c = Color.ORANGE;
				break;
			case 5 : 
				c = Color.YELLOW;
				break;
			case 6:
				c = Color.PINK;
				break;
			case 7 : 
				c = Color.GREEN;
				break;
		}
		
		displayNumber(m, c, n);
		StdDraw.pause(50);
	}
	
	public static void displayPrime( int p, int primeCount )
	{
		int row = (primeCount - 1) / NUMBERS_IN_ROW_PRIME;
		int column = (primeCount - 1) % NUMBERS_IN_ROW_PRIME;
		
		double space = 0.4 / ( 2 * NUMBERS_IN_ROW_PRIME );
		
		double positionY = 1 - ( 2 * MARGIN_TOP + ( space * row ) );
		double positionX = 1 - MARGIN_RIGHT + space + ( space * column ) * 2;
		
		Font font = new Font("Arial", Font.BOLD, FONT_SIZE_PRIME);
		StdDraw.setFont(font);
		StdDraw.setPenColor();
		StdDraw.text(positionX, positionY, Integer.toString(p));
		StdDraw.show();
	}

}
