package ru.roma.vkchart.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ru.roma.vkchart.R;
import ru.roma.vkchart.ui.ui_item.MessageUIItem;
import ru.roma.vkchart.utils.MessageDateComparator;
import ru.roma.vkchart.utils.MyLog;
import ru.roma.vkchart.utils.TimeHelper;

/**
 * Created by Ilan on 01.04.2018.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolderMessage> {

    private List<MessageUIItem> messages;

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


    public MessageUIItem getMessage(int position) {
        return messages.get(position);
    }

    public void setListMessages(List<MessageUIItem> list) {
        if (list != null) {
            messages = list;
            notifyDataSetChanged();
            MyLog.log("list size adapter Messages = " + messages.size());
        }
    }

    public void addMessage(MessageUIItem message) {
        messages.add(message);
        Collections.sort(messages, new MessageDateComparator());
        notifyDataSetChanged();
        MyLog.log("add message to adapter time = " + message.getDate());
    }


    public class ViewHolderMessage extends RecyclerView.ViewHolder {

        private TextView msgLeft, msgRight, timeLeft, timeRight;
        private TimeHelper timeHelper;
        private RelativeLayout mainLeft;
        private LinearLayout mainRight;
        private ImageView indicator;
        private View itemView;


        public ViewHolderMessage(View itemView) {
            super(itemView);
            this.itemView = itemView;
            timeHelper = new TimeHelper();
            msgLeft = itemView.findViewById(R.id.msg_left);
            msgRight = itemView.findViewById(R.id.msg_right);
            timeLeft = itemView.findViewById(R.id.time_left);
            timeRight = itemView.findViewById(R.id.time_right);
            mainLeft = itemView.findViewById(R.id.main_left);
            mainRight = itemView.findViewById(R.id.main_right);
            indicator = itemView.findViewById(R.id.indicator);

        }

        public void bind(MessageUIItem message) {
            showListMessage(message);
            showIndicator(message);
        }

        private void showIndicator(MessageUIItem message) {
            if (message.getOut() == 1) {
//                исходяшие сообшение
                if (message.isError()) {
//                    ошибка при отправке сообщения
                    indicator.setVisibility(View.VISIBLE);
                    indicator.setImageResource(R.mipmap.error);
                    return;
                }
                if (!message.isSent()) {
//                    сообщение оправлено но не пришело подтверждение от сервера
                    indicator.setImageResource(R.mipmap.clock);
                    indicator.setVisibility(View.VISIBLE);
                } else {
                    if (message.getReadState() == 0) {
//                        подтвержина отправка на сервер, но сообщение не прочитано
                        MyLog.log("blue circle");
                        indicator.setImageResource(R.mipmap.circle);
                        indicator.setVisibility(View.VISIBLE);
                    } else {
//                        подтвержена отправка на сервер, сообщение  прочитано
                        indicator.setVisibility(View.GONE);
                    }
                }
            }
        }

        private void showListMessage(MessageUIItem message) {

            int out = message.getOut();
            if (out == 1) {
                mainRight.setVisibility(View.VISIBLE);
                msgRight.setText(message.getBody());
                timeRight.setText(timeHelper.getTime(message.getDate()));
                mainLeft.setVisibility(View.GONE);
            } else {
                mainLeft.setVisibility(View.VISIBLE);
                msgLeft.setText(message.getBody());
                timeLeft.setText(timeHelper.getTime(message.getDate()));
                mainRight.setVisibility(View.GONE);
            }

            if (message.isCheck()) {
                itemView.setBackgroundColor(itemView.getResources().getColor(R.color.colorLightBlue));
            } else {
                itemView.setBackgroundColor(itemView.getResources().getColor(R.color.colorGray));
            }
        }
    }
}
