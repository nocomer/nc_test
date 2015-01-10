package cn.com.jdsc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter{
	public final static int TAB_COUNT = 6;
	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int id) {
		switch (id) {
		case MainMenuActivity.TAB_HOME:
			HomeFragment homeFragment = new HomeFragment();
			return homeFragment;
		case MainMenuActivity.TAB_CATAGORY:
			CategoryFragment categoryFragment = new CategoryFragment();
			return categoryFragment;
		case MainMenuActivity.TAB_PRACTICE:
			PracticeFragment practiceFragment = new PracticeFragment();
			return practiceFragment;
		case MainMenuActivity.TAB_CAR:
			CarFragment carFragment = new CarFragment();
			return carFragment;
		case MainMenuActivity.TAB_BUY:
			BuyFragment buyFragment = new BuyFragment();
			return buyFragment;
		case MainMenuActivity.TAB_MORE:
			MoreFragment moreFragment = new MoreFragment();
			return moreFragment;
		}
		return null;
	}

	@Override
	public int getCount() {
		return TAB_COUNT;
	}
}
