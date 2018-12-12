package com.spider.star.nexbase.servicerequest.adaptor;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.spider.star.nexbase.R;
import com.spider.star.nexbase.databinding.ServicerequestAdaptorBinding;
import com.spider.star.nexbase.servicerequest.modal.NotificationList;
import com.spider.star.nexbase.servicerequest.modal.Status;

import com.spider.star.nexbase.utils.MyProgressDialog;


import java.util.List;
import java.util.Observer;

import java.util.Observable;

public class ServiceRequestAdaptor extends RecyclerView.Adapter<ServiceRequestAdaptor.ViewHolder> implements Observer {

    Activity activity;
    MyProgressDialog myProgressDialog;
    List<NotificationList>lists;

    public ServiceRequestAdaptor(Activity activity, List<NotificationList> lists) {

        this.activity=activity;
        this.lists=lists;
        myProgressDialog=new MyProgressDialog();

    }


    @Override
    public ServiceRequestAdaptor.ViewHolder onCreateViewHolder( ViewGroup parent, int i) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ServicerequestAdaptorBinding binding = DataBindingUtil.inflate(inflater, R.layout.servicerequest_adaptor, parent, false);
        return new ServiceRequestAdaptor.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ServiceRequestAdaptor.ViewHolder holder, int position) {

        holder.statuschanges(lists.get(position));
        setUpObservar(holder.statusItemChanges);
        NotificationList list=lists.get(position);

        holder.binding.text.setText(list.getMessage());

    }

    private void setUpObservar(Observable observable) {

        observable.addObserver(this);

    }

    @Override
    public int getItemCount() {

        return lists.size();
    }

    @Override
    public void update(Observable observable, Object object) {

        if(observable instanceof StatusItemChanges){

            NotificationList items= (NotificationList) object;
            lists.remove(items);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ServicerequestAdaptorBinding binding;
        StatusItemChanges statusItemChanges;

        public ViewHolder(ServicerequestAdaptorBinding  binding) {
            super(binding.getRoot());
            this.binding=binding;

        }

        public void statuschanges(NotificationList notificationList) {

            statusItemChanges=new StatusItemChanges(activity,notificationList);
            binding.setStatusItemchanges(statusItemChanges);
        }
    }
}
