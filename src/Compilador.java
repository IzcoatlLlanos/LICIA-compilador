
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
    }

    private void init() {
        title = ".::COMPILADOR LICA::.";
        setLocationRelativeTo(null);
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".comp");
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(jtpCode);
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
            colorAnalysis();
        });
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCompilar, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(btnCompilar))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        compile();
        /*if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile(); 
         */ //SE COMENTÓ PARA NO GUARDAR EL ARCHIVO Y AGILIZAR
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void compile() {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors);

        //Eliminar errores
        gramatica.delete(new String[]{"error", "ERROR"}, 1);
        //agrupamos los tipos de datos
        gramatica.group("ENTIDADES", "entidades");
        gramatica.group("ENTIDADES_COMP", "ENTIDADES corchete_a numero_entero corchete_c", true);
        gramatica.group("ENTIDADES_COMP", "ENTIDADES numero_entero corchete_c", true, 1, "error sintáctico {}: falta el corchete de apertura [#,%]");
        gramatica.finalLineColumn();
        gramatica.group("ENTIDADES_COMP", "ENTIDADES corchete_a numero_entero", true, 2, "error sintáctico {}: falta el corchete de cierre [#,%]");
        gramatica.group("ENTIDADES_COMP", "ENTIDADES corchete_a corchete_c", true, 3, "error sintáctico {}: falta el indice[#,%]");
        gramatica.initialLineColumn();
        
        gramatica.group("TIPO", "(dato_digitos | dato_entero | dato_entero | dato_fecha | dato_booleano)", true);
        gramatica.group("VALOR", "(numero_entero | numero_decimal | tipo_bool)", true);

        gramatica.group("RANGO", "rango_entidad parentesis_a ((VALOR | identificador) coma (VALOR | identificador))? parentesis_c", true);
        gramatica.group("RANGO", "rango_entidad ((VALOR | identificador) coma (VALOR | identificador))? parentesis_c", true, 4, "\"error sintáctico {}: falta el parentesis de apertura [#,%]");
        //gramatica.group("RANGO", "rango_entidad parentesis_a ((VALOR | identificador) coma (VALOR | identificador))", true, 5, "\"error sintáctico {}: falta el parentesis de cierre [#,%]");
        gramatica.group("RANGO", "rango_entidad parentesis_a (((VALOR | identificador) coma) | (coma (VALOR | identificador)))? parentesis_c", true, 6, "\"error sintáctico {}: falta el un valor de rango[#,%]");      
        
        gramatica.group("ENT", "entidad_unica | ENTIDADES_COMP");
        gramatica.group("ENTI", "ENT punto (FUNCIONES | RANGO)");
        gramatica.group("ENTI", "ENT (FUNCIONES | RANGO)",true,32,"error sintáctico {}: el punto para llamar metodo[#,%]");
        
        //declaracion de variables
        
        gramatica.group("VARIABLE", "TIPO identificador operador_asignacion (VALOR | identificador)", true);
        gramatica.group("VARIABLE", "identificador operador_asignacion (VALOR | identificador)", true);
        gramatica.group("VARIABLE", "TIPO identificador", true);
        gramatica.group("VARIABLE", "TIPO operador_asignacion (VALOR | identificador)", true, 7, "error sintáctico {}: falta el identificador en la declaracion [#, %]");
        gramatica.group("VARIABLE", "TIPO VALOR", false, 8, "error sintáctico {}: falta el identificador y operador de asignación en la declaracion [#, %]");
        gramatica.group("VARIABLE", "operador_asignacion (VALOR | identificador)", false, 9, "error sintáctico {}: falta el identificador y en la declaracion [#, %]");
        gramatica.group("VARIABLE", "identificador (VALOR | identificador)", true, 10, "error sintáctico {}: falta el operador de asignación en la declaracion [#, %]");
        /*
        gramatica.finalLineColumn();
        gramatica.group("VARIABLE", "TIPO identificador operador_asignacion", 5, "error sintáctico {}: falta un valor en la declaracion [#, %]");
        gramatica.initialLineColumn();
        */
        
        //Definición de parametros
        gramatica.group("PARAMETROS", "VALOR(coma VALOR)+");
       
        
        //Definición de palabras reservadas, funciones
        gramatica.group("FUNCION", "reservada_imprime | conversion | reservada_leer");
        gramatica.group("FUNCIONES", "FUNCION parentesis_a (VALOR | PARAMETROS) llaves_a (CODIGO_DF)*? llaves_c", true);
        gramatica.group("FUNCIONES", "FUNCION parentesis_a llaves_a (CODIGO_DF)*? llaves_c", true, 25, "error sintáctico {}: faltan parametros [#,%]");
        gramatica.group("FUNCIONES", "FUNCION (VALOR | PARAMETROS)? parentesis_c", true, 11, "error sintáctico {}: falta el parentesis de apertura [#,%]");
        gramatica.group("FUNCIONES", "FUNCION parentesis_a (VALOR | PARAMETROS)", true, 12, "error sintáctico {}: falta el parentesis de cierre [#,%]");
        
        gramatica.group("MET", "reservada_inicio | reservada_principal | reservada_funcion");
        gramatica.group("METODO", "MET (parentesis_a ((VALOR | PARAMETROS) parentesis_c)|parentesis_a parentesis_c) llaves_a (CODIGO_DF)*? llaves_c", true);
        gramatica.group("METODO", "MET (parentesis_a ((VALOR | PARAMETROS) parentesis_c)|parentesis_a parentesis_c) (CODIGO_DF)*? llaves_c", true, 26, "error sintáctico {}: falta la llave de apertura [#,%]");
        gramatica.group("METODO", "MET (parentesis_a ((VALOR | PARAMETROS) parentesis_c)|parentesis_a parentesis_c) llaves_a (CODIGO_DF)*", true, 27, "error sintáctico {}: falta la llave de cierre [#,%]");
        gramatica.group("METODO", "MET (parentesis_a ((VALOR | PARAMETROS) parentesis_c)|parentesis_a parentesis_c) (CODIGO_DF)*", true, 28, "error sintáctico {}: faltan llaves [#,%]");
        gramatica.group("METODO", "MET ( ((VALOR | PARAMETROS) parentesis_c)| parentesis_c) llaves_a (CODIGO_DF)*? llaves_c", true, 29, "error sintáctico {}: falta parentesis de apertura [#,%]");
        //gramatica.group("METODO", "MET (parentesis_a ((VALOR | PARAMETROS) )| parentesis_a) llaves_a (CODIGO_DF)*? llaves_c", true, 30, "error sintáctico {}: falta parentesis de cierre [#,%]");
        gramatica.group("METODO", "MET (VALOR | PARAMETROS) llaves_a (CODIGO_DF)*? llaves_c", true, 31, "error sintáctico {}: faltan parentesis [#,%]");
