/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.project;

// Java Program to Capture full 
// Image of Screen 
import java.awt.AWTException; 
import java.awt.Rectangle; 
import java.awt.Toolkit; 
import java.awt.Robot; 
import java.awt.image.BufferedImage; 
import java.io.DataOutputStream;
import java.io.IOException; 
import java.io.File; 
import java.net.Socket;
import javax.imageio.ImageIO; 

public class Screenshort { 
	public static final long serialVersionUID = 1L; 
	public void screen(Socket s) 
	{ 
		try { 
			Thread.sleep(120); 
			Robot r = new Robot(); 

			// It saves screenshot to desired path 
			String path = "D:// Shot.jpg"; 

			// Used to get ScreenSize and capture image 
			Rectangle capture = 
			new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
			BufferedImage Image = r.createScreenCapture(capture); 
			ImageIO.write(Image, "jpg", new DataOutputStream(s.getOutputStream())); 
			System.out.println("Screenshot saved"); 
		} 
		catch (AWTException | IOException | InterruptedException ex) { 
			System.out.println(ex); 
		} 
	} 
} 
