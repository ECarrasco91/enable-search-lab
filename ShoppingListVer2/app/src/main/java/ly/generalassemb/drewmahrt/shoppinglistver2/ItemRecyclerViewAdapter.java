package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by student on 10/25/16.
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    List<ItemObject> mItemObjectList;

    public ItemRecyclerViewAdapter(List<ItemObject> itemObjectList) {
        mItemObjectList = itemObjectList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout,
                parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mItem.setText(mItemObjectList.get(position).getItem());
    }

    @Override
    public int getItemCount() {
        return mItemObjectList.size();
    }

    public void replaceData(List<ItemObject> newList){
        mItemObjectList = newList;
        notifyDataSetChanged();
    }
}
