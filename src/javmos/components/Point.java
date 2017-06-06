/* Tim, Shalom, Ravindu
 * ICS4U
 * Javmos GUI
 * Jan. 30th, 2017
*/
package javmos.components;

import javmos.JavmosGUI;
import javmos.enums.RootType;

public class Point extends JavmosComponent implements Comparable<Point> {

    private final int RADIUS = 5;
    private final RootType type;
    private double x, y;
    private final java.awt.geom.Ellipse2D.Double point = new java.awt.geom.Ellipse2D.Double();
    
    public Point(JavmosGUI gui, RootType type, double x, double y) {
        super(gui);
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }

    public RootType getRootType() {
        return type;
    }

    @Override
    public int hashCode() { //Creates a unique hashcode for each Point
        int hash = 2;
        for (int i = 0; i < toString().length(); i++) {
            hash += (int) toString().charAt(i) * x;
        }
        hash += type.ordinal();
        return hash;
    }

   @Override
    public boolean equals(Object object) { //If the hashcodes are equal, the points are the same
        return getClass().hashCode() == object.getClass().hashCode();
    }

    @Override
    public int compareTo(Point point) {
        if (x > point.x) {
            return 1;
        } else if (x < point.x) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return type + ": (" + x + ", " + y + ")";
    }

    public boolean pointClicked(int x, int y) {
        return point.contains(x, y);
    }
    
    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        point.setFrameFromCenter(x * gui.getZoom() / gui.getDomainStep() + gui.getPlaneWidth() / 2, y * -gui.getZoom() / gui.getRangeStep() + gui.getPlaneHeight() / 2,
                x * gui.getZoom() / gui.getDomainStep() + gui.getPlaneWidth() / 2 + gui.getZoom() / 8, y * -gui.getZoom() / gui.getRangeStep() + gui.getPlaneHeight() / 2 + gui.getZoom() / 8);
        graphics2D.setColor(type.getRootColor());
        graphics2D.draw(point);
        graphics2D.fill(point);
    }
}
