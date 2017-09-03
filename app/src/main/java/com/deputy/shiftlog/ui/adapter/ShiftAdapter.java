package com.deputy.shiftlog.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.deputy.shiftlog.R;
import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.Holder> {

    public interface OnItemClickListener {
        void onShiftItemClicked(Shift shift);
        void onOpenMapRequested(Shift shift);
    }

    private ArrayList<Shift> shiftsCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    @Inject
    ShiftAdapter(Context context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.shiftsCollection = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return (this.shiftsCollection != null) ? this.shiftsCollection.size() : 0;
    }

    public ArrayList<Shift> getEntries() {
        return shiftsCollection;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.recycler_shift_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final Shift shift = this.shiftsCollection.get(position);

        if(shift.getImage() != null){
            holder.photo.setImageBitmap(shift.getImage());
        }else{
            holder.progressBar.setVisibility(View.VISIBLE);
            Glide.with(holder.photo.getContext())
                    .load(shift.getImageUrl())
                    .asBitmap()
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            holder.photo.setImageResource(R.drawable.no_image);
                            holder.progressBar.setVisibility(View.GONE);
                            return true;
                        }
                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            shift.setImage(resource);
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .fitCenter()
                    .override(48, 48)
                    .into(holder.photo);
        }

        holder.startTime.setText(shift.getStartTime());
        holder.endTime.setText(shift.getEndTime());

        holder.itemView.setOnClickListener(v -> {
            if (ShiftAdapter.this.onItemClickListener != null) {
                ShiftAdapter.this.onItemClickListener.onShiftItemClicked(shift);
            }
        });

        holder.ibOpenMap.setOnClickListener(view -> {
            if (ShiftAdapter.this.onItemClickListener != null) {
                ShiftAdapter.this.onItemClickListener.onOpenMapRequested(shift);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setShiftsCollection(ArrayList<Shift> shiftsCollection) {
        this.validateShiftCollection(shiftsCollection);
        this.shiftsCollection = shiftsCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateShiftCollection(Collection<Shift> shiftCollection) {
        if (shiftCollection == null) {
            throw new IllegalArgumentException("The list of shifts cannot be of a null value!");
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_shift_item_photo) ImageView photo;
        @BindView(R.id.recycler_shift_item_start_time) TextView startTime;
        @BindView(R.id.recycler_shift_item_end_time) TextView endTime;
        @BindView(R.id.imagebutton_open_map) ImageButton ibOpenMap;
        @BindView(R.id.progress_bar) ProgressBar progressBar;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
