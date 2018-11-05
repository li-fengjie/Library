package com.life.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.jfxmediaimpl.platform.ios.IOSMediaPlayer;

/**
 * Servlet implementation class ImageCodeServlet
 */
@WebServlet("/imagecode")
public class ImageCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> words = new ArrayList<String>();

	@Override
	public void init() throws ServletException {
		String path = getServletContext().getRealPath("WEB-INF/new_words.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
			String line;
			for (; (line = reader.readLine()) != null;) {
				words.add(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageCodeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Random random=new Random();
		int index=random.nextInt(words.size());
		String word=words.get(index);
		request.getSession().setAttribute("checkcode", word);
		int width=120;
		int height=30;
		BufferedImage bufferedImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics=bufferedImage.getGraphics();
		Color color=getRandomColor(200, 240);
		graphics.setColor(color);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width-1, height-1);


		Graphics2D graphics2d=(Graphics2D) graphics;
		int x=10;
		graphics2d.setFont(new Font("宋体",Font.BOLD,18));
		for (int i = 0; i < word.length(); i++) {
			graphics2d.setColor(getRandomColor(20, 110));
			
			int angle=random.nextInt(60)-30;
			double theta=Math.PI/180*angle;
			char c=word.charAt(i);
			
			graphics2d.rotate(theta, x, 20);
			
			graphics2d.drawString(""+c, x, 20);
			
			graphics2d.rotate(-theta, x, 20);
			x+=30;
		}
		
		graphics.setColor(getRandomColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 30; i++) {
			x1=random.nextInt(width);
			y1=random.nextInt(height);
			
			x2=random.nextInt(12);
			y2=random.nextInt(12);
			graphics.drawLine(x1, y1, x1+x2, y1+y2);
		}
		
		graphics.dispose();
		
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		
	}
	
	private Color getRandomColor(int fc,int ec) {
		
		Random random=new Random();
		int r=random.nextInt(ec-fc)+fc;
		int g=random.nextInt(ec-fc)+fc;
		int b=random.nextInt(ec-fc)+fc;
		return new Color(r,g,b);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
