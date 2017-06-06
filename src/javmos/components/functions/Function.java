/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.JavmosComponent;
import javmos.enums.FunctionType;
import javmos.enums.RootType;

public abstract class Function extends JavmosComponent {
    
    protected Function(JavmosGUI gui) {
        super(gui);
    }
    
    public HashSet getXIntercepts() {
        return RootType.X_INTERCEPT.getRoots(gui, this, -50, 50);
    }
    
    public HashSet getCriticalPoints() {
        return RootType.CRITICAL_POINT.getRoots(gui, this, -50, 50);
    }
    
    public HashSet getInflectionPoints() {
        return RootType.INFLECTION_POINT.getRoots(gui, this, -50, 50);
    }
    
    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(java.awt.Color.PINK);
        double scaleFactorX = gui.getZoom() / gui.getDomainStep(); //Scale factor for x values
        double scaleFactorY = gui.getZoom() / gui.getRangeStep(); //Scale factor for y values
        for (double x = gui.getMinDomain(); x < gui.getMaxDomain(); x += 0.01) {
            if (getValueAt(x, FunctionType.ORIGINAL) < gui.getMaxRange() && getValueAt(x, FunctionType.ORIGINAL) > gui.getMinRange()) {
                graphics.draw(new Line2D.Double(x * scaleFactorX + gui.getPlaneWidth() / 2, gui.getPlaneHeight() / 2 - getValueAt(x, FunctionType.ORIGINAL) * scaleFactorY,
                    (x + 0.01) * scaleFactorX + gui.getPlaneWidth() / 2, gui.getPlaneHeight() / 2 - getValueAt(x + 0.01, FunctionType.ORIGINAL) * scaleFactorY));
            }
        }
    }
    
    public abstract String getFirstDerivative();
    
    public abstract String getSecondDerivative();
    
    public abstract double getValueAt(double x, FunctionType functionType);
        
    
}
