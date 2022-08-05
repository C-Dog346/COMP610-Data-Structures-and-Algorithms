/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa_assignment_2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Callum
 */
public class FileSorterGUI extends JPanel implements ActionListener {

    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JLabel number;
    private JLabel maxStrings;
    private JTextField maxStringsField;
    private JLabel input;
    private JTextField inputField;
    private JLabel output;
    private JTextField outputField;
    private JLabel mergeProgress;
    private JLabel splitProgress;
    private JButton processTask;
    private JButton enqueueTask;

    public FileSorterGUI() {
        super(new BorderLayout());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        this.mainPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.number = new JLabel("Number of items in queue: 0");
        this.maxStrings = new JLabel("Max Strings in memory:");
        this.maxStringsField = new JTextField();
        this.input = new JLabel("Input File:");
        this.inputField = new JTextField();
        this.output = new JLabel("Output File:");
        this.outputField = new JTextField();
        this.mergeProgress = new JLabel("Merge Progress:");
        this.splitProgress = new JLabel("Split Progress:");
        this.processTask = new JButton("Process Task");
        this.enqueueTask = new JButton("Enqueue Task");

        this.processTask.addActionListener((ActionListener) this);
        this.enqueueTask.addActionListener((ActionListener) this);

        this.mainPanel.setLayout(new GridLayout(11, 0));
        this.mainPanel.add(this.number);
        this.mainPanel.add(this.maxStrings);
        this.mainPanel.add(this.maxStringsField);
        this.mainPanel.add(this.input);
        this.mainPanel.add(this.inputField);
        this.mainPanel.add(this.output);
        this.mainPanel.add(this.outputField);
        this.mainPanel.add(this.mergeProgress);
        this.mainPanel.add(this.splitProgress);
        this.mainPanel.setPreferredSize(new Dimension(400, 200));

        this.buttonPanel.add(this.processTask);
        this.buttonPanel.add(this.enqueueTask);

        super.add(this.mainPanel, BorderLayout.CENTER);
        super.add(this.buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        try {
            int limit = Integer.parseInt(this.maxStringsField.getText());
            if (source == this.processTask) {
                FileSorter fileSorter = new FileSorter(limit, new File("./resources/" + this.inputField.getText()),
                        new File("./resources/" + this.outputField.getText()));
                Thread thread = new Thread(fileSorter);
                thread.start();
            }
            else if (source == this.enqueueTask) {

            }
        }
        catch (Exception NumberFormatException) {
            System.err.println("Please enter a valid number!");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("FileSorter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FileSorterGUI());
        frame.pack();
        frame.setLocationRelativeTo(null); //pack frame
        frame.setVisible(true);
    }
}
