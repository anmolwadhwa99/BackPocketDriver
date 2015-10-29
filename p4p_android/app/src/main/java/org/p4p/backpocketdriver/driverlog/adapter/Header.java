package org.p4p.backpocketdriver.driverlog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.p4p.backpocketdriver.driverlog.R;

/**
 * Created by user on 07/07/2015.
 */
public class Header implements Item {


    private final String name;

    public Header(String name) {
        this.name = name;
    }

    @Override
    public int getViewType() {
        return RowAdapter.RowType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.header, null);
            // Do some initialization
        } else {
            view = convertView;
        }

        TextView text = (TextView) view.findViewById(R.id.separator);
        text.setText(name);

        return view;
    }

}


