package view;

// Librerias para manejo de GUI en Java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

//Libreria para manejo de archivos
import java.io.File;

//Libreria para manejo de excepciones de la DB
import java.sql.SQLException;

// Importar el Controlador y el JDBCUtilities
import controller.Controlador;
import util.JDBCUtilities;

/*  CREACIÓN DE UNA INTERFAZ GRÁFICA (GUI) SENCILLA PARA MOSTRAR LOS DISTINTOS 
    DATOS DE LAS CONSULTAS EN UNA VENTANA MEDIANTE TABLAS (TIPO JTable) */

public class VistaGUI extends JFrame implements ActionListener {
    // Constantes
    private static final ImageIcon BGI_FILE = new ImageIcon("./resources/bgi.jpg");
    private static final int WIDTH = BGI_FILE.getIconWidth() + 20;
    private static final Dimension GUI_SIZE = new Dimension(WIDTH, BGI_FILE.getIconHeight() + 90);
    private static final Controlador control = new Controlador();
    private static final String[][] COL_HEADER = { { "" }, { "Nombre", "Salario" },
            { "Nombre", "Apellidos", "Salario", "ISR" }, { "Nombre del lider", "Constructora", "Numero de baños" } };
    private static final Color[] PC_COLORS = { new Color(224, 243, 213), new Color(239, 228, 176),
            new Color(255, 224, 183), new Color(227, 255, 255) };
    private static final int N_QUERIES = 3;

    // Atributos
    private Container cp;
    private JLabel status;
    private JLabel bgi;
    private JLabel enunciado;
    private JLabel cantRegistros;
    private JMenuItem elegirDB;
    private JMenuItem salir;
    private JMenuItem limpiaConsulta;
    private JMenuItem about;
    private JMenuItem[] consulta = new JMenuItem[N_QUERIES];
    private JPanel panelCentral;
    private JTable tabla;
    private JScrollPane scrollpane;
    private boolean dbValida = false;
    private String rutaActual = new File(JDBCUtilities.getRouteDB()).getAbsolutePath();

    // Constructor
    public VistaGUI(String titulo) {
        super(titulo);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.cp = this.getContentPane();
        this.addMenuBar();
        this.addPanelCentral();
        this.addPanelInf();
        this.setQueryVisible(false);
        this.setLocationRelativeTo(null);
    }

    // Métodos

    public static void inicializarGUI() {
        VistaGUI app = new VistaGUI("SISTEMA DE CONSULTAS - F.M.C. Constructores");
        JOptionPane.showMessageDialog(app, new JLabel(
                "<html><body><center><h3>==  BIENVENIDO/A AL SISTEMA DE CONSULTAS EN BASE DE DATOS  ==</h3></center><br>"
                        + "<i>Una vez dentro del sistema, diríjase a la opción 'Elegir/Cambiar Archivo de BD' del menú 'Archivo' para<br>"
                        + "seleccionar la correspondiente base de datos del proyecto de construcción...</i><br><br>"
                        + "• El sistema se encargará de comprobar automáticamente si la base de datos elegida es válida o no<br>"
                        + "• Si no elige una tendrá que hacerlo más tarde o de lo contrario no podrá realizar ninguna consulta<br>"
                        + "• Sugerencia: El nombre por defecto del respectivo archivo de la BD es «ProyectosConstruccion.db»</body></html>"),
                "¡Bienvenido! - F.M.C. Constructores", JOptionPane.PLAIN_MESSAGE);
        app.setVisible(true);
    }

    private void addPanelInf() {
        JPanel panelInf = new JPanel();
        status = new JLabel();
        panelInf.add(status);
        cp.add(panelInf, BorderLayout.PAGE_END);
    }

    private void addPanelCentral() {
        panelCentral = new JPanel();

        bgi = new JLabel();
        enunciado = new JLabel();
        cantRegistros = new JLabel();

        tabla = new JTable();
        scrollpane = new JScrollPane(tabla);
        tabla.setEnabled(false);

        panelCentral.add(bgi);
        panelCentral.add(enunciado);
        panelCentral.add(scrollpane);
        panelCentral.add(cantRegistros);
        cp.add(panelCentral, BorderLayout.CENTER);
    }

