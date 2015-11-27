package jp.gr.java_conf.mitchibu.badgeview;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import jp.gr.java_conf.mitchibu.lib.badgeview.BadgeView;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		BadgeView badge = (BadgeView)findViewById(R.id.test);
		badge.setText("test");
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		BadgeView badge = (BadgeView)MenuItemCompat.getActionView(menu.findItem(R.id.action_test));
		badge.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				menu.performIdentifierAction(R.id.action_test, 0);
			}
		});
		int badgeCounter = getUnreadCount();
		if(badgeCounter == 0) badge.hide();
		else badge.setText(String.valueOf(badgeCounter));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_test:
			Toast.makeText(this, "click menu", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private int getUnreadCount() {
		return 1;
	}
}
