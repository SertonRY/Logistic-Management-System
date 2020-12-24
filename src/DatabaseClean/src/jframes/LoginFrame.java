package jframes;

import jpanel.LoginPanel;

import java.awt.*;
import javax.swing.*;

/**
 * @author 23
 */
public class LoginFrame extends JFrame {

    public LoginFrame(String database) {
        //设置窗体名字
        this.setTitle(database);
        //设置窗体大小
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        int jfWidth = (int) (width*0.2604);
        int jfHeight = (int) (height*0.648);
        this.setBounds((width-jfWidth)/2,(height-jfHeight)/2,jfWidth,jfHeight);

        //new一个登陆panel
        LoginPanel loginPanel= new LoginPanel(this);
        this.setContentPane(loginPanel);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
    }

}