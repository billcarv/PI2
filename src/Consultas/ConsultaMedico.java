
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
public class ConsultaMedico extends javax.swing.JFrame {
    
    private Agendamento Agendamento;
    
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Conexao con;
    
    public ConsultaMedico(Object conect) {
        con = (Conexao) conect;
        criaTabelaConsMedico();
        initComponents();
        jScrollPane1.setViewportView(tabela);
    }

   private void criaTabelaConsMedico(){ 
        
        tabela = new JTable(modelo);   
        modelo.addColumn("Codigo");
        modelo.addColumn("Medico");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);

        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        pesquisarConsMedico("SELECT codigo,nome FROM medico  "
                + "WHERE deletado is null");
    }
   
       private void pesquisarConsMedico(String sql){
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
        lblConsultaMedico = new javax.swing.JLabel();
        cmbConsultaMedico = new javax.swing.JComboBox<>();
        txtConsultaMedico = new javax.swing.JTextField();
        btnConsultaMedico = new javax.swing.JButton();
        btnConsultaMedicoConfirmar = new javax.swing.JButton();
        btnConsultaMedicoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblConsultaMedico.setText("Tipo:");

        cmbConsultaMedico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome" }));

        btnConsultaMedico.setText("Pesquisar");
        btnConsultaMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConsultaMedicoMouseClicked(evt);
            }
        });
        btnConsultaMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaMedicoActionPerformed(evt);
            }
        });

        btnConsultaMedicoConfirmar.setText("Confirmar");
        btnConsultaMedicoConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaMedicoConfirmarActionPerformed(evt);
            }
        });

        btnConsultaMedicoCancelar.setText("Cancelar");
        btnConsultaMedicoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaMedicoCancelarActionPerformed(evt);
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
                        .addComponent(lblConsultaMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbConsultaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtConsultaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConsultaMedico))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnConsultaMedicoConfirmar)
                        .addGap(99, 99, 99)
                        .addComponent(btnConsultaMedicoCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsultaMedico)
                    .addComponent(cmbConsultaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConsultaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultaMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultaMedicoConfirmar)
                    .addComponent(btnConsultaMedicoCancelar))
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

    private void btnConsultaMedicoConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaMedicoConfirmarActionPerformed
        Agendamento.nomeMedico = pegaCodigo();
        this.dispose();
        
    }//GEN-LAST:event_btnConsultaMedicoConfirmarActionPerformed

    private void btnConsultaMedicoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaMedicoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnConsultaMedicoCancelarActionPerformed

    private void btnConsultaMedicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultaMedicoMouseClicked

    }//GEN-LAST:event_btnConsultaMedicoMouseClicked

    private void btnConsultaMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaMedicoActionPerformed
        String valor = txtConsultaMedico.getText();
        String consulta;
        consulta = "select codigo,nome from "
                + "medico where ";         
        switch (cmbConsultaMedico.getSelectedIndex()) {
            case 0:
                consulta += " codigo like '%"+ Integer.parseInt(valor)+"%'";                
                break;
            case 1:
                consulta += " nome like '%"+ valor +"%'";
                break;
            case 2:
                consulta += " crm like '%"+ valor +"%'"; 
                break;
        }
        consulta += " and deletado is null";
        
        pesquisarConsMedico(consulta);
    }//GEN-LAST:event_btnConsultaMedicoActionPerformed


   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultaMedico;
    private javax.swing.JButton btnConsultaMedicoCancelar;
    private javax.swing.JButton btnConsultaMedicoConfirmar;
    private javax.swing.JComboBox<String> cmbConsultaMedico;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConsultaMedico;
    private javax.swing.JTextField txtConsultaMedico;
    // End of variables declaration//GEN-END:variables

}
