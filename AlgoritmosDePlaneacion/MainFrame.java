import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private final Algoritmos P;
    private Grafica grafico;
    private JButton adicionarButton;
    private JButton algoritmo1Button;
    private JButton algoritmo2Button;
    private JButton algoritmo3Button;
    private JButton algoritmo4Button;
    private JLabel tiempoInicioLabel;
    private JLabel PrioridadLabel;
    private JLabel tiempoCpuLabel;
    private JTextField tiempoInicioTextField;
    private JTextField PrioridadTextField;
    private JTextField tiempoCpuTextField;
    private JTextField NombreProcTextField;
    private JLabel NombreProc;
    private JPanel aPanel;
    private JPanel bPanel;
    private JPanel cPanel;
    private JPanel dPanel;

    public MainFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        P = new Algoritmos();
        initComponents();
        configurarVentana();
    }

    public void initComponents() {
        setLayout(null);
        aPanel = new JPanel();
        bPanel = new JPanel();
        cPanel = new JPanel();
        dPanel = new JPanel();

        aPanel.setBorder(BorderFactory.createTitledBorder("Proceso"));
        aPanel.setBounds(5, 0, 100, 250);
        NombreProc = new JLabel("Nombre de Proceso");
        NombreProc.setFont(new Font(NombreProc.getName(), Font.PLAIN, 10));
        PrioridadLabel = new JLabel("Prioridad");
        PrioridadLabel.setFont(new Font(PrioridadLabel.getName(), Font.PLAIN, 10));
        tiempoInicioLabel = new JLabel("Tiempo de Inicio");
        tiempoInicioLabel.setFont(new Font(tiempoInicioLabel.getName(), Font.PLAIN, 10));
        tiempoCpuLabel = new JLabel("Tiempo de CPU");
        tiempoCpuLabel.setFont(new Font(tiempoCpuLabel.getName(), Font.PLAIN, 10));
        NombreProcTextField = new JTextField(7);
        PrioridadTextField = new JTextField(7);
        tiempoInicioTextField = new JTextField(7);
        tiempoCpuTextField = new JTextField(7);
        adicionarButton = new JButton("Agregar");
        adicionarButton.addActionListener(this);
        aPanel.add(NombreProc);
        aPanel.add(NombreProcTextField);
        aPanel.add(PrioridadLabel);
        aPanel.add(PrioridadTextField);
        aPanel.add(tiempoInicioLabel);
        aPanel.add(tiempoInicioTextField);
        aPanel.add(tiempoCpuLabel);
        aPanel.add(tiempoCpuTextField);
        aPanel.add(adicionarButton);

        bPanel.setBorder(BorderFactory.createTitledBorder("Gráfica"));
        bPanel.setBounds(5, 250, 720, 290);
        grafico = new Grafica();
        bPanel.add(grafico);

        cPanel.setBorder(BorderFactory.createTitledBorder("Tabla"));
        cPanel.setBounds(105, 0, 460, 250);
        JTable tabla = new JTable(new String[0][], new String[]{"Proceso", "Prioridad", "Inicio", "Tiempo cpu", "Fin", "T", "E", "I"});
        tabla.setPreferredScrollableViewportSize(new Dimension(440, 185));
        JScrollPane scrollPane = new JScrollPane(tabla);
        cPanel.add(scrollPane);

        dPanel.setBorder(BorderFactory.createTitledBorder("Algoritmos"));
        dPanel.setBounds(570, 0, 100, 250);
        algoritmo1Button = new JButton("F.I.F.O.");
        algoritmo2Button = new JButton("R.R.");
        algoritmo3Button = new JButton("S.J.F.");
        algoritmo4Button = new JButton("Prioridades");

        algoritmo1Button.addActionListener(this);
        algoritmo2Button.addActionListener(this);
        algoritmo3Button.addActionListener(this);
        algoritmo4Button.addActionListener(this);

        dPanel.add(algoritmo1Button);
        dPanel.add(algoritmo2Button);
        dPanel.add(algoritmo3Button);
        dPanel.add(algoritmo4Button);
        add(aPanel);
        add(bPanel);
        add(cPanel);
        add(dPanel);
    }

    public void configurarVentana() {
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 575) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - 470) / 2, 800, 640);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulador Planificación de Procesos");
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        JTable tabla;
        JScrollPane scrollPane;
        if (adicionarButton == ae.getSource()) {
            try {
                P.adicionarProceso(NombreProcTextField.getText(),Integer.parseInt(tiempoInicioTextField.getText()), Integer.parseInt(tiempoCpuTextField.getText()), Integer.parseInt(PrioridadTextField.getText()));
            } catch (Exception ignored) {
            }
            NombreProcTextField.setText("");
            tiempoInicioTextField.setText("");
            tiempoCpuTextField.setText("");
            PrioridadTextField.setText("");
            cPanel.setVisible(false);
            remove(cPanel);
            cPanel = new JPanel();
            cPanel.setBorder(BorderFactory.createTitledBorder("Tabla"));
            cPanel.setBounds(105, 0, 460, 250);
            tabla = new JTable(P.getDatosI(), new String[]{"Proceso", "Prioridad", "Inicio", "Tiempo cpu", "Fin", "T", "E", "I"});
            tabla.setPreferredScrollableViewportSize(new Dimension(440, 185));
            scrollPane = new JScrollPane(tabla);
            cPanel.add(scrollPane);
            add(cPanel);
        }

        if (algoritmo1Button == ae.getSource() && P.getNP() > 0) {
            P.fifo();
            cPanel.setVisible(false);
            remove(cPanel);
            cPanel = new JPanel();
            cPanel.setBorder(BorderFactory.createTitledBorder("Tabla"));
            cPanel.setBounds(105, 0, 460, 250);
            tabla = new JTable(P.getDatos(), new String[]{"Proceso", "Prioridad", "Inicio", "Tiempo cpu", "Fin", "T", "E", "I"});
            tabla.setPreferredScrollableViewportSize(new Dimension(440, 185));
            scrollPane = new JScrollPane(tabla);
            cPanel.add(scrollPane);
            add(cPanel);
            grafico.setVisible(false);
            grafico.setX(P.maximoY() - P.getIni()[0] + 1);
            grafico.setY(P.getNP());
            grafico.setD(P.getDatos2());
            grafico.repaint();
            grafico.setVisible(true);
        }

        if (algoritmo4Button == ae.getSource() && P.getNP() > 0) {
            P.prio();
            cPanel.setVisible(false);
            remove(cPanel);
            cPanel = new JPanel();
            cPanel.setBorder(BorderFactory.createTitledBorder("Tabla"));
            cPanel.setBounds(105, 0, 460, 250);
            tabla = new JTable(P.getDatos(), new String[]{"Proceso", "Prioridad", "Inicio", "Tiempo cpu", "Fin", "T", "E", "I"});
            tabla.setPreferredScrollableViewportSize(new Dimension(440, 185));
            scrollPane = new JScrollPane(tabla);
            cPanel.add(scrollPane);
            add(cPanel);
            grafico.setVisible(false);
            grafico.setX(P.maximoY() - P.getIni()[0] + 1);
            grafico.setY(P.getNP());
            grafico.setD(P.getDatos2());
            grafico.repaint();
            grafico.setVisible(true);
        }

        if (algoritmo2Button == ae.getSource()) {
            P.rr();
            cPanel.setVisible(false);
            remove(cPanel);
            cPanel = new JPanel();
            cPanel.setBorder(BorderFactory.createTitledBorder("Tabla"));
            cPanel.setBounds(105, 0, 460, 250);
            tabla = new JTable(P.getDatos(), new String[]{"Proceso", "Prioridad", "Inicio", "Tiempo cpu", "Fin", "T", "E", "I"});
            tabla.setPreferredScrollableViewportSize(new Dimension(440, 185));
            scrollPane = new JScrollPane(tabla);
            cPanel.add(scrollPane);
            add(cPanel);
            grafico.setVisible(false);
            grafico.setX(P.maximoY() - P.getIni()[0] + 1);
            grafico.setY(P.getNP());
            grafico.setD(P.getDatos2());
            grafico.repaint();
            grafico.setVisible(true);
        }

        if (algoritmo3Button == ae.getSource() && P.getNP() > 0) {
            P.sjf();
            cPanel.setVisible(false);
            remove(cPanel);
            cPanel = new JPanel();
            cPanel.setBorder(BorderFactory.createTitledBorder("Tabla"));
            cPanel.setBounds(105, 0, 460, 250);
            tabla = new JTable(P.getDatosOrd(), new String[]{"Proceso", "Prioridad", "Inicio", "Tiempo cpu", "Fin", "T", "E", "I"});
            tabla.setPreferredScrollableViewportSize(new Dimension(440, 185));
            scrollPane = new JScrollPane(tabla);
            cPanel.add(scrollPane);
            add(cPanel);
            grafico.setVisible(false);
            grafico.setX(P.maximoY() - P.getIniOrd()[0] + 1);
            grafico.setY(P.getNP());
            grafico.setD(P.getDatos2Ord());
            grafico.repaint();
            grafico.setVisible(true);
        }
    }

}