    private void addMenuBar() {
        JMenu archivo = new JMenu("Archivo");
        elegirDB = new JMenuItem("Elegir/Cambiar Archivo de BD");
        salir = new JMenuItem("Salir del programa");
        archivo.add(elegirDB);
        archivo.add(salir);
        elegirDB.addActionListener(this);
        salir.addActionListener(this);

        JMenu consultas = new JMenu("Consultas");
        for (int i = 0; i < N_QUERIES; i++) {
            consulta[i] = new JMenuItem("Realizar Consulta #" + (i + 1));
            consultas.add(consulta[i]);
            consulta[i].addActionListener(this);
        }
        limpiaConsulta = new JMenuItem("Limpiar consultas");
        consultas.add(limpiaConsulta);
        limpiaConsulta.addActionListener(this);

        JMenu ayuda = new JMenu("Ayuda");
        about = new JMenuItem("Acerca de...");
        ayuda.add(about);
        about.addActionListener(this);

        JMenuBar mb = new JMenuBar();
        mb.add(archivo);
        mb.add(consultas);
        mb.add(ayuda);
        cp.add(mb, BorderLayout.PAGE_START);
    }

    private void setQueryVisible(boolean estado) {
        this.enableQueries();
        scrollpane.setVisible(estado);
        enunciado.setVisible(estado);
        cantRegistros.setVisible(estado);
        limpiaConsulta.setEnabled(estado);
        bgi.setVisible(!estado);
        if (bgi.isVisible()) {
            panelCentral.setBackground(PC_COLORS[0]);
            bgi.setIcon(BGI_FILE);
            this.setSize(GUI_SIZE);
        }
    }

    private void enableQueries() {
        String ruta = "NINGUNA";
        for (int i = 0; i < N_QUERIES; i++)
            consulta[i].setEnabled(dbValida);
        if (dbValida) {
            String dirPadre = new File(rutaActual).getParentFile().getName();
            ruta = "../" + (dirPadre.equals("") ? "" : dirPadre + "/") + new File(rutaActual).getName();
        }
        status.setText("BD actual:  <" + ruta + ">");
    }

    private void generateQuery(int idConsulta) throws SQLException {
        Object[][] datos = control.crearDatosTabla(idConsulta);
        String textoEnunciado = retornarEnunciado(idConsulta);
        int nRegistros = datos.length;
        int alto = 16 * nRegistros;
        alto = alto > 240 ? 240 : alto;

        tabla.setModel(new DefaultTableModel(datos, COL_HEADER[idConsulta]));
        tabla.setPreferredScrollableViewportSize(new Dimension(WIDTH - 200, alto));
        enunciado.setText(textoEnunciado);
        cantRegistros
                .setText("<html><body><br>Total de registros encontrados: <u>" + nRegistros + "</u><br></body></html>");

        panelCentral.setBackground(PC_COLORS[idConsulta]);
        this.setQueryVisible(true);
        this.setSize(WIDTH, alto + 200 + 16 * (textoEnunciado.split("<br>").length));
    }

    private void chooseDB(String rutaInicial) {
        try {
            JFileChooser jf = new JFileChooser(rutaInicial);
            jf.setDialogTitle("Elegir/Cambiar Archivo de BD");
            jf.setMultiSelectionEnabled(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de base de datos de SQLite (*.db)",
                    "db");
            jf.setFileFilter(filter);
            int opcion = jf.showOpenDialog(this);

            File f = jf.getSelectedFile();
            if (opcion == JFileChooser.APPROVE_OPTION) {
                rutaInicial = f.getAbsolutePath();
                JDBCUtilities.setRouteDB(rutaInicial);

                control.realizarConsulta(1);
                if (!rutaInicial.equals(rutaActual)) {
                    rutaActual = f.getAbsolutePath();
                    dbValida = true;
                    this.setQueryVisible(false);
                    JOptionPane.showMessageDialog(this,
                            "¡La base de datos seleccionada es correcta!\n\n"
                                    + "El sistema está preparado para realizar las consultas",
                            "Base de datos válida", JOptionPane.NO_OPTION);
                }
            } else
                throw new Exception();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "¡El archivo seleccionado no es válido o no contiene "
                            + "datos válidos para efectuar las consultas!\n\n"
                            + "Por favor, asegúrese de elegir un archivo de base de datos adecuado",
                    "Error de acceso - Archivo incorrecto", JOptionPane.ERROR_MESSAGE);
            if (dbValida)
                JDBCUtilities.setRouteDB(rutaActual);
            chooseDB(rutaInicial);
        } catch (Exception e) {
            if (!dbValida) {
                JOptionPane.showMessageDialog(null,
                        "¡Ha decidido no seleccionar ningún archivo de base de datos!"
                                + "\n\nPor favor, no olvide seleccionar una base de datos más adelante"
                                + "\no de lo contrario no podrá realizar ninguna consulta",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                this.setQueryVisible(false);
            }
        }
    }

