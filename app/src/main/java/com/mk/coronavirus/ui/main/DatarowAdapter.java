package com.mk.coronavirus.ui.main;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mk.coronavirus.R;

import java.util.List;

public class DatarowAdapter extends RecyclerView.Adapter<DatarowAdapter.ViewHolder> {

    private List<Datarow> datarowList;
    private final OnItemClickListener clickListener;

    public DatarowAdapter(Context context, List<Datarow> datarowList) {
        this.datarowList = datarowList;
        this.clickListener = (OnItemClickListener) context;
    }

    public void clear() {
        this.datarowList.clear();
        notifyDataSetChanged();
    }

    public void update(List<Datarow> newDataRows) {
        if (this.datarowList.isEmpty()) {
            this.datarowList.addAll(newDataRows);
            notifyItemRangeInserted(0, newDataRows.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return datarowList.size();
                }

                @Override
                public int getNewListSize() {
                    return newDataRows.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return datarowList.get(oldItemPosition) == newDataRows.get(
                            newItemPosition);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Datarow newData = newDataRows.get(newItemPosition);
                    Datarow oldData = datarowList.get(oldItemPosition);

                    return newData.getName().equals(oldData.getName()) && newData.getCaseCount() == oldData.getCaseCount() && newData.getDeathCount() == oldData.getDeathCount();
                }
            });
            datarowList = newDataRows;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datarow datarow = datarowList.get(position);
        if (datarow == null) return;
        holder.tvName.setText(datarow.getName());
        holder.tvCaseCount.setText(String.valueOf(datarow.getCaseCount()));
        holder.tvDeathCount.setText(String.valueOf(datarow.getDeathCount()));
        holder.ivFlag.setImageResource(datarow.getFlag());
    }

    @Override
    public int getItemCount() {
        return datarowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivFlag;
        TextView tvDeathCount;
        TextView tvCaseCount;
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivFlag = itemView.findViewById(R.id.imgFlag);
            tvDeathCount = itemView.findViewById(R.id.tvDeathCount);
            tvCaseCount = itemView.findViewById(R.id.tvCaseCount);
            tvName = itemView.findViewById(R.id.name);
        }

        @Override
        public void onClick(View v) {
            Datarow dr = datarowList.get(getAdapterPosition());
            clickListener.onItemClick(dr.getName(), dr.getFlag(), ivFlag);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String country, int flag, ImageView ivFlag);
    }
}
