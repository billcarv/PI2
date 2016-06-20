
package Relatorios;

import Conexao.Conexao;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author willian.carvalho
 */
public class RelClientes extends javax.swing.JFrame {
    
    
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Conexao con;
    
    public RelClientes(Object conect,int a, int b,String c, int d) {
        con = (Conexao) conect;
        dadosMedicos(a,b,c,d);
        initComponents();
        jScrollPane1.setViewportView(tabela);
    }

   private void dadosMedicos(int ini,int fim,String nome,int atv){ 
        
        tabela = new JTable(modelo);   
        modelo.addColumn("Codigo");
        modelo.addColumn("Cliente");
        modelo.addColumn("CPF");
        modelo.addColumn("Convenio");
        modelo.addColumn("Cadastro");
        modelo.addColumn("Ativo");
        tabela.setShowGrid(false);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(20);

        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        String sql =  "SELECT  cl.codigo as codigo, ";
               sql += " cl.nome as nome, ";
               sql += " cl.cpf as cpf, " ;
               sql += " cv.nome AS convenio, ";
               sql += " cl.cadastro AS cadastro,";
               sql += " cl.ativo as ativo ";
               sql += " FROM cliente cl ";
               sql += " INNER JOIN convenio cv ";
               sql += " ON cl.convenio_codigo = cv.codigo ";
               sql += " AND cv.deletado is null ";
               sql += " WHERE cl.deletado is null ";
               sql += " AND cl.codigo >= "+ini+" ";
               sql += " AND cl.codigo <= "+fim;
               if(!nome.isEmpty()){
                   sql += " AND cl.nome LIKE '%"+nome+"'% ";
               }
               if(atv==1){
                   sql += " AND cl.ativo = 'S' ";
               }
                
                dadosMedicos(sql);
    }
   
       private void dadosMedicos(String sql){
        DecimalFormat ze = new DecimalFormat("000000");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        modelo.setNumRows(0);
        con.executeSQL(sql);
        try {
            con.resultset.first();
            do{
                modelo.addRow(new Object [] {
                ze.format(con.resultset.getObject("codigo")),                                     
                con.resultset.getObject("nome"),
                con.resultset.getObject("cpf"),
                con.resultset.getObject("convenio"),
                df.format(con.resultset.getObject("cadastro")),
                con.resultset.getObject("ativo")});
            }while(con.resultset.next());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
    }
 
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relação de Médicos");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
