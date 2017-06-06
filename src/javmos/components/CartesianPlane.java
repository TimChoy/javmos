/* Tim, Shalom, Ravindu
 * ICS4U
 * Javmos GUI
 * Jan. 30th, 2017
*/
package javmos.components;

import java.text.DecimalFormat;
import javmos.JavmosGUI;

public class CartesianPlane extends JavmosComponent {

    public CartesianPlane(JavmosGUI gui) {
        super(gui);
    }

    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        DecimalFormat df = new DecimalFormat("###.#");
        graphics2D.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, (int) (0.4 * gui.getZoom())));
        graphics2D.setStroke(new java.awt.BasicStroke(1));
        //Middle to right; Middle to bottom
        for (int xy = gui.getPlaneWidth() / 2; xy <= gui.getPlaneWidth(); xy += gui.getZoom()) { 
            graphics2D.drawLine(xy, 0, xy, gui.getPlaneHeight());
            if ((xy - (gui.getPlaneWidth() / 2)) / gui.getZoom() != 0)
                graphics2D.drawString("" + df.format(gui.getDomainStep() * (xy - (gui.getPlaneWidth() / 2)) / gui.getZoom()), xy, gui.getPlaneHeight() / 2 - 4);
            graphics2D.drawLine(0, xy, gui.getPlaneWidth(), xy);
            if ((xy - (gui.getPlaneHeight() / 2)) / gui.getZoom() != 0)
                graphics2D.drawString("" + df.format(gui.getRangeStep() * -(xy - (gui.getPlaneHeight() / 2)) / gui.getZoom()), gui.getPlaneWidth() / 2 + 4, xy);
        }
        //Middle to left; middle to top
        for (int xy = gui.getPlaneWidth() / 2; xy >= 0; xy -= gui.getZoom()) {
            graphics2D.drawLine(xy, 0, xy, gui.getPlaneHeight());
            if ((xy - (gui.getPlaneWidth() / 2)) / gui.getZoom() != 0)
                graphics2D.drawString("" + df.format(gui.getDomainStep() * (xy - (gui.getPlaneWidth() / 2)) / gui.getZoom()), xy, gui.getPlaneHeight() / 2 - 4);
            graphics2D.drawLine(0, xy, gui.getPlaneWidth(), xy);
            if ((xy - (gui.getPlaneHeight() / 2)) / gui.getZoom() != 0)
                graphics2D.drawString("" + df.format(gui.getRangeStep() * -(xy - (gui.getPlaneHeight() / 2)) / gui.getZoom()), gui.getPlaneWidth() / 2 + 4, xy);
        }
        graphics2D.setStroke(new java.awt.BasicStroke(3));
        graphics2D.drawLine(gui.getPlaneWidth() / 2, 0, gui.getPlaneWidth() / 2, gui.getPlaneHeight());
        graphics2D.drawLine(0, gui.getPlaneHeight() / 2, gui.getPlaneWidth(), gui.getPlaneHeight() / 2);
    }    
}
