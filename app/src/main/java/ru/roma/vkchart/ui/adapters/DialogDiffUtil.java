    package ru.roma.vkchart.ui.adapters;

    import android.support.v7.util.DiffUtil;

    import java.util.List;

    import ru.roma.vkchart.domain.entities.Dialog;

    public class DialogDiffUtil extends DiffUtil.Callback {

        private final List<Dialog> oldList;
        private final List<Dialog> newList;

        public DialogDiffUtil(List<Dialog> oldList, List<Dialog> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            Dialog oldDialog = oldList.get(oldItemPosition);
            Dialog newDialog = newList.get(newItemPosition);
            return oldDialog.getId().equals(newDialog.getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Dialog oldDialog = oldList.get(oldItemPosition);
            Dialog newDialog = newList.get(newItemPosition);
            return oldDialog.equals(newDialog);
        }
    }
