/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import swing.blurHash.BlurHash;

/**
 *
 * @author TrongFlorida
 */
public class Test {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("D:\\Java\\TranslateChat\\src\\icon\\testing\\chon.jpg"));
            String blurhashStr = BlurHash.encode(image);
            System.out.println(blurhashStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
