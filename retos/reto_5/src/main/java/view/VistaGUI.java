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

/*  CREACIÓN DE UNA INTERFAZ GRÁFICA (GUI) SENCILLA
    PARA MOSTRAR LOS DISTINTOS DATOS DE LAS CONSULTAS
    EN UNA VENTANA MEDIANTE TABLAS (TIPO JTable) */

public class VistaGUI extends JFrame implements ActionListener {
    // Constantes
    private static final Dimension GUI_SIZE = new Dimension(550, 420);
    private static final Controlador CONTROL = new Controlador();
    private static final String[][] COL_HEADER = { { "" }, { "Nombre", "Salario" },
            { "Nombre", "Apellidos", "Salario", "ISR" }, { "Nombre del lider", "Constructora", "Numero de baños" } };

    // Atributos
    private static String rutaActual = new File(JDBCUtilities.getRouteDB()).getAbsolutePath();
    private Container cp;
    private JLabel status;
    private JMenuItem elegirDB;
    private JMenuItem salir;
    private JMenuItem consulta1;
    private JMenuItem consulta2;
    private JMenuItem consulta3;
    private JMenuItem limpiaConsulta;
    private JMenuItem about;
    private JPanel panelCentral;
    private JTable tabla;
    private int lineas;
    private boolean dbValida = false;

    // Constructor
    public VistaGUI(String titulo) {
        super(titulo);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.cp = this.getContentPane();
        this.addMenuBar();
        this.addPanels();
    }

    // Métodos

    public static void inicializarGUI() {
        JOptionPane.showMessageDialog(null,
                "===   F.M.C. CONSTRUCTORES   ---   BIENVENIDO/A AL SISTEMA DE CONSULTAS EN BD   ===\n\n"
                        + "A continuación, seleccione la base de datos del proyecto de construcción con la cuál trabajará este\n"+"sistema...\n\n"
                        + "■  El sistema se encargará de comprobar automáticamente si la base de datos elegida es válida o no\n"
                        + "■  Si no elige una tendrá que hacerlo más tarde o de lo contrario no podrá realizar ninguna consulta\n"
                        + "■  Sugerencia: El nombre por defecto del respectivo archivo de la BD es «ProyectosConstruccion.db»",
                "¡Bienvenido! - F.M.C. Constructores", JOptionPane.NO_OPTION);
        VistaGUI app = new VistaGUI("SISTEMA DE CONSULTAS - F.M.C. Constructores");
        app.chooseDB(rutaActual);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }

    public void addPanels() {
        panelCentral = new JPanel();
        JPanel panelInf = new JPanel();
        status = new JLabel("");
        panelInf.add(status);
        this.addImage();
        cp.add(panelCentral, BorderLayout.CENTER);
        cp.add(panelInf, BorderLayout.PAGE_END);
    }

    public void addImage() {
        ImageIcon imagen = new ImageIcon("./resources/bgi.jpg");
        JLabel bgi = new JLabel(imagen);
        this.setSize(GUI_SIZE);
        panelCentral.add(bgi);
        panelCentral.setBackground(new Color(224, 243, 213));
        limpiaConsulta.setEnabled(false);
    }

