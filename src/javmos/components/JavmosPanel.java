/* Tim, Shalom, Ravindu
 * ICS4U
 * Javmos GUI
 * Jan. 30th, 2017
*/
package javmos.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JPanel;
import javmos.JavmosGUI;
import javmos.components.functions.Function;
import javmos.listeners.PointClickListener;

public class JavmosPanel extends JPanel {

    private final JavmosGUI gui;
    public final LinkedList <JavmosComponent> components;

    public JavmosPanel(JavmosGUI gui) {
        this.gui = gui;
        this.components = new LinkedList();
    }

    public void setPlane(CartesianPlane plane) {
        components.addFirst(plane);
    }

    public void setFunction(Function function) {
        components.add(function);
        PointClickListener mListen = (PointClickListener) getListeners(MouseListener.class)[0];
        mListen.removePoints();
        HashSet[] set = {function.getXIntercepts(), function.getCriticalPoints(), function.getInflectionPoints()};
        for (HashSet h : set) {
            for (int i = 0; i < h.size(); i++) {
                components.add((Point) h.toArray()[i]);
                mListen.setPoint((Point) h.toArray()[i]);
            }
        }
    }
    
    public Function getFunction() {
        for (JavmosComponent j : components) {
            if (j instanceof Function) {
                return (Function) j;
            }
        } 
        return null;
    }

    @Override
    public void paintComponent(Graphics graphics) { //Draws the main components
        super.paintComponent(graphics);
        setPlane(new CartesianPlane(gui));
        for (int i = 0; i < components.size(); i++) {
            components.get(i).draw((Graphics2D) graphics);
        }
    }    
}
