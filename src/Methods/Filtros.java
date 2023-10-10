package Methods;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Filtros {
    static final String CAMINHO_PADRAO = "C:\\Users\\NOTEBOOK\\PDI\\ConvolucaoFilto\\src\\Image\\lena-ruido.jpg";

    private static final double[] filtro_gau = {
            0.0625, 0.125, 0.0625,
            0.125, 0.25, 0.125,
            0.0625, 0.125, 0.0625
    };

    public static BufferedImage FiltroSuavizado(BufferedImage imagemOriginal) throws IOException {
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        BufferedImage imagemSuavizada = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int linha = 1; linha < largura - 1; linha++) {
            for (int coluna = 1; coluna < altura - 1; coluna++) {
                double red = 0.0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        Color cor = new Color(imagemOriginal.getRGB(linha + i, coluna + j));
                        red += filtro_gau[(i + 1) * 3 + (j + 1)] * cor.getRed();
                    }
                }

               red = Math.min(255, Math.max(0, red));
                Color novaCor = new Color((int) red, (int) red, (int) red);
                imagemSuavizada.setRGB(linha, coluna, novaCor.getRGB());
            }
        }

        String caminho = CAMINHO_PADRAO + "filtrosuavizado.png";

        ImageIO.write(imagemSuavizada, "png", new File(caminho));
        JFrame janela = new JFrame();
        ImageIcon icon = new ImageIcon(imagemSuavizada);
        JLabel label = new JLabel(icon);
        janela.add(label);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        return imagemSuavizada;
    }
    static final String CAMINHO_PADRAO2 = "C:\\Users\\NOTEBOOK\\PDI\\ConvolucaoFilto\\src\\Image\\lena-ruido.jpg";

    public static BufferedImage detectarBordas(BufferedImage imagemOriginal) throws IOException {
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        BufferedImage imagemBordas = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int x = 1; x < largura - 1; x++) {
            for (int y = 1; y < altura - 1; y++) {
                int[][] kernel = {
                        {-1, -1, -1},
                        {-1, 8, -1},
                        {-1, -1, -1}
                };

                int novoValor = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Color pixel = new Color(imagemOriginal.getRGB(x + i - 1, y + j - 1));
                        int grayscaleValue = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                        novoValor += kernel[i][j] * grayscaleValue;
                    }
                }

                novoValor = Math.min(255, Math.max(0, novoValor));
                Color novaCor = new Color(novoValor, novoValor, novoValor);
                imagemBordas.setRGB(x, y, novaCor.getRGB());
            }
        }

        String caminho = CAMINHO_PADRAO + "realceBordas.png";

        ImageIO.write(imagemBordas, "png", new File(caminho));
        JFrame janela = new JFrame();
        ImageIcon icon = new ImageIcon(imagemBordas);
        JLabel label = new JLabel(icon);
        janela.add(label);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        return imagemBordas;
    }
}