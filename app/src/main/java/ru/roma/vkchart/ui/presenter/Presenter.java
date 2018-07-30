package ru.roma.vkchart.ui.presenter;

public abstract class Presenter<T extends Presenter.View> {

    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void initialize() {

    }

    public abstract void detach();

    public interface View {

        void showLoading();

        void hideLoading();

        void showError(String messageError);

        void update();
    }
}
