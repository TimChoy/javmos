/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public class Sine extends Trigonometric {

    public Sine(JavmosGUI gui, String function) {
        super(gui, function, "sin");
    }

    @Override
    public String getFirstDerivative() {
        return "" + a * k + "cos(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return "" + -a * k * k + "sin(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch(functionType) {
            case FIRST_DERIVATIVE:
                return a * k * Math.cos(k * x);
            case SECOND_DERIVATIVE:
                return -a * k * k * Math.sin(k * x);
            case THIRD_DERIVATIVE:
                return -a * k * k * k * Math.cos(k * x);
            default:
                return a * Math.sin(k * x);
        }
    }
    
}
