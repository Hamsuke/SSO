import java.awt.*;
public class Grafica extends Canvas {

    private final Algoritmos A;

    private int x;
    private int y;
    private int[][] D;
    private boolean sw = false;

    public Grafica(Algoritmos p) {
        A = p;
        setSize(500, 250);
        setBackground(hexToRgb("#ffffff"));
    }

    public void paint(Graphics G) {
        Graphics2D g = (Graphics2D) G;
        Color[] colores = new Color[]{
                hexToRgb("#F44336"),
                hexToRgb("#FF9800"),
                hexToRgb("#00BCD4"),
                hexToRgb("#E91E63"),
                hexToRgb("#2196F3"),
                hexToRgb("#673AB7")
        };
        if (sw) {
            g.setStroke(new BasicStroke(1.5F));
            g.drawLine(20, 0, 20, 230);
            g.drawLine(20, 230, 500, 230);
            g.setStroke(new BasicStroke(1.0F));
            int im = 480 / (2 * x) + 20;

            int c;
            int indey;
            for (c = 0; c < x; ++c) {
                indey = 480 / x;
                g.drawLine(im + c * indey, 232, im + c * indey, 228);
                g.drawString(String.valueOf(D[0][0] + c), im + c * indey - 4, 245);
            }

            g.setFont(new Font("Arial", 1, 15));
            c = 65;
            indey = 230 / (2 * y);

            int cont = 0;
            int n;
            int i;
            for (n = y - 1; n >= 0; --n) {
                i = 230 / y;
                g.drawLine(18, indey + n * i, 22, indey + n * i);
                g.setColor(colores[(y - n + 5) % 6]);
                g.drawString(A.getName(cont), 5, indey + n * i + 5);
                g.setColor(Color.black);
                ++c;
                cont++;
            }

            im = 480 / (2 * x) + 20;
            indey = 230 / (2 * y);
            n = y - 1;

            for (i = 0; i < y; ++i) {
                int w = 480 / x;
                int ww = 230 / y;
                g.setColor(Color.yellow);
                g.setStroke(new BasicStroke(3.0F, 1, 0, 3.0F, new float[]{10.0F, 4.0F}, 10.0F));
                g.drawLine(im + (D[i][0] - D[0][0]) * w, indey + n * ww, im + (D[i][0] + D[i][4] - D[0][0]) * w, indey + n * ww);
                g.setColor(colores[i % 6]);
                g.setStroke(new BasicStroke(3.5F));
                g.drawLine(im + (D[i][0] + D[i][4] - D[0][0]) * w, indey + n * ww, im + (D[i][2] - D[0][0]) * w, indey + n * ww);
                g.drawLine(im + (D[i][2] - D[0][0]) * w, indey + n * ww + 3, im + (D[i][2] - D[0][0]) * w, indey + n * ww);
                --n;
            }
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setD(int[][] d) {
        D = d;
        sw = true;
    }

    public static Color hexToRgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

}