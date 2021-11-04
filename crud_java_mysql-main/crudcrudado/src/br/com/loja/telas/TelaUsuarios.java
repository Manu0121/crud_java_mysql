/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.telas;
import java.sql.*;
import br.com.loja.dal.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaUsuarios extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaUsuarios() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void consultar() {
        String sql = "SELECT * FROM usuario WHERE usuarioid=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtID.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                txtNome.setText(rs.getString(2));
                txtLogin.setText(rs.getString(3));
                txtSenha.setText(rs.getString(4));
                txtFone.setText(rs.getString(5));
                comboPerfil.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void adicionar() {
        String sql = "INSERT INTO usuario (usuarioid, user, login, senha, fone, perfil) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, txtID.getText());
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtLogin.getText());
            String pega_senha = new String(txtSenha.getText()); 
            pst.setString(4, pega_senha);
            pst.setString(5, txtFone.getText());
            pst.setString(6, comboPerfil.getSelectedItem().toString());
                        
            if (txtID.getText().isEmpty() || txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || 
            txtSenha.getText().isEmpty() || txtFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                    limpar();
                } 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void remover() {
        int excluir = JOptionPane.showConfirmDialog(null, "Tem certeza de que quer excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (excluir == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM usuario WHERE usuarioid=?";
        
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtID.getText());
                int apaga = pst.executeUpdate();

                if (apaga > 0) {                
                    JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso");
                    limpar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void alterar() {
        String sql = "UPDATE usuario SET user=?, login=?, senha=?, fone=?, perfil=? WHERE usuarioid=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtID.getText());
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtLogin.getText());
            String pega_senha = new String(txtSenha.getText()); 
            pst.setString(4, pega_senha);
            pst.setString(5, txtFone.getText());
            pst.setString(6, comboPerfil.getSelectedItem().toString());

            if (txtID.getText().isEmpty() || txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || 
            txtSenha.getText().isEmpty() || txtFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Falha! Preencha todos os campos.");
            } else {        
                int atualiza = pst.executeUpdate();
                if (atualiza > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso");
                    txtNome.setText(rs.getString(2));
                    txtLogin.setText(rs.getString(3));
                    txtSenha.setText(rs.getString(4));
                    txtFone.setText(rs.getString(5));
                    comboPerfil.setSelectedItem(rs.getString(6));
                }                 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void limpar(){
        txtNome.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
        txtFone.setText(null);
        comboPerfil.setSelectedItem(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFone = new javax.swing.JLabel();
        txtFone = new javax.swing.JTextField();
        lblPerfil = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        comboPerfil = new javax.swing.JComboBox<>();
        txtID = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblLogin = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        alterar = new javax.swing.JButton();
        adicionar = new javax.swing.JButton();
        deletar = new javax.swing.JButton();
        selecionar = new javax.swing.JButton();

        setMaximizable(true);
        setTitle("Usuarios");

        lblFone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFone.setText("Fone");

        txtFone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblPerfil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPerfil.setText("Perfil");

        lblID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblID.setText("ID");

        comboPerfil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user", " " }));

        txtID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNome.setText("Nome");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        lblLogin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLogin.setText("Login");

        txtLogin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblSenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSenha.setText("Senha");

        txtSenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/update.png"))); // NOI18N
        alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarActionPerformed(evt);
            }
        });

        adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/create.png"))); // NOI18N
        adicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarActionPerformed(evt);
            }
        });

        deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/delete.png"))); // NOI18N
        deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarActionPerformed(evt);
            }
        });

        selecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/pesquisar.png"))); // NOI18N
        selecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblID)
                    .addComponent(lblLogin)
                    .addComponent(lblFone))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(lblNome))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSenha)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(alterar)
                            .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selecionar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPerfil)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deletar)
                            .addComponent(comboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogin)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFone)
                        .addComponent(comboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPerfil))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alterar)
                    .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(selecionar)
                    .addComponent(deletar))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_adicionarActionPerformed

    private void deletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarActionPerformed
        remover();
    }//GEN-LAST:event_deletarActionPerformed

    private void selecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecionarActionPerformed
        consultar();
    }//GEN-LAST:event_selecionarActionPerformed

    private void alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarActionPerformed
        alterar();
    }//GEN-LAST:event_alterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JButton alterar;
    private javax.swing.JComboBox<String> comboPerfil;
    private javax.swing.JButton deletar;
    private javax.swing.JLabel lblFone;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JButton selecionar;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}
