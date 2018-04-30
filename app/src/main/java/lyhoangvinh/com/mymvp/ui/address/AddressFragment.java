package lyhoangvinh.com.mymvp.ui.address;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import lyhoangvinh.com.mymvp.R;
import lyhoangvinh.com.mymvp.base.fragment.BaseFragment;
import lyhoangvinh.com.mymvp.base.view.ErrorEntity;
import lyhoangvinh.com.mymvp.model.Address;
import lyhoangvinh.com.mymvp.ui.adapter.AddressAdapter;
import lyhoangvinh.com.mymvp.ui.login.LoginActivity;
import lyhoangvinh.com.mymvp.utils.crop.Functions;

/**
 * Created by LyHoangVinh on 15/04/2018.
 */
public class AddressFragment extends BaseFragment implements AddressView {

    @BindView(R.id.rcvAddress)
    RecyclerView rcv;

    @BindView(R.id.tv)
    TextView tv;

    private AddressPresenter presenter;
    private Realm realm;

    @Override
    protected int getLayout() {
        return R.layout.fragment_address;
    }

    @Override
    protected void bind(View view) {
        realm = Realm.getDefaultInstance();
        presenter = new AddressPresenter(getActivity(), this);
        assert rcv != null;
        rcv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        if (Functions.getUser() != null) {
            tv.setText(Functions.getUser().getFullName());
        }
    }

    @Optional
    @OnClick(R.id.btnVolley)
    public void volley() {
        presenter.loadDataVolley();
    }

    @Optional
    @OnClick(R.id.btnRetrofit)
    public void retrofit() {
        presenter.loadDataTest();
    }

    @Optional
    @OnClick(R.id.btnClear)
    public void clear() {
        AddressAdapter.getInstance(new ArrayList<>());
    }

    @Optional
    @OnClick(R.id.btnRx)
    public void rxJavaGetData() {
        presenter.loadDataRx();
    }

    @Optional
    @OnClick(R.id.btnLogout)
    public void logout() {
        Functions.clearData();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void loadAddressManager(List<Address> list) {
        if (list != null) {
            rcv.setAdapter(AddressAdapter.getInstance(list));
            showToastOk("Get Data Complete");
        }
    }

    private void saveToDatabase(List<Address> list) {
        realm.executeTransactionAsync(realm -> {
            Address a = new Address();
            RealmList<Address> realmList = new RealmList<>();
            realmList.addAll(list);
//            a.setLabel(realmList.get(i).getLabel());
            realm.insert(a);

        }, () -> {
            showToastInfo("Realm Save Success!");
        }, error -> {
            showToastError("Save Error");
        });

    }

    private void refreshData() {
        RealmResults<Address> results = realm.where(Address.class).findAllAsync();
        results.load();
        String output = "";

        for (Address address : results) {
            output += address.toString();
        }
        showToastInfo(output);
    }

    private void deleteFromData(int id) {
        RealmResults<Address> results = realm.where(Address.class).equalTo("id", id).findAll();
        realm.executeTransaction(realm -> results.deleteFromRealm(0));
    }

    private void deleteFullData() {
        RealmResults<Address> results = realm.where(Address.class).findAll();
        realm.executeTransaction(realm -> results.deleteAllFromRealm());
    }

    private void getData() {
        if (!isNetworkAvailable(getActivity())) {
            RealmResults<Address> addresses = realm.where(Address.class).findAll();
            if (addresses.size() == 0) {
                showToastError("Null");
            } else {
                AddressAdapter.getInstance(addresses).notifyDataSetChanged();
                showToastInfo("Realm: " + addresses.size());
            }
        } else {
            presenter.loadDataRetrofit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onError(ErrorEntity errorEntity) {

    }
}

