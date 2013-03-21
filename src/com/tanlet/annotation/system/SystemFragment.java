/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tanlet.annotation.system;

import java.util.concurrent.TimeUnit;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.SystemService;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.tanlet.annotationdemo.R;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.Toast;

/**
 * Show some system tag in annotation:<a href=
 * 'https://github.com/excilys/androidannotations/wiki/SystemServices'>@SystemServices</a
 * > <a href=
 * 'https://github.com/excilys/androidannotations/wiki/WorkingWithThreads'>@UiThread</a
 * > <a href=
 * 'https://github.com/excilys/androidannotations/wiki/WorkingWithThreads'>@UiThread</a
 * >
 * 
 * @author <a href=
 *         'https://github.com/excilys/androidannotations/wiki/WorkingWithThreads'>@Background</a
 *         >
 */
@EFragment(R.layout.system_fragment)
public class SystemFragment extends Fragment {
	@ViewById
	protected Button btNotification;
	@SystemService
	protected NotificationManager notificationManager;

	@ViewById
	protected Button btWorkUI;
	@ViewById
	protected Button btWorkUIDelay;
	@ViewById
	protected Button btWorkBG;

	@Click
	protected void btWorkUI() {
		this.workInUI();
	}

	@Click
	protected void btWorkUIDelay() {
		this.workInUIDelay();
	}

	@Click
	protected void btWorkBG() {
		this.workInBG();
	}

	@Click
	protected void btNotification() {
		this.showNotifications();
	}

	@UiThread
	protected void workInUI() {
		this.showToast("Hi in UI Thread!");

	}

	@UiThread(delay = 2000)
	protected void workInUIDelay() {
		this.showToast("Hi in UI Thread,but delay 2000ms!");
	}

	@Background
	protected void workInBG() {
		this.showToast("Wait 5000 ms");
		try {
			TimeUnit.SECONDS.sleep(50L);
		} catch (InterruptedException e) {
		}
		this.showNotifications();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	void showNotifications() {
		Notification notification;
		if (Build.VERSION.SDK_INT >= 11) {
			notification = new Notification.Builder(getActivity())
					.setSmallIcon(R.drawable.help)
					.setLargeIcon(
							BitmapFactory.decodeResource(getResources(),
									R.drawable.ic_launcher))
					.setContentTitle("AndroidAnnotations")
					.setContentText(
							"https://github.com/excilys/androidannotations/wiki/AvailableAnnotations")
					.build();
		} else {
			notification = new Notification(R.drawable.ic_launcher,
					"AndroidAnnotations", 0);
			PendingIntent contentIntent = PendingIntent.getActivity(
					getActivity(), 0, new Intent(), 0);
			notification.setLatestEventInfo(getActivity(), "My notification",
					"Hello World!", contentIntent);
		}

		notificationManager.notify(1, notification);

	}

	@UiThread
	protected void showToast(String toast) {
		Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
	}
}