    private String retornarEnunciado(int idConsulta) {
        String enunciado = "<html><body><h2><center><u>Tabla de datos - Consulta #" + idConsulta
                + "</u></center></h2><br><i>";
        switch (idConsulta) {
            case 1:
                enunciado += "Seleccione el nombre y el salario de los líderes que se encuentran en la ciudad de “Bogota”";
                break;
            case 2:
                enunciado += "Seleccione el nombre, salario, el impuesto sobre la renta del salario en una columna que se llame"
                        + "<br>“isr” y los dos apellidos concatenados y separados por un espacio en otra columna de nombre “ape”"
                        + "<br><br>• Solo seleccione los registros que tengan un salario mayor a 10000"
                        + "<br>• El isr es el 16% del salario";
                break;
            case 3:
                enunciado += "Seleccione el constructor, el número de baños y el nombre del líder de las construcciones que tengan"
                        + "<br>un id entre 5 y 17, incluyendo los extremos";
                break;
            default:
                return "";
        }
        return enunciado + "</i><br><br></body></html>";
    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == elegirDB) {
            this.chooseDB(rutaActual);
        }
        if (ev.getSource() == salir) {
            this.setQueryVisible(false);
            JOptionPane.showMessageDialog(this,
                    "Gracias por usar el sistema de consultas del proyecto de construcción\n\n¡Hasta una próxima oportunidad!",
                    "Salir del sistema - F.M.C. Constructores", JOptionPane.NO_OPTION);
            System.exit(0);
        }
        if (ev.getSource() == limpiaConsulta) {
            this.setQueryVisible(false);
        }
        if (ev.getSource() == about) {
            JOptionPane.showMessageDialog(this,
                    new JLabel("<html><body><h3>== Sistema de Consultas <i>(ver. 1.1)</i> ==<br></h3>"
                            + "<center><h2>¯\\_( ❛︡ ͜ʖ ❛︠ )_/¯</h2></center><br>"
                            + "<u>Aplicación desarrollada por</u>:<br><br>"
                            + "FABIÁN MAURICIO MORENO CAMARGO<br>Tripulante - Grupo 49<br>"
                            + "Misión TIC UTP<br>2021</center></body></html>"),
                    "Acerca de...", JOptionPane.NO_OPTION);

        }
        try {
            if (ev.getSource() == consulta[0]) {
                this.generateQuery(1);
            }
            if (ev.getSource() == consulta[1]) {
                this.generateQuery(2);
            }
            if (ev.getSource() == consulta[2]) {
                this.generateQuery(3);
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this,
                    "¡Ha ocurrido algo inesperado al intentar acceder o conectar con la base de datos!\n\n*** "
                            + exc.getMessage() + " ***\n\n" + "Por favor, intente lo siguiente:\n\n"
                            + "• Revise que la base de datos elegida exista y sea una válida para el proyecto\n"
                            + "• Intente elegir/cambiar el archivo de base de datos por otro distinto (que sea\n"
                            + "   válido) usando la opción que aparece dentro del menú 'Archivo' del sistema\n"
                            + "• O en última instancia, contacte al soporte técnico del sistema",
                    "Error de acceso/conexión con la DB", JOptionPane.ERROR_MESSAGE);
            dbValida = false;
            this.setQueryVisible(false);
        }
    }
}