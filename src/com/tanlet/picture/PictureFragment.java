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

package com.tanlet.picture;

import java.io.File;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.droid4you.util.cropimage.CropImage;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.OnActivityResult;
import com.googlecode.androidannotations.annotations.ViewById;
import com.tanlet.annotationdemo.R;

/**
 * @author <a href='mailto:tanletwork@gmail.com'>Tanlet</a>
 */
@EFragment(R.layout.picture_fragment)
public class PictureFragment extends Fragment {
	@ViewById
	protected ImageView ivPicture;
	private Uri mImageCaptureUri;

	@Click(R.id.btPicture)
	protected void takePicture() {
		this.doTakePhotoAction();
	}

	PictureFragment_ a;

	@OnActivityResult(value = 10086)
	protected void cutPicture(int resultCode, Intent result) {
		if (resultCode != Activity.RESULT_OK)
			return;
		Intent intent = new Intent(getActivity(), CropImage.class);
		intent.putExtra("image-path", mImageCaptureUri.getPath());
		intent.putExtra("scale", true);
		startActivityForResult(intent, 10019);
	}

	@OnActivityResult(value = 10019)
	protected void showPicture(int resultCode, Intent result) {
		if (resultCode != Activity.RESULT_OK)
			return;
		Bitmap bitMap = BitmapFactory.decodeFile(mImageCaptureUri.getPath());

		BitmapDrawable drawble = new BitmapDrawable(bitMap);
		this.ivPicture.setImageDrawable(drawble);
	}

	private void doTakePhotoAction() {

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		mImageCaptureUri = Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), "annotationDemo"
				+ String.valueOf(System.currentTimeMillis()) + ".jpg"));

		intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
				mImageCaptureUri);

		try {
			intent.putExtra("return-data", false);
			startActivityForResult(intent, 10086);

		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
		}
	}
}
