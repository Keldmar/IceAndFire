package yahnenko.ua.iceandfire.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import yahnenko.ua.iceandfire.R;
import yahnenko.ua.iceandfire.response.ByBooks;
import yahnenko.ua.iceandfire.response.ByHouses;


public class ViewHolderHouses extends RecyclerView.ViewHolder {

    private TextView textName;

    public ViewHolderHouses(View itemView) {
        super(itemView);
        textName = (TextView)itemView.findViewById(R.id.text_name);
    }

    public void setView(ByHouses listed) {
        textName.setText(listed.name);
    }
}
