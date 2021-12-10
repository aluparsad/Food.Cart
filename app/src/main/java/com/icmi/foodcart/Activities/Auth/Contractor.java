package com.icmi.foodcart.Activities.Auth;

import android.app.Activity;

public interface Contractor {
    interface View {

        void onOtpSent();

        void onSuccess();

        void onError(Exception e);
    }

    interface Presenter {

        void loginWithPhoneNumber(Activity activity, String number);

        void VerifyOTP(String otpCode);

    }
}
