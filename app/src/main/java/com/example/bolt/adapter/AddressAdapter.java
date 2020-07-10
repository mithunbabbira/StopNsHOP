package com.example.bolt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bolt.R;
import com.example.bolt.domain.Address;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context applicationContext;
    List<Address> mAddressList;
    private RadioButton mSelectedRadioButton;

    SelectedAddress selectedAddress;

    public AddressAdapter(Context applicationContext, List<Address> mAddressList,SelectedAddress selectedAddress) {
        this.applicationContext = applicationContext;
        this.mAddressList = mAddressList;
        this.selectedAddress = selectedAddress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(R.layout.single_address_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mAddress.setText(mAddressList.get(position).getAddress());

        holder.mRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Address address:mAddressList){
                    // first make everything false
                    address.setSelected(false);
                }
                mAddressList.get(position).setSelected(true);

                if(mSelectedRadioButton!=null){
                    mSelectedRadioButton.setChecked(false);
                }
                mSelectedRadioButton = (RadioButton) v;
                mSelectedRadioButton.setChecked(true);

                selectedAddress.setAddress(mAddressList.get(position).getAddress());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mAddress;
        private RadioButton mRadio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mAddress =itemView.findViewById(R.id.address_dis);
            mRadio = itemView.findViewById(R.id.select_address);

        }
    }

    public interface SelectedAddress{
        public void setAddress(String s);
    }
}
