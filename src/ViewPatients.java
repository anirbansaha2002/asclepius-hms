import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ViewPatients extends JFrame {
    static Font pageFont = new Font("Tahoma", Font.PLAIN, 16);
    static Color darkBlue = new Color(34, 152, 153);
    static Color white = new Color(245, 245, 245);
    static Color black = new Color(66, 66, 66);

    public ViewPatients() {

        // Create panel for header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(darkBlue);
        headerPanel.setBounds(0, 0, 1000, 30);

        JLabel headerLabel = new JLabel("PATIENT INFORMATION");
        headerLabel.setFont(pageFont);
        headerLabel.setForeground(white);
        headerPanel.add(headerLabel);
        add(headerPanel);

        // Create panel for table
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(black);
        tablePanel.setBounds(0, 30, 1000, 480);
        tablePanel.setLayout(new BorderLayout());  // Change layout to fit table correctly
        add(tablePanel);

        // Create Table and set its model
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);  // Add scrollPane to center of the panel

        // Disable auto resizing of columns to allow custom column widths
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Getting values from the database
        try {

            Conn c = new Conn();
            String q = "SELECT * FROM patient_info";
            ResultSet resultSet = c.statement.executeQuery(q);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add column names to table model
            for (int column = 1; column <= columnCount; column++) {
                tableModel.addColumn(metaData.getColumnName(column));
            }

            // Add rows to the table model
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = resultSet.getObject(i + 1);  // JDBC column index starts from 1
                }
                tableModel.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Table customization
        // Set custom column widths
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(160);  // Set preferred width for each column
        }

        // Table look customization
        table.setBackground(black);
        table.setGridColor(black);
        table.setFont(pageFont);
        table.setForeground(white);
        table.setRowHeight(30);
        table.getTableHeader().setFont(pageFont);
        table.getTableHeader().setBackground(black);
        table.getTableHeader().setForeground(darkBlue);

        JButton backButton = new JButton("Back");
        backButton.setBounds(350, 525, 200, 30);
        backButton.setBackground(darkBlue);
        backButton.setForeground(white);
        backButton.setFont(pageFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent E){
                dispose();
            } 
        });
        add(backButton);


        setBounds(280, 240, 960, 560);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
    }
}
