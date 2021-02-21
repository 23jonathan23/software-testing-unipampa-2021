import java.awt.*;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimuladorTela extends JFrame {
    private static final Color COR_VAZIA = Color.white;
    private static final Color COR_INDEFINIDA = Color.gray;

    private final String PREFIXO_ETAPA = "Etapa: ";
    private final String PREFIXO_POPULACAO = "Populacao: ";
    private final JLabel rotuloEtapa, populacao;
    private final VisaoCampo visaoCampo;

    private final Map<Class, Color> cores;
    private final CampoEstatistica estatisticas;

    public SimuladorTela(int height, int width) {
        estatisticas = new CampoEstatistica();
        cores = new LinkedHashMap<Class, Color>();

        setTitle("Simulacao Lobos-guará e ovelhas");
        rotuloEtapa = new JLabel(PREFIXO_ETAPA, JLabel.CENTER);
        populacao = new JLabel(PREFIXO_POPULACAO, JLabel.CENTER);

        setLocation(100, 50);

        visaoCampo = new VisaoCampo(height, width);

        Container conteudos = getContentPane();
        conteudos.add(rotuloEtapa, BorderLayout.NORTH);
        conteudos.add(visaoCampo, BorderLayout.CENTER);
        conteudos.add(populacao, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public void setCor(Class animalClass, Color color) {
        cores.put(animalClass, color);
    }

    private Color getCor(Class animalClass) {
        Color coluna = cores.get(animalClass);
        if (coluna == null) {
            return COR_INDEFINIDA;
        } else {
            return coluna;
        }
    }

    public void mostraStatus(int etapa, Campo campo) {
        if (!isVisible()) {
            setVisible(true);
        }

        rotuloEtapa.setText(PREFIXO_ETAPA + etapa);
        estatisticas.redefine();

        visaoCampo.preparePaint();

        for (int row = 0; row < campo.getProfundidade(); row++) {
            for (int col = 0; col < campo.getLargura(); col++) {
                Object animal = campo.getObjectAt(row, col);
                if (animal != null) {
                    estatisticas.incrementaContador(animal.getClass());
                    visaoCampo.drawMark(col, row, getCor(animal.getClass()));
                } else {
                    visaoCampo.drawMark(col, row, COR_VAZIA);
                }
            }
        }
        estatisticas.contadorFinalizado();

        populacao.setText(PREFIXO_POPULACAO + estatisticas.getPopulationDetails(campo));
        visaoCampo.repaint();
    }

    public boolean ehViavel(Campo campo) {
        return estatisticas.ehViavel(campo);
    }

    private class VisaoCampo extends JPanel {
        private final int GRID_VIEW_SCALING_FACTOR = 6;

        private final int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        public VisaoCampo(int height, int width) {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR, gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        public void preparePaint() {
            if (!size.equals(getSize())) {
                size = getSize();
                fieldImage = visaoCampo.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if (xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if (yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }

        public void drawMark(int x, int y, Color color) {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale - 1, yScale - 1);
        }

        @Override
        public void paintComponent(Graphics g) {
            if (fieldImage != null) {
                Dimension currentSize = getSize();
                if (size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                } else {
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}
