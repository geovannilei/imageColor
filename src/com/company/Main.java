package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

public class Main {


    public static class Col{
        private int num1;
        private int num2;
    }
    public static void main(String[] args) {
        int width = 963;    //width of the image
        int height = 640;   //height of the image
        BufferedImage image = null;
        File f = null;
        JFrame frame = new JFrame("Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //read image
        try{
            f = new File("src/DHNaX_FXsAIu_Fj.jpg"); //image file path
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(f);
            frame.setSize(image.getWidth(),image.getHeight());
           int[][] result = (convertTo2DUsingGetRGB(image));
           System.out.println(image.getWidth());
            System.out.println(image.getHeight());
            ArrayList<Col> box = new ArrayList<>();
           BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
           for(int  i = 0; i < image.getHeight(); i++){
               for(int j = 0; j < image.getWidth(); j++){
                   Col col = new Col();
                   col.num1 = result[i][j];
                   col.num2 =  getHue(new Color(result[i][j]).getRed(),new Color(result[i][j]).getGreen(),new Color(result[i][j]).getBlue());
                   box.add(col);
               }
           }
           Collections.sort(box, new Comparator<Col>() {
               @Override
               public int compare(Col o1, Col o2) {
                   int hue1 = o1.num2;
                   int hue2 = o2.num2;
                   return Integer.compare(hue1,hue2);
               }
           });

            int count = 0;

            for(int i = 0; i< image.getHeight();i++){
                for(int j =0; j< image.getWidth();j++){
                    System.out.println(box.get(count).num2);
                    image2.setRGB(j,i,box.get(count++).num1);


                }
            }
            JPanel pane = new JPanel(){
                @Override
                protected void paintComponent(Graphics g){
                    super.paintComponent(g);
                    g.drawImage(image2, 0 , 0 ,null);
                }
            };
            frame.add(pane);
            frame.setVisible(true);

        }catch(IOException e){
            System.out.println("Error: "+e);
        }
    }
    private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result[row][col] = image.getRGB(col, row);
            }
        }

        return result;
    }
    public static int getHue(int red, int green, int blue) {

        float min = Math.min(Math.min(red, green), blue);
        float max = Math.max(Math.max(red, green), blue);

        float hue = 0f;
        if (max == red) {
            hue = (green - blue) / (max - min);

        } else if (max == green) {
            hue = 2f + (blue - red) / (max - min);

        } else {
            hue = 4f + (red - green) / (max - min);
        }

        hue = hue * 60;
        if (hue < 0) hue = hue + 360;

        return Math.round(hue);
    }

}
