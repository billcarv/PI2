package Aplicacao;

import Conexao.Conexao;
import Consultas.ConsultaCliente;
import Consultas.ConsultaEspecialidade;
import Consultas.ConsultaMedico;
import Consultas.ConsultaUnidade;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Agendamento extends javax.swing.JInternalFrame {

    Conexao con = new Conexao();
    private ConsultaCliente ConsultaCliente;
    private ConsultaUnidade ConsultaUnidade;
    private ConsultaEspecialidade ConsultaEspecialidade;
    private ConsultaMedico ConsultaMedico;    
    private int opcao;
    private int codigo;
    public static String nomeCliente;
    public static String nomeUnidade;
    public static String nomeEspecialidade;
    public static String nomeMedico;
    
      
    public Agendamento(Object cod,int opc) {
        opcao = opc;
        codigo = Integer.parseInt((String) cod);
        initComponents();
        desabilitaItens();
        if(opcao==2){
            preencheAgendamento(codigo);
        }
   }
 
   private void desabilitaItens(){
       lblAgendamentoEspecialidade.setEnabled(false);
       lblAgendamentoUnidade.setEnabled(false);
       txtAgendamentoEspecialidade.setEnabled(false);
       txtAgendamentoUnidade.setEnabled(false);
       btnAgendamentoEspecialidade.setEnabled(false);
       btnAgendamentoUnidade.setEnabled(false);
       txtAgendamentoEspecialidade.setEditable(false);
       txtAgendamentoUnidade.setEditable(false);
       btnAgendamentoEspecialidade.setVisible(false);
       btnAgendamentoUnidade.setVisible(false);       
   }
    
   private void habilitaItens(){
       lblAgendamentoEspecialidade.setEnabled(true);
       lblAgendamentoUnidade.setEnabled(true);
       txtAgendamentoEspecialidade.setEnabled(true);
       txtAgendamentoUnidade.setEnabled(true);
       btnAgendamentoEspecialidade.setEnabled(true);
       btnAgendamentoUnidade.setEnabled(true);
       txtAgendamentoEspecialidade.setEditable(true);
       txtAgendamentoUnidade.setEditable(true);
       btnAgendamentoEspecialidade.setVisible(true);
       btnAgendamentoUnidade.setVisible(true);           
   }
   
     private void criaCliente() { 
        boolean agendado = false;
        String sql = "";
        int reply;
        String dt = cmbDataAgendamento.getText();
        String dia = dt.substring(6)+"-"+dt.substring(3,5)+"-"+dt.substring(0,2)+" ";
                dia += cmbHorario.getSelectedItem();
        String medico = txtAgendamentoMedico.getText().trim();
        String cliente = txtAgendamentoCliente.getText().trim();

        con.executeSQL("SELECT ag.codigo as codigo FROM agendamento ag "
            + " JOIN medico md ON md.codigo = ag.medico_codigo AND md.deletado is null "
            + " AND md.nome = '"+medico+"'  WHERE dt_hr_inicio = '"+dia+"' "
            + " AND ag.deletado is null ");

        try {
            if(!con.resultset.next()){
                agendado = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Agendamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(agendado){
            sql = "insert into agendamento (dt_hr_inicio,reservado,espera,";
            sql +=" status_agendamento_codigo,medico_codigo,cliente_codigo)";
            sql +=" values ('"+dia+"','S','N',1,(SELECT codigo from medico where";
            sql +=" nome = '"+medico+"'),(SELECT codigo from cliente where ";
            sql +=" nome = '"+cliente+"'))";
            JOptionPane.showMessageDialog(null, "Confirmado");
        }else{
            reply = JOptionPane.showConfirmDialog(null, "Já existe agendamento neste dia, deseja reservar?", "Aviso!", JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_OPTION){
                sql = "insert into agendamento (dt_hr_inicio,reservado,espera,";
                sql +=" status_agendamento_codigo,medico_codigo,cliente_codigo)";
                sql +=" values ('"+dia+"','N','S',2,(SELECT codigo from medico where";
                sql +=" nome = '"+medico+"'),(SELECT codigo from cliente where ";
                sql +=" nome = '"+cliente+"'))";
            }
              
        }
        con.updateSQL(sql);
        this.dispose();
    } 
     
    private void cancelarAgendamento(){
        
    } 
    
    private void preencheAgendamento(int codigo){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hr = new SimpleDateFormat("HH:mm");
        DecimalFormat ze = new DecimalFormat("000000");
        int index;
        
        String sql = "SELECT ag.codigo as codigo, cl.nome as nome,"
                + "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as "
                + "especialidade,un.nome as unidade,ag.reservado as reservado, "
                + "ag.espera as espera "
                + "FROM agendamento ag "
                + "JOIN cliente cl "
                + "ON cl.codigo = ag.cliente_codigo "
                + "JOIN medico md "
                + "ON md.codigo = ag.medico_codigo "
                + "JOIN especialidade ep "
                + "ON ep.codigo = md.especialidade_codigo "
                + "JOIN unidade un "
                + "ON un.codigo = md.unidade_codigo "
                + "JOIN status_agendamento sa "
                + "ON sa.codigo = ag.status_agendamento_codigo "
                + "WHERE ag.deletado is null "
                + "AND ag.codigo = "+codigo; 
        
        con.executeSQL(sql);
        
        try {
            con.resultset.first();
            txtAgendamentoCodigo.setText(ze.format(Integer.parseInt(Integer.toString(con.resultset.getInt("codigo")))));
            txtAgendamentoCliente.setText(con.resultset.getString("nome"));
            cmbDataAgendamento.setText(df.format(con.resultset.getDate("data")));
            cmbHorario.setSelectedItem(hr.format(con.resultset.getTime("data")));
            txtAgendamentoMedico.setText(con.resultset.getString("medico"));
            txtAgendamentoEspecialidade.setText(con.resultset.getString("especialidade"));
            txtAgendamentoUnidade.setText(con.resultset.getString("unidade"));
            
            //cmbConvenioCliente.set(con.resultset.getInt("convenio_codigo"));
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        lblAgendamentoCodigo = new javax.swing.JLabel();
        txtAgendamentoCodigo = new javax.swing.JTextField();
        lblAgendamentoHorario = new javax.swing.JLabel();
        cmbHorario = new javax.swing.JComboBox<>();
        lblAgendamentoCliente = new javax.swing.JLabel();
        txtAgendamentoCliente = new javax.swing.JTextField();
        btnAgendamentoCliente = new javax.swing.JButton();
        txtAgendamentoMedico = new javax.swing.JTextField();
        lblAgendamentoMedico = new javax.swing.JLabel();
        lblAgendamentoEspecialidade = new javax.swing.JLabel();
        lblAgendamentoUnidade = new javax.swing.JLabel();
        btnConfirmar2 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        txtAgendamentoUnidade = new javax.swing.JTextField();
        lblAgendamentoData = new javax.swing.JLabel();
        btnAgendamentoUnidade = new javax.swing.JButton();
        txtAgendamentoEspecialidade = new javax.swing.JTextField();
        btnAgendamentoEspecialidade = new javax.swing.JButton();
        btnAgendamentoMedico = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        cmbDataAgendamento = new datechooser.beans.DateChooserCombo();

        jTextField2.setText("jTextField2");

        setTitle("Agendamento");

        lblAgendamentoCodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAgendamentoCodigo.setText("Código");

        txtAgendamentoCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAgendamentoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgendamentoCodigoActionPerformed(evt);
            }
        });

        lblAgendamentoHorario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAgendamentoHorario.setText("Horário");

        cmbHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00" }));

        lblAgendamentoCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAgendamentoCliente.setText("Cliente");

        txtAgendamentoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgendamentoClienteActionPerformed(evt);
            }
        });

        btnAgendamentoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Aplicacao/zoom.png"))); // NOI18N
        btnAgendamentoCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAgendamentoClienteFocusGained(evt);
            }
        });
        btnAgendamentoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgendamentoClienteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgendamentoClienteMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAgendamentoClienteMouseReleased(evt);
            }
        });
        btnAgendamentoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendamentoClienteActionPerformed(evt);
            }
        });

        txtAgendamentoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgendamentoMedicoActionPerformed(evt);
            }
        });
        txtAgendamentoMedico.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtAgendamentoMedicoPropertyChange(evt);
            }
        });

        lblAgendamentoMedico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAgendamentoMedico.setText("Médico");

        lblAgendamentoEspecialidade.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAgendamentoEspecialidade.setText("Especialidade");

        lblAgendamentoUnidade.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAgendamentoUnidade.setText("Unidade");

        btnConfirmar2.setText("Confirmar");
        btnConfirmar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmar2MouseClicked(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
        });

        lblAgendamentoData.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAgendamentoData.setText("Data");

        btnAgendamentoUnidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Aplicacao/zoom.png"))); // NOI18N
        btnAgendamentoUnidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAgendamentoUnidadeFocusGained(evt);
            }
        });
        btnAgendamentoUnidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgendamentoUnidadeMouseClicked(evt);
            }
        });
        btnAgendamentoUnidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendamentoUnidadeActionPerformed(evt);
            }
        });

        btnAgendamentoEspecialidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Aplicacao/zoom.png"))); // NOI18N
        btnAgendamentoEspecialidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAgendamentoEspecialidadeFocusGained(evt);
            }
        });
        btnAgendamentoEspecialidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgendamentoEspecialidadeMouseClicked(evt);
            }
        });
        btnAgendamentoEspecialidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendamentoEspecialidadeActionPerformed(evt);
            }
        });

        btnAgendamentoMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Aplicacao/zoom.png"))); // NOI18N
        btnAgendamentoMedico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAgendamentoMedicoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnAgendamentoMedicoFocusLost(evt);
            }
        });
        btnAgendamentoMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgendamentoMedicoMouseClicked(evt);
            }
        });
        btnAgendamentoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendamentoMedicoActionPerformed(evt);
            }
        });

        cmbDataAgendamento.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    cmbDataAgendamento.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
    cmbDataAgendamento.setCurrentNavigateIndex(0);
    cmbDataAgendamento.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator1)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(txtAgendamentoEspecialidade)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnAgendamentoEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(txtAgendamentoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnAgendamentoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAgendamentoCodigo)
                            .addComponent(lblAgendamentoData, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtAgendamentoCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblAgendamentoEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(cmbDataAgendamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblAgendamentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAgendamentoHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 35, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAgendamentoMedico)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtAgendamentoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAgendamentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAgendamentoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblAgendamentoUnidade)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtAgendamentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))))))
            .addContainerGap(29, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConfirmar2)
            .addGap(18, 18, 18)
            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(141, 141, 141))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblAgendamentoCodigo)
                .addComponent(lblAgendamentoCliente))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAgendamentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAgendamentoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAgendamentoData)
                        .addComponent(lblAgendamentoHorario)
                        .addComponent(lblAgendamentoMedico)))
                .addComponent(btnAgendamentoCliente))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbDataAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(23, 23, 23)
                    .addComponent(lblAgendamentoEspecialidade)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtAgendamentoEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtAgendamentoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgendamentoMedico))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgendamentoUnidade))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(lblAgendamentoUnidade)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAgendamentoEspecialidade)
                        .addComponent(txtAgendamentoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(32, 32, 32)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnCancelar)
                .addComponent(btnLimpar)
                .addComponent(btnSair)
                .addComponent(btnConfirmar2))
            .addContainerGap(27, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAgendamentoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgendamentoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgendamentoCodigoActionPerformed

    private void btnConfirmar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmar2MouseClicked
        if(opcao == 1 ){
            criaCliente();
        }else{
            preencheAgendamento(codigo);
        }
    }//GEN-LAST:event_btnConfirmar2MouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        limpaCampos();
        this.dispose();
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnAgendamentoClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendamentoClienteMouseClicked
        ConsultaCliente obj = new ConsultaCliente(con);
        obj.setVisible(true);
    }//GEN-LAST:event_btnAgendamentoClienteMouseClicked

    private void btnAgendamentoUnidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendamentoUnidadeMouseClicked
        ConsultaUnidade obj = new ConsultaUnidade(con);
        obj.setVisible(true);
    }//GEN-LAST:event_btnAgendamentoUnidadeMouseClicked

    private void btnAgendamentoEspecialidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendamentoEspecialidadeMouseClicked
        ConsultaEspecialidade obj = new ConsultaEspecialidade(con);
        obj.setVisible(true);
    }//GEN-LAST:event_btnAgendamentoEspecialidadeMouseClicked

    private void btnAgendamentoMedicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendamentoMedicoMouseClicked
        ConsultaMedico obj = new ConsultaMedico(con);
        obj.setVisible(true);
    }//GEN-LAST:event_btnAgendamentoMedicoMouseClicked

    private void txtAgendamentoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgendamentoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgendamentoClienteActionPerformed

    private void btnAgendamentoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendamentoClienteActionPerformed
        this.ConsultaCliente = new ConsultaCliente(con);        
    }//GEN-LAST:event_btnAgendamentoClienteActionPerformed

    private void btnAgendamentoClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendamentoClienteMouseReleased
                // TODO add your handling code here:
    }//GEN-LAST:event_btnAgendamentoClienteMouseReleased

    private void btnAgendamentoClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgendamentoClienteMouseExited

    }//GEN-LAST:event_btnAgendamentoClienteMouseExited

    private void btnAgendamentoClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgendamentoClienteFocusGained
        txtAgendamentoCliente.setText(getNomeCliente());
    }//GEN-LAST:event_btnAgendamentoClienteFocusGained

    private void btnAgendamentoUnidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgendamentoUnidadeFocusGained
        txtAgendamentoUnidade.setText(getNomeUnidade());
    }//GEN-LAST:event_btnAgendamentoUnidadeFocusGained

    private void btnAgendamentoEspecialidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgendamentoEspecialidadeFocusGained
        txtAgendamentoEspecialidade.setText(getNomeEspecialidade());
    }//GEN-LAST:event_btnAgendamentoEspecialidadeFocusGained

    private void btnAgendamentoMedicoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgendamentoMedicoFocusGained
        txtAgendamentoMedico.setText(getNomeMedico());        
    }//GEN-LAST:event_btnAgendamentoMedicoFocusGained

    private void btnAgendamentoUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendamentoUnidadeActionPerformed
        this.ConsultaUnidade = new ConsultaUnidade(con);
    }//GEN-LAST:event_btnAgendamentoUnidadeActionPerformed

    private void btnAgendamentoEspecialidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendamentoEspecialidadeActionPerformed
        this.ConsultaEspecialidade = new ConsultaEspecialidade(con);
    }//GEN-LAST:event_btnAgendamentoEspecialidadeActionPerformed

    private void btnAgendamentoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendamentoMedicoActionPerformed
        this.ConsultaMedico = new ConsultaMedico(con);
    }//GEN-LAST:event_btnAgendamentoMedicoActionPerformed

    private void txtAgendamentoMedicoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtAgendamentoMedicoPropertyChange

    }//GEN-LAST:event_txtAgendamentoMedicoPropertyChange

    private void txtAgendamentoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgendamentoMedicoActionPerformed
     // TODO add your handling code here:
    }//GEN-LAST:event_txtAgendamentoMedicoActionPerformed

    private void btnAgendamentoMedicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgendamentoMedicoFocusLost
        habilitaItens();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgendamentoMedicoFocusLost
  
    private void limpaCampos(){
        txtAgendamentoCliente.setText("");
        txtAgendamentoEspecialidade.setText("");
        txtAgendamentoMedico.setText("");
        txtAgendamentoUnidade.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgendamentoCliente;
    private javax.swing.JButton btnAgendamentoEspecialidade;
    private javax.swing.JButton btnAgendamentoMedico;
    private javax.swing.JButton btnAgendamentoUnidade;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar2;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSair;
    private datechooser.beans.DateChooserCombo cmbDataAgendamento;
    private javax.swing.JComboBox<String> cmbHorario;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblAgendamentoCliente;
    private javax.swing.JLabel lblAgendamentoCodigo;
    private javax.swing.JLabel lblAgendamentoData;
    private javax.swing.JLabel lblAgendamentoEspecialidade;
    private javax.swing.JLabel lblAgendamentoHorario;
    private javax.swing.JLabel lblAgendamentoMedico;
    private javax.swing.JLabel lblAgendamentoUnidade;
    private javax.swing.JTextField txtAgendamentoCliente;
    private javax.swing.JTextField txtAgendamentoCodigo;
    private javax.swing.JTextField txtAgendamentoEspecialidade;
    private javax.swing.JTextField txtAgendamentoMedico;
    private javax.swing.JTextField txtAgendamentoUnidade;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the ConsultaCliente
     */
    public ConsultaCliente getConsultaCliente() {
        return ConsultaCliente;
    }

    /**
     * @param ConsultaCliente the ConsultaCliente to set
     */
    public void setConsultaCliente(ConsultaCliente ConsultaCliente) {
        this.ConsultaCliente = ConsultaCliente;
    }
    
        /**
     * @return the nomeCliente
     */
    public static String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @param aNomeCliente the nomeCliente to set
     */
    public static void setNomeCliente(String aNomeCliente) {
        nomeCliente = aNomeCliente;
    }

    /**
     * @return the nomeUnidade
     */
    public static String getNomeUnidade() {
        return nomeUnidade;
    }

    /**
     * @param aNomeUnidade the nomeUnidade to set
     */
    public static void setNomeUnidade(String aNomeUnidade) {
        nomeUnidade = aNomeUnidade;
    }

    /**
     * @return the ConsultaUnidade
     */
    public ConsultaUnidade getConsultaUnidade() {
        return ConsultaUnidade;
    }

    /**
     * @param ConsultaUnidade the ConsultaUnidade to set
     */
    public void setConsultaUnidade(ConsultaUnidade ConsultaUnidade) {
        this.ConsultaUnidade = ConsultaUnidade;
    }

    /**
     * @return the ConsultaEspecialidade
     */
    public ConsultaEspecialidade getConsultaEspecialidade() {
        return ConsultaEspecialidade;
    }

    /**
     * @param ConsultaEspecialidade the ConsultaEspecialidade to set
     */
    public void setConsultaEspecialidade(ConsultaEspecialidade ConsultaEspecialidade) {
        this.ConsultaEspecialidade = ConsultaEspecialidade;
    }

    /**
     * @return the ConsultaMedico
     */
    public ConsultaMedico getConsultaMedico() {
        return ConsultaMedico;
    }

    /**
     * @param ConsultaMedico the ConsultaMedico to set
     */
    public void setConsultaMedico(ConsultaMedico ConsultaMedico) {
        this.ConsultaMedico = ConsultaMedico;
    }
    
    /**
     * @return the nomeEspecialidade
     */
    public static String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    /**
     * @param aNomeEspecialidade the nomeEspecialidade to set
     */
    public static void setNomeEspecialidade(String aNomeEspecialidade) {
        nomeEspecialidade = aNomeEspecialidade;
    }

    /**
     * @return the nomeMedico
     */
    public static String getNomeMedico() {
        return nomeMedico;
    }

    /**
     * @param aNomeMedico the nomeMedico to set
     */
    public static void setNomeMedico(String aNomeMedico) {
        nomeMedico = aNomeMedico;
    }
}
