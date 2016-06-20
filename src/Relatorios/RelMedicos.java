
package Relatorios;

import Aplicacao.Agendamento;
import Conexao.Conexao;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author willian.carvalho
 */
public class RelMedicos extends javax.swing.JFrame {
    
    private Agendamento Agendamento;
    
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Conexao con;
    
    public RelMedicos(Object conect,int a, int b) {
        con = (Conexao) conect;
        criaTabelaConsMedico(a,b);
        initComponents();
        jScrollPane1.setViewportView(tabela);
    }

   private void criaTabelaConsMedico(int ini,int fim){ 
        
        tabela = new JTable(modelo);   
        modelo.addColumn("Codigo");
        modelo.addColumn("Medico");
        modelo.addColumn("CRM");
        modelo.addColumn("Unidade");
        modelo.addColumn("Especialidade");
        tabela.setShowGrid(false);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(30);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(30);

        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        dadosMedicos("SELECT  md.codigo as cod, " +
                    " md.nome as nome, " +
                    " md.crm as crm, " +
                    " un.nome AS unidade, " +
                    " es.nome AS especialidade" +
                    " FROM medico md " +
                    " INNER JOIN unidade un " +
                    " ON md.unidade_codigo = un.codigo " +
                    " AND un.deletado is null " +
                    " INNER JOIN especialidade es " +
                    " ON md.especialidade_codigo = es.codigo " +
                    " AND es.deletado is null " +
                    " WHERE md.deletado is null " +
                    " AND md.codigo >= "+ini+" "+
                    " AND md.codigo <= "+fim );
    }
   
       private void dadosMedicos(String sql){
        DecimalFormat ze = new DecimalFormat("000000");  
        
        modelo.setNumRows(0);
        con.executeSQL(sql);
        try {
            con.resultset.first();
            do{
                modelo.addRow(new Object [] {
                ze.format(con.resultset.getObject("cod")),                                     
                con.resultset.getObject("nome"),
                con.resultset.getObject("crm"),
                con.resultset.getObject("unidade"),
                con.resultset.getObject("especialidade")});
            }while(con.resultset.next());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
    }
       
    private String pegaCodigo(){
        int x = tabela.getSelectedRow();
        Object codigo = modelo.getValueAt(x,1);
        return (String) codigo;
    } 
    
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relação de Médicos");
        setResizable(false);

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
