package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import io.IO;
import matr.Asignatura;
import ui.editores.AsignaturaEditor;

/**
 *
 * @author Jedabero
 */
public class AsignPanel extends JPanel implements MouseListener {

    private Asignatura asign;

    private boolean inside;

    private final Polygon tick;
    private final Point tickLoc;

    private int txth;

    private final Color defaultColor;

    public AsignPanel(final PeriodPanel parent, Asignatura a) {
        //super(parent, 400, 150, new Dimension(150, 110), new Dimension(210, 170));
        super();
        setPreferredSize(new Dimension(150, 110));
        this.asign = a;
        setBorder(BorderFactory.createLineBorder(Color.yellow));

        tickLoc = new Point(120, txth);
        tick = new Polygon(
                new int[]{tickLoc.x + 1, tickLoc.x + 3, tickLoc.x + 6, tickLoc.x + 14, tickLoc.x + 16, tickLoc.x + 6},
                new int[]{tickLoc.y + 8, tickLoc.y + 6, tickLoc.y + 9, tickLoc.y + 1, tickLoc.y + 3, tickLoc.y + 13},
                6);
        addMouseListener(this);
        defaultColor = parent.getBackground();
        setToolTipText(a.toString());

        JPopupMenu jpm = new JPopupMenu("menu");
        JMenuItem jm0 = new JMenuItem("Editar");
        jm0.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent aev) {
                AsignaturaEditor ae = new AsignaturaEditor(asign, parent.getPeriodo());
                int r = JOptionPane.showConfirmDialog(null, ae, "Title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (r == JOptionPane.OK_OPTION) {
                    try {
                        asign = ae.getA();
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Error en Valor de Nota", "ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);
                    } catch (NullPointerException npe) {
                        JOptionPane.showMessageDialog(null, "Error en Valor de Nota\n" + npe.getMessage(), "VACIO", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "VACIO", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    System.out.println("No");
                }
                repaint();
            }
        });
        jpm.add(jm0);

        JMenuItem jm1 = new JMenuItem("Matricular");
        jm1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (asign.puedeMatr()) {
                    
                } else {
                    StringBuilder mes = new StringBuilder("No puede matricular " );
                    mes.append(asign.getNombre()).append(IO.LS.p).append("Razón: ");
                    if (asign.isAprobada()) {
                        mes.append("Ya está aprobada.");
                    } else {
                        
                    }
                    JOptionPane.showMessageDialog(null, mes, "Matricula", JOptionPane.ERROR_MESSAGE);
                }
                repaint();
            }
        });
        jpm.add(jm1);

        setComponentPopupMenu(jpm);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //Cambia color de fondo cuando el mouse entre o salga.
        if (!inside) {
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            setBackground(defaultColor);
        } else {
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
            setBackground(defaultColor.brighter());
        }

        txth = g.getFontMetrics().getHeight();//Altura de las String

        //Cambia color de fondo de acuerdo al estado de la asignatura
        if (!asign.puedeMatr() && !asign.isAprobada()) {
            setBackground(new Color(200, 200, 200));
            g2d.setColor(Color.blue);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(tickLoc.x, tickLoc.y + 2, 10, 10);
            g2d.setStroke(new BasicStroke(1));
            g2d.setColor(Color.black);
        }
        //Dibujar indicador de matriculada.
        if (asign.isMatriculada()) {
            g2d.setColor(Color.blue);
            g2d.fillOval(tickLoc.x, tickLoc.y + 1, 12, 12);
        } else if (asign.puedeMatr()) {
            g2d.setColor(Color.green);
            g2d.fillOval(tickLoc.x, tickLoc.y + 1, 12, 12);
        }
        g2d.setColor(Color.black);

        //Dibujar chulito de aprobada.
        if (asign.isAprobada()) {
            g2d.drawPolygon(tick);
            g2d.setColor(Color.green);
            g2d.fillPolygon(tick);
            g2d.setColor(Color.black);
        }

        String cod = asign.getCodigo();
        g2d.drawString(cod, 5, txth);

        String cred = "Cred. " + asign.getCreditos();
        g2d.drawString(cred, 30 + getStringLen(cod), txth);

        String nom = asign.getNombre();
        int y = getHeight() / 2 - txth;
        int lineLen = getStringLen(nom);

        if (lineLen > getWidth()) {
            int mid = nom.indexOf(" ", nom.indexOf(" ") + 1);
            String nom1 = nom.substring(0, mid);
            int len1 = getStringLen(nom1);
            g2d.drawString(nom1, (getWidth() / 2) - (len1 / 2), y);
            String nom2 = nom.substring(mid + 1, nom.length());
            int len2 = getStringLen(nom2);
            g2d.drawString(nom2, (getWidth() / 2) - (len2 / 2), y += txth);
        } else {
            g2d.drawString(nom, (getWidth() / 2) - (lineLen / 2), y);
            y += txth;
        }

        if (0d != asign.getNota()) {
            g2d.drawString("Calif.: " + asign.getNota(), 5, y += txth);
        }
        if (null != asign.getRequisito()) {
            g2d.drawString("Req.: " + asign.getRequisito().getCodigo(), 5, y += txth);
        }
        if (null != asign.getCorequisito()) {
            g2d.drawString("Coreq.: " + asign.getCorequisito().getCodigo(), 5, y += txth);
        }
        g2d.drawString("Periodo: " + asign.getPeriodo(), 5, y += txth);
        g2d.drawString("Nivel: " + asign.getNivel(), 90, y);

    }

    public void setAsignatura(Asignatura asign) {
        this.asign = asign;
    }

    public Asignatura getAsignatura() {
        return asign;
    }

    public int getStringLen(String s) {
        return (int) this.getGraphics().getFontMetrics().getStringBounds(s, this.getGraphics()).getWidth();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("c" + e.getButton());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("p" + e.getButton());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        inside = true;
        repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        inside = false;
        repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
