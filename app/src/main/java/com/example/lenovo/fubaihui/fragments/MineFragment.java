package com.example.lenovo.fubaihui.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.fubaihui.R;
import com.example.lenovo.fubaihui.mainactivity.AddressActivity;
import com.example.lenovo.fubaihui.mainactivity.CollectionActivity;
import com.example.lenovo.fubaihui.mainactivity.DataActivity;
import com.example.lenovo.fubaihui.mainactivity.DiscountActivity;
import com.example.lenovo.fubaihui.mainactivity.FriendActivity;
import com.example.lenovo.fubaihui.mainactivity.OrderActivity;
import com.example.lenovo.fubaihui.mainactivity.RecommendActivity;
import com.example.lenovo.fubaihui.mainactivity.RecommendNumActivity;
import com.example.lenovo.fubaihui.mainactivity.SettledinActivity;
import com.example.lenovo.fubaihui.mainactivity.WalletActivity;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.yiyatech.utils.SharedPrefrenceUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
    private static final String USER = "user";
    @BindView(R.id.iv_mine_image)
   ImageView ivMineImage;
   @BindView(R.id.tv_mine_name)
   TextView tvMineName;
   @BindView(R.id.tv_mine_account)
   TextView tvMineAccount;
   @BindView(R.id.ll_mine_data)
   LinearLayout llMineData;
   @BindView(R.id.ll_mine_discount)
   LinearLayout llMineDiscount;
   @BindView(R.id.ll_mine_setting)
   LinearLayout llMineSetting;
   @BindView(R.id.ll_mine_recommend)
   LinearLayout llMineRecommend;
   @BindView(R.id.ll_mine_recommend_num)
   LinearLayout llMineRecommendNum;
   @BindView(R.id.ll_mine_address)
   LinearLayout llMineAddress;
   @BindView(R.id.ll_mine_settled_in)
   LinearLayout llMineSettledIn;
   @BindView(R.id.ll_mine_login)
   LinearLayout llMineLogin;
   @BindView(R.id.ll_mine_phone)
   LinearLayout llMinePhone;
   @BindView(R.id.ll_mine_friend)
   TextView llMineFriend;
   @BindView(R.id.ll_mine_collection)
   TextView llMineCollection;
   @BindView(R.id.ll_mine_wallet)
   TextView llMineWallet;
   @BindView(R.id.ll_mine_order)
   TextView llMineOrder;
    private String mPhone;
    private String phonename;
   Unbinder unbinder;


    public static MineFragment newInstance() {
      MineFragment fragment = new MineFragment();
      Bundle bundle = new Bundle();
      fragment.setArguments(bundle);
      return fragment;
   }


   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_mine, container, false);
      unbinder = ButterKnife.bind(this, view);
      return view;
   }

   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
      initView();
      getPermission();
   }

    private void getPermission() {
        XXPermissions.with(getActivity())
                .constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES)
                // 支持请求 6.0 悬浮窗权限 8.0 请求安装权限
                .permission(Manifest.permission.CAMERA, Manifest.permission
                        .CALL_PHONE)
                //不指定权限则自动获取清单中的危险权限
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (denied.size() != 0) Toast.makeText(getActivity(), "拒绝权限影响您正常使用", Toast.LENGTH_SHORT).show();
                    }
                });
        //XXPermissions.gotoPermissionSettings(this);//跳转到权限设置页面
    }

   private void initView() {
      EventBus.getDefault().register(this);
      Glide.with(getActivity()).load(R.drawable.ic_fubaihui)
          .circleCrop()
          .into(ivMineImage);
       String phone = SharedPrefrenceUtils.getString(getContext(), USER);
       tvMineAccount.setText(phone);
   }

   @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
   public void eventBus(String data1){
      Glide.with(getActivity()).load(data1).circleCrop().into(ivMineImage);
      SharedPreferences user = getContext().getSharedPreferences("user",getContext().MODE_PRIVATE);
      SharedPreferences.Editor edit = user.edit();
      edit.putBoolean("isBoolean",true);
      edit.putString("path",data1);
      edit.commit();
   }

   @OnClick({R.id.ll_mine_data, R.id
       .ll_mine_discount, R.id.ll_mine_setting, R.id.ll_mine_recommend, R.id
       .ll_mine_recommend_num, R.id.ll_mine_address, R.id.ll_mine_settled_in, R.id
       .ll_mine_login, R.id.ll_mine_phone, R.id.ll_mine_friend, R.id.ll_mine_collection, R.id
       .ll_mine_wallet, R.id.ll_mine_order})
   public void onClick(View view) {
       phonename = tvMineAccount.getText().toString();
      switch (view.getId()) {
         case R.id.ll_mine_data:
            startActivity(new Intent(getActivity(),DataActivity.class));
            break;
         case R.id.ll_mine_discount:
            startActivity(new Intent(getActivity(),DiscountActivity.class));
            break;
         case R.id.ll_mine_setting:
            startActivity(new Intent(getActivity(),DataActivity.class));
            break;
         case R.id.ll_mine_recommend:
            startActivity(new Intent(getActivity(),RecommendActivity.class));
            break;
         case R.id.ll_mine_recommend_num:
            startActivity(new Intent(getActivity(),RecommendNumActivity.class));
            break;
         case R.id.ll_mine_address:
            startActivity(new Intent(getActivity(),AddressActivity.class));
            break;
         case R.id.ll_mine_settled_in:
            startActivity(new Intent(getActivity(),SettledinActivity.class));
            break;
         case R.id.ll_mine_login:
            //登录
            break;
         case R.id.ll_mine_phone:
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + "18434916114");
            intent.setData(data);
            startActivity(intent);
            break;
         case R.id.ll_mine_friend:
            startActivity(new Intent(getActivity(),FriendActivity.class));
            break;
         case R.id.ll_mine_collection:
            startActivity(new Intent(getActivity(),CollectionActivity.class));
            break;
         case R.id.ll_mine_wallet:
            startActivity(new Intent(getActivity(),WalletActivity.class));
            break;
         case R.id.ll_mine_order:
            startActivity(new Intent(getActivity(),OrderActivity.class));
            break;
      }
   }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences user = getContext().getSharedPreferences("user",getContext().MODE_PRIVATE);
        String path = user.getString("path", null);
        Boolean isBoolean = user.getBoolean("isBoolean",false);
        if(isBoolean==true){
            Glide.with(getContext()).load(Uri.parse(path)).circleCrop().into(ivMineImage);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
