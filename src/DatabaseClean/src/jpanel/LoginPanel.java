package jpanel;


import jframes.LoginFrame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;


/**
 * @author Hongwei Ma on 2020/12/18 19:40
*/


public class LoginPanel extends JPanel{
    int Owidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int Oheight = Toolkit.getDefaultToolkit().getScreenSize().height;

    int jfWidth = (int) (Owidth*0.2604);
    int jfHeight = (int) (Oheight*0.648);
    Image image = null;
    JFrame jf;

    public static String ID;
    public static int type;


    String password;

    public LoginPanel(JFrame jf){
        this.jf = jf;
        LoginPanel();

    }

    public void LoginPanel() {


        removeAll();
        updateUI();

        URL imageURL = LoginFrame.class.getResource("/images/header.jpg");
        try {
            image = ImageIO.read(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }



        Container con = this;
        //设置为自定义布局
        con.setLayout(null);

         /**
         * widthText     是提示文字的X轴坐标
         * widthField    是输入框的X轴坐标
         *
         * hadithFirst  是第1个横行的Y轴坐标
         * hadithSecond 是第2个横行的Y轴坐标
         * hadithThird  是第3个横行的Y轴坐标
          */


        int widthText = (int) (jfWidth*0.16);
        int widthField = (int) (jfWidth*0.3);

        int hadithFirst = (int) (jfHeight*0.214);
        int hadithSecond = (int) (jfHeight*0.357);
        int hadithThird = (int) (jfHeight*0.5);


        //设置登录选择提示文字
        JLabel jlabelBox = new JLabel("您是:");
        jlabelBox.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)));
        jlabelBox.setBounds(widthText, hadithFirst, (int) (jfWidth*0.2), (int) (jfHeight*0.071));

        //登录选择下拉框定义
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("买家");
        comboBox.addItem("商家");
        comboBox.addItem("管理员");
        comboBox.setFont(new Font("宋体", 52, (int) (jfHeight*0.03135)));
        comboBox.setBounds((int) (jfWidth*0.4), hadithFirst, (int) (jfWidth*0.2), (int) (jfHeight*0.057));


        //设置账号提示文字
        JLabel jlabelID = new JLabel("手机号:");
        jlabelID.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+1));
        jlabelID.setBounds(widthText-27, hadithSecond, (int) (jfWidth*0.2), (int) (jfHeight*0.057));

        //设置密码提示文字
        JLabel jlabelMM = new JLabel("密码:");
        jlabelMM.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+1));
        jlabelMM.setBounds(widthText, hadithThird, (int) (jfWidth*0.2), (int) (jfHeight*0.057));

        //定义账号输入框
        JTextField IDField = new JTextField(20);
        IDField.setFont(new Font("宋体", 52, (int) (jfHeight*0.03135)+2));
        IDField.setBounds(widthField, hadithSecond, (int) (jfWidth*0.5), (int) (jfHeight*0.057));

        //定义密码输入框
        JPasswordField jPasswordField = new JPasswordField(20);
        jPasswordField.setFont(new Font("宋体", 52, (int) (jfHeight*0.03135)+2));
        jPasswordField.setBounds(widthField, hadithThird, (int) (jfWidth*0.5), (int) (jfHeight*0.057));

        //定义登录按钮
        JButton BtnLogin= new JButton("登录");
        BtnLogin.setFont(new Font("宋体",Font.BOLD,(int) (jfHeight*0.03135)+2));
        BtnLogin.setBounds((int) (jfWidth*0.25),(int) (jfHeight*0.714),(int) (jfWidth*0.2),(int) (jfHeight*0.057));

        //定义注册按钮
        JButton BtnCreate = new JButton("注册");
        BtnCreate.setFont(new Font("宋体",Font.BOLD,(int) (jfHeight*0.03135)+2));
        BtnCreate.setBounds((int) (jfWidth*0.55),(int) (jfHeight*0.714),(int) (jfWidth*0.2),(int) (jfHeight*0.057));

        con.add(jlabelBox);
        con.add(comboBox);
        con.add(jlabelID);
        con.add(jlabelMM);
        con.add(IDField);
        con.add(jPasswordField);
        con.add(BtnLogin);
        con.add(BtnCreate);

        BtnCreate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                RegisterPanel();
            }
        });

        //登录
        BtnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ID = IDField.getText();
                password = String.valueOf(jPasswordField.getPassword());

                if (comboBox.getSelectedItem() == "买家") {
                    LoginPanel.type = 0;
                }
                if (comboBox.getSelectedItem() == "商家") {
                    LoginPanel.type = 1;
                }
                switch (Response.login(type,ID,password)) {
                    case Done : {
                        jf.setVisible(false);
                        jf.setBounds(0, (int)(Oheight*0.037), Owidth, (int)(Oheight*0.963));
                        MainPanel();
                        jf.setVisible(true);
                        break;
                    }
                    case IncorrectUID : {
                        JOptionPane.showMessageDialog(jf,
                                "<html><font size=14>账号不存在!", "错误",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    case IncorrectPassword : {
                        JOptionPane.showMessageDialog(jf,
                                "<html><font size=14>密码错误!", "错误",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    default : {
                        break;
                    }
                }
            }
        });
    }

    public void RegisterPanel()   {


        removeAll();
        updateUI();
        URL imageURL = LoginFrame.class.getResource("/images/header.jpg");
        try {
            image = ImageIO.read(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Container con = this;
        //设置为自定义布局
        con.setLayout(null);

        /**
         * widthText     是提示文字的X轴坐标
         * widthField    是输入框的X轴坐标
         *
         * hadithFirst  是第1个横行的Y轴坐标
         * hadithSecond 是第2个横行的Y轴坐标
         * hadithThird  是第3个横行的Y轴坐标
         */

        JButton BtnBack = new JButton("<-");
        BtnBack.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)-6));
        BtnBack.setBounds(10, 10, (int) (jfWidth * 0.1)+10, (int)(jfHeight * 0.036)+5);

        int widthText = (int) (jfWidth*0.16);
        int widthField = (int)(jfWidth*0.3);

        int hadithFirst = (int)(jfHeight * 0.1);
        int hadithSecond = (int)(jfHeight * 0.214);
        int hadithThird = (int)(jfHeight * 0.328);
        int hadithFourth = (int)(jfHeight * 0.443);
        int hadithFifth = (int)(jfHeight * 0.557);


        //设置登录选择提示文字
        JLabel jlabelBox = new JLabel("您是:");
        jlabelBox.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+1));
        jlabelBox.setBounds(widthText, hadithFirst, (int)(jfWidth * 0.2), (int)(jfHeight * 0.07));

        //登录选择下拉框定义
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("买家");
        comboBox.addItem("商家");
        comboBox.setFont(new Font("宋体", 52, (int) (jfHeight*0.03135)));
        comboBox.setBounds((int)(jfWidth * 0.4), hadithFirst, (int) (jfWidth*0.2), (int)(jfHeight * 0.057));

        /*-----------文字提示----------*/
        //设置账号提示文字
        JLabel labelID = new JLabel("手机号:");
        labelID.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+1));
        labelID.setBounds(widthText-27, hadithSecond, (int) (jfWidth*0.2), (int)(jfHeight * 0.07));

        //设置地址提示文字
        JLabel labelAddress = new JLabel("地址:");
        labelAddress.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+1));
        labelAddress.setBounds(widthText, hadithThird, (int) (jfWidth*0.2), (int)(jfHeight * 0.07));

        //设置密码提示文字
        JLabel labelPassword = new JLabel("密码:");
        labelPassword.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+1));
        labelPassword.setBounds(widthText, hadithFourth, (int) (jfWidth*0.2), (int)(jfHeight * 0.07));

        //设置确认密码提示文字
        JLabel labelPasswordVerify = new JLabel("确认密码:");
        labelPasswordVerify.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+1));
        labelPasswordVerify.setBounds(widthText-45, hadithFifth, (int) (jfWidth*0.36), (int)(jfHeight * 0.07));

        /*-----------文字提示END----------*/

        /*-----------输入框----------*/

        //定义账号输入框
        JTextField IDField = new JTextField(20);
        IDField.setFont(new Font("宋体", 52, 26));
        IDField.setBounds(widthField, hadithSecond, (int) (jfWidth*0.5), (int)(jfHeight * 0.07));

        //定义地址输入框
        JTextField AddressField = new JTextField(20);
        AddressField.setFont(new Font("宋体", 52, 26));
        AddressField.setBounds(widthField, hadithThird, (int) (jfWidth*0.5), (int)(jfHeight * 0.07));

        //定义密码输入框
        JPasswordField PasswordField = new JPasswordField(20);
        PasswordField.setFont(new Font("宋体", 52, (int) (jfHeight*0.03135)+2));
        PasswordField.setBounds(widthField, hadithFourth, (int) (jfWidth*0.5), (int)(jfHeight * 0.07));

        //定义确认密码输入框
        JPasswordField PasswordVerifyField = new JPasswordField(20);
        PasswordVerifyField.setFont(new Font("宋体", 52, (int) (jfHeight*0.03135)+2));
        PasswordVerifyField.setBounds(widthField, hadithFifth, (int) (jfWidth*0.5), (int)(jfHeight * 0.07));

        /*-----------输入框END----------*/

        //定义提交按钮
        JButton BtnCo = new JButton("提交");
        BtnCo.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BtnCo.setBounds((int) (jfWidth*0.4), (int) (jfHeight*0.714), (int) (jfWidth*0.2), (int)(jfHeight * 0.07));

        con.add(BtnBack);

        con.add(jlabelBox);
        con.add(comboBox);

        con.add(labelID);
        con.add(labelAddress);
        con.add(labelPassword);
        con.add(labelPasswordVerify);

        con.add(IDField);
        con.add(AddressField);
        con.add(PasswordField);
        con.add(PasswordVerifyField);

        con.add(BtnCo);

        //返回到登录界面
        BtnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel();
            }
        });

        //注册提交
        BtnCo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)  {
                String iam = String.valueOf(comboBox.getSelectedItem());

                LoginPanel.ID = IDField.getText();
                String address = AddressField.getText();
                String password = String.valueOf(PasswordField.getPassword());
                String passwordVerify = String.valueOf(PasswordVerifyField.getPassword());

                if (iam == "买家") {
                    LoginPanel.type = 0;
                }
                if (iam == "商家")  {
                    LoginPanel.type = 1;
                }
                switch (Response.register(type,ID,address,password,passwordVerify)) {
                    case Done : {
                        jf.setVisible(false);
                        jf.setBounds(0, (int) (Oheight*0.037), Owidth, (int) (Oheight*0.963));
                        MainPanel();
                        jf.setVisible(true);
                        break;
                    }
                    case ExistedUID : {
                        JOptionPane.showMessageDialog(jf,
                                "<html><font size=14>账号已存在!", "错误",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    case IncorrectUID : {
                        JOptionPane.showMessageDialog(jf,
                                "<html><font size=12>账号长度不是11位!", "错误",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    case IncorrectAddress : {
                        JOptionPane.showMessageDialog(jf,
                                "<html><font size=14>地址长度不符!", "错误",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    case IncorrectPassword : {
                        JOptionPane.showMessageDialog(jf,
                                "<html><font size=14>密码位数不符!", "错误",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    case PasswordMismatched : {
                        JOptionPane.showMessageDialog(jf,
                                "<html><font size=14>密码不同!", "错误",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    default : {
                        System.out.println(Response.register(type,ID,address,password,passwordVerify));
                        break;
                    }
                }
            }
        });


    }

    public void MainPanel() {

        ArrayList<Result> arrayList;
        arrayList = Response.query(LoginPanel.type,LoginPanel.ID);
        int len = arrayList.size();


        repaint();
        removeAll();
        updateUI();
        URL imageURL = LoginFrame.class.getResource("/images/wallpaper.jpg");
        try {
            image = ImageIO.read(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Container con = this;
        //设置为自定义布局
        con.setLayout(null);

        /**
         * widthLeftButton     是按钮的X轴坐标
         * heightLeftButton1th 是第一个按钮的Y轴坐标
         * heightLeftButton2th 是第二个按钮的Y轴坐标
         * heightLeftButton3th 是第三个按钮的Y轴坐标
         * heightLeftButton4th 是第四个按钮的Y轴坐标
         *
         * width    按钮的宽度
         * height   按钮的高度
         */
        int width = (int) ( Owidth *0.104);
        int height = (int) ( Oheight *0.0463);

        int widthLeftButton = (int) ( Owidth *0.0417);
        int heightLeftButton1th = (int) ( Oheight *0.185);
        int heightLeftButton3th = (int) ( Oheight *0.37);
        int heightLeftButton4th = (int) ( Oheight *0.556);

        //定义商品列表按钮
        JButton BtnList = new JButton("货物列表");
        BtnList.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BtnList.setBounds(widthLeftButton, heightLeftButton1th, width, height);


        //定义商品列表按钮
        JButton BtnInfo = new JButton("个人信息");
        BtnInfo.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BtnInfo.setBounds(widthLeftButton, heightLeftButton3th, width, height);

        //定义商品列表按钮
        JButton BtnLogout = new JButton("退出登录");
        BtnLogout.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BtnLogout.setBounds(widthLeftButton, heightLeftButton4th, width, height);

        con.add(BtnList);
        con.add(BtnInfo);
        con.add(BtnLogout);
        /*==================================list===========================================*/



        JPanel list = new JPanel();
        list.setLayout(null);
        list.setBounds((int) (Owidth*0.234), (int) (Oheight*0.185), (int) (Owidth*0.469), (int) (Oheight*0.648));

        JLabel GoodsIDLabel = new JLabel("运单号");
        GoodsIDLabel.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        GoodsIDLabel.setBounds((int) (Owidth*0.026), (int) (Oheight*0.0463), (int) (Owidth*0.13), (int) (Oheight*0.0463));

        JLabel BuyersIDLabel = new JLabel("收货人手机号");
        BuyersIDLabel.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BuyersIDLabel.setBounds((int) (Owidth*0.1823), (int) (Oheight*0.0463), (int) (Owidth*0.13), (int) (Oheight*0.0463));

        JLabel BuyerAddressLabel = new JLabel("收货人地址");
        BuyerAddressLabel.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BuyerAddressLabel.setBounds((int) (Owidth*0.365), (int) (Oheight*0.0463), (int) (Owidth*0.13), (int) (Oheight*0.0463));

        list.add(GoodsIDLabel);
        list.add(BuyersIDLabel);
        list.add(BuyerAddressLabel);

        if (LoginPanel.type == 0) {
            BuyersIDLabel.setText("发货人手机号");
            BuyerAddressLabel.setText("发货人地址");
        }

        /*=================================*/


        JLabel[] goodsIDLabel = new JLabel[len];
        JLabel[] buyersIDLabel = new JLabel[len];;
        JLabel[] buyerAddressLabel = new JLabel[len];;
        for (int i = 0; i < len; i++) {
            Result result = arrayList.get(i);
            goodsIDLabel[i] = new JLabel(result.getID());
            goodsIDLabel[i].setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
            goodsIDLabel[i].setBounds((int) (Owidth*0.026), (int) (Oheight*0.093)+i*(int) (Oheight*0.056), (int) (Owidth*0.13), (int) (Oheight*0.0463));

            buyersIDLabel[i] = new JLabel(result.getUID());
            buyersIDLabel[i].setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
            buyersIDLabel[i].setBounds((int) (Owidth*0.1823), (int) (Oheight*0.093)+i * (int) (Oheight*0.056), (int) (Owidth*0.13), (int) (Oheight*0.0463));

            buyerAddressLabel[i] = new JLabel(result.getAddress());
            buyerAddressLabel[i].setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
            buyerAddressLabel[i].setBounds((int) (Owidth*0.365), (int) (Oheight*0.093)+i * (int) (Oheight*0.056), (int) (Owidth*0.13), (int) (Oheight*0.0463));

            list.add(goodsIDLabel[i]);
            list.add(buyersIDLabel[i]);
            list.add(buyerAddressLabel[i]);
        }



        /*===================================货物列表========================================*/
        if (LoginPanel.type == 1) {

            JLabel addBuyerIDLabel = new JLabel("收货人手机号:");
            addBuyerIDLabel.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
            addBuyerIDLabel.setBounds((int) (Owidth*0.026), (int) (Oheight*0.556), (int) (Owidth*0.13), (int) (Oheight*0.0463));

            JTextField addBuyerID = new JTextField();
            addBuyerID.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
            addBuyerID.setBounds((int) (Owidth*0.156), (int) (Oheight*0.556), (int) (Owidth*0.156), (int) (Oheight*0.0463));

            JButton BtnAddBuyer = new JButton("添加包裹");
            BtnAddBuyer.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
            BtnAddBuyer.setBounds((int) (Owidth*0.339), (int) (Oheight*0.556), (int) (Owidth*0.104),(int) (Oheight*0.0463));

            list.add(addBuyerIDLabel);
            list.add(addBuyerID);
            list.add(BtnAddBuyer);



            BtnAddBuyer.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (Response.newLogisticInformation(LoginPanel.ID,addBuyerID.getText())) {
                        case Done:{
                            MainPanel();
                            JOptionPane.showMessageDialog(jf,
                                    "<html><font size=14>添加成功!", "成功",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        default :{
                            JOptionPane.showMessageDialog(jf,
                                    "<html><font size=14>添加失败!", "错误",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                    }
                }
            });

        }

        con.add(list);

        BtnList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel();
            }
        });

        BtnInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InfoPanel();
            }
        });

        BtnLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                int height = Toolkit.getDefaultToolkit().getScreenSize().height;

                int jfWidth = (int) (width*0.2604);
                int jfHeight = (int) (height*0.648);
                jf.setVisible(false);
                jf.setBounds((width-jfWidth)/2,(height-jfHeight)/2,jfWidth,jfHeight);
                LoginPanel();
                jf.setVisible(true);

            }
        });




    }

    public void InfoPanel() {

        String address = Response.queryAddress(LoginPanel.type, LoginPanel.ID);
        String password = Response.queryPassword(LoginPanel.type, LoginPanel.ID);

        repaint();
        removeAll();
        updateUI();
        URL imageURL = LoginFrame.class.getResource("/images/wallpaper.jpg");
        try {
            image = ImageIO.read(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Container con = this;
        //设置为自定义布局
        con.setLayout(null);

        /**
         * widthLeftButton     是按钮的X轴坐标
         * heightLeftButton1th 是第一个按钮的Y轴坐标
         * heightLeftButton2th 是第二个按钮的Y轴坐标
         * heightLeftButton3th 是第三个按钮的Y轴坐标
         * heightLeftButton4th 是第四个按钮的Y轴坐标
         *
         * width    按钮的宽度
         * height   按钮的高度
         */
        int width = (int) (Owidth*0.104);
        int height = (int) (Oheight*0.046);

        int widthLeftButton = (int) (Owidth*0.042);
        int heightLeftButton1th = (int) (Oheight*0.185);
        int heightLeftButton3th = (int) (Oheight*0.37);
        int heightLeftButton4th = (int) (Oheight*0.556);




        //定义商品列表按钮
        JButton BtnList = new JButton("货物列表");
        BtnList.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BtnList.setBounds(widthLeftButton, heightLeftButton1th, width, height);


        //定义商品列表按钮
        JButton BtnInfo = new JButton("个人信息");
        BtnInfo.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BtnInfo.setBounds(widthLeftButton, heightLeftButton3th, width, height);

        //定义商品列表按钮
        JButton BtnLogout = new JButton("退出登录");
        BtnLogout.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BtnLogout.setBounds(widthLeftButton, heightLeftButton4th, width, height);


        con.add(BtnList);
        con.add(BtnInfo);
        con.add(BtnLogout);

        /*========================================================================================================*/
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds((int) (Owidth*0.365),(int) (Oheight*0.139),(int) (Owidth*0.365),(int) (Oheight*0.648));

        /*============手机号==========*/
        JLabel IDTextArea = new JLabel("手机号:");
        IDTextArea.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        IDTextArea.setBounds((int) (Owidth*0.052),(int) (Oheight*0.0926),(int) (Owidth*0.052),(int) (Oheight*0.046));

        JLabel ID = new JLabel();
        ID.setText(LoginPanel.ID);
        ID.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        ID.setBounds((int) (Owidth*0.13),(int) (Oheight*0.0926),(int) (Owidth*0.156),(int) (Oheight*0.046));

        /*============地址==========*/
        JLabel addressTextArea = new JLabel("地址:");
        addressTextArea.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        addressTextArea.setBounds((int) (Owidth*0.052),(int) (Oheight*0.185),(int) (Owidth*0.052),(int) (Oheight*0.046));

        JTextField addressText = new JTextField();
        addressText.setText(address);
        addressText.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        addressText.setBounds((int) (Owidth*0.13),(int) (Oheight*0.185),(int) (Owidth*0.156),(int) (Oheight*0.046));

        /*============密码==========*/
        JLabel passwordTextArea = new JLabel("密码:");
        passwordTextArea.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        passwordTextArea.setBounds((int) (Owidth*0.052),(int) (Oheight*0.278),(int) (Owidth*0.052),(int) (Oheight*0.046));

        JTextField passwordText = new JTextField();
        passwordText.setText(password);
        passwordText.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        passwordText.setBounds((int) (Owidth*0.13),(int) (Oheight*0.278),(int) (Owidth*0.156),(int) (Oheight*0.046));

        /*============修改按钮=======*/
        JButton Btnchange = new JButton("修改");
        Btnchange.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        Btnchange.setBounds((int) (Owidth*0.156),(int) (Oheight*0.46),(int) (Owidth*0.052),(int) (Oheight*0.046));

        /*============销毁账户==========*/
        JButton BtnShutdown = new JButton("销毁账户");
        BtnShutdown.setFont(new Font("宋体", Font.BOLD, (int) (jfHeight*0.03135)+2));
        BtnShutdown.setBounds((int) (Owidth*0.13),(int) (Oheight*0.556),(int) (Owidth*0.104),(int) (Oheight*0.046));


        infoPanel.add(ID);
        infoPanel.add(IDTextArea);
        infoPanel.add(addressTextArea);
        infoPanel.add(passwordTextArea);
        infoPanel.add(addressText);
        infoPanel.add(passwordText);

        infoPanel.add(Btnchange);
        infoPanel.add(BtnShutdown);


        con.add(infoPanel);


        /*========================================================================================================*/

        /*============================================监听=========================================================*/

        BtnList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel();
            }
        });

        BtnInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InfoPanel();
            }
        });

        BtnLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                int height = Toolkit.getDefaultToolkit().getScreenSize().height;

                int jfWidth = (int) (width*0.2604);
                int jfHeight = (int) (height*0.648);
                jf.setVisible(false);
                jf.setBounds((width-jfWidth)/2,(height-jfHeight)/2,jfWidth,jfHeight);
                LoginPanel();
                jf.setVisible(true);

            }
        });

        Btnchange.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordText.getText();

                String address = addressText.getText();
                String message = "<html><font size=6>确定要把地址为:"
                        +address
                        +"\n<html><font size=6>把密码修改为"
                        +password;
                int isVerify = JOptionPane.showConfirmDialog(jf,
                        message,"标题",
                        JOptionPane.YES_NO_OPTION);
                if (isVerify == 0) {
                    System.out.println(Response.updateInformation(LoginPanel.type, LoginPanel.ID,address,password));

                    switch (Response.updateInformation(LoginPanel.type, LoginPanel.ID,address,password)) {
                        case Done:{
                            JOptionPane.showMessageDialog(jf,
                                    "<html><font size=14>修改成功", "成功",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        case IncorrectAddress:{
                            JOptionPane.showMessageDialog(jf,
                                    "<html><font size=14>地址长度不符合!", "错误",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        case IncorrectPassword:{
                            JOptionPane.showMessageDialog(jf,
                                    "<html><font size=14>密码长度不符合!", "错误",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        default :{
                            JOptionPane.showMessageDialog(jf,
                                    "<html><font size=14>出现未知的错误，请稍后再试!", "错误",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }

                }
                if (isVerify == 1) {


                }


            }
        });

        BtnShutdown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Response.delete(LoginPanel.type, LoginPanel.ID)) {
                    case Done: {
                        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
                        int jfWidth = (int) (width * 0.2604);
                        int jfHeight = (int) (height * 0.648);
                        jf.setVisible(false);
                        jf.setBounds((width - jfWidth) / 2, (height - jfHeight) / 2, jfWidth, jfHeight);
                        LoginPanel();
                        jf.setVisible(true);
                        break;
                    }
                    default :{
                        break;
                    }
                }
            }
        });


    }


    @Override
    protected void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        g.clearRect(0, 0, width, height);

        //画背景图
        g.drawImage(image, 0, 0, width, height, null);

        //加透明遮罩
        g.setColor(new Color(255, 255, 255, 50));
        g.fillRect(0, 0, width, height);
    }

}
