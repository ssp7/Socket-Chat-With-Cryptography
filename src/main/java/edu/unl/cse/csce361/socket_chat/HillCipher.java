package edu.unl.cse.csce361.socket_chat;

import java.lang.reflect.Array;
import java.util.Scanner;

public class HillCipher implements Cipher {
   private String ciphertext;
   private String plaintext;
   private int[] shift;
	@Override
	public String encipher(String plaintext2) {
		Scanner scan = new Scanner(System.in);
      System.out.println("Please enter the text for encryption");
      plaintext2 = scan.nextLine();
      this.plaintext = plaintext2;
      this.plaintext= plaintext.replaceAll("\\s","");
      this.plaintext= plaintext.toUpperCase();
      int i = 0;
      while(i < plaintext.length()){
    	  
      }
    return null;
	}

	@Override
	public String decipher(String ciphertext) {

		return null;
	}

}
