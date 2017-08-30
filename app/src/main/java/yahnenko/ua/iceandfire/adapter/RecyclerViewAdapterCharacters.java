package yahnenko.ua.iceandfire.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yahnenko.ua.iceandfire.R;
import yahnenko.ua.iceandfire.interfaceForclick.ItemClickCallbackCharacters;
import yahnenko.ua.iceandfire.response.ByName;

public class RecyclerViewAdapterCharacters extends RecyclerView.Adapter<ViewHolderName> {
    private List<ByName> listedsName;
    private final ItemClickCallbackCharacters itemClackCallback;

    public RecyclerViewAdapterCharacters(ItemClickCallbackCharacters itemClackCallback) {
        this.listedsName = new ArrayList<>();
        this.itemClackCallback = itemClackCallback;
    }

    public void addView(List<ByName> listedsName) {
        int listsize = this.listedsName.size() - 1;
        for (ByName byname : listedsName) {
            this.listedsName.add(byname);
            notifyItemInserted(listsize);
        }
//        this.listedsName.addAll(listedsName);
//        notifyItemRangeInserted(listsize, this.listedsName.size() - 1);
    }

    public void addViewAfterFilters(List<ByName> listedsName) {
        this.listedsName = listedsName;
        notifyDataSetChanged();
    }

    /**
     * Создание новых View и ViewHolderForThree элемента списка, которые впоследствии могут переиспользоваться.
     */
    @Override
    public ViewHolderName onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_name, viewGroup, false);
        return new ViewHolderName(v);
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    @Override
    public void onBindViewHolder(ViewHolderName viewHolder, final int i) {
        if (listedsName.get(i).name.equals("")) {

        }
        viewHolder.setView(listedsName.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByName byName = listedsName.get(i);
                itemClackCallback.onPersonClick(byName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listedsName.size();
    }

}
