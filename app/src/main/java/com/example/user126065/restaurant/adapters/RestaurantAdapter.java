package com.example.user126065.restaurant.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user126065.restaurant.DetailActivity;
import com.example.user126065.restaurant.ItemClickListener;
import com.example.user126065.restaurant.R;
import com.example.user126065.restaurant.model.ResClass;


import java.util.ArrayList;

/**
 * Created by brad on 2017/02/11.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder> implements Filterable {

    private ArrayList<ResClass> mData;
    private ArrayList<ResClass>   mFilteredList;
    public ArrayList<ResClass> checkedPlayers = new ArrayList<>();
    private Activity mACtivity;
    private Context mContext;

    public RestaurantAdapter(ArrayList<ResClass> data, Activity activity) {
        this.mData = data;
        this.mACtivity = activity;
         this.mFilteredList = data;


    }

    @Override
    public RestaurantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurant, parent, false);
        return new RestaurantHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantHolder holder, int position) {
        ResClass restaurant = mData.get(position);

        holder.setName(restaurant.getName());
        holder.setAddress(restaurant.getAddress());
        holder.setCost("Average cost for 2: " + restaurant.getCurrency() + restaurant.getCost());
        //holder.setRating(restaurant.getRating());
        holder.setItemClickListener(new ItemClickListener() {
                                        @Override
                                        public void onItemClick(View v, int pos) {
                                            CheckBox chk = (CheckBox) v;

                                            //CKE IF ITS CHECKED OR NOT
                                            if (chk.isChecked()) {
                                                checkedPlayers.add(mData.get(pos));
                                            } else if (!chk.isChecked()) {
                                                checkedPlayers.remove(mData.get(pos));
                                            }
                                        }


                                    });
        Glide.with(mACtivity)
                .load(restaurant.getImageUrl())
                .into(holder.restaurantImageView);


    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                     mData = mFilteredList;
                } else {

                    ArrayList<ResClass> filteredList = new ArrayList<>();

                    for (ResClass androidVersion : mData) {

                        if ( androidVersion.getName().toLowerCase().contains(charString) ) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mData = (ArrayList<ResClass>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class RestaurantHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView restaurantImageView;
        TextView restaurantNameTextView;
        TextView restaurantAddressTextView;
        TextView restaurantRatingTextView;
        TextView costTextView;
        TextView distanceTextView;
        CheckBox chk;

        ItemClickListener itemClickListener;


        public RestaurantHolder(final View itemView) {
            super(itemView);

            restaurantImageView = (ImageView) itemView.findViewById(R.id.imageview_restaurant);
            restaurantNameTextView = (TextView) itemView.findViewById(R.id.textview_restaurant_name);
            restaurantAddressTextView = (TextView) itemView.findViewById(R.id.restaurant_address_textview);
            restaurantRatingTextView = (TextView) itemView.findViewById(R.id.rating);
            costTextView = (TextView) itemView.findViewById(R.id.cost_for_two_textview);
            distanceTextView = (TextView) itemView.findViewById(R.id.restaurant_distance_textview);
            chk= (CheckBox) itemView.findViewById(R.id.chk);

            chk.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    ResClass clickedDataItem  = mData.get(pos);


                    Intent i = new Intent(v.getContext(), DetailActivity.class);

                    //pass the position of the item to single class
                    i.putExtra("name", mData.get(pos).getName());
                    i.putExtra("numOfSongs", mData.get(pos).getCost());
                    i.putExtra("thumbnail", mData.get(pos).getImageUrl());
                    i.putExtra("POSITION", pos); //you can put your position to next activity.
                    v.getContext().startActivity(i);
                    Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v){
//                    int pos = getAdapterPosition();
//
//                    if (pos != RecyclerView.NO_POSITION){
//                        Restaurant clickedDataItem  = mData.get(pos);
//                        //Intent intent = new Intent(this,DetailActivity.class);
//                         //Intent intent = new Intent(mACtivity, DetailActivity.class);
//                        //intent.putExtra("name", mData.get(pos).getName());
//                        //intent.putExtra("numOfSongs", mData.get(pos).getNumOfSongs());
//                        //intent.putExtra("thumbnail", mData.get(pos).getThumbnail());
//                        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        //mACtivity.startActivity(intent);
//                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            });




        }

        public void setName(String name) {
            restaurantNameTextView.setText(name);
        }

        public void setAddress(String address) {
            restaurantAddressTextView.setText(address);
        }

        public void setRating(String rating) {
            restaurantRatingTextView.setText(rating);
        }

        public void setCost(String cost) {
            costTextView.setText(cost);
        }

       public void setDistance(String distance) {
           distanceTextView.setText(distance);
       }


        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }

}