/*
        gramatica.group("METODO", "MET parentesis_a llaves_a (CODIGO_DF)*? llaves_c",true,27,"error sintáctico {}: falta el parentesis de apertura [#,%]");
        gramatica.group("METODO", "MET parentesis_c llaves_a (CODIGO_DF)*? llaves_c",true,28,"error sintáctico {}: falta el parentesis de cierre [#,%]");
        gramatica.group("METODO", "MET parentesis_a parentesis_c (CODIGO_DF)*? llaves_c",true,29,"error sintáctico {}: falta la llave de apertura [#,%]");
        gramatica.group("METODO", "MET parentesis_a parentesis_c llaves_a (CODIGO_DF)* ",true,30,"error sintáctico {}: falta la llave de cierre [#,%]");
        gramatica.group("METODO", "MET parentesis_a",true,25,"error sintáctico {}: falta el parentesis de cierre [#,%]");
        gramatica.group("METODO", "MET parentesis_c",true,26,"error sintáctico {}: falta el parentesis de apertura [#,%]");
        
        /*/
        
        //Definir componente de código
        gramatica.group("CODIGO_DF", "(FUNCIONES | VARIABLE | ENTI | ENTIDADES_COMP)",true);
        
        //definir estructura condición
        gramatica.group("CONDICION_D", "(VALOR | identificador) ((operador_logico | operador_relacional) (VALOR | identificador))+ ");
        //gramatica.group("CONDICION", "parentesis_a CONDICION_D parentesis_c");
        
        //Definir si
        gramatica.group("E_SI","(reservada_si | reservada_no | reservada_si_no | reservada_mientras)");
        gramatica.group("SI","E_SI parentesis_a CONDICION_D parentesis_c llaves_a (CODIGO_DF)*? llaves_c");
        gramatica.group("SI","E_SI parentesis_a CONDICION_D parentesis_c (CODIGO_DF)*? llaves_c",true,13,"error sintáctico {}: faltan las llaves de apertura [#,%]");
        //gramatica.group("SI","E_SI parentesis_a CONDICION_D parentesis_c llaves_a (CODIGO_DF)*",true,14,"error sintáctico {}: faltan las llaves de cierre [#,%]");
        //gramatica.group("SI","E_SI parentesis_a CONDICION_D parentesis_c (CODIGO_DF)*",true,15,"error sintáctico {}: faltan las llaves [#,%]");
        gramatica.group("SI","E_SI CONDICION_D llaves_a (CODIGO_DF)*? llaves_c",true,16,"error sintáctico {}: faltan parentesis [#,%]");
        gramatica.group("SI","E_SI CONDICION_D parentesis_c llaves_a (CODIGO_DF)*? llaves_c",true,17,"error sintáctico {}: faltan el parentesis de apertura [#,%]");
        gramatica.group("SI","E_SI parentesis_a CONDICION_D llaves_a (CODIGO_DF)*? llaves_c",true,18,"error sintáctico {}: faltan el parentesis de cierre [#,%]");
        
        //
        gramatica.group("INCDEC", "identificador operador_aritmetico operador_aritmetico ");
        //definir ciclo
        gramatica.group("CONDICION_CICLO", "identificador doblePunto VALOR puntoYcoma CONDICION_D puntoYcoma INCDEC");
        gramatica.group("CICLO_H", "reservada_ciclo parentesis_a CONDICION_CICLO parentesis_c llaves_a (CODIGO_DF)*? llaves_c");
        gramatica.group("CICLO_H", "reservada_ciclo CONDICION_CICLO parentesis_c llaves_a (CODIGO_DF)*? llaves_c",true,19,"error sintáctico {}: faltan el parentesis de apertura [#,%]");
        gramatica.group("CICLO_H", "reservada_ciclo parentesis_a CONDICION_CICLO llaves_a (CODIGO_DF)*? llaves_c",true,20,"error sintáctico {}: faltan el parentesis de cierre [#,%]");
        gramatica.group("CICLO_H", "reservada_ciclo parentesis_a CONDICION_CICLO parentesis_c (CODIGO_DF)*? llaves_c",true,21,"error sintáctico {}: falta la llave de apertura [#,%]");
        //gramatica.group("CICLO_H", "reservada_ciclo parentesis_a CONDICION_CICLO parentesis_c llaves_a (CODIGO_DF)*? ",true,22,"error sintáctico {}: falta la llave de cierre [#,%]");
        gramatica.group("CICLO_H", "reservada_ciclo CONDICION_CICLO llaves_a (CODIGO_DF)*? ",true,23,"error sintáctico {}: faltan parentesis [#,%]");
        //gramatica.group("CICLO_H", "reservada_ciclo parentesis_a CONDICION_CICLO parentesis_c (CODIGO_DF)*?",true,24,"error sintáctico {}: falta las llaves [#,%]");
        

        
        //componente sitáctico para retornar 
        //gramatica.group("RETORNO", "reservada_retorna");
        
        /* Mostrar gramáticas */
        gramatica.show();
    }

    private void semanticAnalysis() {
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompilar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
