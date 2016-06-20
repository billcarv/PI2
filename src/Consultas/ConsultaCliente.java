
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
public class ConsultaCliente extends javax.swing.JFrame {
    
    private Agendamento Agendamento;
    
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Conexao con;
    
    public ConsultaCliente(Object conect) {
        con = (Conexao) conect;
        criaTabelaConsCliente();
        initComponents();
        jScrollPane1.setViewportView(tabela);
    }

   private void criaTabelaConsCliente(){ 
        
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
        pesquisarConsCliente("SELECT codigo,nome FROM cliente  "
                + "WHERE deletado is null");
    }
   
       private void pesquisarConsCliente(String sql){
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
        lblConsultaCliente = new javax.swing.JLabel();
        cmbConsultaCliente = new javax.swing.JComboBox<>();
        txtConsultaCliente = new javax.swing.JTextField();
        btnConsultaClientePesquisar = new javax.swing.JButton();
        btnConsultaClienteConfirmar = new javax.swing.JButton();
        btnConsultaClienteCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblConsultaCliente.setText("Tipo:");

        cmbConsultaCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome", "CPF" }));

        btnConsultaClientePesquisar.setText("Pesquisar");
        btnConsultaClientePesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConsultaClientePesquisarMouseClicked(evt);
            }
        });
        btnConsultaClientePesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaClientePesquisarActionPerformed(evt);
            }
        });

        btnConsultaClienteConfirmar.setText("Confirmar");
        btnConsultaClienteConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaClienteConfirmarActionPerformed(evt);
            }
        });

        btnConsultaClienteCancelar.setText("Cancelar");
        btnConsultaClienteCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaClienteCancelarActionPerformed(evt);
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
                        .addComponent(lblConsultaCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConsultaClientePesquisar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnConsultaClienteConfirmar)
                        .addGap(99, 99, 99)
                        .addComponent(btnConsultaClienteCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsultaCliente)
                    .addComponent(cmbConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConsultaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultaClientePesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultaClienteConfirmar)
                    .addComponent(btnConsultaClienteCancelar))
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

    private void btnConsultaClienteConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaClienteConfirmarActionPerformed
        Agendamento.nomeCliente = pegaCodigo();
        this.dispose();
        
    }//GEN-LAST:event_btnConsultaClienteConfirmarActionPerformed

    private void btnConsultaClienteCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaClienteCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnConsultaClienteCancelarActionPerformed

    private void btnConsultaClientePesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultaClientePesquisarMouseClicked

    }//GEN-LAST:event_btnConsultaClientePesquisarMouseClicked

    private void btnConsultaClientePesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaClientePesquisarActionPerformed
        String valor = txtConsultaCliente.getText();
        String consulta;
        consulta = "select codigo,nome from "
                + "cliente where ";         
        switch (cmbConsultaCliente.getSelectedIndex()) {
            case 0:
                consulta += " codigo like '%"+ Integer.parseInt(valor)+"%'";                
                break;
            case 1:
                consulta += " nome like '%"+ valor +"%'";
                break;
            case 2:
                consulta += " cpf like '%"+ valor +"%'"; 
                break;
        }
        consulta += " and deletado is null";
        
        pesquisarConsCliente(consulta);
    }//GEN-LAST:event_btnConsultaClientePesquisarActionPerformed


   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultaClienteCancelar;
    private javax.swing.JButton btnConsultaClienteConfirmar;
    private javax.swing.JButton btnConsultaClientePesquisar;
    private javax.swing.JComboBox<String> cmbConsultaCliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConsultaCliente;
    private javax.swing.JTextField txtConsultaCliente;
    // End of variables declaration//GEN-END:variables

}
