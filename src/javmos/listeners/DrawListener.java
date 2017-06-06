package javmos.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javmos.JavmosGUI;
import javmos.components.JavmosPanel;
import javmos.components.functions.*;

public class DrawListener implements ActionListener {

    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public DrawListener(JavmosGUI gui, JavmosPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            String s = gui.getEquationField();
            Function function;
            if (s.contains("sin")) {
                function = new Sine(gui, s);
            } else if (s.contains("cos")) {
                function = new Cosine(gui, s);
            } else if (s.contains("tan")) {
                function = new Tangent(gui, s);
            } else if (s.contains("ln") || s.contains("log")) {
                function = new Logarithmic(gui, s);
            } else {
                function = new Polynomial(gui, s);
            }
            panel.components.clear();
            panel.setFunction(function);
            panel.repaint();
            gui.setFirstDerivativeLabel("f'(x) = " + panel.getFunction().getFirstDerivative());
            gui.setSecondDerivativeLabel("f''(x) = " + panel.getFunction().getSecondDerivative());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Function Error", JOptionPane.ERROR_MESSAGE);
        }
        panel.repaint();
    }
}
