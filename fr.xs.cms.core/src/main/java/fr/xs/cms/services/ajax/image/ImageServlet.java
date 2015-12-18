package fr.xs.cms.services.ajax.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.xs.jtk.tools.Debugger;

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int 	WIDTH 				= 640;
	private static final int 	HEIGHT 				= 480;
	private static final Color 	BACKGROUND_COLOR 	= new Color(100,169,100);
	private static final Color 	COLOR 				= new Color(0, 0, 0);
	private static final Font 	FONT 				= new Font("Times New Roman", Font.BOLD, 46);
	private static final Font 	FOOT_FONT 			= new Font("Courier", Font.ITALIC, 14);
	private static final Color 	FOOT_COLOR 			= Color.BLACK;

	static private int i = 0;

	static public String requestMethod() { return "post"; }
	static public String requestURL()    { return "/images-servlet"; }
	static public String requestAsync()  { return "true"; }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Debugger.log("ImageServlet::doGet!");
    	processNoArgs(request, response);
    	return ;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Debugger.log("ImageServlet::doPost!");
    	processNoArgs(request, response);
    	return ;
    }

    protected void processNoArgs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			String what = request.getParameter("type");

			if(what == null) {
		    	response.setContentType("image/jpg");
		    	BufferedImage img = getAnImageBuffer(i++);
		    	ServletOutputStream out = response.getOutputStream();
		    	//JPEGCodec.createJPEGEncoder(out).encode(img);
	    	    ImageIO.write(img, "png", out);
		    	img.flush();
		    	
			} else {
//		    	byte[] imageData = new byte[640 * 480 * 3];
//		    	response.setContentType("image/jpg");
//		    	response.getOutputStream().write(imageData);
			}
		} catch (Throwable theException) {
				System.out.println(theException);
		}
    }
   
	public BufferedImage getAnImageBuffer(int _count) throws IOException {
	   BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_INDEXED);

	   Graphics graphics = image.getGraphics();
	   graphics.setColor(BACKGROUND_COLOR);
	   graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
	   graphics.setColor(COLOR);
	   graphics.setFont(FONT);
	   graphics.drawString("Une image JAVA", 10, HEIGHT/2);
	   graphics.drawString("siret: " + "123456", 10, HEIGHT/2+50);
	   graphics.drawString("type: " + i, 10, HEIGHT/2+100);
	   graphics.setFont(FOOT_FONT);
	   graphics.setColor(FOOT_COLOR);
	   graphics.drawString("Created by " + "sanke", 10, HEIGHT - 30);
	   graphics.drawString("faire un test en AJAX (fps max?) !!!", 10, HEIGHT - 10);

	   return image;
	}

}
