package com.icmi.foodcart.Utils;


import java.io.Serializable;

public interface Listener {

    enum StatePayment implements Serializable {
        PAYED, FAILED;
    }

    interface OnResultListener<T> {
        void onResult(T result);
    }

    interface Observer<T> {
        void onChangeOccured(T changedData);
    }

    interface OnItemClickListener {
        void onClick(Object item);
    }

}
