
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
public class ConsultaEspecialidade extends javax.swing.JFrame {
    
    private Agendamento Agendamento;
    
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Conexao con;
    
    public ConsultaEspecialidade(Object conect) {
        con = (Conexao) conect;
        criaTabelaConsEspecialidade();
        initComponents();
        jScrollPane1.setViewportView(tabela);
    }

   private void criaTabelaConsEspecialidade(){ 
        
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
            pesquisa = "SELECT ep.codigo,ep.nome FROM especialidade ep ";
                if(!Agendamento.nomeMedico.isEmpty()){
                    pesquisa += " JOIN medico md ";
                    pesquisa += " ON md.especialidade_codigo = ep.codigo ";
                    pesquisa += " AND md.nome = '"+Agendamento.nomeMedico+"'"; 
                    pesquisa += " AND md.deletado is null ";
                }        
                pesquisa += "WHERE ep.deletado is null ";
        }  
                pesquisarConsEspecialidade(pesquisa);

    }
   
       private void pesquisarConsEspecialidade(String sql){
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
        lblConsultaEspecialidade = new javax.swing.JLabel();
        cmbConsultaEspecialidade = new javax.swing.JComboBox<>();
        txtConsultaEspecialidade = new javax.swing.JTextField();
        btnConsultaEspecialidade = new javax.swing.JButton();
        btnConsultaEspecialidadeConfirmar = new javax.swing.JButton();
        btnConsultaEspecialidadeCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblConsultaEspecialidade.setText("Tipo:");

        cmbConsultaEspecialidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome" }));

        btnConsultaEspecialidade.setText("Pesquisar");
        btnConsultaEspecialidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConsultaEspecialidadeMouseClicked(evt);
            }
        });
        btnConsultaEspecialidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaEspecialidadeActionPerformed(evt);
            }
        });

        btnConsultaEspecialidadeConfirmar.setText("Confirmar");
        btnConsultaEspecialidadeConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaEspecialidadeConfirmarActionPerformed(evt);
            }
        });

        btnConsultaEspecialidadeCancelar.setText("Cancelar");
        btnConsultaEspecialidadeCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaEspecialidadeCancelarActionPerformed(evt);
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
                        .addComponent(lblConsultaEspecialidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbConsultaEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtConsultaEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConsultaEspecialidade))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnConsultaEspecialidadeConfirmar)
                        .addGap(99, 99, 99)
                        .addComponent(btnConsultaEspecialidadeCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsultaEspecialidade)
                    .addComponent(cmbConsultaEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConsultaEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultaEspecialidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultaEspecialidadeConfirmar)
                    .addComponent(btnConsultaEspecialidadeCancelar))
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

    private void btnConsultaEspecialidadeConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaEspecialidadeConfirmarActionPerformed
        Agendamento.nomeEspecialidade = pegaCodigo();
        this.dispose();
        
    }//GEN-LAST:event_btnConsultaEspecialidadeConfirmarActionPerformed

    private void btnConsultaEspecialidadeCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaEspecialidadeCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnConsultaEspecialidadeCancelarActionPerformed

    private void btnConsultaEspecialidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultaEspecialidadeMouseClicked

    }//GEN-LAST:event_btnConsultaEspecialidadeMouseClicked

    private void btnConsultaEspecialidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaEspecialidadeActionPerformed
        String valor = txtConsultaEspecialidade.getText();
        String consulta;
        consulta = "select codigo,nome from "
                + "especialidade where ";         
        switch (cmbConsultaEspecialidade.getSelectedIndex()) {
            case 0:
                consulta += " codigo like '%"+ Integer.parseInt(valor)+"%'";                
                break;
            case 1:
                consulta += " nome like '%"+ valor +"%'";
                break;
        }
        consulta += " and deletado is null";
        
        pesquisarConsEspecialidade(consulta);
    }//GEN-LAST:event_btnConsultaEspecialidadeActionPerformed


   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultaEspecialidade;
    private javax.swing.JButton btnConsultaEspecialidadeCancelar;
    private javax.swing.JButton btnConsultaEspecialidadeConfirmar;
    private javax.swing.JComboBox<String> cmbConsultaEspecialidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConsultaEspecialidade;
    private javax.swing.JTextField txtConsultaEspecialidade;
    // End of variables declaration//GEN-END:variables

}
