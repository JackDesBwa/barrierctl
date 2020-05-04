package org.desbwa.barrierctl;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.io.FileWriter;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout l = new LinearLayout(this);
		setContentView(l);

		Button buttonOn = new Button(this);
		buttonOn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.5f));
		buttonOn.setText("On");
		buttonOn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Try to enable 3D barrier (NEO3DO?)
				try {
					FileWriter fstream = new FileWriter("/sys/class/enable3d/enable-3d");
					fstream.write("1");
					fstream.close();
				} catch (Exception e) {
					// pass
				}

				// Try to enable 3D barrier (MasterImage?)
				try {
					FileWriter fstream = new FileWriter("/dev/mi3d_tn_ctrl");
					fstream.write(0x20);
					fstream.close();
				} catch (Exception e) {
					// pass
				}
			}
		});
		l.addView(buttonOn);

		Button buttonOff = new Button(this);
		buttonOff.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0.5f));
		buttonOff.setText("Off");
		buttonOff.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Try to disable 3D barrier (NEO3DO?)
				try {
					FileWriter fstream = new FileWriter("/sys/class/enable3d/enable-3d");
					fstream.write("0");
					fstream.close();
				} catch (Exception e) {
					// pass
				}

				// Try to disable 3D barrier (MasterImage?)
				try {
					FileWriter fstream = new FileWriter("/dev/mi3d_tn_ctrl");
					fstream.write(0x10);
					fstream.close();
				} catch (Exception e) {
					// pass
				}
			}
		});
		l.addView(buttonOff);
	}
}
