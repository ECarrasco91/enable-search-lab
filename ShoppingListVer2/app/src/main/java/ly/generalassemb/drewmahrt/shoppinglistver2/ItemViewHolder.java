package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by student on 10/25/16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView mItem;

    public ItemViewHolder(View itemView) {
        super(itemView);

        mItem = (TextView) itemView.findViewById(R.id.list_item);
    }
}
