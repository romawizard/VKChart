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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ru.roma.vkchart.R;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.utils.MyLog;
import ru.roma.vkchart.utils.TimeHelper;


/**
 * Created by Ilan on 26.02.2018.
 */

public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.ViewHolderDialog> {

    private int colorWhite;
    private int colorLightBlue;
    private List<Dialog> dialogs;
    private OnItemClickListener listener;

    public DialogsAdapter() {
        dialogs = new ArrayList<>();
    }

    @Override
    public ViewHolderDialog onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_dialog, parent, false);
        colorLightBlue = parent.getResources().getColor(R.color.colorLightBlue);
        colorWhite = parent.getResources().getColor(R.color.colorWhite);
        return new ViewHolderDialog(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDialog holder, int position) {
        holder.bind(dialogs.get(position));
    }

    @Override
    public int getItemCount() {
        return dialogs == null ? 0 : dialogs.size();
    }

    public void setListDialogs(List<Dialog> list) {
        dialogs = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public List<Dialog> getData() {
        return dialogs;


    }

    public interface OnItemClickListener {
        void onItemClick(Dialog dialog);
    }


    public class ViewHolderDialog extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, text, time;
        CircleImageView photoDialog, photoOwner, imageOnline;
        CardView cardView;
        ImageView unread;

        public ViewHolderDialog(View itemView) {
            super(itemView);
            photoDialog = itemView.findViewById(R.id.photo_dialog);
            photoOwner = itemView.findViewById(R.id.photo_owner);
            name = itemView.findViewById(R.id.name_dialog);
            text = itemView.findViewById(R.id.text_dialog);
            time = itemView.findViewById(R.id.time_dialog);
            cardView = itemView.findViewById(R.id.item_dialog_card);
            unread = itemView.findViewById(R.id.unread);
            imageOnline = itemView.findViewById(R.id.online);

            itemView.setOnClickListener(this);
        }

        public void bind(Dialog dialog) {

            setName(dialog);

            setTime(dialog);

            loadPhoto(dialog);

            loadPhotoOwner(dialog);

            setText(dialog);

            checkReadState(dialog);

            setOnlineImage(dialog);
        }

        private void checkReadState(Dialog dialog) {

            if (dialog.getReadState() == 0) {
//                собщение не прочитано

                if (dialog.getOut() == 1) {
//                    сообщение отправлено владельцем
                    unread.setVisibility(View.VISIBLE);
                    cardView.setCardBackgroundColor(colorWhite);
                } else {
                    unread.setVisibility(View.GONE);
                    cardView.setCardBackgroundColor(colorLightBlue);
                }
            } else {
                cardView.setCardBackgroundColor(colorWhite);
                unread.setVisibility(View.GONE);
            }
        }

        private void loadPhotoOwner(Dialog dialog) {
            int outState = dialog.getOut();
            if (outState == 1) {
                String pathOwner = dialog.getPhotoOwner();
                Glide
                        .with(MyApplication.getInstance())
                        .load(pathOwner)
                        .into(photoOwner);
                photoOwner.setVisibility(View.VISIBLE);
            } else photoOwner.setVisibility(View.GONE);
        }

        private void setText(Dialog dialog) {

            String result = dialog.getBody();
            text.setText(result);
        }

        private void loadPhoto(Dialog dialog) {

            String loadPath = dialog.getPhoto100();

            if (!TextUtils.isEmpty(loadPath)) {
                Glide
                        .with(MyApplication.getInstance())
                        .load(loadPath)
                        .into(photoDialog);
            }
        }

        private void setTime(Dialog dialog) {

            long unixTime = dialog.getDate();

            String result = new TimeHelper().getTime(unixTime);

            time.setText(result);
        }

        private void setName(Dialog dialog) {

            StringBuilder sb = new StringBuilder();

            String title = dialog.getTitle();

            if (!TextUtils.isEmpty(title)) {
                sb.append(title);
            } else {

                sb.append(dialog.getFirstName() + " " + dialog.getLastName());
            }

            name.setText(sb.toString());
        }

        private void setOnlineImage(Dialog dialog){
            int online = dialog.getOnline();
            if (online == 1){
                imageOnline.setVisibility(View.VISIBLE);
            }else {
                imageOnline.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View view) {
            MyLog.log("onClick DialogAdapter");
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (listener != null) {
                    listener.onItemClick(dialogs.get(position));
                }
            }
        }
    }

}
