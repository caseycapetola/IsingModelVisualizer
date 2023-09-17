import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class IsingModelVisualizer extends JFrame {
    private static final long serialVersionUID = 1L;
	private static final int GRID_SIZE = 300;
    private static final double BLUE_THRESHOLD = 0.5;
    private static final int BETA = 10;
    private static final int POS = 1;
    private static final int NEG = -1;

    private JPanel[][] gridPanels;
    private int[][] grid;
    private Random random;
    private int xcoord, ycoord;

    public IsingModelVisualizer() {
        random = new Random();
        gridPanels = new JPanel[GRID_SIZE][GRID_SIZE];
        grid = new int[GRID_SIZE][GRID_SIZE];
        initializeGUI();
        startPixelUpdate();
    }

    private void initializeGUI() {
        setTitle("Ising Model Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                gridPanels[i][j] = new JPanel();
//                if(j<75) {
//                	gridPanels[i][j].setBackground(Color.BLUE); // Initial color is blue
//                	grid[i][j] = NEG;
//            	}
//                else {
//                	gridPanels[i][j].setBackground(Color.RED); // Initial color is red
//                	grid[i][j] = POS;
//                }
//                add(gridPanels[i][j]);
//            }
//        }
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                gridPanels[i][j] = new JPanel();
//                gridPanels[i][j].setPreferredSize(new Dimension(2,2));
//                if(j%2==0 || i%2==0) {
//                	gridPanels[i][j].setBackground(Color.BLUE); // Initial color is blue
//                	grid[i][j] = NEG;
//            	}
//                else {
//                	gridPanels[i][j].setBackground(Color.RED); // Initial color is red
//                	grid[i][j] = POS;
//                }
//                add(gridPanels[i][j]);
//            }
//        }
        
        
        for (int i = 0; i < GRID_SIZE; i++) {
          for (int j = 0; j < GRID_SIZE; j++) {
              gridPanels[i][j] = new JPanel();
              gridPanels[i][j].setPreferredSize(new Dimension(2,2));
              double rand = random.nextFloat();
              if(rand < BLUE_THRESHOLD) {
              	gridPanels[i][j].setBackground(Color.BLUE); // Initial color is blue
              	grid[i][j] = NEG;
          	}
              else {
              	gridPanels[i][j].setBackground(Color.RED); // Initial color is red
              	grid[i][j] = POS;
              }
              add(gridPanels[i][j]);
          }
      }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startPixelUpdate() {
        Timer timer = new Timer(1, e -> updatePixels());
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
        xcoord = random.nextInt(GRID_SIZE);
        ycoord = random.nextInt(GRID_SIZE);
        double flipPos = isingCalculation(xcoord, ycoord);
        if (random.nextDouble() > flipPos) { // Legacy code: if (random.nextDouble() < BLUE_THRESHOLD)
            gridPanels[xcoord][ycoord].setBackground(Color.BLUE);
        } else {
            gridPanels[xcoord][ycoord].setBackground(Color.RED);
        }
        
    }
    
    private double isingCalculation(int x, int y) {
    	int numPos = 0;
    	int numNeg = 0;
    	
		// CHECK HORIZONTAL NEIGHBORS
		if(y>0) {
			if(grid[x][y-1] == POS) {
				numPos++;
			}
			else {
				numNeg++;
			}
		}
		if(y<GRID_SIZE-1) {
			if(grid[x][y+1] == POS) {
				numPos++;
			}
			else {
				numNeg++;
			}
		}
		
		// CHECK VERTICAL NEIGHBORS
		if(x>0) {
			if(grid[x-1][y] == POS) {
				numPos++;
			}
			else {
				numNeg++;
			}
		}
		if(x<GRID_SIZE-1) {
			if(grid[x+1][y] == POS) {
				numPos++;
			}
			else {
				numNeg++;
			}
		}
		
		// DO CALCULATION
//		System.out.println("Prob: " + Math.exp(BETA*numPos)/(Math.exp(BETA*numPos) + Math.exp(BETA*numNeg)));
		
		return (Math.exp(BETA*numPos)/(Math.exp(BETA*numPos) + Math.exp(BETA*numNeg)));
    	
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IsingModelVisualizer());
    }
}
