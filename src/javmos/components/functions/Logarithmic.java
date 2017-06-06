/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import javmos.JavmosGUI;
import javmos.enums.FunctionType;

/**
 *
 * @author ravin
 */
public class Logarithmic extends Function {
    
    public double a;
    public double base;
    public double k;

    public Logarithmic(JavmosGUI gui, String function) {
        super(gui);
        function = function.substring(function.indexOf("=") + 1);
        a = (function.charAt(0) != 'l') ? Double.parseDouble(function.substring(0, function.indexOf("l"))) : 1;
        function = function.substring(function.indexOf("l"));
        if ((function.substring(0, function.indexOf("("))).equals("ln")) {
            base = Math.E;
        } else {
            base = Double.parseDouble(function.substring(function.indexOf("g") + 1, function.indexOf("(")));
        }
        function = function.substring(function.indexOf("(") + 1);
        k = (function.charAt(0) != 'x') ? Double.parseDouble(function.substring(0, function.indexOf("x"))) : 1;
    }

    @Override
    public String getFirstDerivative() {
        if (base == Math.E) {
            return "" + a + "/x";
        }
        return "" + a + "/xln" + base;
    }

    @Override
    public String getSecondDerivative() {
        if (base == Math.E) {
            return "" + -a + "/x^2";
        }
        return "" + -a + "/x^2ln" + base;
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch(functionType) {
            case FIRST_DERIVATIVE:
                return a / (x * Math.log(base));
            case SECOND_DERIVATIVE:
                return -a / (x * x * Math.log(base));
            case THIRD_DERIVATIVE:
                return 2 * a / (x * x * x * Math.log(base));
            default:
                return a * Math.log(k * x) / Math.log(base);
        }
    }
    
}
