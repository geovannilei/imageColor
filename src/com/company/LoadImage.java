package com.company;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class LoadImage extends Applet {
    private BufferedImage img;

    public void init(){
        try {
            URL url = new URL(getCodeBase(), "DHNaX_FXsAIu_Fj.jpg");
            img = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void paint(Graphics g){
        g.drawImage(img,50,50,null);
    }
}
