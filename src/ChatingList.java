import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import java.awt.Image;

public class ChatingList extends JFrame {

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

    public ChatingList() {
        setTitle("Chating List");
        setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 550);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("채팅방 만들기");
        mnNewMenu.setFont(new Font("휴먼모음T", Font.PLAIN, 13));
        mnNewMenu.setBackground(new Color(255, 255, 255));
        mnNewMenu.setForeground(new Color(0, 0, 0));
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("일반 채팅");
        mntmNewMenuItem.setFont(new Font("휴먼모음T", Font.PLAIN, 13));
        mnNewMenu.add(mntmNewMenuItem);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("팀 채팅");
        mntmNewMenuItem_1.setFont(new Font("휴먼모음T", Font.PLAIN, 13));
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFriendList();
            }
        });
        mnNewMenu.add(mntmNewMenuItem_1);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 204, 102));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        String[] columnNames = {"Profile", "Name"};
        Object[][] data = {
            {new ImageIcon("path"), "김종국"},
            {new ImageIcon("path"), "송지효"},
            {new ImageIcon("path"), "유재서"},
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
        table.setShowGrid(false);
        table.setEnabled(false);
        table.setRowHeight(60);  
        table.getColumn("Profile").setMaxWidth(80);  
        table.setFont(new Font("휴먼모음T", Font.BOLD, 12));
        table.getTableHeader().setReorderingAllowed(false);
        table.setDefaultRenderer(ImageIcon.class, new ProfileCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        JLabel NewLabel = new JLabel("");
        NewLabel.setFont(new Font("휴먼모음T", Font.BOLD, 12));
        NewLabel.setBackground(new Color(255, 255, 255));
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/img/chat.png"));

        NewLabel.setText("<html>"
                + "<table>"
                + "<tr>"
                + "<td><img src='" + icon1 + "'></td>"
                + "<td width='120'></td>"
                + "</tr>"
                + "</table>"
                + "</html>");

        NewLabel.setOpaque(true);
        NewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        NewLabel.setPreferredSize(new Dimension(100, 50)); 
        NewLabel.setBorder(new EmptyBorder(0, 160, 0, 0));
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
    }

    private void TransChatingPage() {
       FriendListFrame friendList = new FriendListFrame();
       friendList.setVisible(true);  
        setVisible(false);
    }
    
    private void openFriendList() {
        FriendListFrame friendList = new FriendListFrame();
        friendList.setVisible(true);
        this.setVisible(false);
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

