package com.vfs.rxjavamaster;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Integer> list;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = findViewById(R.id.txtView);
        list = new ArrayList<>();
        //
        rxJavaJust();

        rxJavaFromArray();

        rxRange();

    }

    private void setData() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer i : list) {
            stringBuffer.append(i+" ");
        }
        txtView.setText(stringBuffer);
    }

    private void rxRange() {
        Observable.range(1, 10)
                .buffer(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onCompleted() {
                        setData();
                        Log.d(TAG, "onCompleted: called 1:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onCompleted: called 2:");
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, "onCompleted: called 3:" + integers);
                        list.addAll(integers);
                    }
                });
    }

    private void rxJavaFromArray() {
        Integer[] numbers = {1, 2, 3, 4, 5};

        Observable.from(numbers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: rxfrom 1:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onCompleted: rxfrom 2:");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onCompleted: rxfrom 3:" + integer);
                    }
                });
    }

    private void rxJavaJust() {
        Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: rxjava just1:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onCompleted: rxjava just2:");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onCompleted: rxjava just3:" + integer);
                    }
                });

    }
}
