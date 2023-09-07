import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class IsingModelVisualizer extends JFrame {
    private static final long serialVersionUID = 1L;
	private static final int GRID_SIZE = 150; // Change this to your desired grid size
    private static final double BLUE_THRESHOLD = 0.5; // Adjust this threshold as needed

    private JPanel[][] gridPanels;
    private Random random;
    private int xcoord, ycoord;

    public IsingModelVisualizer() {
        random = new Random();
        gridPanels = new JPanel[GRID_SIZE][GRID_SIZE];
        initializeGUI();
        startPixelUpdate();
    }

    private void initializeGUI() {
        setTitle("Ising Model Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridPanels[i][j] = new JPanel();
                if(j<75) {
                	gridPanels[i][j].setBackground(Color.BLUE); // Initial color is blue
            	}
                else {
                	gridPanels[i][j].setBackground(Color.RED); // Initial color is red
                }
                add(gridPanels[i][j]);
            }
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startPixelUpdate() {
        Timer timer = new Timer(1000, e -> updatePixels());
        timer.start();
    }

    private void updatePixels() {
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                if (random.nextDouble() < BLUE_THRESHOLD) {
//                    gridPanels[i][j].setBackground(Color.BLUE);
//                } else {
//                    gridPanels[i][j].setBackground(Color.RED);
//                }
//            }
//        }
        xcoord = random.nextInt(150);
        ycoord = random.nextInt(150);
        if (random.nextDouble() < BLUE_THRESHOLD) {
            gridPanels[xcoord][ycoord].setBackground(Color.BLUE);
        } else {
            gridPanels[xcoord][ycoord].setBackground(Color.RED);
        }
        
    }
    
    private void isingCalculation() {
    	
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IsingModelVisualizer());
    }
}
