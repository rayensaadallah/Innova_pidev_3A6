/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.security.NoSuchAlgorithmException;
import java.util.function.Function;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Asus
 */
public class Hashing {
  public static void main(String[] args) throws NoSuchAlgorithmException
	{
		String  originalPassword ="rayen";
		String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(13));
		System.out.println(generatedSecuredPasswordHash);
                     System.out.println("$2y$13$P2u2PD9LwuJ130SPE.2o2epdMNoK1TfiCzrdkuhjK1oz7awr9MEBW");
		boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
		System.out.println(matched);
	}
    
}
