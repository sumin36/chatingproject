import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JSpinner;

public class ReservationMessageFrame extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField textField;
   private JLabel lblNewLabel_1;
   private JTextPane textPane;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               ReservationMessageFrame frame = new ReservationMessageFrame();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public ReservationMessageFrame() {
      setTitle("메시지 예약");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 400, 550);
      contentPane = new JPanel();
      contentPane.setBackground(new Color(255, 255, 255));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("메시지 예약");
      lblNewLabel.setFont(new Font("휴먼모음T", Font.BOLD, 17));
      lblNewLabel.setBounds(12, 10, 90, 31);
      contentPane.add(lblNewLabel);
      
      textField = new JTextField();
      textField.setToolTipText("");
      textField.setBorder(new LineBorder(new Color(255, 204, 102), 2, true));
      textField.setForeground(new Color(0, 0, 0));
      textField.setFont(new Font("휴먼모음T", Font.PLAIN, 13));
      textField.setBounds(12, 51, 362, 268);
      contentPane.add(textField);
      textField.setColumns(10);
      
      lblNewLabel_1 = new JLabel("윤소정");
      lblNewLabel_1.setFont(new Font("휴먼모음T", Font.PLAIN, 18));
      lblNewLabel_1.setBounds(12, 339, 362, 20);
      contentPane.add(lblNewLabel_1);
      
      textPane = new JTextPane();
      textPane.setBounds(76, 357, 7, 21);
      contentPane.add(textPane);
      
      JLabel lblNewLabel_1_1 = new JLabel("날짜 설정");
      lblNewLabel_1_1.setFont(new Font("휴먼모음T", Font.PLAIN, 18));
      lblNewLabel_1_1.setBounds(12, 376, 72, 20);
      contentPane.add(lblNewLabel_1_1);
      
      JSpinner spinner = new JSpinner();
      spinner.setBounds(95, 377, 30, 22);
      contentPane.add(spinner);
      
      JSpinner spinner_1 = new JSpinner();
      spinner_1.setBounds(137, 377, 30, 22);
      contentPane.add(spinner_1);
      
      JSpinner spinner_2 = new JSpinner();
      spinner_2.setBounds(179, 377, 30, 22);
      contentPane.add(spinner_2);
   }
}
