package yahnenko.ua.iceandfire.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import yahnenko.ua.iceandfire.R;
import yahnenko.ua.iceandfire.response.ByBooks;


public class ViewHolderBooks extends RecyclerView.ViewHolder{

    private TextView textName;

    public ViewHolderBooks(View itemView) {
        super(itemView);
        textName = (TextView)itemView.findViewById(R.id.text_name);
    }

    public void setView(ByBooks listed) {
        textName.setText(listed.name);
    }
}
