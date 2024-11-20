import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

public class FriendListFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FriendListFrame frame = new FriendListFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FriendListFrame() {
        setTitle("Friends List");
        setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 550);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 204, 102));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        String[] columnNames = {"Profile", "Name"};
        Object[][] data = {
                {new ImageIcon("path"), "김종국"},
                {new ImageIcon("path"), "송지효"},
                {new ImageIcon("path"), "유재석"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public Class<?> getColumnClass(int column) {
                return (column == 0) ? ImageIcon.class : String.class;
            }
        };

        JTable table = new JTable(new DefaultTableModel(
                new Object[][] {
                        {null, "김종국"},
                        {null, "송지효"},
                        {null, "유재석"},
                },
                new String[] {
                        "Profile", "Name"
                }
        ));
        table.setShowHorizontalLines(false);
        table.setShowGrid(false);
        table.setFocusable(false);
        table.setFocusTraversalKeysEnabled(false);
        table.setAutoscrolls(false);
        table.setAutoCreateColumnsFromModel(false);
        table.setEnabled(false);
        table.setRowHeight(60);
        table.getColumn("Profile").setMaxWidth(80);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new Font("휴먼모음T", Font.BOLD, 12));

        table.setDefaultRenderer(ImageIcon.class, new ProfileCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JLabel NewLabel = new JLabel("");
        NewLabel.setBackground(new Color(255, 255, 255));
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/img/chat.png"));

        NewLabel.setText("<html>"
                + "<table>"
                + "<td width='120'></td>"
                + "<td><img src='" + icon1 + "'></td>"
                + "</table>"
                + "</html>");

        NewLabel.setOpaque(true);
        NewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        NewLabel.setPreferredSize(new Dimension(100, 50));
        NewLabel.setBorder(new EmptyBorder(0, 40, 0, 0));
        contentPane.add(NewLabel, BorderLayout.SOUTH);

        NewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                TransChatingPage();
            }
        });

        contentPane.add(NewLabel, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new EmptyBorder(0,0,0,0));
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel profile = new JLabel("");
        profile.setBackground(new Color(255, 255, 255));
        profile.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/img/chat.png"));
        String userName = "윤소정";

        profile.setText("<html>"
                + "<table>"
                + "<tr>"
                + "<td><img src='" + icon3 + "'></td>"  // 프로필 이미지
                + "<td>" + userName + "</td>"  // 사용자 이름
                + "</tr>"
                + "</table>"
                + "</html>");

        profile.setOpaque(true);
        profile.setBorder(new EmptyBorder(0, 0, 0, 0)); // 여백 설정
        profile.setHorizontalAlignment(SwingConstants.LEFT);
        profile.setPreferredSize(new Dimension(300, 50));  // 적당한 크기 설정
        panel.add(profile);

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
