package com.lescoccinellesmali.postit.view.fragment;
import java.util.ArrayList;

import com.lescoccinellesmali.postit.R;
import com.lescoccinellesmali.postit.model.Post;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;

import java.util.Timer;
import android.os.Parcelable;
import android.content.Context;


public class TopFragment extends Fragment{
	Button mStudyGroupButton, mEventButton, mTodayButton, mThisWeekButton, mSearchButton;
	EditText mSearchText;
	ArrayList<Post> mPosts;	String query;
	TopFragmentListenerInterface mCallback;
//    ViewPager viewPager;
//    MyPagerAdapter myPagerAdapter;


    int noofsize = 5;
    ViewPager myPager = null;
    int count = 0;
    Timer timer;

	Button next, prev;
	public interface TopFragmentListenerInterface{
		public void onSearchButtonClicked(String query);
		public void onStudyGroupButtonClicked();
		public void onEventButtonClicked();
		public void onTodayButtonClicked();
		public void onThisWeekButtonClicked();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


	}	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallback = (TopFragmentListenerInterface) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement TopFragmentListenerInterface");
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.top_fragment, null);
		mSearchText = (EditText) v.findViewById(R.id.searchText);
		mSearchButton = (Button) v.findViewById(R.id.searchButton);
		mSearchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				query = mSearchText.getText().toString();
				//Log.v("EditText", query);
				mCallback.onSearchButtonClicked(query); 
			}
		});
		mStudyGroupButton = (Button) v.findViewById(R.id.studyGroup);
		mStudyGroupButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.onStudyGroupButtonClicked(); 
			}
		});
		mEventButton = (Button) v.findViewById(R.id.event);
		mEventButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.onEventButtonClicked(); 
			}
		});
		mTodayButton = (Button) v.findViewById(R.id.today);
		mTodayButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.onTodayButtonClicked(); 
			}
		});
		mThisWeekButton = (Button) v.findViewById(R.id.thisWeek);
		mThisWeekButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.onThisWeekButtonClicked(); 
			}
		});

        ViewPagerAdapter adapter = new ViewPagerAdapter(TopFragment.this,noofsize);
        myPager = (ViewPager)v.findViewById(R.id.reviewpager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);

        // Do not delete this code - Timer for auto sliding
//        timer  = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(count<=5){
//                            myPager.setCurrentItem(count);
//                            count++;
//                        }else{
//                            count = 0;
//                            myPager.setCurrentItem(count);
//                        }
//                    }
//                });
//            }
//        }, 500, 3000);

