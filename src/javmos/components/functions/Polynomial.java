/* Tim, Shalom, Ravindu
 * ICS4U
 * Javmos GUI
 * Jan. 30th, 2017
*/
package javmos.components.functions;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javmos.JavmosGUI;
import javmos.enums.FunctionType;
import javmos.exceptions.PolynomialException;

public class Polynomial extends Function {

    private int[] degrees;
    private double[] coefficients;

    public Polynomial(JavmosGUI gui, String polynomial) {
        super(gui);

        try {
            int index = 0;
            StringBuilder poly = new StringBuilder();
            poly.append(polynomial);
            polynomial = poly.delete(0, poly.indexOf("=") + 1).toString();
            if (polynomial.equals("+") || polynomial.equals("-")) {
                throw new PolynomialException();
            }
            if (polynomial.charAt(0) == ('-')) {
                index = polynomial.indexOf("-", index) + 1;
            }
            while (polynomial.indexOf("-", index) != -1) { //While there are still '-'s in the polynomial
                polynomial = poly.insert(polynomial.indexOf("-", index), "+").toString();
                index = polynomial.indexOf("-", index) + 1; //Start from next index
            }
            ArrayList<String> pArrayList = new ArrayList<>(Arrays.asList(polynomial.split("\\+"))); //ArrayList used to edit array
            if (pArrayList.get(0).equals("")) {
                pArrayList.remove(0);
            }
            String pArray[] = Arrays.copyOf(pArrayList.toArray(), pArrayList.toArray().length, String[].class);
            coefficients = new double[pArray.length];
            degrees = new int[pArray.length];
            for (int i = 0; i < pArray.length; i++) { // Sorts terms into respective arrays
                if (pArray[i].equals("x")) {
                    coefficients[i] = 1.0;
                    degrees[i] = 1;
                } else if (pArray[i].equals("-x")) {
                    coefficients[i] = -1.0;
                    degrees[i] = 1;
                } else if (pArray[i].contains("x")) {
                    String[] term = pArray[i].split("x");
                    switch (term[0]) {
                        case "-":
                            coefficients[i] = -1.0;
                            break;
                        case "":
                            coefficients[i] = 1.0;
                            break;
                        default:
                            coefficients[i] = Double.parseDouble(term[0]);
                            break;
                    }
                    degrees[i] = (term.length == 2) ? Integer.parseInt(term[1].substring(1)) : 1;
                } else {
                    coefficients[i] = Double.parseDouble(pArray[i]);
                    degrees[i] = 0;
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(gui.frame, polynomial + " is not a valid function!",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override
    public String getFirstDerivative() {
        double[] derivCoefficients = new double[coefficients.length];
        int[] derivDegrees = new int[degrees.length];
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0 && (degrees[i] != 0)) {
                derivCoefficients[i] = coefficients[i] * degrees[i];
                derivDegrees[i] = degrees[i] - 1;
            }
        }
        String equation = "";
        for (int i = 0; i < derivCoefficients.length; i++) {
            if (derivDegrees[i] < 0 || (Math.abs(derivCoefficients[i]) == 1.0 && derivDegrees[i] != 0)) {
                equation += "";
            } else if ((derivCoefficients[i] < 0 || i == 0) || Math.abs(derivCoefficients[i]) == 1.0) { // If coefficient is negative
                equation += derivCoefficients[i];
            } else if (derivCoefficients[i] > 0 && i != 0) { // If coefficient is positive
                equation += "+" + derivCoefficients[i];
            }

            if (derivDegrees[i] == 1) { //Add degree according to degree value
                equation += "x";
            } else if (derivDegrees[i] > 1) {
                equation += "x^" + derivDegrees[i];
            }
        }
        return equation;
    }

    @Override
    public String getSecondDerivative() {
        return new Polynomial(gui, getFirstDerivative()).getFirstDerivative();
    }
    
    @Override
    public double getValueAt(double x, FunctionType functionType) {
        Polynomial p = null;
        if (functionType != null) switch (functionType) {
            case FIRST_DERIVATIVE:
                p = new Polynomial(gui, getFirstDerivative());
                break;
            case SECOND_DERIVATIVE:
                p = new Polynomial(gui, getSecondDerivative());
                break;
            case THIRD_DERIVATIVE:
                Polynomial j = new Polynomial(gui, getFirstDerivative());
                p = new Polynomial(gui, j.getSecondDerivative());
                break;
            default:
                p = this;
                break;
        }
        double[] coefficient = p.coefficients;
        int[] degree = p.degrees;
        double value = 0;
        for (int i = 0; i < coefficient.length; i++) {
            if (degree[i] < 0) {
                value += 0;
            } else if (degree[i] == 0) {
                value += coefficient[i];
            } else {
                value += coefficient[i] * Math.pow(x, degree[i]);
            }
        }
        return value;
    }
}