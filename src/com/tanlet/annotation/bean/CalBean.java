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

package com.tanlet.annotation.bean;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * @author <a href='mailto:tanletwork@gmail.com'>Tanlet</a>
 */
@EBean
public class CalBean {
	@ViewById
	protected EditText etNum1;
	@ViewById
	protected EditText etNum2;
	@ViewById
	protected TextView tvResult;
	@RootContext
	Context context;

	@UiThread
	public void CalResult() {
		int result;
		String num1 = this.etNum1.getText().toString().trim();
		String num2 = this.etNum2.getText().toString().trim();
		if (!num1.equals("") && !num2.equals("")) {
			result = Integer.valueOf(num2) + Integer.valueOf(num2);
			this.tvResult.setText("" + result);
			Toast.makeText(context, "Hello ! I work in a bean",
					Toast.LENGTH_SHORT).show();
		}
	}
}
