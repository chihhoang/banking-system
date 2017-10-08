/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.menu;

import com.bank.filefilter.BofFilter;
import com.bank.models.Bank;
import com.bank.models.Customer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chihoang
 */
public class MainMenu extends javax.swing.JFrame {
  
  private Bank bank;
  private String savedLocation;
  /**
     * Creates new form MainMenu
     */
    public MainMenu() {
      initComponents();
      setLocationRelativeTo(null);
      setTitle("Bank Application");
      bank = new Bank();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    contentPane = new javax.swing.JPanel();
    addButton = new javax.swing.JButton();
    removeButton = new javax.swing.JButton();
    depositButton = new javax.swing.JButton();
    withdrawButton = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    accountTable = new javax.swing.JTable();
    menuBar = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    saveAsMenuItem = new javax.swing.JMenuItem();
    saveMenuItem = new javax.swing.JMenuItem();
    openMenuItem = new javax.swing.JMenuItem();
    exitMenuItem = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    addButton.setText("Add Account");
    addButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addButtonActionPerformed(evt);
      }
    });

    removeButton.setText("Remove Account");
    removeButton.setEnabled(false);
    removeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        removeButtonActionPerformed(evt);
      }
    });

    depositButton.setText("Deposit");
    depositButton.setEnabled(false);
    depositButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        depositButtonActionPerformed(evt);
      }
    });

    withdrawButton.setText("Withdrawal");
    withdrawButton.setEnabled(false);
    withdrawButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        withdrawButtonActionPerformed(evt);
      }
    });

    accountTable.setAutoCreateRowSorter(true);
    accountTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    accountTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "First Name", "Last Name", "Account Number", "Balance"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    accountTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    accountTable.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        accountTableMouseClicked(evt);
      }
    });
    jScrollPane1.setViewportView(accountTable);

    javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
    contentPane.setLayout(contentPaneLayout);
    contentPaneLayout.setHorizontalGroup(
      contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(contentPaneLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(contentPaneLayout.createSequentialGroup()
            .addComponent(addButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(removeButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(depositButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(withdrawButton)
            .addGap(0, 20, Short.MAX_VALUE))
          .addComponent(jScrollPane1))
        .addContainerGap())
    );
    contentPaneLayout.setVerticalGroup(
      contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(contentPaneLayout.createSequentialGroup()
        .addGap(15, 15, 15)
        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(addButton)
          .addComponent(removeButton)
          .addComponent(depositButton)
          .addComponent(withdrawButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
        .addContainerGap())
    );

    fileMenu.setText("File");

    saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
    saveAsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bank/resources/saveIcon.png"))); // NOI18N
    saveAsMenuItem.setText("Save As...");
    saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveAsMenuItemActionPerformed(evt);
      }
    });
    fileMenu.add(saveAsMenuItem);

    saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.META_MASK));
    saveMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bank/resources/saveIcon.png"))); // NOI18N
    saveMenuItem.setText("Save");
    saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveMenuItemActionPerformed(evt);
      }
    });
    fileMenu.add(saveMenuItem);

    openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.META_MASK));
    openMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bank/resources/openIcon.png"))); // NOI18N
    openMenuItem.setText("Open");
    openMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        openMenuItemActionPerformed(evt);
      }
    });
    fileMenu.add(openMenuItem);

    exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bank/resources/exit.png"))); // NOI18N
    exitMenuItem.setText("Exit");
    exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        exitMenuItemActionPerformed(evt);
      }
    });
    fileMenu.add(exitMenuItem);

    menuBar.add(fileMenu);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
      int selectedRow = accountTable.getSelectedRow();
      if (selectedRow >= 0) {
        Customer customer = getSelectedCustomer(selectedRow);
        if (customer != null) {
          removeCustomerFromTable(customer);
          bank.remove(customer);
        }
      }
    }//GEN-LAST:event_removeButtonActionPerformed
    
    // get selected customer from the table
    private Customer getSelectedCustomer(int selectedRow) {
      Customer customer = null;
      
      if (selectedRow >= 0) {
        int accountNumber = (int) accountTable.getValueAt(selectedRow, 2);
        customer = bank.getCustomerByAccountNumber(accountNumber);
      }
      return customer;
    }
    
    private void depositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositButtonActionPerformed
      int selectedRow = accountTable.getSelectedRow();      
      Customer customer = getSelectedCustomer(selectedRow);
      
      if (customer != null) {
        DepositMenu menu = new DepositMenu(this, true, customer);
        menu.setVisible(true);
        reloadCustomerRowData(selectedRow, customer);        
      }
    }//GEN-LAST:event_depositButtonActionPerformed

    private void withdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawButtonActionPerformed
      int selectedRow = accountTable.getSelectedRow();      
      Customer customer = getSelectedCustomer(selectedRow);
      
      if (customer != null) {
        WithdrawMenu menu = new WithdrawMenu(this, true, customer);
        menu.setVisible(true);
        reloadCustomerRowData(selectedRow, customer);
      }
    }//GEN-LAST:event_withdrawButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
      AddAccountMenu menu = new AddAccountMenu(this, true, bank);
      menu.setVisible(true);

      if (menu.getCustomer() != null) {
        addCustomerToTable(menu.getCustomer());
      }
    }//GEN-LAST:event_addButtonActionPerformed

  private void accountTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountTableMouseClicked
    setAccountButtonsActive(true);
    
    if (evt.getClickCount() == 2) {
      int selectedRow = accountTable.getSelectedRow();      
      Customer customer = getSelectedCustomer(selectedRow);
      
      if (customer != null) {
        AccountDetailsPage details = new AccountDetailsPage(this, true, customer);
        details.setVisible(true);
      }
    }
  }//GEN-LAST:event_accountTableMouseClicked

  private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
    JFileChooser chooser = new JFileChooser();
    // Display only directories or .bof files
    chooser.setFileFilter(new BofFilter());
    chooser.setAcceptAllFileFilterUsed(false);
    
    int result = chooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      String fileName = file.toString();
      if (!fileName.endsWith(".bof")) {
        fileName += ".bof";
      }
      
      if (saveFile(fileName)) {
        savedLocation = fileName;
      };
    }
  }//GEN-LAST:event_saveAsMenuItemActionPerformed
  
  private boolean saveFile(String fileName) {
    try (OutputStream fOut = new FileOutputStream(fileName); ObjectOutputStream objOut = new ObjectOutputStream(fOut)) {
      objOut.writeObject(bank);
      JOptionPane.showMessageDialog(this, "Your bank data has been saved", "Data Saved", JOptionPane.INFORMATION_MESSAGE);
      return true;
    } catch (FileNotFoundException ex) {
      return false;
    } catch (IOException ex) {
      return false;
    }
  }
  
  private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new BofFilter());
    chooser.setAcceptAllFileFilterUsed(false);
    
    int result = chooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      if (!chooser.getSelectedFile().toString().toLowerCase().endsWith(".bof")) {
        JOptionPane.showMessageDialog(this, "Unsupported type selected", "Unsupported File", JOptionPane.ERROR_MESSAGE);
      } else {
        try (InputStream fIn = new FileInputStream(chooser.getSelectedFile()); ObjectInputStream objIn = new ObjectInputStream(fIn)) {
          Object bankData = objIn.readObject();
          if (bankData instanceof Bank) {
            bank = (Bank) bankData;
            reloadTable();
          }
          
          savedLocation = chooser.getSelectedFile().toString();
        } catch (FileNotFoundException ex) {
          Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
          Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }//GEN-LAST:event_openMenuItemActionPerformed

  private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
    if (savedLocation != null) {
      saveFile(savedLocation);
    } else {
      saveAsMenuItemActionPerformed(evt);
    }
  }//GEN-LAST:event_saveMenuItemActionPerformed
    
    private void addCustomerToTable(Customer customer) {
      DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
      model.addRow(new Object[]{});
      reloadCustomerRowData(model.getRowCount() - 1, customer);
    }
    
    private void removeCustomerFromTable(Customer customer) {
      DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
      model.removeRow(accountTable.getSelectedRow());
    }
    
    private void reloadCustomerRowData(int selectedRow, Customer customer) {
      NumberFormat formatter = NumberFormat.getCurrencyInstance();
      
      DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
      model.setValueAt(customer.getFirstName(), selectedRow, 0);
      model.setValueAt(customer.getLastName(), selectedRow, 1);
      model.setValueAt(customer.getAccount().getAccountNumber(), selectedRow, 2);
      model.setValueAt(formatter.format(customer.getAccount().getBalance()), selectedRow, 3);
    }
    
    // reload the table after deserialization
    private void reloadTable() {
      for (Customer c : bank.getCustomers()) {
        addCustomerToTable(c);
      }
    }
    
    private void setAccountButtonsActive(boolean active) {
      removeButton.setEnabled(active);
      depositButton.setEnabled(active);
      withdrawButton.setEnabled(active);
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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTable accountTable;
  private javax.swing.JButton addButton;
  private javax.swing.JPanel contentPane;
  private javax.swing.JButton depositButton;
  private javax.swing.JMenuItem exitMenuItem;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem openMenuItem;
  private javax.swing.JButton removeButton;
  private javax.swing.JMenuItem saveAsMenuItem;
  private javax.swing.JMenuItem saveMenuItem;
  private javax.swing.JButton withdrawButton;
  // End of variables declaration//GEN-END:variables

}
