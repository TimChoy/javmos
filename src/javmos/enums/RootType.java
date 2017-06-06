/* Tim, Shalom, Ravindu
 * ICS4U
 * Javmos GUI
 * Jan. 30th, 2017
*/
package javmos.enums;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.Point;
import javmos.components.functions.Function;

public enum RootType {
    
    X_INTERCEPT(Color.GREEN, "X-INT:", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.RED, "CP:", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.BLUE, "IP:", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);

    public final int ATTEMPTS = 15;
    public final String rootName;
    public final FunctionType functionOne;
    public final FunctionType functionTwo;
    public final Color rootColor;
    

    RootType(Color rootColor, String rootName, FunctionType functionOne, FunctionType functionTwo) {
        this.rootColor = rootColor;
        this.rootName = rootName;
        this.functionOne = functionOne;
        this.functionTwo = functionTwo;
    }

    public Color getRootColor() {
        return rootColor;
    }

    public String getRootName() {
        return rootName;
    }
    
    public java.util.HashSet<Point> getRoots(JavmosGUI gui, Function function, double minDomain, double maxDomain) {
       DecimalFormat df = new DecimalFormat("##.###");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        java.util.HashSet set = new HashSet();
        double step = (gui.getDomainStep() < 0) ? -gui.getDomainStep() : gui.getDomainStep(); //Handles negative domain steps
        for (double x = minDomain; x < maxDomain; x += 0.1 * step) { //Finds roots between the minimum and maximum domains
            try {
                double root = newtonsMethod(function, x, ATTEMPTS);
                Point p = new Point(gui, this, Double.parseDouble(df.format(root)), Double.parseDouble(df.format(function.getValueAt(root, FunctionType.ORIGINAL))));
                set.add(p);
            } catch (NullPointerException e) { // Catches errors in Newton's method
            }
        }
        return set;
    }

    private Double newtonsMethod(Function function, double guess, int attempts) {
        if (Math.abs(function.getValueAt(guess, functionOne) / (function.getValueAt(guess, functionTwo))) < 0.00000001) {
            return guess;
        }
        if (attempts == 0) {
            return null;
        }
        return newtonsMethod(function, guess - (function.getValueAt(guess, functionOne) / function.getValueAt(guess, functionTwo)), attempts - 1);
    }
}
