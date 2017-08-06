package yahnenko.ua.iceandfire.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import yahnenko.ua.iceandfire.R;
import yahnenko.ua.iceandfire.interfaceForclick.ItemClickCallbackBooks;
import yahnenko.ua.iceandfire.response.ByBooks;



public class RecyclerViewAdapterBooks extends RecyclerView.Adapter<ViewHolderBooks>{
    private List<ByBooks> listedsBooks;
    private final ItemClickCallbackBooks itemClickCallbackBooks;

    public RecyclerViewAdapterBooks(ItemClickCallbackBooks itemClickCallbackBooks) {
        this.itemClickCallbackBooks = itemClickCallbackBooks;
    }

    public void addView(List<ByBooks> listedsBooks) {
        this.listedsBooks = listedsBooks;
        notifyDataSetChanged();
    }



    @Override
    public ViewHolderBooks onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_name, viewGroup, false);
        return new ViewHolderBooks(v);
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    @Override
    public void onBindViewHolder(ViewHolderBooks viewHolder, final int i) {
        viewHolder.setView(listedsBooks.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByBooks byBooks = listedsBooks.get(i);
                itemClickCallbackBooks.onBooksClick(byBooks);
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
        return listedsBooks.size();
    }
}
