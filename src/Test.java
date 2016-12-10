
public class Test {

	public static void main(String[] args) {
		Bussion bussion = new Bussion();

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i <= 50; i++) {
					bussion.sub();
				}

			}
		}).start();

		for (int i = 1; i <= 50; i++) {
			bussion.main();
			;
		}

	}

}

class Bussion {

	private boolean flag = true;

	public synchronized void sub() {
		if (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 1; i <= 10; i++) {
			System.out.println("子线程循环" + i);
		}

		flag = false;

		notify();

	}

	public synchronized void main() {
		if (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 100; i++) {
			System.out.println("main " + i);
		}

		flag = true;

		notify();

	}

}
