package johnschroeders.marketfree;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//TODO manage rotation for the recylcer view (save data to a bundle for screen rotation and change
// layout for screen roation so card does not cause problems)
public class MyRecylcerViewAdapterForOrdersStatus extends RecyclerView.Adapter<MyRecylcerViewAdapterForOrdersStatus.ViewHolder> {

    private ArrayList<Order> passedInArrayList;
    private LayoutInflater mInflater;

    private static final String TAG = "OrderStatusActivity";

    // data is passed into the constructor
    MyRecylcerViewAdapterForOrdersStatus(Context context, ArrayList<Order> data) {
        this.mInflater = LayoutInflater.from(context);
        this.passedInArrayList = data;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "on create view holder recyclerview class");


        View view = mInflater.inflate(R.layout.recycler_view_item_2, parent, false);
        return new ViewHolder(view);
    }


    // binds the data to the TextView in each row for each member in the passedInArraylist member
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "setting text values in adapterview for orders");

        holder.orderIDpopulate.setText(this.passedInArrayList.get(position).getOrderID());


        // setting up which color to use depending on the order status in mdata.
        try {

            switch (this.passedInArrayList.get(position).getOrderStatus()) {
                case "Pending":
                    holder.orderStatusImage.
                            setCompoundDrawables(holder.imageYellow, null, null, null);
                    break;
                case "Approved":
                    holder.orderStatusImage.
                            setCompoundDrawables(holder.imageBlue, null, null, null);
                    break;
                case "Canceled":
                    holder.orderStatusImage.
                            setCompoundDrawables(holder.imageRed, null, null, null);
                    break;
                case "Complete":
                    holder.orderStatusImage.
                            setCompoundDrawables(holder.imageGreen, null, null, null);
                    break;
            }

        } catch (Exception e) {
            holder.orderStatusImage.
                    setCompoundDrawables(holder.imageYellow, null, null, null);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        try {
            return this.passedInArrayList.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView orderIDpopulate;
        TextView orderStatusImage;

        Drawable imageYellow;
        Drawable imageGreen;
        Drawable imageBlue;
        Drawable imageRed;

        ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "setting text values viewHolder");

            orderIDpopulate = itemView.findViewById(R.id.OrderIDPopulate);
            orderStatusImage = itemView.findViewById(R.id.OrderStatusIconPopulate);

            imageYellow = itemView.getContext().getResources().getDrawable(R.drawable.yellowbutton);
            int h = imageYellow.getIntrinsicWidth();
            int w = imageYellow.getIntrinsicWidth();
            imageYellow.setBounds(1, 1, w, h);

            imageGreen = itemView.getContext().getResources().getDrawable(R.drawable.greenbutton);
            int he = imageGreen.getIntrinsicWidth();
            int wi = imageGreen.getIntrinsicWidth();
            imageGreen.setBounds(1, 1, he, wi);

            imageBlue = itemView.getContext().getResources().getDrawable(R.drawable.bluebutton);
            int hei = imageBlue.getIntrinsicWidth();
            int wid = imageBlue.getIntrinsicWidth();
            imageBlue.setBounds(1, 1, hei, wid);

            imageRed = itemView.getContext().getResources().getDrawable(R.drawable.redbutton);
            int heigh = imageRed.getIntrinsicWidth();
            int widt = imageRed.getIntrinsicWidth();
            imageRed.setBounds(1, 1, heigh, widt);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // create the fragment and load it as a replacement to the activitys frame to cover
            // all aspects
            Fragment orderFragment = new OrderFragment();
            Order order = passedInArrayList.get(this.getAdapterPosition());
            Log.d(TAG, "In ONCLICK with Order ID clicked: " +
                    passedInArrayList.get(this.getAdapterPosition()).getOrderID() +
                    " and order ID" + order.getOrderID());

            //Adding data to bundle to pass on to the fragment class for population.
            Bundle bundle = new Bundle();
            bundle.putParcelable("OrderClicked", order);
            orderFragment.setArguments(bundle);

            //get reference to calling activity to utilize getsupportfragmentmanager method
            AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
            appCompatActivity.getSupportFragmentManager().beginTransaction().add(R.id.OrderStatusFrame,
                    orderFragment).commit();
            Log.d(TAG, "after order fragment inflation");
        }
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        void onItemClick(View view, int position);
    }
/*
    //Get the order from the position that was clicked that has a populated OrderID
    //TODO need to add more (like a composite key with customerkey and orderID) for clicked order
    private Order getOrder(String orderID) {
        Log.d(TAG, "ItemClicked with orderID 19283aererterrtdfsa74");
        db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("Orders");
        final Query query =collectionReference.whereEqualTo("Orders", orderID );
        Log.d(TAG, "Getting query now");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "task was successful");
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        Order orderReturned2 = document.toObject(Order.class);
                        copyOutOrder(orderReturned2);
                    }
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Task failed "+e.getCause());
            }
        });
        return this.ordertemp;
    }
*/

}