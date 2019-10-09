/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.project;



import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;


/**
 *
 * @author Ankit
 */
public class ClientHandler extends Thread implements ObserverInterface {
    Socket socket = null;
    private static Robot robot;
    private boolean connect = true;
    ServerProject server;
    Screenshort screen_1 =new Screenshort();
 
   private  JniFunctionCall jni = new JniFunctionCall();
   
    private int virtualKey[] = {38,37,39,40,KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_S};
    
    public ClientHandler() {}
    
    public  ClientHandler (Socket socket, Robot robot) {
     this.socket = socket;
     this.robot = robot;
   
    }
  
 
    @Override
    public void run() {
       
         InputStreamReader input = null;
            BufferedReader buff = null;
            String line = null;
            
            try {
             
                input = new InputStreamReader(socket.getInputStream());
                buff = new BufferedReader(input); 
               
            } catch (IOException ex) {
                //Logger.dddddgedtLoggeddr(Sedrver.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            while (connect) {
                
                if (!connect) {
                 input = null;
                 buff = null;
                  line = null;
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                
                try {
                    line  = buff.readLine();
                     if ((line == null || line.equalsIgnoreCase("exit"))) {
                    socket.close();
                    return;
                }
                     System.out.println(line);
                     ////System.out.println(line);
                     if(line.equalsIgnoreCase("left")){
				//Simulate press and release of key 'n'
                                System.out.println("we are in left ");
				robot.keyPress(KeyEvent.VK_LEFT);
				robot.keyRelease(KeyEvent.VK_LEFT);
			}
                     else if(line.equalsIgnoreCase("screen")) {
                         System.out.println("you call for screen shot buddy");
                         Rectangle capture =  
            new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
                         BufferedImage image = robot.createScreenCapture(capture); 
                       writeJPG(image, socket.getOutputStream(),0.7f);
                     }
			//if user clicks on previous
			else if(line.equalsIgnoreCase("right")){
				//Simulate press and release of key 'p'
                                    robot.keyPress(KeyEvent.VK_RIGHT);
				robot.keyRelease(KeyEvent.VK_RIGHT);		        	
			}
			//if user clicks on play/pause
			else if(line.equalsIgnoreCase("play")){
				//Simulate press and release of spacebar
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
			}
			//input will come in x,y format if user moves mouse on mousepad
			else if(line.contains(",")){
				float movex=Float.parseFloat(line.split(",")[0]);//extract movement in x direction
				float movey=Float.parseFloat(line.split(",")[1]);//extract movement in y direction
				Point point = MouseInfo.getPointerInfo().getLocation(); //Get current mouse position
				float nowx=point.x;
				float nowy=point.y;
				robot.mouseMove((int)(nowx+movex),(int)(nowy+movey));//Move mouse pointer to new location
			}
			//if user taps on mousepad to simulate a left click
			else if(line.contains("left_click")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			}
                     else if(line.contains("a")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_A);
			}
                     else if(line.contains("b")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_B);
				robot.keyRelease(KeyEvent.VK_B);
			}
                     else if(line.contains("c")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_C);
			}
                     else if(line.contains("d")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
			}
                     else if(line.contains("e")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_E);
			}
                     else if(line.contains("f")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_F);
			}
                     else if(line.contains("g")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_G);
				robot.keyRelease(KeyEvent.VK_G);
			}
                     else if(line.contains("h")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_H);
				robot.keyRelease(KeyEvent.VK_H);
			}
                     else if(line.contains("i")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_I);
				robot.keyRelease(KeyEvent.VK_I);
			}
                     else if(line.contains("j")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
			}
                     else if(line.contains("k")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_K);
				robot.keyRelease(KeyEvent.VK_K);
			}
                     else if(line.contains("l")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_L);
			}
                     else if(line.contains("m")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_M);
				robot.keyRelease(KeyEvent.VK_M);
			}
                     else if(line.contains("n")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_N);
			}
                     else if(line.contains("o")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_O);
				robot.keyRelease(KeyEvent.VK_O);
			}
                     else if(line.contains("p")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_P);
			}
                     else if(line.contains("q")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_Q);
				robot.keyRelease(KeyEvent.VK_Q);
			}
                     else if(line.contains("r")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_R);
			}
                     else if(line.contains("s")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_S);
			}
                     else if(line.contains("t")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_T);
			}
                     else if(line.contains("u")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_U);
				robot.keyRelease(KeyEvent.VK_U);
			}
                     else if(line.contains("v")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
			}
                     else if(line.contains("x")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_X);
			}
                     else if(line.contains("y")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_Y);
				robot.keyRelease(KeyEvent.VK_Y);
			}
                     else if(line.contains("z")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_Z);
				robot.keyRelease(KeyEvent.VK_Z);
			}
                     else if(line.contains("alt")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_ALT);
			}
                     else if(line.contains("shift")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
                     else if(line.contains("cntrl")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
                     else if(line.contains("enter")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}
                      else if(line.contains("space")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
			}
                     else if(line.contains("up")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);
			}
                     else if(line.contains("down")){ 
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
			}
                     else if(line.contains("left")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_LEFT);
				robot.keyRelease(KeyEvent.VK_LEFT);
			}
                     else if(line.contains("right")){
				//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
				robot.keyPress(KeyEvent.VK_RIGHT);
				robot.keyRelease(KeyEvent.VK_RIGHT);
			}
                } catch(IOException e) {
                    
                }
                
                
               // jni.move(true);
                 
              /*  for (int i = 0; i < inputFromClient.length()/2; i++) {
                   int index =  Integer.parseInt(inputFromClient.split(",")[i]);
                   
                  switch(index) {
                      
                      case 0 :
                   jni.moveLeft(true);
                          break;
                      case 1 :
                             jni.moveRight(true);
                         
                          break;
                      case 2 :
                            jni.move(true); 
                          break;
                      case 3 : 
                         
                            jni.moveBottom(true);
                          break;
                      default :
                          robot.keyPress(virtualKey[index]);
                          break;
                  }
                }
                
             try {
                 Thread.sleep(100);
                   } catch (InterruptedException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
             }
            
              for (int i = 0; i < inputFromClient.length()/2; i++) {
                   int index =  Integer.parseInt(inputFromClient.split(",")[i]);
                   
                  switch(index) {
                      case 0 :
                     jni.moveLeft(false);
                        
                          break;
                      case 1 :
                         jni.moveRight(false); 
                          break;
                      case 2 :
                          
                           jni.move(false);
                          break;
                      case 3 : 
                           jni.moveBottom(false);
                          break;
                      default :
                          robot.keyRelease(virtualKey[index]);
                          break;
                  }
                }
                 
                 // jni.pressButtons(virtualKey);
                 
                 
                 /*  if (inputFromClient.equalsIgnoreCase("play")) {
                 robot.keyPress(KeyEvent.VK_SPACE);
                 robot.keyRelease(KeyEvent.VK_SPACE);
                 }
                 else if (inputFromClient.equalsIgnoreCase("left")) {
                 
                 jni.moveLeft();
                 
                 } else if (inputFromClient.equalsIgnoreCase("right")) {
                 jni.moveRight();
                 
                 } else if (inputFromClient.equalsIgnoreCase("top")) {
                 
                 jni.move();
                 
                 } else if (inputFromClient.equalsIgnoreCase("bottom")) {
                 
                 jni.moveBottom();
                 
                 } else if (inputFromClient.equalsIgnoreCase("oButton")) {
                 robot.keyPress(KeyEvent.VK_W);
                 try {
                 Thread.sleep(100);
                 robot.keyRelease(KeyEvent.VK_W);
                 } catch (InterruptedException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 }
                 else if (inputFromClient.equalsIgnoreCase("sqButton")) {
                 robot.keyPress(KeyEvent.VK_S);
                 try {
                 Thread.sleep(30);
                 robot.keyRelease(KeyEvent.VK_S);
                 } catch (InterruptedException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 } else if (inputFromClient.equalsIgnoreCase("triButton")) {
                 
                 robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                 try {
                 Thread.sleep(100);
                 robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                 } catch (InterruptedException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
                 }
                 else if (inputFromClient.equalsIgnoreCase("xButton")) {
                 robot.keyPress(KeyEvent.VK_D);
                 try {
                 Thread.sleep(30);
                 robot.keyRelease(KeyEvent.VK_D);
                 } catch (InterruptedException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 }
                 else if(inputFromClient.equalsIgnoreCase("previous")) {
                 robot.keyPress(KeyEvent.VK_W);
                 
                 try {
                 Thread.sleep(30);
                 robot.keyRelease(KeyEvent.VK_W);
                 } catch (InterruptedException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
                 }
                 
                 else if(inputFromClient.contains(",")) {
                 float pointX = Float.parseFloat(inputFromClient.split(",")[0]);
                 float pointY = Float.parseFloat(inputFromClient.split(",")[1]);
                 
                 Point point = MouseInfo.getPointerInfo().getLocation();
                 float nowX = point.x;
                 float nowY = point.y;
                 robot.mouseMove((int)(pointX + nowX),(int) (nowY +pointY));
                 System.out.println("THE Mouse x and y position = "+pointX + " "+pointY);
                 
                 }
                 
                 else if(inputFromClient.equalsIgnoreCase("Left_click")) {
                 
                 robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                 try {
                 Thread.sleep(300);
                 robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                 } catch (InterruptedException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
                 
                 }
                 
                 else if(inputFromClient.equalsIgnoreCase("next")) {
                 robot.keyPress(KeyEvent.VK_DOWN);
                 robot.keyRelease(KeyEvent.VK_DOWN);
                 } */
           
            
        }
            
       
        
       
        
    }
    
public static void writeJPG(
    BufferedImage bufferedImage,
    OutputStream outputStream,
    float quality) throws IOException
{
    Iterator<ImageWriter> iterator =
        ImageIO.getImageWritersByFormatName("jpg");
    ImageWriter imageWriter = iterator.next();
    ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
    imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    imageWriteParam.setCompressionQuality(quality);
    ImageOutputStream imageOutputStream =
        new MemoryCacheImageOutputStream(outputStream);
    imageWriter.setOutput(imageOutputStream);
    IIOImage iioimage = new IIOImage(bufferedImage, null, null);
    imageWriter.write(null, iioimage, imageWriteParam);
    imageOutputStream.flush();
}
     
      
    @Override
    public void onObserve() {
       
        connect = false;
        
         //To change body of generated methods, choose Tools | Templates.
    }
    
}
