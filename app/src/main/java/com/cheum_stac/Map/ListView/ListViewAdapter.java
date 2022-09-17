package com.cheum_stac.Map.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.cheum_stac.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements Filterable {
    public ListViewAdapter() {}

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>(); //원본 데이터 리스트
    private ArrayList<ListViewItem> filteredItemList = listViewItemList; //필터링 데이터 리스트
    Filter listFilter ;

    @Override
    public int getCount() {
        return filteredItemList.size() ;
    }
    @Override
    public long getItemId(int position) {
        return position ;
    }
    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position) ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // listview_item.xml ==inflate==> convertView 참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // 화면에 표시될 View으로부터 위젯에 대한 참조 획득
        TextView sNameText = (TextView) convertView.findViewById(R.id.text_store_name);
        TextView sLoctText = (TextView) convertView.findViewById(R.id.text_store_location);

        // Data Set(filteredItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = filteredItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        sNameText.setText(listViewItem.getTitle());
        sLoctText.setText(listViewItem.getDesc());

        return convertView;
    }

    // 데이터 추가
    public void addItem(String name, String location, int id) {
        ListViewItem item = new ListViewItem();

        item.setTitle(name);
        item.setDesc(location);
        item.setId(id);

        listViewItemList.add(item);
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }
        return listFilter ;
    }

    private class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = listViewItemList ;
                results.count = listViewItemList.size() ;
            } else {
                ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>() ;

                for (ListViewItem item : listViewItemList) {
                    if (item.getTitle().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            item.getDesc().toUpperCase().contains(constraint.toString().toUpperCase()))
                    {
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filteredItemList = (ArrayList<ListViewItem>) results.values ;

            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }
}