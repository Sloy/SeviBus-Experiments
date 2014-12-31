package com.sloydev.sevibus.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sloydev.sevibus.R;
import com.sloydev.sevibus.domain.BusStop;
import com.sloydev.sevibus.domain.repository.BusStopRepository;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;


public class MainActivity extends BaseActivity {

    @InjectView(R.id.list) ListView listView;

    @Inject @Deprecated BusStopRepository busStopRepository;
    private BusStopSimpleAdapter listAdapter;

    @Override protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override protected void initializeViews() {
        ButterKnife.inject(this);
    }

    @Override protected void created() {
        showAllStops();
    }

    @Deprecated //TODO just for testing, this will be removed
    private void showAllStops() {
        List<BusStop> allBusStops = busStopRepository.getAllBusStops();
        listAdapter = new BusStopSimpleAdapter(allBusStops);
        listView.setAdapter(listAdapter);
    }

    @OnItemClick(R.id.list)
    public void openStop(int position) {
        BusStop busStop = listAdapter.getItem(position);
        Integer busStopNumber = busStop.getNumber();
        Intent launchIntent = new Intent(this, BusStopDetailActivity.class);
        launchIntent.putExtra(BusStopDetailActivity.EXTRA_STOP_NUMBER, busStopNumber);
        startActivity(launchIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private class BusStopSimpleAdapter extends BaseAdapter {
        private List<BusStop> allBusStops;

        public BusStopSimpleAdapter(List<BusStop> allBusStops) {
            this.allBusStops = allBusStops;
        }

        @Override public int getCount() {
            return allBusStops.size();
        }

        @Override public BusStop getItem(int position) {
            return allBusStops.get(position);
        }

        @Override public long getItemId(int position) {
            return position;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_2, parent, false);
            TextView text = (TextView) view.findViewById(android.R.id.text1);
            text.setText(String.valueOf(getItem(position).getNumber()));
            TextView text2 = (TextView) view.findViewById(android.R.id.text2);
            text2.setText(getItem(position).getName());
            return view;
        }
    }
}
