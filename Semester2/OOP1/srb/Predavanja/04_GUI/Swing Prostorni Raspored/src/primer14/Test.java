package primer14;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class Test extends JFrame {

	private static final long serialVersionUID = 1L;

	private volatile boolean running = false;

	private JProgressBar progressBar;
	private JButton button = new JButton("Start");

	private SwingWorker<String, Integer> worker;

	public Test() {
		setSize(300, 200);
		setTitle("SwingWorker Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		getContentPane().add(progressBar);
		getContentPane().add(button);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!running) {
					running = true;
					progressBar.setValue(0);
					button.setText("Stop");

					// String - rezultat rada, koji vraća metoda
					// doInBackground(), a koji se moze pokupiti
					// pozivom metode get()
					// Integer - tip podatka koji se racuna, kao medjurezultat.
					// Ovi međurezultati se objavljuju metodom publish(), a
					// prima ih metoda process()
					worker = new SwingWorker<String, Integer>() {
						// executed on a background worker thread
						@Override
						protected String doInBackground() throws Exception {
							for (int i = 1; i <= 10; i++) {
								try {
									Thread.sleep(1000);
								} catch (Exception ex) {
									throw ex;
								}
								// ovo nije neophodno, ako se worker zaustavlja
								// sa cancel(true)
								// if (isCancelled())
								// break;
								int intermediateValue = i * 10;
								// objavimo medjurezultat (primice ga metoda
								// process)
								publish(intermediateValue);
							}
							return "Done";
						}

						/**
						 * Prima medjurezultate, i odavde ih prikazujem u swing
						 * komponentama Ako metoda publish posalje jednu
						 * vrednost, lista chunks ima jedan element: publish(1,
						 * 2, 3) -> process(1, 2, 3), odn. lista chunks ima tri
						 * elementa.
						 */
						@Override
						protected void process(java.util.List<Integer> chunks) {
							System.out.println("process()");
							for (Integer chunk : chunks) {
								System.out.println("Progres: "
										+ chunk.intValue());
								progressBar.setValue(chunk.intValue());
							}
						}

						/**
						 * Poziva se kada se zavrsi izvrsenje (milom ili silom).
						 */
						@Override
						protected void done() {
							button.setText("Start");
							running = false;
						}
					};

					// startujemo worker
					worker.execute();
				} else {
					// parametar true na silu zaustavlja worker
					worker.cancel(true);
				}
			}
		});
	}

	public static void main(String[] args) {
		new Test().setVisible(true);
	}
}