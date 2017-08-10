package yahnenko.ua.iceandfire.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import yahnenko.ua.iceandfire.interfaceForclick.ItemClickCallbackCharacters;
import yahnenko.ua.iceandfire.R;
import yahnenko.ua.iceandfire.response.ByName;

public class RecyclerViewAdapterCharacters extends RecyclerView.Adapter<ViewHolderName> {
    private List<ByName> listedsName;
    private final ItemClickCallbackCharacters itemClackCallback;

    public RecyclerViewAdapterCharacters(ItemClickCallbackCharacters itemClackCallback) {

        this.itemClackCallback = itemClackCallback;
    }

    public void addView(List<ByName> listedsName) {
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

//    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//        }
//
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//            int visibleItemCount = layoutManager.getChildCount();
//            int totalItemCount = layoutManager.getItemCount();
//            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//
//            if (!isLoading && !isLastPage) {
//                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
//                        && firstVisibleItemPosition >= 0
//                        && totalItemCount >= PAGE_SIZE) {
//                    loadMoreItems();
//                }
//            }
//        }
//    };

    @Override
    public int getItemCount() {
        return listedsName.size();
    }
    
}
