import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Colemak
	extends Frame implements KeyListener, ActionListener {

	private Label labelIn;
	private Label labelOut;
	private TextField qwertyIn;
	private TextField colemakOut;
	private Button clearWriteButton;

	private Map <Character,Character> table = new HashMap<Character,Character>();

	public Colemak() {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		labelIn = new Label("Enter QWERTY:");
		add(labelIn);

		qwertyIn = new TextField(200);
		qwertyIn.addActionListener(this);
		add(qwertyIn);

		qwertyIn.addKeyListener(this);

		labelOut = new Label("Colemak:");
		add(labelOut);

		colemakOut = new TextField(200);
		colemakOut.setEditable(false);
		add(colemakOut);

		clearWriteButton = new Button("Clear and write");
		add(clearWriteButton);
		clearWriteButton.addActionListener(this);

		setTitle("Syed's QWERTY to Colemak program");
		setSize(350, 350);
		setVisible(true);

		table.put('e', 'f'); table.put('r', 'p'); table.put('t', 'g'); table.put('y', 'j'); table.put('u', 'l'); table.put('i', 'u'); table.put('o', 'y'); table.put('p', ';'); table.put('s', 'r'); table.put('d', 's'); table.put('f', 't'); table.put('g', 'd'); table.put('j', 'n'); table.put('k', 'e'); table.put('l', 'i'); table.put(';', 'o'); table.put('n', 'k'); table.put('E', 'F'); table.put('R', 'P'); table.put('T', 'G'); table.put('Y', 'J'); table.put('U', 'L'); table.put('I', 'U'); table.put('O', 'Y'); table.put('P', ':'); table.put('S', 'R'); table.put('D', 'S'); table.put('F', 'T'); table.put('G', 'D'); table.put('J', 'N'); table.put('K', 'E'); table.put('L', 'I'); table.put(':', 'O'); table.put('N', 'K');
	}

	public String qwertyToColemak(String s) {
		String n = "";
		Character x = '0';
		for (Character c : s.toCharArray()) {
			if (table.containsKey(c)) {
				x = table.get(c);
			} else {
				x = c;
			}
			n += Character.toString(x);
		}
		return n;
	}

	public static void main(String[] args) {
		new Colemak();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("colemak-out.txt", true)));
			out.println(colemakOut.getText());
			out.close();
			System.out.println("Write.")
		} catch (IOException e) {
			System.out.println("No write.");
			e.printStackTrace();
		}
		qwertyIn.setText("");
		colemakOut.setText("");
	}

	public void keyReleased(KeyEvent e)
	{
		colemakOut.setText(qwertyToColemak(qwertyIn.getText()));
	}

	public void keyPressed(KeyEvent e)
	{
		colemakOut.setText(qwertyToColemak(qwertyIn.getText()));
	}

	public void keyTyped(KeyEvent e)
	{}

}

