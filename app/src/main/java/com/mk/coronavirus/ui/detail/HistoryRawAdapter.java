package com.mk.coronavirus.ui.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mk.coronavirus.R;
import com.mk.coronavirus.db.model.CountryHistoricalData;

import java.util.List;

class HistoryRawAdapter extends RecyclerView.Adapter<HistoryRawAdapter.ViewHolder> {

    private List<CountryHistoricalData> historyList;

    public HistoryRawAdapter(Context context, List<CountryHistoricalData> historyList) {
        this.historyList = historyList;
    }

    public void clear() {
        this.historyList.clear();
        notifyDataSetChanged();
    }

    public void update(List<CountryHistoricalData> newDataList) {
        if (this.historyList.isEmpty()) {
            this.historyList.addAll(newDataList);
            notifyItemRangeInserted(0, newDataList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return historyList.size();
                }

                @Override
                public int getNewListSize() {
                    return newDataList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return historyList.get(oldItemPosition) == newDataList.get(
                            newItemPosition);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    CountryHistoricalData newData = newDataList.get(newItemPosition);
                    CountryHistoricalData oldData = historyList.get(oldItemPosition);

                    return newData.getDate().equals(oldData.getDate())
                            && newData.getNewCases() == oldData.getNewCases()
                            && newData.getTotalCases() == oldData.getTotalCases()
                            && newData.getNewDeaths() == oldData.getNewDeaths()
                            && newData.getTotalDeaths() == oldData.getTotalDeaths();
                }
            });
            historyList = newDataList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public HistoryRawAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_card, parent, false);
        HistoryRawAdapter.ViewHolder viewHolder = new HistoryRawAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRawAdapter.ViewHolder holder, int position) {
        CountryHistoricalData datarow = historyList.get(position);
        if (datarow == null) return;
        holder.tvDate.setText(datarow.getDate());
        holder.tvTotalDeathCount.setText(String.valueOf(datarow.getTotalDeaths()));
        int activeCount = datarow.getTotalCases() - datarow.getTotalRecovered() - datarow.getNewDeaths();
        holder.tvActiveCaseCount.setText(String.valueOf(activeCount));
        holder.tvNewDeathCount.setText(String.valueOf(datarow.getNewDeaths()));
        holder.tvNewCaseCount.setText(String.valueOf(datarow.getNewCases()));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvTotalDeathCount;
        TextView tvActiveCaseCount;
        TextView tvNewDeathCount;
        TextView tvNewCaseCount;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTotalDeathCount = itemView.findViewById(R.id.tvTotalDeaths);
            tvActiveCaseCount = itemView.findViewById(R.id.tvTotalCases);
            tvNewDeathCount = itemView.findViewById(R.id.tvNewDeaths);
            tvNewCaseCount = itemView.findViewById(R.id.tvNewCases);
        }
    }
}
