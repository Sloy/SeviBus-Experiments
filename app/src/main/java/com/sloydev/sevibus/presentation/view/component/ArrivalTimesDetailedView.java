package com.sloydev.sevibus.presentation.view.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sloydev.sevibus.R;
import com.sloydev.sevibus.presentation.model.ArrivalTimesModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ArrivalTimesDetailedView extends LinearLayout {
    private LayoutInflater layoutInflater;

    public ArrivalTimesDetailedView(Context context) {
        super(context);
        init(context);
    }

    public ArrivalTimesDetailedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ArrivalTimesDetailedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ArrivalTimesDetailedView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        layoutInflater = LayoutInflater.from(context);

        if (isInEditMode()) {
            displayMockData();
        }
    }

    public void addArrival(ArrivalTimesModel arrival) {
        //TODO actualizar en vez de añadir siempre
        ArrivalDetailViewHolder newItemHolder = createViewHolder();
        bindData(newItemHolder, arrival);
        View itemView = newItemHolder.itemView;
        this.addView(itemView);
    }

    private ArrivalDetailViewHolder createViewHolder() {
        View newView = layoutInflater.inflate(R.layout.list_item_arrival_detail, this, false);
        return new ArrivalDetailViewHolder(newView);
    }

    private void bindData(ArrivalDetailViewHolder holder, ArrivalTimesModel arrival) {
        holder.lineBadge.setText(arrival.getLineName());
        holder.nextTime.setText(arrival.getNextBusTime());
        String secondBusTime = arrival.getSecondBusTime();
        if (secondBusTime != null && !secondBusTime.isEmpty()) {
            holder.secondTime.setText(secondBusTime);
            holder.secondTime.setVisibility(VISIBLE);
        } else {
            holder.secondTime.setVisibility(INVISIBLE);
        }
        setNextTimeStyle(holder);
    }

    private void setNextTimeStyle(ArrivalDetailViewHolder holder) {
        String currentText = holder.nextTime.getText().toString();
        Pattern numberPattern = Pattern.compile("(\\d+?)");
        Matcher matcher = numberPattern.matcher(currentText);
        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            int bigTextSize = getResources().getDimensionPixelSize(R.dimen.arrival_detail_big_number);
            CharacterStyle bigTextSpan = new AbsoluteSizeSpan(bigTextSize);

            SpannableStringBuilder spanText = new SpannableStringBuilder(currentText);
            spanText.setSpan(bigTextSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.nextTime.setText(spanText);
        }
    }


    private void displayMockData() {
        ArrivalTimesModel arrivalTimesModel = new ArrivalTimesModel();
        arrivalTimesModel.setLineName("01");
        arrivalTimesModel.setNextBusTime("Próximo 9 min");
        arrivalTimesModel.setSecondBusTime("Siguiente 24 min");

        addArrival(arrivalTimesModel);
        addArrival(arrivalTimesModel);

        ArrivalTimesModel arrivalNotAvailable = new ArrivalTimesModel();
        arrivalNotAvailable.setLineName("A1");
        arrivalNotAvailable.setNextBusTime("No disponible");
        addArrival(arrivalNotAvailable);

        addArrival(arrivalTimesModel);

        ArrivalTimesModel imminent = new ArrivalTimesModel();
        imminent.setLineName("C2");
        imminent.setNextBusTime("Llegada inminente");
        imminent.setSecondBusTime("Siguiente 5 minutos");

        addArrival(imminent);

        addArrival(arrivalTimesModel);
    }

    class ArrivalDetailViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.arrival_detail_line_badge) TextView lineBadge;
        @InjectView(R.id.arrival_detail_next_time) TextView nextTime;
        @InjectView(R.id.arrival_detail_second_time) TextView secondTime;

        public ArrivalDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
