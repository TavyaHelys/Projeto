package br.upe.piii.carinhas.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


@WebServlet(
	    name = "AvatasServlet",
		urlPatterns = {"/carinhas"}
        )
public class AvatasServlet extends HttpServlet {

	private static final long serialVersionUID = -5682041569532261795L;

	@Autowired
	RestTemplate restTemplate;
	
	//metodo get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("Nome");
		//if (nome.trim().equals("")) throw new IllegalArgumentException();
		
		// ********* verivicar se a string esta vazia 
		
		String url = "https://api.adorable.io/avatas/300/" + nome + ".jpg";
		byte[] imagem = this.restTemplate.getForObject(url, byte[].class);
		
		resp.setContentType("imagem/png");
		ByteArrayInputStream bs = new ByteArrayInputStream(imagem);
		BufferedImage ing = ImageIO.read(bs);
		
		ImageIO.write(ing, "png", resp.getOutputStream());
	}
}
