/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


public class Utils {

    public static String nomRobot() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGH"
                + "IJKLMNOPQRSTUVWXYZ1234567890*#"; // Tu supprimes les lettres dont tu ne veux pas
        String pass = "";
        for (int x = 0; x < 10; x++) {
            int i = (int) Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
            pass += chars.charAt(i);
        }
        return pass;
    }

}
