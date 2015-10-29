package org.p4p.backpocketdriver.driverlog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.models.Journey;

/**
 * Created by user on 07/07/2015.
 */
public class ListItem implements Item {
    private Journey journey;
    RowAdapter.RowType type;

    public ListItem(Journey journey) {
        this.journey = journey;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    @Override
    public int getViewType() {
        return type.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.my_list_item, null);
            // Do some initialization
        } else {
            view = convertView;
        }

        TextView textView = (TextView) view.findViewById(R.id.list_content1);
        textView.setText(journey.toString());
        return view;
    }



}
