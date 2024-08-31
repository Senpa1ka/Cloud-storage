import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Облачное хранилище");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Панель для логотипа
        JPanel logoPanel = new JPanel();
        JLabel logoLabel = new JLabel();
        // Здесь можно установить изображение логотипа
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Арсений\\Downloads\\image.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        logoLabel.setIcon(scaledIcon);

        logoPanel.add(logoLabel);

        // Панель для круглой кнопки
        JPanel buttonPanel = new JPanel();
        JButton roundButton = new MainWindowController("Загрузить");
        buttonPanel.add(roundButton);


        final JLabel label = new JLabel("Выбранный файл");
        label.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(label);


        roundButton.setAlignmentX(CENTER_ALIGNMENT);

        roundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    label.setText(file.getName());
                    try {
                        FileTransfer.main(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        });



        // Добавление панелей в окно
        setLayout(new BorderLayout());
        add(logoPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    // Класс для создания круглой кнопки
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}


