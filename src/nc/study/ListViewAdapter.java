package nc.study;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cn.com.jdsc.R;

import android.content.Context;
import android.graphics.Color;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Toast;

import android.widget.SimpleAdapter;
import android.widget.TextView;


public class ListViewAdapter extends BaseExpandableListAdapter implements
		OnItemClickListener
{
	public static final int ItemHeight = 48;// 每项的高度
	public static final int PaddingLeft = 36;// 每项的高度
	private int myPaddingLeft = 0;

	private MyGridView toolbarGrid;

	private List<String> menu_toolbar_name_array = new ArrayList<String>();
	private List<Integer> menu_toolbar_image_array = new ArrayList<Integer>();
	
	
	private List<TreeNode> treeNodes = new ArrayList<TreeNode>();

	private Context parentContext;

	private LayoutInflater layoutInflater;

	static public class TreeNode
	{
		public Object parent;
		public List<Object> childs = new ArrayList<Object>();
	}

	public ListViewAdapter(Context view, int myPaddingLeft)
	{
		parentContext = view;
		this.myPaddingLeft = myPaddingLeft;
	}

	public List<TreeNode> GetTreeNode()
	{
		return treeNodes;
	}

	public void UpdateTreeNode(List<TreeNode> nodes)
	{
		treeNodes = nodes;
	}

	public void RemoveAll()
	{
		treeNodes.clear();
	}

	public Object getChild(int groupPosition, int childPosition)
	{
		return treeNodes.get(groupPosition).childs.get(childPosition);
	}

	public int getChildrenCount(int groupPosition)
	{
		return treeNodes.get(groupPosition).childs.size();
	}
	
	// 定义文字获取方法
	static public TextView getTextView(Context context)
	{
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, ItemHeight);

		TextView textView = new TextView(context);
		textView.setLayoutParams(lp);
		textView.setTextSize(20);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		return textView;
	}

	//可自定义ExpandableListView + GridView
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			layoutInflater = (LayoutInflater) parentContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.view, null);
			
			toolbarGrid = (MyGridView) convertView
					.findViewById(R.id.GridView_toolbar);
			
			// 设置每行列数
			toolbarGrid.setNumColumns(4);
			// 位置居中
			toolbarGrid.setGravity(Gravity.CENTER);
			// 水平间隔
			toolbarGrid.setHorizontalSpacing(10);
			
			// Set menu adapter
			toolbarGrid.setAdapter(getSecondaryMenu());
			
			toolbarGrid.setOnItemClickListener(this);

		}

		return convertView;
	}

	/* Add secondary menu */
	public int  addSecondaryMenu(List<String> name,
			List<Integer> image){
		
		return 0;
	}
	
	/* Delete secondary menu */
	public int  DeleteSecondaryMenu(List<String> name,
			List<Integer> image){
		
		return 0;
	}
	
	/* Set secondary menu */
	public int  setSecondaryMenu(List<String> name,
			List<Integer> image){
		
		if((name.size()& 
				image.size()) == 0){
			return -1;
		}
		
		int nameSize = menu_toolbar_name_array.size();
		
		for (int i = 0; i < name.size(); i++){			
			if(i < nameSize){
				menu_toolbar_name_array.set(i, name.get(i));
			}else{
				menu_toolbar_name_array.add(name.get(i));
			}
		}
		
		int imageSize = menu_toolbar_image_array.size();
		
		for (int i = 0; i < image.size(); i++){		
			if(i < imageSize){
				menu_toolbar_image_array.set(i, image.get(i));
			}else{
				menu_toolbar_image_array.add(image.get(i));
			}
		}
		
		return 0;
	}
	
	public SimpleAdapter getSecondaryMenu(){
		return getMenuAdapter(menu_toolbar_name_array,
				menu_toolbar_image_array);
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent)
	{
		TextView textView = getTextView(this.parentContext);
		textView.setText(getGroup(groupPosition).toString());
		textView.setPadding(myPaddingLeft + PaddingLeft, 0, 0, 0);
		textView.setTextColor(Color.BLACK);
		textView.setBackgroundColor(Color.parseColor("#336699"));
		return textView;
	}

	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	public Object getGroup(int groupPosition)
	{
		return treeNodes.get(groupPosition).parent;
	}

	public int getGroupCount()
	{
		return treeNodes.size();
	}

	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return true;
	}

	public boolean hasStableIds()
	{
		return true;
	}

	private SimpleAdapter getMenuAdapter1(String[] menuNameArray,
			int[] imageResourceArray)
	{
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < menuNameArray.length; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imageResourceArray[i]);
			map.put("itemText", menuNameArray[i]);
			data.add(map);
		}
		SimpleAdapter simperAdapter = new SimpleAdapter(parentContext, data,
				R.layout.item_menu, new String[] { "itemImage", "itemText" },
				new int[] { R.id.item_image, R.id.item_text });
		return simperAdapter;
	}
	
	public SimpleAdapter getMenuAdapter(List<String> menuNameArray,
			List<Integer> imageResourceArray)
	{
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		
		for (int i = 0; i < menuNameArray.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imageResourceArray.get(i));
			map.put("itemText", menuNameArray.get(i));
			data.add(map);
		}
		
		SimpleAdapter simperAdapter = new SimpleAdapter(parentContext, data,
				R.layout.item_menu, new String[] { "itemImage", "itemText" },
				new int[] { R.id.item_image, R.id.item_text });
		return simperAdapter;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		Toast.makeText(parentContext, "当前选中的是:" + position, Toast.LENGTH_SHORT)
				.show();

	}
}