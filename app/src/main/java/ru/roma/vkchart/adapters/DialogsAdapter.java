package ru.roma.vkchart.adapters;

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
import ru.roma.vkchart.MyApplication;
import ru.roma.vkchart.R;
import ru.roma.vkchart.holder.DataDialogs;
import ru.roma.vkchart.utils.MyLog;
import ru.roma.vkchart.utils.TimeHalper;
import ru.roma.vkchart.view.PositionListener;

/**
 * Created by Ilan on 26.02.2018.
 */

public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.ViewHolderDialog> {

    private List<DataDialogs> dialogses;

    private PositionListener positionListener;

    int colorLightBlue;
    private int colorWhite;

    public DialogsAdapter() {
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

        if (position ==( getItemCount()- 1 ) ){
            MyLog.log("last position in list. count list = " + getItemCount());
            positionListener.lastPosition(getItemCount());
        }

        holder.bind(dialogses.get(position));

    }


    @Override
    public int getItemCount() {
        return dialogses == null ? 0 : dialogses.size();
    }

    public void setDialogs(List<DataDialogs> list) {
        MyLog.log("new list in adapter. count = " + list.size());
        dialogses = list;
        notifyDataSetChanged();
    }

    public void setPositionListener(PositionListener positionListener) {
        this.positionListener = positionListener;
    }

    public class ViewHolderDialog extends RecyclerView.ViewHolder {

        TextView name, text, time;
        CircleImageView photoDialog, photoOwner;
        CardView cardView;
        ImageView unread;

        public ViewHolderDialog(View itemView) {
            super(itemView);
            photoDialog = itemView.findViewById(R.id.photo_dialog);
            photoOwner = itemView.findViewById(R.id.photo_owner);
            name = itemView.findViewById(R.id.name_dialog);
            text = itemView.findViewById(R.id.text_dialog);
            time = itemView.findViewById(R.id.time_dialog);
            cardView =itemView.findViewById(R.id.item_dialog_card);
            unread = itemView.findViewById(R.id.unread);

        }

        public void bind(DataDialogs dialog) {

            setName(dialog);

            setTime(dialog);

            loadPhoto(dialog);

            loadPhotoOwner(dialog);

            setText(dialog);

            checkReadState(dialog);
        }

        private void checkReadState(DataDialogs dialog) {

            if (dialog.getReadState() == 0){
//                собщение не прочитано

                if (dialog.getOut()  == 1){
//                    сообщение отправлено владельцем
                    unread.setVisibility(View.VISIBLE);
                    cardView.setCardBackgroundColor(colorWhite);
                }
                else{
                    unread.setVisibility(View.GONE);
                    cardView.setCardBackgroundColor(colorLightBlue);
                }
            }
            else {
                cardView.setCardBackgroundColor(colorWhite);
                unread.setVisibility(View.GONE);
            }
        }

        private void loadPhotoOwner(DataDialogs dialog) {
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

        private void setText(DataDialogs dialog) {

            String result = dialog.getBody();
            text.setText(result);
        }

        private void loadPhoto(DataDialogs dialog) {

            String loadPath = dialog.getPhoto100();

            if (!TextUtils.isEmpty(loadPath)) {
                Glide
                        .with(MyApplication.getInstance())
                        .load(loadPath)
                        .into(photoDialog);
            }
        }

        private void setTime(DataDialogs dialog) {

            long unixTime = dialog.getDate();

            String result = new TimeHalper().getTime(unixTime);

            time.setText(result);
        }

        private void setName(DataDialogs dialog) {

            StringBuilder sb = new StringBuilder();

            String titel = dialog.getTitle();

            if (!TextUtils.isEmpty(titel)) {
                sb.append(titel);
            } else {

                sb.append(dialog.getFirstName() + " " + dialog.getLastName());
            }

            name.setText(sb.toString());
        }
    }


}
