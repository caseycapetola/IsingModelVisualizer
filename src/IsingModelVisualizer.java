import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IsingModelVisualizer extends JFrame {
    private JPanel[][] grid;

    public IsingModelVisualizer(int n) {
        setTitle("Pixel Grid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(n, n));
        
        grid = new JPanel[n][n];

        // Initialize the grid with default color (e.g., white)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = new JPanel();
                grid[i][j].setPreferredSize(new Dimension(5, 5));
                grid[i][j].setBackground(Color.RED);
//                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Add borders for better visualization
                grid[i][j].addMouseListener(new PixelClickListener(i, j)); // Add mouse listener to update color
                add(grid[i][j]);
            }
        }
        grid[10][10].setBackground(Color.BLUE);

        pack();
        setLocationRelativeTo(null);
    }

    private class PixelClickListener extends MouseAdapter {
        private int row, col;

        public PixelClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Handle mouse click event to update the color of the clicked pixel
            Color newColor = JColorChooser.showDialog(null, "Choose Color", grid[row][col].getBackground());
            if (newColor != null) {
                grid[row][col].setBackground(newColor);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int n = 150; // Change this to your desired grid size (n by n)
            IsingModelVisualizer pixelGrid = new IsingModelVisualizer(n);
            pixelGrid.setVisible(true);
        });
    }
}
