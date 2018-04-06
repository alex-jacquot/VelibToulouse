package view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow {

	

	public static void create() throws IOException {
		JFrame test = new JFrame("Google Maps");

		try {
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=43.60,1.44&zoom=12&size=630x600&maptype=roadmap&key=AIzaSyCcuKRt6PkePi0QmKBFR0i3G-DvXz0ToRg&format=jpg";
			String destinationFile = "image.jpg";
			String str = destinationFile;
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		test.add(new JLabel(new ImageIcon(
				(new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600, java.awt.Image.SCALE_SMOOTH))));

		test.setVisible(true);
		test.pack();

	}
}
