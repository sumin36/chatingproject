import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import ChatEx.JavaChatClientView;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblWelcome;
    private JPasswordField passwordField;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JPasswordField portField;
    private Socket socket;
    private PrintWriter out;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginFrame() {
        setTitle("로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 550);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 204, 102));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Yellow Chat");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 78, 386, 30);
        lblNewLabel.setFont(new Font("휴먼모음T", Font.BOLD, 22));
        contentPane.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBorder(new LineBorder(new Color(255, 204, 102), 1, true));
        textField.setFont(new Font("휴먼모음T", Font.PLAIN, 13));
        textField.setHorizontalAlignment(SwingConstants.LEFT);
        textField.setBounds(99, 132, 183, 30);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("로그인");
        btnNewButton.setBorder(new LineBorder(new Color(255, 204, 102), 1, true));
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("휴먼모음T", Font.BOLD, 12));
        btnNewButton.setBounds(99, 331, 183, 30);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 사용자가 입력한 포트 번호와 이름
                    String id = textField.getText().trim();
                    String port = new String(portField.getPassword()).trim();

                    // 서버와 연결
                    socket = new Socket("localhost", Integer.parseInt(port));
                    out = new PrintWriter(socket.getOutputStream(), true);
                    
                    // 서버에 이름 전송 (입장 메시지)
                    out.println(id);
//수정
                    // 서버로부터 응답을 기다리는 부분 (예: "Name님이 입장하셨습니다.")
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String serverMessage = in.readLine();
                    appendText(serverMessage); // 서버의 메시지를 출력

                    // 서버와 연결된 후 프레임 전환
                    FriendListFrame friendListFrame = new FriendListFrame();
                    friendListFrame.setVisible(true);
                    dispose(); // 현재 로그인 창 닫기
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "서버와 연결할 수 없습니다.", "연결 실패", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnNewButton);

        lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setFont(new Font("휴먼모음T", Font.BOLD, 15));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(37, 140, 50, 15);
        contentPane.add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("PW");
        lblNewLabel_2.setFont(new Font("휴먼모음T", Font.BOLD, 15));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(37, 190, 50, 15);
        contentPane.add(lblNewLabel_2);
        
        lblNewLabel_3 = new JLabel("Port");
        lblNewLabel_3.setFont(new Font("휴먼모음T", Font.BOLD, 15));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(37, 240, 50, 15);
        contentPane.add(lblNewLabel_3);
        
        lblWelcome = new JLabel("Welcome!");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setFont(new Font("휴먼모음T", Font.BOLD, 22));
        lblWelcome.setBounds(0, 38, 386, 30);
        contentPane.add(lblWelcome);
        
        passwordField = new JPasswordField();
        passwordField.setBorder(new LineBorder(new Color(255, 204, 102), 1, true));
        passwordField.setBounds(99, 183, 183, 30);
        contentPane.add(passwordField);
        
        portField = new JPasswordField();
        portField.setBorder(new LineBorder(new Color(255, 204, 102), 1, true));
        portField.setBounds(99, 237, 183, 30);
        contentPane.add(portField);
    }

    private void appendText(String str) {
        // 여기에 서버 메시지를 처리하는 메서드 작성 (예: 출력창에 띄우기)
        JOptionPane.showMessageDialog(this, str, "서버 메시지", JOptionPane.INFORMATION_MESSAGE);
    }
}

