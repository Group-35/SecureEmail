/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author AlexChatham
 */
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class hillcipher {

	private static final int MAX_INPUT_SIZE= 10000;
	private static final int LINE_LENGTH= 80;
	

	public static void main(String[] args) throws FileNotFoundException, IOException {
               
        
                Scanner stdin= new Scanner(System.in);
            
		//declare file names
                //key
            
                String keyFilename= "samplekey.txt";
                
                
		File keyFile= new File(keyFilename);

                
                
		//plaintext
		String plaintextFilename= "sampleplain.txt";
		

		File plaintextFile = new File(plaintextFilename);

		//the "n" value of the key matrix. determines how many rows and columns there are.
		int keyRowsCols = 0;
		int[][] key= new int[500][500];
                
                
               
                
                
                
                
		//Begin reading files. Start with the key. Handle errors here.
		try {
			Scanner keyScanner = new Scanner(keyFile);

			//Key matrices are always squares, and here we're reading in
			//the number of rows and columns.
			keyRowsCols= keyScanner.nextInt();
                        System.out.print(keyRowsCols);
                        System.out.print("\n");
			//double loop that inputs the key matrix into a 2D array that should exactly
			//match the input.
			for (int i= 0; i < keyRowsCols; i++)
			{
				for (int j= 0; j < keyRowsCols; j++){
					key[i][j]= keyScanner.nextInt();
                                        //print each part of the key
                                        System.out.print(key[i][j]+" ");
                                }
                               //add a line after each part of the key
                                System.out.print("\n");
                        }
		}
		//Handle bad filename. Just exit program if error found, no recovery.
		catch (FileNotFoundException e)
		{
			System.out.println("Error: bad key filename. Exiting.");
			System.exit(1);
		}

		//read in the plaintext to a String. later, we'll parse it out into chars.
		String plaintextInput= "";
		try
		{
			Scanner plaintextScanner = new Scanner(plaintextFile);

			//read in tokens from the plaintext until we've exhausted it. just concat for now
			while (plaintextScanner.hasNext() == true)
				plaintextInput+= plaintextScanner.next();
                       //print out actual message
                        System.out.print(plaintextInput);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: bad plaintext filename. Exiting...");
			System.exit(1);
		}
                //enter space for visual effects
                System.out.println("\n");
		//make sure the input is not too long.
		if (plaintextInput.length() > MAX_INPUT_SIZE)
		{
			System.out.println("Error: input too large. Ending program...");
			System.exit(1);
		}

		//turn the trimmed and lowered string into a char array. We will now process
		//this array and put it in a final array.
		char[] rawPlaintext= plaintextInput.trim().toLowerCase().toCharArray();
		char[] plaintext= new char[rawPlaintext.length];

		int j= 0;
		for (int i= 0; i< rawPlaintext.length; i++)
		{
			//strip away all non letters
			if (rawPlaintext[i] < 'a' || rawPlaintext[i] > 'z')
				continue;
			//add letters to the final plaintext array
			else
				plaintext[j++]= rawPlaintext[i];
		}

		char[] ciphertext= new char[plaintext.length+1];

		//perform the matrix multiplication. store in the ciphertext array
		//l= letter		r= row		c= column	j= length of actual plaintext
		for (int l= 0; l< j; l+= keyRowsCols)
		{
			for (int r=0; r< keyRowsCols; r++)
			{
				for (int c=0; c< keyRowsCols; c++)
				{
					//this checks for run over in case we run out of plaintext to encode
					if ((l+c) < j)
					{
						ciphertext[l+r]+= (char) (key[r][c]* (plaintext[l+c]-'a'));
					}

					//this is the code that is used in case of run out
					else
					{
						//'x'-'a' looks weird, but it's there to be consistent with the way we encode normally
						ciphertext[l+r]+= (char) (key[r][c]* ('x'-'a'));
					}
				}
				//mod 26 and add 'a' to keep it alphabetical
				ciphertext[(l+r)]= (char) (ciphertext[(l+r)] % 26 + 'a');
				}
		}
		try
		{
			

			//print out , 80 characters per line.
			for (int i=0; i<ciphertext.length;)
			{
				for(j=0; j<LINE_LENGTH; j++)
				{
					if (i<ciphertext.length)
						System.out.print(ciphertext[i++]);
				}
				System.out.print("\n");
			}
                 
			
		}
        
                finally
                {
                    System.exit(1);
                }
        
        }
		
	}
