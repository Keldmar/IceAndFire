package yahnenko.ua.iceandfire.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import yahnenko.ua.iceandfire.R;
import yahnenko.ua.iceandfire.interfaceForclick.ItemClickCallbackHouses;
import yahnenko.ua.iceandfire.response.ByHouses;


public class RecyclerViewAdapterHouses extends RecyclerView.Adapter<ViewHolderHouses> {
    private List<ByHouses> listedsHouses;
    private final ItemClickCallbackHouses itemClickCallbackHouses;

    public RecyclerViewAdapterHouses(ItemClickCallbackHouses itemClickCallbackHouses) {
        this.itemClickCallbackHouses = itemClickCallbackHouses;
    }

    public void addView(List<ByHouses> listedsHouses) {
        this.listedsHouses = listedsHouses;
        notifyDataSetChanged();
    }



    @Override
    public ViewHolderHouses onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_name, viewGroup, false);
        return new ViewHolderHouses(v);
    }


    @Override
    public void onBindViewHolder(ViewHolderHouses viewHolder, final int i) {
        viewHolder.setView(listedsHouses.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByHouses byHouses = listedsHouses.get(i);
                itemClickCallbackHouses.onHouseClick(byHouses);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listedsHouses.size();
    }
}
