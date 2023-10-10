package Main;

import Methods.Filtros;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        BufferedImage imagem = ImageIO.read(new File("C:\\Users\\NOTEBOOK\\PDI\\ConvolucaoFilto\\src\\Image\\lena-ruido.jpg"));
        BufferedImage imagemSuavizada = Filtros.FiltroSuavizado(imagem);

        double limite  = 50.0;
        BufferedImage imagem2 = ImageIO.read(new File("C:\\Users\\NOTEBOOK\\PDI\\ConvolucaoFilto\\src\\Image\\lena-ruido.jpg"));
        BufferedImage imagemBordas = Filtros.detectarBordas(imagem2);

    }
}
