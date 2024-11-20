import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Insets;

public class SecretMessageFrame extends JFrame {
    //수정
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea chatArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	SecretMessageFrame frame = new SecretMessageFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SecretMessageFrame() {
       setTitle("채팅방");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 550);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("윤소정 (비밀)");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("휴먼모음T", Font.BOLD, 16));
        lblNewLabel.setBounds(70, 10, 168, 24);
        contentPane.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBorder(new LineBorder(new Color(255, 204, 0), 3, true));
        textField.setBackground(new Color(255, 255, 255));
        textField.setHorizontalAlignment(SwingConstants.LEFT);
        textField.setBounds(8, 470, 302, 32);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("전송");
        btnNewButton.setBorder(new LineBorder(new Color(255, 204, 0), 3, true));
        btnNewButton.setBackground(new Color(255, 204, 102));
        btnNewButton.setFont(new Font("휴먼모음T", Font.BOLD, 14));
        btnNewButton.setBounds(315, 470, 59, 32);
        contentPane.add(btnNewButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new LineBorder(new Color(255, 255, 255)));
        scrollPane.setBounds(103, 44, 271, 416);
        contentPane.add(scrollPane);
        
        chatArea = new JTextArea();
        chatArea.setBackground(new Color(255, 255, 255));
        chatArea.setMargin(new Insets(15, 10, 2, 2));
        scrollPane.setViewportView(chatArea);
        chatArea.setFont(new Font("휴먼모음T", Font.PLAIN, 13));
        chatArea.setBorder(new LineBorder(new Color(255, 204, 102), 3, true));
        chatArea.setEditable(false);
        
        JButton btnNewButton_1 = new JButton("<");
        btnNewButton_1.setBorder(new LineBorder(new Color(255, 204, 0), 3, true));
        btnNewButton_1.setFont(new Font("휴먼모음T", Font.BOLD, 12));
        btnNewButton_1.setBackground(new Color(255, 204, 102));
        btnNewButton_1.setBounds(8, 10, 41, 23);
        contentPane.add(btnNewButton_1);
    
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new LineBorder(new Color(255, 204, 102), 3, true));
        textArea.setBounds(8, 44, 83, 416);
        contentPane.add(textArea);
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // 텍스트 필드에서 Enter 키 입력 이벤트
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
    }

    private void sendMessage() {
        String message = textField.getText();
        if (!message.isEmpty()) {
            chatArea.append("윤소정 -" + message + "\n\n");
            textField.setText("");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
        }
    }
}