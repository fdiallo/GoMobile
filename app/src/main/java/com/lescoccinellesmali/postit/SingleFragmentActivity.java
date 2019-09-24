package com.lescoccinellesmali.postit;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lescoccinellesmali.postit.adapter.NavDrawerListAdapter;
import com.lescoccinellesmali.postit.helper.NavDrawerItem;
import com.lescoccinellesmali.postit.view.fragment.BuyFragment;
import com.lescoccinellesmali.postit.view.fragment.MarketPlaceFragment;
import com.lescoccinellesmali.postit.view.fragment.SellFragment;
import com.lescoccinellesmali.postit.view.fragment.SupportAndFeedbackFragment;
import com.lescoccinellesmali.postit.view.fragment.TradeFragment;
//import com.lescoccinellesmali.postit.model.NavDrawerItem;
import android.widget.EditText;

import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;

public abstract class SingleFragmentActivity extends FragmentActivity {
	protected abstract Fragment createFragment();

	protected abstract Fragment createTopFragment();

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	// nav drawer title
	private CharSequence mDrawerTitle;
	// used to store app title
	private CharSequence mTitle;
	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	SessionManager session;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		FragmentManager fm = getSupportFragmentManager();
		Fragment topFragment = fm.findFragmentById(R.id.topFragment);
		if (topFragment == null) {
			topFragment = createTopFragment();
		}
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if (fragment == null) {
			fragment = createFragment();
		}
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.topFragment, topFragment);
		transaction.replace(R.id.fragmentContainer, fragment);
		transaction.commit();

		mTitle = mDrawerTitle = getTitle();
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		navDrawerItems = new ArrayList<NavDrawerItem>();
		// adding nav drawer items to array
		// Home
		//For home page
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
						.getResourceId(0, -1)));
		//Account
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		// Edit posts
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));

		// Sell posts
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1)));
/*


		// Buy posts
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));

		// Trade posts
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
				.getResourceId(5, -1)));

*/

		// Support
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));
		
		/*
		 * // Communities, Will add a counter here navDrawerItems.add(new
		 * NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1),
		 * true, "22")); 
		 * // Pages navDrawerItems.add(new
		 * NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		 * // What's hot, We will add a counter here navDrawerItems.add(new
		 * NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1),
		 * true, "50+"));
		 */
		// Recycle the typed array
		navMenuIcons.recycle();
		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			// displayView(0);
		}
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// Register mMessageReceiver to receive messages.
		LocalBroadcastManager.getInstance(this).registerReceiver(
				mMessageReceiver, new IntentFilter(LOG_OUT));
	}
	
	final String LOG_OUT = "event_logout";

	// handler for received Intents for logout event
	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// do your code snippet here.
			finish();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// Unregister since the activity is not visible
		LocalBroadcastManager.getInstance(this).unregisterReceiver(
				mMessageReceiver);
		super.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		session = new SessionManager(getApplicationContext());
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		// case R.id.action_settings:
		// return true;
		case R.id.action_logoff:
			session.logoutUser();
			// do this on logout button click
			final String LOG_OUT = "event_logout";
			Intent intent = new Intent(LOG_OUT);
			// send the broadcast to all activities who are listening
			LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/***
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		// menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		menu.findItem(R.id.action_logoff).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * Slide menu item click listener
	 */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// Display view for selected nav drawer item
			displayView(position);
		}
	}

	/**
	 * Displaying fragment view for selected nav drawer list item
	 */
	private void displayView(int position) {
		// Update the main content by replacing fragments
		Fragment fragment = null;
		// Fragment deleteAccountFragment = null;
		switch (position) {
		case 0:
			//fragment = new PostListActivity();
			Intent dashboard = new Intent(getApplicationContext(),
					PostListActivity.class);
			startActivity(dashboard);
			finish();
			break;
		case 1:
			fragment = new UpdateAccountFragment();
			// deleteAccountFragment = new DeleteAccountFragment();
			break;
		case 2:
			fragment = new EditPostFragment();
			break;
		case 3:
			 fragment = new MarketPlaceFragment();
			break;

			/*case 4:
				fragment = new BuyFragment();
				break;

			case 5:
				fragment = new TradeFragment();
				break;
*/
			case 4:
				fragment = new SupportAndFeedbackFragment();
				break;
		
		default:
			break;
		}
		if (fragment != null) {
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction tm = fm.beginTransaction();
			tm.replace(R.id.containerForFragment, fragment).commit();

			// getSupportFragmentManager().beginTransaction().replace(R.id.topFragment,
			// fragment).commit();
			// Update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// Error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

//	//For dimmissing keyboard on edittext boxes
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		View view = getCurrentFocus();
//		if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
//			int scrcoords[] = new int[2];
//			view.getLocationOnScreen(scrcoords);
//			float x = ev.getRawX() + view.getLeft() - scrcoords[0];
//			float y = ev.getRawY() + view.getTop() - scrcoords[1];
//			if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
//				((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
//		}
//		return super.dispatchTouchEvent(ev);
//	}
}