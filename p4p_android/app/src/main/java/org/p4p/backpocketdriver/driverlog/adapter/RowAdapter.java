package org.p4p.backpocketdriver.driverlog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Anmol
 * on 07/07/2015.
 */
public class RowAdapter extends ArrayAdapter<Item> {

    private LayoutInflater mInflater;

    public RowAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItem(position).getView(mInflater, convertView);
    }

    public enum RowType {
        LIST_ITEM, HEADER_ITEM
    }


    @Override
    public int getViewTypeCount() {
        return RowType.values().length;

    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }
}