    public void addMenuBar() {
        JMenu archivo = new JMenu("Archivo");
        elegirDB = new JMenuItem("Elegir/Cambiar Archivo de BD");
        salir = new JMenuItem("Salir del programa");
        archivo.add(elegirDB);
        archivo.add(salir);
        elegirDB.addActionListener(this);
        salir.addActionListener(this);

        JMenu consultas = new JMenu("Consultas");
        consulta1 = new JMenuItem("Realizar Consulta #1");
        consulta2 = new JMenuItem("Realizar Consulta #2");
        consulta3 = new JMenuItem("Realizar Consulta #3");
        limpiaConsulta = new JMenuItem("Limpiar consultas");
        consultas.add(consulta1);
        consultas.add(consulta2);
        consultas.add(consulta3);
        consultas.add(limpiaConsulta);
        consulta1.addActionListener(this);
        consulta2.addActionListener(this);
        consulta3.addActionListener(this);
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

    public void generateJTable(int idConsulta, Color bgcolor) throws SQLException {
        Object[][] datos = CONTROL.crearDatosTabla(idConsulta);
        int nRegistros = datos.length;
        int ancho = 450;
        int alto = 16 * nRegistros;
        alto = alto > 240 ? 240 : alto;

        JLabel titulo = new JLabel("<html><body><u>Tabla de datos - Consulta #" + idConsulta + "</u></body></html>");
        JLabel enunciado = new JLabel(retornarEnunciado(idConsulta));
        JLabel cantRegistros = new JLabel(
                "<html><body><br>Total de registros encontrados: <u>" + nRegistros + "</u></body></html>");

        tabla.setModel(new DefaultTableModel(datos, COL_HEADER[idConsulta]));
        JScrollPane scrollpane = new JScrollPane(tabla);
        tabla.setPreferredScrollableViewportSize(new Dimension(ancho, alto));
        tabla.setEnabled(false);

        panelCentral.setVisible(false);
        panelCentral.removeAll();
        panelCentral.add(titulo, BorderLayout.NORTH);
        panelCentral.add(enunciado, BorderLayout.CENTER);
        panelCentral.add(scrollpane, BorderLayout.SOUTH);
        panelCentral.add(cantRegistros, BorderLayout.PAGE_END);
        panelCentral.setBackground(bgcolor);
        cp.add(panelCentral, BorderLayout.CENTER);
        this.setSize(ancho + 100, alto + 190 + 16 * lineas);
        if (!limpiaConsulta.isEnabled())
            limpiaConsulta.setEnabled(true);
        panelCentral.setVisible(true);
    }

    public void chooseDB(String rutaInicial) {
        try {
            if (this.isVisible())
                this.setVisible(false);

            JFileChooser jf = new JFileChooser(rutaInicial);
            jf.setMultiSelectionEnabled(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de base de datos de SQLite (*.db)",
                    "db");
            jf.setFileFilter(filter);
            jf.showOpenDialog(this);

            File f = jf.getSelectedFile();
            rutaInicial = f.getAbsolutePath();
            JDBCUtilities.setRouteDB(rutaInicial);

            if (!rutaInicial.equals(rutaActual)) {
                CONTROL.realizarConsulta(1);
                rutaActual = f.getAbsolutePath();
                dbValida = true;
                status.setText("BD actual: <" + f.getName() + ">");
                this.enableQueries(true);
                this.cleanQueries();
                JOptionPane.showMessageDialog(null,
                        "¡La base de datos seleccionada es correcta!\n\n"
                                + "El sistema está preparado para realizar las consultas",
                        "Base de datos válida", JOptionPane.NO_OPTION);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
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
                this.enableQueries(false);
            }
        }
    }

    public String retornarEnunciado(int idConsulta) {
        String enunciado = "<html><body><br>";
        switch (idConsulta) {
            case 1:
                enunciado += "Seleccione el nombre y el salario de los Lideres que se encuentran"
                        + "<br>en la ciudad de “Bogota”";
                lineas = 4;
                break;
            case 2:
                enunciado += "Seleccione el nombre, salario, el impuesto sobre la renta del salario"
                        + "<br>en una columna que se llame “isr” y los dos apellidos concatenados "
                        + "<br>y separados por un espacio en otra columna de nombre “ape”<br>"
                        + "<br>- Solo seleccione los registros que tengan un salario mayor a 10000"
                        + "<br>- El isr es el 16% del salario";
                lineas = 8;
                break;
            case 3:
                enunciado += "Seleccione el Constructor, el número de baños y el nombre del Lider de las"
                        + "<br>Construcciones que tengan un id entre 5 y 17 incluyendo los extremos";
                lineas = 4;
                break;
            default:
                return "";
        }
        return enunciado + "<br><br></body></html>";
    }

    public void cleanQueries() {
        if (limpiaConsulta.isEnabled()) {
            panelCentral.setVisible(false);
            panelCentral.removeAll();
            this.addImage();
            cp.add(panelCentral, BorderLayout.CENTER);
            panelCentral.setVisible(true);
            limpiaConsulta.setEnabled(false);
        }
    }

    public void enableQueries(boolean estado) {
        consulta1.setEnabled(estado);
        consulta2.setEnabled(estado);
        consulta3.setEnabled(estado);
        if(!dbValida)
            status.setText("BD actual: <NINGUNA>");
    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == elegirDB) {
            this.chooseDB(rutaActual);
            this.setVisible(true);
        }
        if (ev.getSource() == salir) {
            this.dispose();
            JOptionPane.showMessageDialog(null,
                    "Gracias por usar el sistema de consultas del proyecto de construcción\n\n¡Hasta una próxima oportunidad!",
                    "Salir del sistema - F.M.C. Constructores", JOptionPane.NO_OPTION);
            System.exit(0);
        }
        if (ev.getSource() == limpiaConsulta) {
            this.cleanQueries();
        }
        if (ev.getSource() == about) {
            JOptionPane
                    .showMessageDialog(null,
                            "Aplicación desarrollada por:\n\n" + "¯\\_(ツ)_/¯\n\n" + "FABIÁN MAURICIO MORENO CAMARGO\n"
                                    + "Tripulante - Grupo 49\nMisión TIC UTP\n2021",
                            "Acerca de...", JOptionPane.NO_OPTION);
        }
        try {
            if (tabla == null)
                tabla = new JTable();
            if (ev.getSource() == consulta1) {
                this.generateJTable(1, new Color(239, 228, 176));
            }
            if (ev.getSource() == consulta2) {
                this.generateJTable(2, new Color(255, 224, 183));
            }
            if (ev.getSource() == consulta3) {
                this.generateJTable(3, new Color(227, 255, 255));
            }
        } catch (Exception exc) {
            if (this.isVisible())
                this.setVisible(false);
            JOptionPane.showMessageDialog(null,
                    "¡Ha ocurrido algo inesperado al intentar acceder o conectar con la base de datos!\n\n" + "*** "
                            + exc.getMessage() + " ***\n\n" + "Por favor, intente lo siguiente:\n\n"
                            + "■  Revise que la base de datos elegida exista y sea una válida para el proyecto\n"
                            + "■  Intente cambiar el archivo de base de datos por otro distinto (y que sea válido)\n"
                            + "   usando la opción que aparece dentro del menú 'Archivo' para ello\n"
                            + "■  Si no ha elegido una BD, use igualmente la opción que aparece dentro del menú\n"
                            + "   'Archivo' para seleccionar una adecuada\n"
                            + "■  O en última instancia, contacte al soporte técnico del sistema",
                    "Error de acceso/conexión con la DB", JOptionPane.ERROR_MESSAGE);
            dbValida = false;
            this.enableQueries(false);
            this.cleanQueries();
            this.setVisible(true);
        }
    }
}