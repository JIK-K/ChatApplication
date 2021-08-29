
package GuiComponent;

import Data.User;

public class LogPrint extends javax.swing.JPanel {
    User user = new User();
    
    public LogPrint() {
        initComponents();
        printingLog(user);
    }
    public void printingLog(User user){
        PrintLabel.setText(user.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PrintLabel = new javax.swing.JLabel();

        PrintLabel.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N
        PrintLabel.setText("나와라");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PrintLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PrintLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PrintLabel;
    // End of variables declaration//GEN-END:variables

}