//        viewPager = (ViewPager)v.findViewById(R.id.myViewPager);
//        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
//        myPagerAdapter = new MyPagerAdapter();
//        viewPager.setAdapter(myPagerAdapter);

		return v;
	}


    private class ViewPagerAdapter extends PagerAdapter {
        int size;
        Fragment fragment;
        View layout;
        TextView pagenumber1, pagenumber2, pagenumber3, pagenumber4, pagenumber5;
        ImageView pageImage;
        Button click;

        public ViewPagerAdapter(TopFragment topFragment, int noofsize) {
            // TODO Auto-generated constructor stub
            size = noofsize;
            fragment = topFragment;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return size;
        }

        @Override
        public Object instantiateItem(View container, int position) {
            // TODO Auto-generated method stub
            LayoutInflater inflater = (LayoutInflater) fragment.getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.pages, null);
            pagenumber1 = (TextView) layout.findViewById(R.id.pagenumber1);
            pagenumber2 = (TextView) layout.findViewById(R.id.pagenumber2);
            pagenumber3 = (TextView) layout.findViewById(R.id.pagenumber3);
            pagenumber4 = (TextView) layout.findViewById(R.id.pagenumber4);
            pagenumber5 = (TextView) layout.findViewById(R.id.pagenumber5);
            pageImage = (ImageView) layout.findViewById(R.id.imageView1);
            int pagenumberTxt = position + 1;
            //pagenumber1.setText("Now your in Page No  " +pagenumberTxt );

            try {
                if (pagenumberTxt == 1) {
                    pageImage.setBackgroundResource(R.drawable.ic_one);
                    pagenumber1.setTextColor(Color.RED);
                    pagenumber2.setTextColor(Color.WHITE);
                    pagenumber3.setTextColor(Color.WHITE);
                    pagenumber4.setTextColor(Color.WHITE);
                    pagenumber5.setTextColor(Color.WHITE);
                } else if (pagenumberTxt == 2) {
                    pageImage.setBackgroundResource(R.drawable.ic_two);
                    pagenumber1.setTextColor(Color.WHITE);
                    pagenumber2.setTextColor(Color.RED);
                    pagenumber3.setTextColor(Color.WHITE);
                    pagenumber4.setTextColor(Color.WHITE);
                    pagenumber5.setTextColor(Color.WHITE);
                } else if (pagenumberTxt == 3) {
                    pageImage.setBackgroundResource(R.drawable.ic_three);
                    pagenumber1.setTextColor(Color.WHITE);
                    pagenumber2.setTextColor(Color.WHITE);
                    pagenumber3.setTextColor(Color.RED);
                    pagenumber4.setTextColor(Color.WHITE);
                    pagenumber5.setTextColor(Color.WHITE);
                } else if (pagenumberTxt == 4) {
                    pageImage.setBackgroundResource(R.drawable.ic_four);
                    pagenumber1.setTextColor(Color.WHITE);
                    pagenumber2.setTextColor(Color.WHITE);
                    pagenumber3.setTextColor(Color.WHITE);
                    pagenumber4.setTextColor(Color.RED);
                    pagenumber5.setTextColor(Color.WHITE);
                } else if (pagenumberTxt == 5) {
                    pageImage.setBackgroundResource(R.drawable.ic_five);
                    pagenumber1.setTextColor(Color.WHITE);
                    pagenumber2.setTextColor(Color.WHITE);
                    pagenumber3.setTextColor(Color.WHITE);
                    pagenumber4.setTextColor(Color.WHITE);
                    pagenumber5.setTextColor(Color.RED);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            ((ViewPager) container).addView(layout, 0);
            return layout;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }

//    private class ZoomOutPageTransformer implements ViewPager.PageTransformer {
//        private static final float MIN_SCALE = 0.85f;
//        private static final float MIN_ALPHA = 0.5f;
//
//        public void transformPage(View view, float position) {
//            int pageWidth = view.getWidth();
//            int pageHeight = view.getHeight();
//
//            if (position < -1) { // [-Infinity,-1)
//                // This page is way off-screen to the left.
//                view.setAlpha(0);
//
//            } else if (position <= 1) { // [-1,1]
//                // Modify the default slide transition to shrink the page as well
//                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
//                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
//                if (position < 0) {
//                    view.setTranslationX(horzMargin - vertMargin / 2);
//                } else {
//                    view.setTranslationX(-horzMargin + vertMargin / 2);
//                }
//
//                // Scale the page down (between MIN_SCALE and 1)
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);
//
//                // Fade the page relative to its size.
//                view.setAlpha(MIN_ALPHA +
//                        (scaleFactor - MIN_SCALE) /
//                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//
//            } else { // (1,+Infinity]
//                // This page is way off-screen to the right.
//                view.setAlpha(0);
//            }
//        }
//    }
//
//
//
//    private class MyPagerAdapter extends PagerAdapter{
//
//        int NumberOfPages = 5;
//
//        int[] res = {
//                //R.drawable.ic_one;
//                android.R.drawable.ic_dialog_alert,
//                android.R.drawable.ic_menu_camera,
//                android.R.drawable.ic_menu_compass,
//                android.R.drawable.ic_menu_directions,
//                android.R.drawable.ic_menu_gallery};
//        int[] backgroundcolor = {
//                0xFF101010,
//                0xFF202020,
//                0xFF303030,
//                0xFF404040,
//                0xFF505050};
//
//        @Override
//        public int getCount() {
//            return NumberOfPages;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//
//            TextView textView = new TextView(getContext());
//            textView.setTextColor(Color.WHITE);
//            textView.setTextSize(30);
//            textView.setTypeface(Typeface.DEFAULT_BOLD);
//            textView.setText(String.valueOf(position));
//
//            ImageView imageView = new ImageView(getContext());
//            imageView.setImageResource(res[position]);
//            LayoutParams imageParams = new LayoutParams(
//                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
//            imageView.setLayoutParams(imageParams);
//
//            LinearLayout layout = new LinearLayout(getContext());
//            layout.setOrientation(LinearLayout.VERTICAL);
//            LayoutParams layoutParams = new LayoutParams(
//                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
//            layout.setBackgroundColor(backgroundcolor[position]);
//            layout.setLayoutParams(layoutParams);
//            layout.addView(textView);
//            layout.addView(imageView);
//
//            final int page = position;
//            layout.setOnClickListener(new OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext(),
//                            "Page " + page + " clicked",
//                            Toast.LENGTH_LONG).show();
//                }});
//
//            container.addView(layout);
//            return layout;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((LinearLayout)object);
//        }
//
//    }



}