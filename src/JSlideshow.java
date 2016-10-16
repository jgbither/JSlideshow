import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Josh Bither on 6/1/2016.
 * Take Home Quiz
 */
public class JSlideshow implements ActionListener {

    JFrame jfrm;
    ImageIcon icon = new ImageIcon();
    JButton play;
    int num = 0;
    JButton pause ;
    Timer timer;
    JPanel jpan;
    File file;
    ArrayList<ImageIcon> list = new ArrayList<>();

    JSlideshow(){


        jfrm = new JFrame("Slideshow");
        jfrm.setLayout(new GridLayout(3,1));

        icon = new ImageIcon("JSlideshow.png");

        jfrm.setIconImage(icon.getImage());

        jfrm.setSize(800,600);

        jpan = new JPanel();

        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jfrm.setLocationByPlatform(true);

        play = new JButton("Play");
        play.addActionListener(this);
        pause = new JButton("Pause");
        pause.addActionListener(this);

        try {
            FileReader fileReader = new FileReader(new File("imagelist.txt"));
            Scanner scan = new Scanner(fileReader);
            while(scan.hasNextLine()){
                list.add(new ImageIcon(scan.nextLine()));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(num == list.size()){
                    num = 0;
                }
                if(num < list.size()){
                    icon.setImage(list.get(num).getImage());
                    System.out.println(list.size());
                    num++;
                }

                for(int x = 0; x < list.size(); x++){
                    System.out.println(list.get(x).toString());
                }

            }
        });

        timer.start();

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem load = new JMenuItem("Load Images");
        load.addActionListener(this);
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(this);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        file.add(load);
        file.add(about);
        file.add(exit);
        menuBar.add(file);

        JLabel lab = new JLabel(icon);
        jfrm.setJMenuBar(menuBar);
        jfrm.getContentPane().add(pause);
        jfrm.getContentPane().add(play);
        jfrm.getContentPane().add(lab);

        jfrm.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("Play")){
            timer.start();
        }
        if(ae.getActionCommand().equals("Pause")){
            timer.stop();
        }

        if(ae.getActionCommand().equals("Exit")){
            jfrm.setVisible(false);
            System.exit(0);
        }
        if(ae.getActionCommand().equals("About")){
            JOptionPane.showMessageDialog(jfrm,"JSlideshow version 0.1\nCopyright (c)2016 J.Bither","About",JOptionPane.INFORMATION_MESSAGE,icon);
        }
    }

    public static void main(String[] args){
        new JSlideshow();
    }
}
