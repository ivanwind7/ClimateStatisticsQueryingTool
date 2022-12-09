package climatesys;

import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.math.RoundingMode;

public class BuildGUI {
    public static String username = "guest";
    public static String password = "84983c60f7daadc1cb8698621f802c0d9f9a3c3c295c810748fb048115c186ec";
    public static Connection c = null;
    public static Statement stmt = null;
    public static JLabel helloLabel = new JLabel("Hello, guest!", JLabel.CENTER);
    public static JLabel starLabel = new JLabel("star");
    public static Color bkgColor = new Color(68, 114, 196);
    public static Color borderColor = new Color(47, 82, 143);
    public static Color markColor = new Color(97, 134 , 200);
    public static JLabel positionLabel = new JLabel("Seattle", JLabel.CENTER);
    public static JLabel temperatureLabel = new JLabel("7º", JLabel.CENTER);
    public static JLabel conditionLabel = new JLabel("Partly Cloudy", JLabel.CENTER);
    public static JLabel airQualityLabel = new JLabel("78", JLabel.CENTER);
    public static JLabel rainfallLabel = new JLabel("0mm", JLabel.CENTER);
    public static JLabel humidityLabel = new JLabel("100%", JLabel.CENTER);
    public static JLabel pressureLabel = new JLabel("1021mb", JLabel.CENTER);
    public static JLabel windLabel = new JLabel("20m/s", JLabel.CENTER);
    public static JLabel visibilityLabel = new JLabel("30km", JLabel.CENTER);
    public static JLabel timeLabel1 = new JLabel("Now", JLabel.CENTER);
    public static JLabel timeLabel2 = new JLabel("2", JLabel.CENTER);
    public static JLabel timeLabel3 = new JLabel("3", JLabel.CENTER);
    public static JLabel timeLabel4 = new JLabel("4", JLabel.CENTER);
    public static JLabel timeLabel5 = new JLabel("5", JLabel.CENTER);
    public static JLabel timeLabel6 = new JLabel("6", JLabel.CENTER);
    public static JLabel imgLabel1 = new JLabel();
    public static JLabel imgLabel2 = new JLabel();
    public static JLabel imgLabel3 = new JLabel();
    public static JLabel imgLabel4 = new JLabel();
    public static JLabel imgLabel5 = new JLabel();
    public static JLabel imgLabel6 = new JLabel();
    public static JLabel tempLabel1 = new JLabel("1º", JLabel.CENTER);
    public static JLabel tempLabel2 = new JLabel("2º", JLabel.CENTER);
    public static JLabel tempLabel3 = new JLabel("3º", JLabel.CENTER);
    public static JLabel tempLabel4 = new JLabel("4º", JLabel.CENTER);
    public static JLabel tempLabel5 = new JLabel("5º", JLabel.CENTER);
    public static JLabel tempLabel6 = new JLabel("6º", JLabel.CENTER);
    public static JTextField searchBar = new JTextField();
    public static List<String> markedPositions = new ArrayList<>();
    public static JLabel[] markLabelArray = new JLabel[5];
    public static boolean marked = false;
    public static void placeComponents(JPanel panel, JFrame frame) {
        // Set layout for the panel
        panel.setLayout(null);

        // Panels
        JPanel signUpPanel = new JPanel();
        JPanel signInPanel = new JPanel();
        JPanel markerPanel = new JPanel();

        // User panel
        JPanel userPanel = new JPanel();
        userPanel.setBounds(0, 0, 40, 40);
        userPanel.setBackground(bkgColor);
        userPanel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.add(userPanel);
        userPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                userPanel.setBackground(borderColor);
                signUpPanel.setVisible(true);
                signInPanel.setVisible(true);
                markerPanel.setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
                userPanel.setBackground(bkgColor);
                int positionX = e.getX();
                int positionY = e.getY();
                if(!(0 <= positionX && positionX <= 88 && 38 <= positionY && positionY <= 139)) {
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                }
            }
        });
        // User icon
        JLabel userIconLabel = new JLabel();
        ImageIcon userIcon = new ImageIcon("src/resources/user-fill.png");
        userIconLabel.setIcon(userIcon);
        userPanel.add(userIconLabel);
        // Sign up panel
        signUpPanel.setVisible(false);
        signUpPanel.setBounds(0, 38, 80, 35);
        signUpPanel.setBackground(bkgColor);
        signUpPanel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.add(signUpPanel);
        signUpPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                signUpPanel.setBackground(borderColor);
                for(int i = 0; i < markedPositions.size(); i++) {
                    markLabelArray[i].setVisible(false);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
                signUpPanel.setBackground(bkgColor);
                int positionX = e.getX();
                int positionY = e.getY();
                if(!(0 <= positionX && positionX <= 75 && 0 <= positionY && positionY <= 96)) {
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setEnabled(false);
                signUporIn(true, frame);
            }
        });
        JLabel signUpLabel = new JLabel("Sign up");
        signUpLabel.setFont(new Font("", 0, 15));
        signUpLabel.setForeground(Color.WHITE);
        signUpPanel.add(signUpLabel);
        // Sign in panel
        signInPanel.setVisible(false);
        signInPanel.setBounds(0, 71, 80, 35);
        signInPanel.setBackground(bkgColor);
        signInPanel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.add(signInPanel);
        signInPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                signInPanel.setBackground(borderColor);
                for(int i = 0; i < markedPositions.size(); i++) {
                    markLabelArray[i].setVisible(false);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
                signInPanel.setBackground(bkgColor);
                int positionX = e.getX();
                int positionY = e.getY();
                if(!(0 <= positionX && positionX <= 75 && 0 <= positionY && positionY <= 63)) {
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setEnabled(false);
                signUporIn(false, frame);
            }
        });
        JLabel signInLabel = new JLabel("Sign in");
        signInLabel.setFont(new Font("", 0, 15));
        signInLabel.setForeground(Color.WHITE);
        signInPanel.add(signInLabel);
        // Marker panel
        markerPanel.setVisible(false);
        markerPanel.setBounds(0, 104, 80, 35);
        markerPanel.setBackground(bkgColor);
        markerPanel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.add(markerPanel);
        markerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                markerPanel.setBackground(borderColor);
                if(!markedPositions.get(0).equals("")) {
                    for(int i = 0; i < markedPositions.size(); i++) {
                        markLabelArray[i].setVisible(true);
                        markLabelArray[i].setText(markedPositions.get(i));
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
                markerPanel.setBackground(bkgColor);
                int positionX = e.getX();
                int positionY = e.getY();
                if(!((0 <= positionX && positionX <= 75 && 0 <= positionY && positionY <= 30) ||
                        (80 <= positionX && positionX <= 175 && 0 <= positionY && positionY <= 33 * markedPositions.size()))) {
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                    for(int i = 0; i < markedPositions.size(); i++) {
                        markLabelArray[i].setVisible(false);
                    }
                }
            }
        });
        JLabel markerLabel = new JLabel("Marker >");
        markerLabel.setFont(new Font("", 0, 15));
        markerLabel.setForeground(Color.WHITE);
        markerPanel.add(markerLabel);
        // mark label 1
        JLabel markLabel1 = new JLabel("Seattle", JLabel.CENTER);
        markLabel1.setVisible(false);
        markLabel1.setOpaque(true);
        markLabel1.setBounds(78, 104, 100, 35);
        markLabel1.setForeground(Color.WHITE);
        markLabel1.setFont(new Font("", 0, 15));
        markLabel1.setBackground(markColor);
        markLabel1.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        markLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setClimate(getClimate(markLabel1.getText()));
                for(int i = 0; i < markedPositions.size(); i++) {
                    markLabelArray[i].setVisible(false);
                }
                signUpPanel.setVisible(false);
                signInPanel.setVisible(false);
                markerPanel.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                markLabel1.setBackground(borderColor);
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                markLabel1.setBackground(markColor);
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
                int positionX = e.getX();
                int positionY = e.getY();
                if(!((-75 <= positionX && positionX <= 10 && -68 <= positionY && positionY <= 33) ||
                        (0 <= positionX && positionX <= 95 && 0 <= positionY && positionY <= 33 * markedPositions.size()))) {
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                    for(int i = 0; i < markedPositions.size(); i++) {
                        markLabelArray[i].setVisible(false);
                    }
                }
            }
        });
        panel.add(markLabel1);
        // mark label 2
        JLabel markLabel2 = new JLabel("Los Angeles", JLabel.CENTER);
        markLabel2.setVisible(false);
        markLabel2.setOpaque(true);
        markLabel2.setBounds(78, 137, 100, 35);
        markLabel2.setForeground(Color.WHITE);
        markLabel2.setFont(new Font("", 0, 15));
        markLabel2.setBackground(markColor);
        markLabel2.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        markLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setClimate(getClimate(markLabel2.getText()));
                for(int i = 0; i < markedPositions.size(); i++) {
                    markLabelArray[i].setVisible(false);
                }
                signUpPanel.setVisible(false);
                signInPanel.setVisible(false);
                markerPanel.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                markLabel2.setBackground(borderColor);
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                markLabel2.setBackground(markColor);
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
                int positionX = e.getX();
                int positionY = e.getY();
                if(!((-75 <= positionX && positionX <= 10 && -101 <= positionY && positionY <= 0) ||
                        (0 <= positionX && positionX <= 95 && -10 <= positionY && positionY <= 33 * (markedPositions.size() - 1)))) {
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                    for(int i = 0; i < markedPositions.size(); i++) {
                        markLabelArray[i].setVisible(false);
                    }
                }
            }
        });
        panel.add(markLabel2);
        // mark label 3
        JLabel markLabel3 = new JLabel("Beijing", JLabel.CENTER);
        markLabel3.setVisible(false);
        markLabel3.setOpaque(true);
        markLabel3.setBounds(78, 170, 100, 35);
        markLabel3.setForeground(Color.WHITE);
        markLabel3.setFont(new Font("", 0, 15));
        markLabel3.setBackground(markColor);
        markLabel3.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        markLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setClimate(getClimate(markLabel3.getText()));
                for(int i = 0; i < markedPositions.size(); i++) {
                    markLabelArray[i].setVisible(false);
                }
                signUpPanel.setVisible(false);
                signInPanel.setVisible(false);
                markerPanel.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                markLabel3.setBackground(borderColor);
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                markLabel3.setBackground(markColor);
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
                int positionX = e.getX();
                int positionY = e.getY();
                if(!((-75 <= positionX && positionX <= 10 && -134 <= positionY && positionY <= -33) ||
                        (0 <= positionX && positionX <= 95 && -10 <= positionY && positionY <= 33 * (markedPositions.size() - 2)))) {
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                    for(int i = 0; i < markedPositions.size(); i++) {
                        markLabelArray[i].setVisible(false);
                    }
                }
            }
        });
        panel.add(markLabel3);
        // mark label 4
        JLabel markLabel4 = new JLabel("Changsha", JLabel.CENTER);
        markLabel4.setVisible(false);
        markLabel4.setOpaque(true);
        markLabel4.setBounds(78, 203, 100, 35);
        markLabel4.setForeground(Color.WHITE);
        markLabel4.setFont(new Font("", 0, 15));
        markLabel4.setBackground(markColor);
        markLabel4.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        markLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setClimate(getClimate(markLabel4.getText()));
                for(int i = 0; i < markedPositions.size(); i++) {
                    markLabelArray[i].setVisible(false);
                }
                signUpPanel.setVisible(false);
                signInPanel.setVisible(false);
                markerPanel.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                markLabel4.setBackground(borderColor);
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                markLabel4.setBackground(markColor);
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
                int positionX = e.getX();
                int positionY = e.getY();
                if(!((-75 <= positionX && positionX <= 10 && -167 <= positionY && positionY <= -66) ||
                        (0 <= positionX && positionX <= 95 && -10 <= positionY && positionY <= 33 * (markedPositions.size() - 3)))) {
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                    for(int i = 0; i < markedPositions.size(); i++) {
                        markLabelArray[i].setVisible(false);
                    }
                }
            }
        });
        panel.add(markLabel4);
        // mark label 5
        JLabel markLabel5 = new JLabel("New York", JLabel.CENTER);
        markLabel5.setVisible(false);
        markLabel5.setOpaque(true);
        markLabel5.setBounds(78, 236, 100, 35);
        markLabel5.setForeground(Color.WHITE);
        markLabel5.setFont(new Font("", 0, 15));
        markLabel5.setBackground(markColor);
        markLabel5.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        markLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setClimate(getClimate(markLabel5.getText()));
                for(int i = 0; i < markedPositions.size(); i++) {
                    markLabelArray[i].setVisible(false);
                }
                signUpPanel.setVisible(false);
                signInPanel.setVisible(false);
                markerPanel.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                markLabel5.setBackground(borderColor);
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                markLabel5.setBackground(markColor);
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
                int positionX = e.getX();
                int positionY = e.getY();
                if(!((-75 <= positionX && positionX <= 10 && -200 <= positionY && positionY <= -99) ||
                        (0 <= positionX && positionX <= 95 && -10 <= positionY && positionY <= 33 * (markedPositions.size() - 4)))) {
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                    for(int i = 0; i < markedPositions.size(); i++) {
                        markLabelArray[i].setVisible(false);
                    }
                }
            }
        });
        panel.add(markLabel5);

        markLabelArray[0] = markLabel1;
        markLabelArray[1] = markLabel2;
        markLabelArray[2] = markLabel3;
        markLabelArray[3] = markLabel4;
        markLabelArray[4] = markLabel5;

        // Search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBounds(38, 0, 262, 40);
        searchPanel.setBackground(bkgColor);
        searchPanel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.add(searchPanel);
        // search icon
        JLabel searchIconLabel = new JLabel();
        searchIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!searchBar.getText().equals("")) {
                    setClimate(getClimate(searchBar.getText()));
                }
            }
        });
        searchIconLabel.setBounds(226,5,30,30);
        ImageIcon searchIcon = new ImageIcon("src/resources/search.png");
        searchIconLabel.setIcon(searchIcon);
        searchPanel.add(searchIconLabel);
        // search bar
        searchBar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER && !searchBar.getText().equals("")) {
                    setClimate(getClimate(searchBar.getText()));
                    for(int i = 0; i < markedPositions.size(); i++) {
                        markLabelArray[i].setVisible(false);
                    }
                    signUpPanel.setVisible(false);
                    signInPanel.setVisible(false);
                    markerPanel.setVisible(false);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) { }
        });
        searchBar.setBounds(20, 5, 200, 30);
        searchBar.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        searchPanel.add(searchBar);

        // Hello label
        helloLabel.setBounds(0,40,300, 20);
        panel.add(helloLabel);

        // Star Label
        starLabel.setBounds(240, 60, 40, 40);
        starLabel.setIcon(new ImageIcon("src/resources/star-no.png"));
        starLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(marked) {
                    String newBookmarks = "";
                    for(String s: markedPositions) {
                        if(!s.equals(positionLabel.getText())) newBookmarks += s + ",";
                    }
                    markedPositions = Arrays.asList(newBookmarks.split(","));
                    new User(username).delBookmark(c, stmt, newBookmarks);
                    starLabel.setIcon(new ImageIcon("src/resources/star-no.png"));
                    marked = false;
                }else {
                    if(markedPositions.size() >= 5) JOptionPane.showMessageDialog(null, "Marks reach upper limit", "Warning", JOptionPane.WARNING_MESSAGE);
                    else {
                        String newBookmarks = new User(username).addBookmark(c, stmt, positionLabel.getText() + ",");
                        markedPositions = Arrays.asList(newBookmarks.split(","));
                        starLabel.setIcon(new ImageIcon("src/resources/star-yes.png"));
                        marked = true;
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
            }
        });
        panel.add(starLabel);

        // Basic climate panel
        JPanel basicClimatePanel = new JPanel();
        basicClimatePanel.setLayout(null);
        basicClimatePanel.setBounds(82, 60, 134, 134);
        basicClimatePanel.setBackground(bkgColor);
        basicClimatePanel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.add(basicClimatePanel);
        // position
        positionLabel.setBounds(0,0, 134, 34);
        positionLabel.setForeground(Color.WHITE);
        positionLabel.setFont(new Font("", 1, 20));
        basicClimatePanel.add(positionLabel);
        // temperature
        temperatureLabel.setBounds(10,34, 124, 60);
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setFont(new Font("", 1, 50));
        basicClimatePanel.add(temperatureLabel);
        // condition
        conditionLabel.setBounds(0,94, 134, 40);
        conditionLabel.setForeground(Color.WHITE);
        conditionLabel.setFont(new Font("", 1, 15));
        basicClimatePanel.add(conditionLabel);

        // Future climate panel
        JPanel futureClimatePanel = new JPanel();
        futureClimatePanel.setLayout(null);
        futureClimatePanel.setBounds(20, 200, 260, 70);
        futureClimatePanel.setBackground(bkgColor);
        futureClimatePanel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.add(futureClimatePanel);

        timeLabel1.setBounds(3,3,40,12);
        imgLabel1.setBounds(3,15,40,40);
        tempLabel1.setBounds(3,55,40,12);
        timeLabel1.setForeground(Color.WHITE);
        timeLabel1.setFont(new Font("", 1, 12));
        tempLabel1.setForeground(Color.WHITE);
        tempLabel1.setFont(new Font("", 1, 12));
        futureClimatePanel.add(timeLabel1);
        futureClimatePanel.add(imgLabel1);
        futureClimatePanel.add(tempLabel1);

        timeLabel2.setBounds(46,3,40,12);
        imgLabel2.setBounds(46,15,40,40);
        tempLabel2.setBounds(46,55,40,12);
        timeLabel2.setForeground(Color.WHITE);
        timeLabel2.setFont(new Font("", 1, 12));
        tempLabel2.setForeground(Color.WHITE);
        tempLabel2.setFont(new Font("", 1, 12));
        futureClimatePanel.add(timeLabel2);
        futureClimatePanel.add(imgLabel2);
        futureClimatePanel.add(tempLabel2);

        timeLabel3.setBounds(89,3,40,12);
        imgLabel3.setBounds(89,15,40,40);
        tempLabel3.setBounds(89,55,40,12);
        timeLabel3.setForeground(Color.WHITE);
        timeLabel3.setFont(new Font("", 1, 12));
        tempLabel3.setForeground(Color.WHITE);
        tempLabel3.setFont(new Font("", 1, 12));
        futureClimatePanel.add(timeLabel3);
        futureClimatePanel.add(imgLabel3);
        futureClimatePanel.add(tempLabel3);

        timeLabel4.setBounds(132,3,40,12);
        imgLabel4.setBounds(132,15,40,40);
        tempLabel4.setBounds(132,55,40,12);
        timeLabel4.setForeground(Color.WHITE);
        timeLabel4.setFont(new Font("", 1, 12));
        tempLabel4.setForeground(Color.WHITE);
        tempLabel4.setFont(new Font("", 1, 12));
        futureClimatePanel.add(timeLabel4);
        futureClimatePanel.add(imgLabel4);
        futureClimatePanel.add(tempLabel4);

        timeLabel5.setBounds(175,3,40,12);
        imgLabel5.setBounds(175,15,40,40);
        tempLabel5.setBounds(175,55,40,12);
        timeLabel5.setForeground(Color.WHITE);
        timeLabel5.setFont(new Font("", 1, 12));
        tempLabel5.setForeground(Color.WHITE);
        tempLabel5.setFont(new Font("", 1, 12));
        futureClimatePanel.add(timeLabel5);
        futureClimatePanel.add(imgLabel5);
        futureClimatePanel.add(tempLabel5);

        timeLabel6.setBounds(218,3,40,12);
        imgLabel6.setBounds(218,15,40,40);
        tempLabel6.setBounds(218,55,40,12);
        timeLabel6.setForeground(Color.WHITE);
        timeLabel6.setFont(new Font("", 1, 12));
        tempLabel6.setForeground(Color.WHITE);
        tempLabel6.setFont(new Font("", 1, 12));
        futureClimatePanel.add(timeLabel6);
        futureClimatePanel.add(imgLabel6);
        futureClimatePanel.add(tempLabel6);

        // Detail climate panel
        JPanel detailClimatePanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("src/resources/air-quality.png").getImage(),35,10,42,42,this);
                g.drawImage(new ImageIcon("src/resources/pressure.png").getImage(),35,80,42,42,this);
                g.drawImage(new ImageIcon("src/resources/rainfall.png").getImage(),111,12,38,38,this);
                g.drawImage(new ImageIcon("src/resources/wind.png").getImage(),109,80,42,42,this);
                g.drawImage(new ImageIcon("src/resources/humidity.png").getImage(),183,10,42,42,this);
                g.drawImage(new ImageIcon("src/resources/visibility.png").getImage(),183,80,42,42,this);
            }
        };
        detailClimatePanel.setBounds(20, 275, 260, 150);
        detailClimatePanel.setLayout(null);
        detailClimatePanel.setBackground(bkgColor);
        detailClimatePanel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.add(detailClimatePanel);
        // air quality
        airQualityLabel.setBounds(25, 52, 62, 20);
        airQualityLabel.setForeground(Color.WHITE);
        airQualityLabel.setFont(new Font("", 1, 15));
        detailClimatePanel.add(airQualityLabel);
        // rainfall
        rainfallLabel.setBounds(89, 52, 82, 20);
        rainfallLabel.setForeground(Color.WHITE);
        rainfallLabel.setFont(new Font("", 1, 15));
        detailClimatePanel.add(rainfallLabel);
        // humidity
        humidityLabel.setBounds(183, 52, 42, 20);
        humidityLabel.setForeground(Color.WHITE);
        humidityLabel.setFont(new Font("", 1, 15));
        detailClimatePanel.add(humidityLabel);
        // pressure
        pressureLabel.setBounds(15, 122, 82, 20);
        pressureLabel.setForeground(Color.WHITE);
        pressureLabel.setFont(new Font("", 1, 15));
        detailClimatePanel.add(pressureLabel);
        // wind
        windLabel.setBounds(99, 122, 62, 20);
        windLabel.setForeground(Color.WHITE);
        windLabel.setFont(new Font("", 1, 15));
        detailClimatePanel.add(windLabel);
        // visibility
        visibilityLabel.setBounds(173, 122, 62, 20);
        visibilityLabel.setForeground(Color.WHITE);
        visibilityLabel.setFont(new Font("", 1, 15));
        detailClimatePanel.add(visibilityLabel);

        // initial climate info
        setClimate(getClimate("Seattle"));
        // connect db
        connectDB();
        // sign in guest
        User tempUser = new User(username, "guest");
        tempUser.signIn(c, stmt);
        helloLabel.setText("Hello, " + tempUser.getUserName() + "!");
        if(tempUser.getBookmarks() != null) {
            markedPositions = Arrays.asList(tempUser.getBookmarks().split(","));
            for(String s: markedPositions) {
                if(s.equals(positionLabel.getText())) {
                    starLabel.setIcon(new ImageIcon("src/resources/star-yes.png"));
                    marked = true;
                    break;
                }
            }
        }
    }

    private static void connectDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:userDB.db");
            stmt = c.createStatement();
            System.out.println("[DB]OPENED DATABASE SUCCESSFULLY");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    private static Climate getClimate(String city) {
        Climate climate = new Climate();
        climate.setPosition(city);
        climate.setClimateNow();
        climate.setClimateFuture();
        return climate;
    }

    private static boolean setClimate(Climate climate) {
        if(climate.getClimateNow().equals("") || climate.getClimateFuture().equals("")) {
            JOptionPane.showMessageDialog(null, "Climate info is not available", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }else {
            int timeNow;
            JSONObject climateNowJson = new JSONObject(climate.getClimateNow());
            JSONObject locationJson = climateNowJson.getJSONObject("location");
            JSONObject climateInfoJson = climateNowJson.getJSONObject("current");
            positionLabel.setText(locationJson.getString("name"));
            temperatureLabel.setText((int)climateInfoJson.getDouble("temp_c") + "º");
            airQualityLabel.setText(climateInfoJson.getJSONObject("air_quality").getBigDecimal("pm2_5").setScale(1, RoundingMode.HALF_UP) + "");
            rainfallLabel.setText(climateInfoJson.getBigDecimal("precip_mm").setScale(0, RoundingMode.HALF_UP) + "mm");
            humidityLabel.setText(climateInfoJson.getBigDecimal("humidity") + "%");
            pressureLabel.setText(climateInfoJson.getBigDecimal("pressure_mb").setScale(0, RoundingMode.HALF_UP) + "mb");
            windLabel.setText(climateInfoJson.getBigDecimal("wind_mph").setScale(0, RoundingMode.HALF_UP) + "m/s");
            visibilityLabel.setText(climateInfoJson.getBigDecimal("vis_km").setScale(0, RoundingMode.HALF_UP) + "km");
            JSONObject climateFutureJson = new JSONObject(climate.getClimateFuture());
            JSONObject climateFutureCurrentJson = climateFutureJson.getJSONObject("current");
            timeNow = Integer.parseInt(climateFutureCurrentJson.getString("last_updated").split(" ")[1].split(":")[0]);
            timeLabel2.setText((timeNow + 1) % 24 + "");
            timeLabel3.setText((timeNow + 2) % 24 + "");
            timeLabel4.setText((timeNow + 3) % 24 + "");
            timeLabel5.setText((timeNow + 4) % 24 + "");
            timeLabel6.setText((timeNow + 5) % 24 + "");
            JSONArray climateFuture2DaysJsonArray = climateFutureJson.getJSONObject("forecast").getJSONArray("forecastday");
            JSONArray climateFutureDay1JsonArray = climateFuture2DaysJsonArray.getJSONObject(0).getJSONArray("hour");
            JSONArray climateFutureDay2JsonArray = climateFuture2DaysJsonArray.getJSONObject(1).getJSONArray("hour");
            List<String> imgList = new ArrayList<>();
            List<String> tepList = new ArrayList<>();
            for(int i = timeNow; i < 24; i++) {
                JSONObject tempDay = climateFutureDay1JsonArray.getJSONObject(i);
                imgList.add(tempDay.getJSONObject("condition").getString("icon").substring(2));
                tepList.add(tempDay.getBigDecimal("temp_c").setScale(0, RoundingMode.HALF_UP) + "º");
            }
            for(int i = 0; i < timeNow - 18; i++) {
                JSONObject tempDay = climateFutureDay2JsonArray.getJSONObject(i);
                imgList.add(tempDay.getJSONObject("condition").getString("icon").substring(2));
                tepList.add(tempDay.getBigDecimal("temp_c").setScale(0, RoundingMode.HALF_UP) + "º");
            }
            imgLabel1.setIcon(new ImageIcon("src/resources/" + imgList.get(0)));
            imgLabel2.setIcon(new ImageIcon("src/resources/" + imgList.get(1)));
            imgLabel3.setIcon(new ImageIcon("src/resources/" + imgList.get(2)));
            imgLabel4.setIcon(new ImageIcon("src/resources/" + imgList.get(3)));
            imgLabel5.setIcon(new ImageIcon("src/resources/" + imgList.get(4)));
            imgLabel6.setIcon(new ImageIcon("src/resources/" + imgList.get(5)));
            tempLabel1.setText(tepList.get(0));
            tempLabel2.setText(tepList.get(1));
            tempLabel3.setText(tepList.get(2));
            tempLabel4.setText(tepList.get(3));
            tempLabel5.setText(tepList.get(4));
            tempLabel6.setText(tepList.get(5));
            boolean k = true;
            for(String s: markedPositions) {
                if(s.equals(positionLabel.getText())) {
                    starLabel.setIcon(new ImageIcon("src/resources/star-yes.png"));
                    marked = true;
                    k = false;
                    break;
                }
            }
            if(k) {
                starLabel.setIcon(new ImageIcon("src/resources/star-no.png"));
                marked = false;
            }
            return true;
        }
    }

    private static String signUporIn(boolean upOrIn, JFrame dadFrame) {
        JFrame frame;
        if(upOrIn) frame = new JFrame("Sign Up");
        else frame = new JFrame("Sign In");
        frame.setBounds(dadFrame.getX() + 15, dadFrame.getY() + 150, 265, 160);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }
            @Override
            public void windowClosing(WindowEvent e) {
                dadFrame.setEnabled(true);
            }
            @Override
            public void windowClosed(WindowEvent e) { }
            @Override
            public void windowIconified(WindowEvent e) { }
            @Override
            public void windowDeiconified(WindowEvent e) { }
            @Override
            public void windowActivated(WindowEvent e) { }
            @Override
            public void windowDeactivated(WindowEvent e) { }
        });

        // Build a JPanel and add it to frame
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        // username label
        JLabel usernameLabel = new JLabel("username");
        usernameLabel.setBounds(20, 15, 70, 20);
        panel.add(usernameLabel);
        // username field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(100,10,150,30);
        panel.add(usernameField);
        // password label
        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(20, 60, 70, 20);
        panel.add(passwordLabel);
        // password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100,55,150,30);
        panel.add(passwordField);
        // confirm button
        JButton confirmButton = new JButton("confirm");
        confirmButton.setBounds(92, 95, 80,30);
        panel.add(confirmButton);
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                confirmButton.setEnabled(false);
                boolean res;
                User tempUser = new User(usernameField.getText(), passwordField.getText());
                if(upOrIn) res = tempUser.signUp(c, stmt);
                else res = tempUser.signIn(c, stmt);
                if(res) {
                    frame.setVisible(false);
                    dadFrame.setEnabled(true);
                    username = tempUser.getUserName();
                    helloLabel.setText("Hello, " + tempUser.getUserName() + "!");
                    markedPositions = new ArrayList<>();
                    if(tempUser.getBookmarks() != null) markedPositions = Arrays.asList(tempUser.getBookmarks().split(","));
                    boolean k = true;
                    for(String s: markedPositions) {
                        if(s.equals(positionLabel.getText())) {
                            starLabel.setIcon(new ImageIcon("src/resources/star-yes.png"));
                            marked = true;
                            k = false;
                            break;
                        }
                    }
                    if(k) {
                        starLabel.setIcon(new ImageIcon("src/resources/star-no.png"));
                        marked = false;
                    }
                }else {
                    if(upOrIn) JOptionPane.showMessageDialog(null, "The username already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                    else JOptionPane.showMessageDialog(null, "The username or password is incorrect", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                confirmButton.setEnabled(true);
            }
        });
        // Set frame visible
        frame.setVisible(true);
        return "";
    }
}