/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import javmos.JavmosGUI;

public abstract class Trigonometric extends Function {
    
    protected double a;
    protected double k;
    
    public Trigonometric(JavmosGUI gui, String function, String name) {
        super(gui);
        //If there is no coefficient, it is assumed to be 1
        a =(function.charAt(0) != name.charAt(0)) ? Double.parseDouble(function.substring(0, function.indexOf(name.charAt(0)))) : 1;
        k = (function.charAt(function.indexOf("(") + 1) != 'x') ? Double.parseDouble(function.substring(function.indexOf("(") + 1, function.indexOf("x"))) : 1;
    }
    
}
