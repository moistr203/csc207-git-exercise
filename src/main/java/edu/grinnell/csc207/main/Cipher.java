package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;

public class Cipher {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Error: Incorrect number of parameters.");
            return;
        }

        String action = null, cipherType = null, str = null, key = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-encode") || args[i].equals("-decode")) {
                action = args[i].substring(1);
            } else if (args[i].equals("-caesar") || args[i].equals("-vigenere")) {
                cipherType = args[i].substring(1);
            } else if (str == null) {
                str = args[i];
            } else if (key == null) {
                key = args[i];
            }
        }

        if (action == null || cipherType == null || str == null || key == null) {
            System.err.println("Error: Missing required parameters.");
            return;
        }

        if (cipherType.equals("caesar")) {
            if (key.length() != 1 || !Character.isLowerCase(key.charAt(0))) {
                System.err.println("Error: Invalid key for Caesar cipher.");
                return;
            }
            char keyChar = key.charAt(0);
            String result = action.equals("encode") 
                ? CipherUtils.caesarEncrypt(str, keyChar) 
                : CipherUtils.caesarDecrypt(str, keyChar);
            System.out.println(result);
        } else if (cipherType.equals("vigenere")) {
            if (!key.matches("[a-z]+")) {
                System.err.println("Error: Invalid key for Vigenère cipher.");
                return;
            }
            String result = action.equals("encode") 
                ? CipherUtils.vigenereEncrypt(str, key) 
                : CipherUtils.vigenereDecrypt(str, key);
            System.out.println(result);
        } else {
            System.err.println("Error: Unknown cipher type.");
        }
    }
}
