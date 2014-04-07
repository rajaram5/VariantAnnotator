/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.lumc.variantannotator.sparql;

/**
 *
 * @author rajaram
 */
public class Test {
    
    private int combination(int n, int k) {      
               
        // n!/(n-k)!*k!
        int comb = factorial(n)/((factorial(n-k))*factorial(k));
        return comb;
    }
    
    private int factorial(int n) {
        
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact =fact*i;
        }        
        return fact;     
    } 
    
    
    public static void main (String arg[]) {
        
        Test test = new Test();
        
        System.out.println(test.factorial(5));
        System.out.println(test.combination(3, 2));
    }
    
}
