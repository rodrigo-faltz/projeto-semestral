package projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLeaderBoard extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private String[][] partidas;
    private RecebeDoDB receba = new RecebeDoDB();

    public TelaLeaderBoard() {
        
        // Set the title of the window
        
        setTitle("Leaderboard");

        // Set the layout for the frame (BorderLayout)
        setLayout(new BorderLayout());

        // Create a JComboBox and add some items
        String[] comboBoxItems = {"Vitorias por Ano", "Vitorias por Mes", "Vitorias por Semana",
                                    "Derrotas por Ano", "Derrotas por Mes", "Derrotas por Semana"};
        JComboBox<String> comboBox = new JComboBox<>(comboBoxItems);
        // Add JComboBox to the top (BorderLayout.NORTH)
        add(comboBox, BorderLayout.NORTH);

        // Create a JTable with an empty data model
        String[] initialColumnNames = {"Jogador", "Qtd de Vitorias"};
        tableModel = new DefaultTableModel(new Object[][]{}, initialColumnNames);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        // Add JTable to the center (BorderLayout.CENTER)
        add(tableScrollPane, BorderLayout.CENTER);

        // Create a JButton
        JButton button = new JButton("Voltar");
        // Add JButton to the bottom (BorderLayout.SOUTH)
        add(button, BorderLayout.SOUTH);

        comboBox.setSelectedItem("Vitorias por Ano");  // Start with "Vitorias por Ano"
        updateTableData("Vitorias por Ano");

        // Add ActionListener to JComboBox to update table data and column names when selection changes
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                updateTableData(selectedOption);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial();
                dispose();
            }
        });

        // Set default close operation and window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    // Method to update the table data and column names based on the selected item in the combo box
    private void updateTableData(String selectedOption) {
        Object[][] data = new Object[][]{};
        String[] columnNames = new String[]{};

        // Change the data and column names based on the selected option
        switch (selectedOption) {
            case "Vitorias por Ano":
                partidas = receba.leaderboard(true, "year");
                data = partidas;
                columnNames = new String[]{"Jogador", "Qtd de Vitorias"};
                break;
            case "Vitorias por Mes":
                partidas = receba.leaderboard(true, "month");
                data = partidas;
                columnNames = new String[]{"Jogador", "Qtd de Vitorias"};
                break;
            case "Vitorias por Semana":
                partidas = receba.leaderboard(true, "week");
                data = partidas;
                columnNames = new String[]{"Jogador", "Qtd de Vitorias"};
                break;
            case "Derrotas por Ano":
                partidas = receba.leaderboard(false, "year");
                data = partidas;
                columnNames = new String[]{"Jogador", "Qtd de Derrotas"};
                break;
            case "Derrotas por Mes":
                partidas = receba.leaderboard(false, "month");
                data = partidas;
                columnNames = new String[]{"Jogador", "Qtd de Derrotas"};
                break;
            case "Derrotas por Semana":
                partidas = receba.leaderboard(false, "week");
                data = partidas;
                columnNames = new String[]{"Jogador", "Qtd de Derrotas"};
                break;
        }

        // Update the table model with new data and column names
        tableModel.setDataVector(data, columnNames);
    }
}
