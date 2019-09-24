package com.lescoccinellesmali.postit;
import android.support.v4.app.Fragment;

public class PostActivity extends SingleFragmentActivity {
	@Override
	protected Fragment createFragment() {
		int postId = (Integer) getIntent()
				.getSerializableExtra(PostFragment.EXTRA_POST_ID);
		return PostFragment.newInstance(postId);
	}
	@Override
	protected Fragment createTopFragment() {
		return new TopFragment();
	}	
}
