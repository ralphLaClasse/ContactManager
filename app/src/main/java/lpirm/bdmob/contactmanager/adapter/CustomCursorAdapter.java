package lpirm.bdmob.contactmanager.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lpirm.bdmob.contactmanager.R;
import lpirm.bdmob.contactmanager.databaseManager.DatabaseConstants;

public class CustomCursorAdapter extends CursorAdapter {
	Context context;

	public CustomCursorAdapter(Context context, Cursor c) {
		super(context, c);
		this.context = context;
	}

	@Override
	public void bindView(View view, Context arg1, Cursor cursor) {
		final ImageView contact_photo = (ImageView) view
				.findViewById(R.id.contact_photo);
		final TextView contact_name = (TextView) view
				.findViewById(R.id.contact_name);
		final TextView contact_phone = (TextView) view
				.findViewById(R.id.contact_phone);
		final TextView contact_email = (TextView) view
				.findViewById(R.id.contact_email);
		contact_name.setText(cursor.getString(cursor
				.getColumnIndexOrThrow(DatabaseConstants.TABLE_ROW_NAME)));
		contact_phone.setText(cursor.getString(cursor
				.getColumnIndexOrThrow(DatabaseConstants.TABLE_ROW_PHONENUM)));
		contact_email.setText(cursor.getString(cursor
				.getColumnIndexOrThrow(DatabaseConstants.TABLE_ROW_EMAIL)));
		setImage(cursor.getBlob(cursor
				.getColumnIndex(DatabaseConstants.TABLE_ROW_PHOTOID)),
				contact_photo);
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final View view = LayoutInflater.from(context).inflate(
				R.layout.contact_list_row, null, false);
		return view;
	}

	private void setImage(byte[] blob, ImageView img) {

		if (blob != null) {
			Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
			img.setImageBitmap(bmp);
		}
	}
}
