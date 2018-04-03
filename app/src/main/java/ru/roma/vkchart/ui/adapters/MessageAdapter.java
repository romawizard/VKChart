package ru.roma.vkchart.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.roma.vkchart.R;
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

        TextView msgLeft, msgRight, timeLeft, timeRight;
        TimeHalper timeHelper;


        public ViewHolderMessage(View itemView) {
            super(itemView);
            timeHelper = new TimeHalper();
            msgLeft = itemView.findViewById(R.id.msg_left);
            msgRight = itemView.findViewById(R.id.msg_right);
            timeLeft = itemView.findViewById(R.id.time_left);
            timeRight = itemView.findViewById(R.id.time_right);
        }

        public void bind(Message message) {
            showListMessage(message);
            showTime(message);
        }

        private void showTime(Message message) {

            int out = message.getOut();
            if (out == 1){
                timeRight.setVisibility(View.VISIBLE);
                timeRight.setText(timeHelper.getTime(message.getDate()));
                timeLeft.setVisibility(View.GONE);
            }
            else {
                timeLeft.setVisibility(View.VISIBLE);
                timeLeft.setText(timeHelper.getTime(message.getDate()));
                timeRight.setVisibility(View.GONE);
            }
        }

        private void showListMessage(Message message) {

            int out = message.getOut();
            if (out == 1){
                msgRight.setVisibility(View.VISIBLE);
                msgRight.setText(message.getBody());
                msgLeft.setVisibility(View.GONE);
            }else {
                msgLeft.setVisibility(View.VISIBLE);
                msgLeft.setText(message.getBody());
                msgRight.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View view) {

        }
    }
}
