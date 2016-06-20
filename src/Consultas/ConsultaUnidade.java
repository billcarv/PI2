
package Consultas;

import Aplicacao.Agendamento;
import Conexao.Conexao;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author willian.carvalho
 */
public class ConsultaUnidade extends javax.swing.JFrame {
    
    private Agendamento Agendamento;
    
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Conexao con;
    
    public ConsultaUnidade(Object conect) {
        con = (Conexao) conect;
        criaTabelaConsUnidade();
        initComponents();
        jScrollPane1.setViewportView(tabela);
    }

   private void criaTabelaConsUnidade(){ 
        
        tabela = new JTable(modelo);   
        modelo.addColumn("Codigo");
        modelo.addColumn("Unidade");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);

        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        String pesquisa;
        if(Agendamento.nomeMedico.isEmpty()){
         pesquisa = " SELECT codigo = '000',nome =' ' ";   
        }else{
            pesquisa = "SELECT un.codigo,un.nome FROM unidade un ";
                if(!Agendamento.nomeMedico.isEmpty()){
                    pesquisa += " JOIN medico md ";
                    pesquisa += " ON md.unidade_codigo = un.codigo ";
                    pesquisa += " AND md.nome = '"+Agendamento.nomeMedico+"'"; 
                    pesquisa += " AND md.deletado is null ";
                }        
                pesquisa += "WHERE un.deletado is null ";
        }  
                pesquisarConsUnidade(pesquisa);
    }
   
       private void pesquisarConsUnidade(String sql){
        DecimalFormat ze = new DecimalFormat("000000");  
        
        modelo.setNumRows(0);
        con.executeSQL(sql);
        try {
            con.resultset.first();
            do{
                modelo.addRow(new Object [] {
                ze.format(con.resultset.getObject("codigo")),                                     
                con.resultset.getObject("nome")});
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
        lblConsultaUnidade = new javax.swing.JLabel();
        cmbConsultaUnidade = new javax.swing.JComboBox<>();
        txtConsultaUnidade = new javax.swing.JTextField();
        btnConsultaUnidade = new javax.swing.JButton();
        btnConsultaUnidadeConfirmar = new javax.swing.JButton();
        btnConsultaUnidadeCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblConsultaUnidade.setText("Tipo:");

        cmbConsultaUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome" }));

        txtConsultaUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultaUnidadeActionPerformed(evt);
            }
        });

        btnConsultaUnidade.setText("Pesquisar");
        btnConsultaUnidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConsultaUnidadeMouseClicked(evt);
            }
        });
        btnConsultaUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaUnidadeActionPerformed(evt);
            }
        });

        btnConsultaUnidadeConfirmar.setText("Confirmar");
        btnConsultaUnidadeConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaUnidadeConfirmarActionPerformed(evt);
            }
        });

        btnConsultaUnidadeCancelar.setText("Cancelar");
        btnConsultaUnidadeCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaUnidadeCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblConsultaUnidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbConsultaUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtConsultaUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConsultaUnidade))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnConsultaUnidadeConfirmar)
                        .addGap(99, 99, 99)
                        .addComponent(btnConsultaUnidadeCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsultaUnidade)
                    .addComponent(cmbConsultaUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConsultaUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultaUnidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultaUnidadeConfirmar)
                    .addComponent(btnConsultaUnidadeCancelar))
                .addContainerGap(18, Short.MAX_VALUE))
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

    private void btnConsultaUnidadeConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaUnidadeConfirmarActionPerformed
        Agendamento.nomeUnidade = pegaCodigo();
        this.dispose();
        
    }//GEN-LAST:event_btnConsultaUnidadeConfirmarActionPerformed

    private void btnConsultaUnidadeCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaUnidadeCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnConsultaUnidadeCancelarActionPerformed

    private void btnConsultaUnidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultaUnidadeMouseClicked

    }//GEN-LAST:event_btnConsultaUnidadeMouseClicked

    private void btnConsultaUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaUnidadeActionPerformed
        String valor = txtConsultaUnidade.getText();
        String consulta;
        consulta = "select codigo,nome from "
                + "unidade where ";         
        switch (cmbConsultaUnidade.getSelectedIndex()) {
            case 0:
                consulta += " codigo like '%"+ Integer.parseInt(valor)+"%'";                
                break;
            case 1:
                consulta += " nome like '%"+ valor +"%'";
                break;
        }
        consulta += " and deletado is null";
        
        pesquisarConsUnidade(consulta);
    }//GEN-LAST:event_btnConsultaUnidadeActionPerformed

    private void txtConsultaUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultaUnidadeActionPerformed

    }//GEN-LAST:event_txtConsultaUnidadeActionPerformed


   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultaUnidade;
    private javax.swing.JButton btnConsultaUnidadeCancelar;
    private javax.swing.JButton btnConsultaUnidadeConfirmar;
    private javax.swing.JComboBox<String> cmbConsultaUnidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConsultaUnidade;
    private javax.swing.JTextField txtConsultaUnidade;
    // End of variables declaration//GEN-END:variables

}
