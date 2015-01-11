package cn.com.jdsc;

import nc.lib.*;
import nc_study.ListViewActivity;
import nc_study.ListViewAdapter;
import nc_study.MyGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.com.jdsc.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class CategoryFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ExpandableListView expandableListView;

		ListViewAdapter treeViewAdapter;

		ArrayList<String> groups = new ArrayList<String>();
		String[][] child = { { "" }, { "" }, { "", "" } };

		List<String> menu_toolbar_name_array = Arrays.asList("�洢��", "�ҵ�����",
				"ͼ�鵼��", "ϵͳ����", "ϵͳ�ָ�", "���ȿ��", "������", "��������", "���ڿ���", "�˳�ϵͳ",
				"������", "��������", "���ڿ���", "�˳�ϵͳ", "���ڿ���", "�˳�ϵͳ", "���ڿ���", "�˳�ϵͳ",
				"���ڿ���", "�˳�ϵͳ");

		List<Integer> menu_toolbar_image_array = Arrays.asList(
				R.drawable.icon_sdcard, R.drawable.icon_sdcard,
				R.drawable.icon_sdcard, R.drawable.icon_sdcard,
				R.drawable.icon_sdcard, R.drawable.icon_sdcard,
				R.drawable.icon_sdcard, R.drawable.icon_sdcard,
				R.drawable.icon_sdcard, R.drawable.icon_sdcard,
				R.drawable.icon_sdcard, R.drawable.icon_sdcard,
				R.drawable.icon_sdcard, R.drawable.icon_sdcard,
				R.drawable.icon_sdcard, R.drawable.icon_sdcard,
				R.drawable.icon_sdcard, R.drawable.icon_sdcard,
				R.drawable.icon_sdcard, R.drawable.icon_sdcard);

		// TODO Auto-generated method stub
		System.out.println("CategoryFragment");

		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main_category);

		// todo
		HttpInter HttpInter = new HttpInter();
		JSONArray jsonArray = HttpInter.getCategory();
		
		// NullPointer
		if (jsonArray.length() > 0) {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject job = null;
				try {
					job = jsonArray.getJSONObject(i);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("Get json object error!");
					e.printStackTrace();
				}

				String value = null;
				try {
					value = job.getString("MainMenu");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("cant not get json object 'MainMenue'!");
					e.printStackTrace();
				}
				groups.add(value);
			}
		}

		View view = inflater.inflate(R.layout.main_category, container, false);

		treeViewAdapter = new ListViewAdapter(view.getContext(),
				ListViewAdapter.PaddingLeft >> 1);
		expandableListView = (ExpandableListView) view
				.findViewById(R.id.expandableListView);

		List<ListViewAdapter.TreeNode> treeNode = treeViewAdapter.GetTreeNode();

		for (int i = 0; i < groups.size(); i++) {
			ListViewAdapter.TreeNode node = new ListViewAdapter.TreeNode();
			node.parent = groups.get(i);
			for (int ii = 0; ii < child[i].length; ii++) {
				node.childs.add(child[i][ii]);
			}
			treeNode.add(node);
		}

		int result = treeViewAdapter.setSecondaryMenu(menu_toolbar_name_array,
				menu_toolbar_image_array);

		if (result != 0) {
			Debug debug = new Debug();
			debug.nc_dialog("setSecondaryMenu Fail!");
		}

		treeViewAdapter.UpdateTreeNode(treeNode);
		expandableListView.setAdapter(treeViewAdapter);

		// return inflater.inflate(R.layout.main_category, container, false);
		return view;
	}
}
