import java.awt.*;
import java.awt.event.*;

public class GuiHandler {
    private int clicked = 0;
    private int prevRow, prevCol, moves = 0;
    final private Button[][] buttons;

    public GuiHandler(int[][] map, Game game) {

        Frame f = new Frame("Candy Crush");

        Panel inputPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        final TextField tf = new TextField(20);
        tf.setText("Make at least 27 to -1 to win!");
        inputPanel.add(tf);
        f.add(inputPanel, BorderLayout.NORTH);

        Panel buttonPanel = new Panel(new GridBagLayout());
        int buttonSize = 50;
        int gridSize = 6;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        buttons = new Button[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                final int row = j;
                final int col = i;
                Button b = new Button("" + map[j][i]);
                b.setPreferredSize(new Dimension(buttonSize, buttonSize));
                b.addActionListener(e -> {
                    if (clicked == 1) {

                        int result = game.moveTile(prevRow, prevCol, row, col);

                        if (result == 0) {
                            updateGUI(game.getMap());
                            clicked = 0;
                            moves++;
//                                tf.setText("SWITCHING");
                            tf.setText("MOVES: " + moves);
                        }
                        else if(result == 1){
                            clicked = 0;
//                                tf.setText("Nice!");
                        }
                        else{
                            updateGUI(game.getMap());
                            tf.setText("YOU WON!!! in " + moves + " moves");
                            System.out.println("YOU WON!!! in " + moves + " moves");
                            System.exit(0);
                        }

                    } else {
//                            tf.setText("Click one more to switch");
                        clicked++;
                        prevRow = row;
                        prevCol = col;
                    }
                });

                gbc.gridx = j;
                gbc.gridy = i;

                buttonPanel.add(b, gbc);

                buttons[j][i] = b;
            }
        }

        f.add(buttonPanel, BorderLayout.CENTER);

        f.pack();
        f.setVisible(true);


        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        game.moveTile(0, 0, 0, 0);
        updateGUI(game.getMap());
    }

    public void updateGUI(int[][] newMap) {
        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[0].length; j++) {
                buttons[j][i].setLabel("" + newMap[i][j]);
            }
        }
    }
}
