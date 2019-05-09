package dev.jorik.counters.activities.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import dev.jorik.counters.R;
import dev.jorik.counters.entities.SimpleCounter;

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.CounterHolder> {
    private List<SimpleCounter> data;
    private Callback callback;

    public CounterAdapter(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public CounterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item__simple_counter, viewGroup, false);
        return new CounterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterHolder holder, int i) {
        SimpleCounter simpleCounter = data.get(i);
        holder.name.setText(simpleCounter.getName());
        holder.count.setText(String.valueOf(simpleCounter.getCount()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<SimpleCounter> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void addItem(int position){
        notifyItemInserted(position);
    }

    public void updateItem(int position){
        notifyItemChanged(position);
    }

    public void removeItem(int position){
        notifyItemRemoved(position);
    }

    public interface Callback{
        void onClick(int position);
        boolean onHold(int position);
        void plusClick(int position);
        void minusClick(int position);
    }

    public class CounterHolder extends RecyclerView.ViewHolder{
        Button minus, plus;
        TextView name, count;

        public CounterHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return callback.onHold(getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getAdapterPosition());
                }
            });
            plus = itemView.findViewById(R.id.btn_counterLI_plus);
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.plusClick(getAdapterPosition());
                }
            });
            minus = itemView.findViewById(R.id.btn_counterLI_minus);
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.minusClick(getAdapterPosition());
                }
            });
            name = itemView.findViewById(R.id.tv_counterLI_name);
            count = itemView.findViewById(R.id.tv_counterLI_count);
        }
    }
}
