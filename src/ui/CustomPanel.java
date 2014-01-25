package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author jedabero
 */
public class CustomPanel extends JPanel
        implements MouseListener, ActionListener {

    private final Timer timer;
    private Dimension newSize;

    private int enterDelay;
    private int exitDelay;

    private Dimension start;
    private Dimension end;

    private boolean isInside;

    private int wstep;
    private int hstep;

    private JPanel parent;

    public CustomPanel(JPanel parent, int inDelay, int outDelay, Dimension start, Dimension end) {
        super();
        this.parent = parent;
        this.start = start;
        this.end = end;
        setPreferredSize(start);
        setBorder(BorderFactory.createLineBorder(Color.yellow));
        addMouseListener(this);
        timer = new Timer(20, this);
        timer.setInitialDelay(100);
        enterDelay = inDelay;
        exitDelay = outDelay;
    }

    /**
     *
     * @param duration duracion en milisegundos
     */
    public void calculate(int duration) {

        int tstep = duration / timer.getDelay();
        wstep = (newSize.width - getWidth()) / tstep;
        wstep = (wstep == 0) ? 1 : wstep;
        hstep = (newSize.height - getHeight()) / tstep;
        hstep = (hstep == 0) ? 1 : hstep;
        System.out.println(wstep + "+" + hstep);
    }

    public void redimension() {
        int wleft = Math.abs(newSize.width - getWidth());
        int hleft = Math.abs(newSize.height - getHeight());
        System.out.println(wleft + "," + hleft);

        if ((wleft <= Math.abs(wstep)) && (getWidth() != newSize.width)) {
            setSize(newSize.width, getHeight());
        } else if ((hleft <= Math.abs(hstep)) && (getHeight() != newSize.height)) {
            setSize(getWidth(), newSize.height);
        } else {
            setSize(getWidth() + wstep, getHeight() + hstep);
        }

        if ((getWidth() == newSize.width) && (getHeight() == newSize.height)) {
            timer.stop();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked at (" + e.getX() + "," + e.getY() + ")");
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed at (" + e.getX() + "," + e.getY() + ")");
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released at (" + e.getX() + "," + e.getY() + ")");
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered at (" + e.getX() + "," + e.getY() + ")");
        isInside = true;
        newSize = end;
        calculate(enterDelay);
        if (timer.isRunning()) {
            timer.stop();
            timer.restart();
        } else {
            timer.start();
        }
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Left at (" + e.getX() + "," + e.getY() + ")");
        isInside = false;
        newSize = start;
        calculate(exitDelay);
        if (timer.isRunning()) {
            timer.stop();
            timer.restart();
        } else {
            timer.start();
        }
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        redimension();
        //this.repaint();
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getStringLen(String s) {
        return (int) this.getGraphics().getFontMetrics().getStringBounds(s, this.getGraphics()).getWidth();
    }

    public int getFontHeight() {
        return this.getGraphics().getFontMetrics().getHeight();
    }

    public void setEnterDelay(int enterDelay) {
        this.enterDelay = enterDelay;
    }

    public void setExitDelay(int exitDelay) {
        this.exitDelay = exitDelay;
    }

    public void setStart(Dimension start) {
        this.start = start;
    }

    public void setEnd(Dimension end) {
        this.end = end;
    }

    public boolean isInside() {
        return isInside;
    }

}
