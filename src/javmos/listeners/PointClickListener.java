package javmos.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.Point;

public class PointClickListener implements MouseListener {

    private final JavmosGUI gui;
    private final HashSet<Point> points;

    public PointClickListener(JavmosGUI gui) {
        this.gui = gui;
        this.points = new HashSet<>();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (!points.isEmpty()) {
            points.stream().filter((point) -> (point.pointClicked(event.getX(), event.getY()))).forEach((point) -> {
                gui.setPointLabel(point.toString(), point.getRootType());
            });
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Not needed!
    }

    public void setPoint(Point point) {
        points.add(point);
    }

    public HashSet<Point> getPoints() {
        return points;
    }
    
    public void removePoints() {
        points.clear();
    }
}
