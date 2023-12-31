package codSoftJava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class GradeCalculator {
	private JTextField[] subjectFields;
    private JLabel resultLabel;

    public GradeCalculator() {
        JFrame frame = new JFrame("Grade Calculator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel subjectLabel = new JLabel("Number of Subjects:");
        subjectLabel.setBounds(20, 20, 150, 20);
        frame.add(subjectLabel);

        JTextField numSubjectsField = new JTextField();
        numSubjectsField.setBounds(180, 20, 50, 20);
        frame.add(numSubjectsField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 20, 100, 20);
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numSubjects = Integer.parseInt(numSubjectsField.getText());
                createSubjectInputForm(numSubjects);
            }
        });

        resultLabel = new JLabel("");
        resultLabel.setBounds(20, 60, 350, 80);
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    private void createSubjectInputForm(int numSubjects) {
        JFrame inputFrame = new JFrame("Enter Marks");
        inputFrame.setSize(400, 250);
        inputFrame.setLayout(null);

        subjectFields = new JTextField[numSubjects];

        int yOffset = 20;
        for (int i = 0; i < numSubjects; i++) {
            JLabel subjectLabel = new JLabel("Subject " + (i + 1) + ":");
            subjectLabel.setBounds(20, yOffset, 70, 20);
            inputFrame.add(subjectLabel);

            subjectFields[i] = new JTextField();
            subjectFields[i].setBounds(100, yOffset, 50, 20);
            inputFrame.add(subjectFields[i]);

            yOffset += 30;
        }

        JButton calculateResultButton = new JButton("Calculate Results");
        calculateResultButton.setBounds(20, yOffset, 150, 30);
        inputFrame.add(calculateResultButton);

        calculateResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalMarks = 0;
                for (int i = 0; i < numSubjects; i++) {
                    totalMarks += Integer.parseInt(subjectFields[i].getText());
                }

                double averagePercentage = (double) totalMarks / numSubjects;
                char grade = calculateGrade(averagePercentage);

                resultLabel.setText("Total Marks: " + totalMarks + "  |  Average Percentage: " +
                        averagePercentage + "%  |  Grade: " + grade);
            }
        });

        JButton submitMarksButton = new JButton("Submit Marks");
        submitMarksButton.setBounds(180, yOffset, 120, 30);
        inputFrame.add(submitMarksButton);

        submitMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResultButton.doClick(); // Simulate a click on the Calculate Results button
            }
        });
        inputFrame.setVisible(true);
    }

    private char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculator();
            }
        });
    }
}