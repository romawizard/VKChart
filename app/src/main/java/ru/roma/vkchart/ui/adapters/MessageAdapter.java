package ru.roma.vkchart.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ru.roma.vkchart.R;
import ru.roma.vkchart.app.MyApplication;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.utils.MyLog;
import ru.roma.vkchart.utils.TimeHalper;

/**
 * Created by Ilan on 01.04.2018.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolderMessage> {

    List<Message> messages;

    @Override
    public ViewHolderMessage onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new ViewHolderMessage(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMessage holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }
    public void setListMesages(List<Message> list) {
        messages = list;
        notifyDataSetChanged();
        MyLog.log("list size adapter Messages = " + messages.size());
    }

    public class ViewHolderMessage extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView msgLeft, msgRight;


        public ViewHolderMessage(View itemView) {
            super(itemView);
            msgLeft = itemView.findViewById(R.id.msg_left);
            msgRight = itemView.findViewById(R.id.msg_right);
        }

        public void bind(Message message) {
            msgLeft.setText(message.getBody());
        }

        @Override
        public void onClick(View view) {

        }
    }
}
