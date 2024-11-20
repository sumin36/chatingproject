import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChatServer extends JFrame {
    private JPanel contentPane;
    private JTextArea textArea;
    private JTextField txtPortNumber;
    private ServerSocket serverSocket;
    private Set<PrintWriter> clients = new HashSet<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ChatServer frame = new ChatServer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ChatServer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 338, 386);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 10, 300, 244);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);

        JLabel lblPortNumber = new JLabel("Port Number");
        lblPortNumber.setBounds(12, 264, 87, 26);
        contentPane.add(lblPortNumber);

        txtPortNumber = new JTextField();
        txtPortNumber.setHorizontalAlignment(SwingConstants.CENTER);
        txtPortNumber.setText("30000");
        txtPortNumber.setBounds(111, 264, 199, 26);
        contentPane.add(txtPortNumber);
        txtPortNumber.setColumns(10);

        JButton btnServerStart = new JButton("Server Start");
        btnServerStart.addActionListener(e -> startServer());
        btnServerStart.setBounds(12, 300, 300, 35);
        contentPane.add(btnServerStart);
    }

    private void startServer() {
        int port = Integer.parseInt(txtPortNumber.getText());
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            appendText("서버 소켓 생성 오류");
            return;
        }
        appendText("채팅 서버 실행 중...");
        txtPortNumber.setEnabled(false);

        new Thread(() -> {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    appendText("새로운 참가자 접속: " + clientSocket);
                    new ClientHandler(clientSocket).start();
                } catch (IOException e) {
                    appendText("accept 중 오류 발생");
                    break;
                }
            }
        }).start();
    }

    private void appendText(String str) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(str + "\n");
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String name;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    name = in.readLine();
                    out.println(name+"님이 들어왔습니다.");
                    if (name == null) {
                        return;
                    }
                    if (!name.isEmpty()) {
                        break;
                    }
                }
                
                String joinMessage = name + "님이 들어왔습니다.";
                broadcast(joinMessage);

                appendText(name + "님이 접속했습니다.");
                synchronized (clients) {
                    clients.add(out);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    broadcast(name + ": " + message);
                }
            } catch (IOException e) {
                appendText(e.toString());
            } finally {
                if (name != null) {
                    appendText(name + "님이 나갔습니다.");
                }
                if (out != null) {
                    synchronized (clients) {
                        clients.remove(out);
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }

        private void broadcast(String message) {
            synchronized (clients) {
                for (PrintWriter writer : clients) {
                    writer.println(message);
                }
            }
        }
    }