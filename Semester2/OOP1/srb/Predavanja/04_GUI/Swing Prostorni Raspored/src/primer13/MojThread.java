package primer13;

public class MojThread extends Thread {
	Test t;
	public MojThread(Test t) {
		this.t = t;
	}
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			if (t.stop) {
				t.stop = false;
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {
			}
			t.progressBar.setValue(i * 10);
			System.out.println("Done " + i * 10 + "%");
		}
		
		t.running = false;
		t.b.setText(" Start ");
	}
}
