import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adm
 */
public class vendasVIEW extends javax.swing.JFrame {

    private javax.swing.JTable listaVendas;

    /**
     * Creates new form vendasVIEW
     */
    public vendasVIEW() {
        initComponents();
        listarProdutosVendidos();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas");

        javax.swing.JLabel titulo = new javax.swing.JLabel("Produtos Vendidos");
        titulo.setFont(new java.awt.Font("Lucida Fax", 0, 18));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        listaVendas = new javax.swing.JTable();
        listaVendas.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nome", "Valor", "Status"}
        ));
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(listaVendas);

        javax.swing.JButton btnVoltar = new javax.swing.JButton("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        javax.swing.JPanel rodape = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        rodape.add(btnVoltar);

        javax.swing.JPanel painel = new javax.swing.JPanel(new java.awt.BorderLayout(10, 10));
        painel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painel.add(titulo, java.awt.BorderLayout.NORTH);
        painel.add(scroll, java.awt.BorderLayout.CENTER);
        painel.add(rodape, java.awt.BorderLayout.SOUTH);

        setContentPane(painel);
        setSize(480, 320);
        setLocationRelativeTo(null);
    }

    private void listarProdutosVendidos(){
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();

            DefaultTableModel model = (DefaultTableModel) listaVendas.getModel();
            model.setNumRows(0);

            ArrayList<ProdutosDTO> vendidos = produtosdao.listarProdutosVendidos();

            for (int i = 0; i < vendidos.size(); i++) {
                model.addRow(new Object[]{
                    vendidos.get(i).getId(),
                    vendidos.get(i).getNome(),
                    vendidos.get(i).getValor(),
                    vendidos.get(i).getStatus()
                });
            }
        } catch (Exception e) {
        }
    }
}
