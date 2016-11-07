package lpirm.bdmob.contactmanager.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;

import lpirm.bdmob.contactmanager.R;
import lpirm.bdmob.contactmanager.adapter.CustomCursorAdapter;
import lpirm.bdmob.contactmanager.databaseManager.DatabaseManager;
import lpirm.bdmob.contactmanager.provider.*;

public class ContactsMainActivity extends Activity implements OnClickListener,
		LoaderManager.LoaderCallbacks<Cursor> {

	final static String TAG = "MainActivity";
	public final static int CONTACT_ADD_REQ_CODE = 100;
	public final static int CONTACT_UPDATE_REQ_CODE = 101;
	public final static String REQ_TYPE = "req_type";
	public final static String ITEM_POSITION = "item_position";
	private static final int CUR_LOADER = 0;

	private ListView listContact;
	private Button addNewButton;

	DatabaseManager dm;

	private CustomCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		bindViews();
		setListeners();
		dm = new DatabaseManager(this);

		mAdapter = new CustomCursorAdapter(this, null);

		displayListView();
		getLoaderManager().initLoader(CUR_LOADER, null, this);
	}

	private void displayListView() {

		// Assign adapter to ListView
		listContact.setAdapter(mAdapter);
		registerForContextMenu(listContact);

	}

	private void bindViews() {
		addNewButton = (Button) findViewById(R.id.addNew);
		listContact = (ListView) findViewById(R.id.list);
	}

	private void setListeners() {
		addNewButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.addNew:
			Intent intent = new Intent(ContactsMainActivity.this,
					AddNewContactActivity.class);
			intent.putExtra(REQ_TYPE, CONTACT_ADD_REQ_CODE);
			startActivityForResult(intent, CONTACT_ADD_REQ_CODE);
			break;
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater m = getMenuInflater();
		m.inflate(R.menu.del_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.delete_item:

		case R.id.update_item:

			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CONTACT_ADD_REQ_CODE) {

			if (resultCode == RESULT_OK) {
			} else if (resultCode == RESULT_CANCELED) {

			}
		} else if (requestCode == CONTACT_UPDATE_REQ_CODE) {
			if (resultCode == RESULT_OK) {
			} else if (resultCode == RESULT_CANCELED) {

			}
		}
	}

	/*
	 * Callback that's invoked when the system has initialized the Loader and is
	 * ready to start the query. This usually happens when initLoader() is
	 * called. The loaderID argument contains the ID value passed to the
	 * initLoader() call.
	 */
	@Override
	public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle) {
		/*
		 * Takes action based on the ID of the Loader that's being created
		 */
		switch (loaderID) {
		case CUR_LOADER:
			// Returns a new CursorLoader
			return new CursorLoader(this, // Parent activity context
					PersonalContactContract.CONTENT_URI, // Table to query
					PersonalContactContract.PROJECTION_ALL, // Projection to
															// return
					null, // No selection clause
					null, // No selection arguments
					null // Default sort order
			);
		default:
			// An invalid id was passed in
			return null;
		}
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// TODO Auto-generated method stub
		this.mAdapter.changeCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		this.mAdapter.changeCursor(null);

	}

}
