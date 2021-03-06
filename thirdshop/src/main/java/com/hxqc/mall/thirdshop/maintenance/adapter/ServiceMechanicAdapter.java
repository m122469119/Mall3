package com.hxqc.mall.thirdshop.maintenance.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxqc.mall.core.util.ImageUtil;
import com.hxqc.mall.thirdshop.R;
import com.hxqc.mall.thirdshop.maintenance.model.Mechanic;
import com.hxqc.mall.thirdshop.maintenance.views.SelectableRoundedImageView;
import com.hxqc.mall.thirdshop.maintenance.views.ServiceMechanicDialog;

import java.util.ArrayList;

/**
 * Author :胡仲俊
 * Date : 2016-03-09
 * FIXME
 * Todo 预约服务技师
 */
public class ServiceMechanicAdapter extends RecyclerView.Adapter<ServiceMechanicAdapter.SelectCounselorViewHolder> {

    private LayoutInflater mLayoutInflater;
    private SelectCounselorClickListener mSelectCounselorClickListener = null;
    private int isChecked = -1; //默认选中的标识
    private ArrayList<Mechanic> mServiceMechanics;
    private Context mContext;

    public ServiceMechanicAdapter(Context context, ArrayList<Mechanic> serviceMechanic) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mServiceMechanics = serviceMechanic;

    }

    public void notifyData(ArrayList<Mechanic> serviceMechanic) {
        this.mServiceMechanics = serviceMechanic;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(SelectCounselorClickListener selectCounselorClickListener) {
        this.mSelectCounselorClickListener = selectCounselorClickListener;
    }

    @Override
    public ServiceMechanicAdapter.SelectCounselorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_select_counselor, parent, false);
        SelectCounselorViewHolder selectCounselorViewHolder = new SelectCounselorViewHolder(view);

        return selectCounselorViewHolder;
    }

    public int getIsChecked() {
        return isChecked;
    }

    /**
     * 更改选择标识
     *
     * @param isChecked
     * @return
     * @throws
     */
    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public void onBindViewHolder(final SelectCounselorViewHolder holder, final int position) {
        if (position == isChecked) {
//            holder.mCounselorCheckView.setSelected(true);
            holder.mCounselorCheckView.setVisibility(View.VISIBLE);
            holder.mCounselorParentInfoView.setBackgroundResource(R.drawable.shape_border_orange);
        } else {
//            holder.mCounselorCheckView.setSelected(false);
            holder.mCounselorCheckView.setVisibility(View.GONE);
            holder.mCounselorParentInfoView.setBackgroundResource(R.drawable.shape_border_transparent);
        }
        ImageUtil.setImage(mContext, holder.mCounselorImgView, mServiceMechanics.get(position).avatar, R.drawable.ic_productcomment_list_user);

        holder.mCounselorNameView.setText(mServiceMechanics.get(position).name);
        holder.mCounselorInfoView.setText(mServiceMechanics.get(position).station);
        holder.mCounselorLogosView.setText(mServiceMechanics.get(position).info);

        holder.mCounselorParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选中效果在cardview的background里,单选判定可以放到moder里,右上角的三角形对钩还没有切图
                mSelectCounselorClickListener.onItemClick(v, position);
            }
        });

        holder.mCounselorImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceMechanicDialog serviceMechanicDialog = new ServiceMechanicDialog(mContext, mServiceMechanics.get(position));
                serviceMechanicDialog.build();
                serviceMechanicDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mServiceMechanics == null ? 0 : mServiceMechanics.size();
    }

    public interface SelectCounselorClickListener {
        void onItemClick(View view, int position);
    }

    class SelectCounselorViewHolder extends RecyclerView.ViewHolder {

        private SelectableRoundedImageView mCounselorImgView;
        private TextView mCounselorNameView;
        private ImageView mCounselorCheckView;
        private TextView mCounselorInfoView;
        private TextView mCounselorLogosView;
        private CardView mCounselorParentView;
        private RelativeLayout mCounselorParentInfoView;

        public SelectCounselorViewHolder(View itemView) {
            super(itemView);
            mCounselorImgView = (SelectableRoundedImageView) itemView.findViewById(R.id.select_counselor_img);
            mCounselorParentView = (CardView) itemView.findViewById(R.id.select_counselor_parent);
            mCounselorParentInfoView = (RelativeLayout) itemView.findViewById(R.id.select_counselor_parent_info);
            mCounselorNameView = (TextView) itemView.findViewById(R.id.select_counselor_name);
            mCounselorCheckView = (ImageView) itemView.findViewById(R.id.select_counselor_check);
            mCounselorInfoView = (TextView) itemView.findViewById(R.id.select_counselor_info);
            mCounselorLogosView = (TextView) itemView.findViewById(R.id.select_counselor_logos);
        }
    }
}
