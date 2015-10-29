package org.p4p.backpocketdriver.driverlog.adapter;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Anmol on 07/07/2015.
 */
public interface Item {
    public int getViewType();
    public View getView(LayoutInflater inflater, View convertView);

}
