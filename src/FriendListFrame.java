import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Component;

public class FriendListFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String userName; // 사용자 이름
    private String pw;
    private String port;
    private JLabel profile; // 사용자 프로필을 표시할 JLabel

    public FriendListFrame() {
        this.userName = "기본 이름";  // 기본 사용자 이름 설정
        this.pw = "";  // 기본 비밀번호 설정
        this.port = "";  // 기본 포트 설정
        initialize();
    }
    
    // 사용자 정보를 인자로 받는 생성자
    public FriendListFrame(String userName, String pw, String port) {
        this.userName = userName;  // 전달된 사용자 이름 저장
        this.pw = pw;
        this.port = port;
        initialize();
    }

    private void initialize() {
        setTitle("Friends List");
        setFont(new Font("휴먼모음T", Font.BOLD, 12));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 550);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 204, 102));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // 친구 목록 테이블 데이터 설정
        String[] columnNames = {"Profile", "Name"};
        Object[][] data = {
            {new ImageIcon("path"), "Friend 1"},
            {new ImageIcon("path"), "Friend 2"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public Class<?> getColumnClass(int column) {
                return (column == 0) ? ImageIcon.class : String.class;
            }
        };

        JTable table = new JTable(model);
        table.setShowHorizontalLines(false);
        table.setShowGrid(false);
        table.setFocusable(false);
        table.setEnabled(false);
        table.setRowHeight(60);
        table.getColumn("Profile").setMaxWidth(80);
        table.setFont(new Font("휴먼모음T", Font.BOLD, 12));

        table.setDefaultRenderer(ImageIcon.class, new ProfileCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 하단 영역에 텍스트 추가
        JLabel NewLabel = new JLabel("");
        NewLabel.setBackground(new Color(255, 255, 255));
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/img/text.png"));
        NewLabel.setText("<html><table><td width='120'></td><td><img src='" + icon1 + "'></td></table></html>");
        NewLabel.setOpaque(true);
        NewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        NewLabel.setPreferredSize(new Dimension(100, 50));
        NewLabel.setBorder(new EmptyBorder(0, 40, 0, 0));
        contentPane.add(NewLabel, BorderLayout.SOUTH);

        // 마우스 클릭 시 채팅 페이지로 이동
        NewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TransChatingPage();
            }
        });

        // 상단에 사용자 프로필 표시
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        contentPane.add(panel, BorderLayout.NORTH);

        profile = new JLabel(""); // profile 레이블 초기화
        profile.setBackground(new Color(255, 255, 255));
        profile.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/img/Profile.png"));

        // 여기에서 userName을 동적으로 업데이트
        updateProfileName();

        profile.setOpaque(true);
        profile.setBorder(new EmptyBorder(0, 0, 0, 0));
        profile.setHorizontalAlignment(SwingConstants.LEFT);
        profile.setPreferredSize(new Dimension(300, 50));
        panel.add(profile);
    }

    // 사용자 이름 업데이트 메소드
    private void updateProfileName() {
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/img/Profile//수정.png"));
        profile.setText("<html><table><tr><td><img src='" + icon3 + "'></td><td>" + userName + "</td></tr></table></html>");
    }

    private void TransChatingPage() {
        ChatingList chatPage = new ChatingList();
        chatPage.setVisible(true);
        setVisible(false);
    }

    private static class ProfileCellRenderer extends JLabel implements TableCellRenderer {
        public ProfileCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
                setText(null);
            } else if (value instanceof String) {
                setText((String) value);
                setIcon(null);
            }
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return this;
        }
    }
}

