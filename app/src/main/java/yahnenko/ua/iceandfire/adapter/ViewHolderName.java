package yahnenko.ua.iceandfire.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import yahnenko.ua.iceandfire.R;
import yahnenko.ua.iceandfire.response.ByName;

/**
 * Created by Keldmar on 28.07.2017.
 */

public class ViewHolderName extends RecyclerView.ViewHolder {

    private TextView textName;

    public ViewHolderName(View itemView) {
        super(itemView);
        textName = (TextView)itemView.findViewById(R.id.text_name);
    }

    public void setView(ByName listed) {
        textName.setText(listed.name);
    }
}
