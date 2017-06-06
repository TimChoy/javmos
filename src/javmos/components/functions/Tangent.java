/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public class Tangent extends Trigonometric {

    public Tangent(JavmosGUI gui, String function) {
        super(gui, function, "tan");
    }

    @Override
    public String getFirstDerivative() {
        return "" + a * k + "sec^2(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return "" + 2 * a * k * k + "sec^2(" + k + "x)tan(" + k +"x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return a * k / Math.pow((Math.cos(k * x)), 2);
            case SECOND_DERIVATIVE:
                return 2 * a * k * k * Math.tan(k * x) / Math.pow(Math.cos(k * x), 2);
            case THIRD_DERIVATIVE:
                return 2 * a * Math.pow(k, 3) * (1 / Math.pow(Math.cos(k * x), 2)) *
                        (3 * (1 / Math.pow(Math.cos(k * x), 2)) - 2);
            default:
                return a * Math.tan(k * x);
        }
    }
    
}
