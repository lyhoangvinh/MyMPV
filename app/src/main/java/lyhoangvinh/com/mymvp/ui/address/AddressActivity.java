package lyhoangvinh.com.mymvp.ui.address;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import lyhoangvinh.com.mymvp.model.base.activity.BaseActivity;
import lyhoangvinh.com.mymvp.model.object.Address;
import lyhoangvinh.com.mymvp.ui.adapter.AddressAdapter;

/**
 * Created by LyHoangVinh on 21/03/2018.
 */

public class AddressActivity extends BaseActivity implements AddressView {

    @BindView(R.id.rcvAddress)
    RecyclerView rcv;

    @BindView(R.id.tv)
    TextView tv;

    private AddressPresenter presenter;
    private Realm realm;
    private List<Address> listAdd;

    @Override
    protected int getLayout() {
        return R.layout.activity_address;
    }

    @Override
    protected void bind() {
        realm = Realm.getDefaultInstance();
        listAdd = new ArrayList<>();
        presenter = new AddressPresenter(this, this);
        assert rcv != null;
        rcv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(AddressAdapter.getInstance(listAdd));
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
        listAdd.clear();
        AddressAdapter.getInstance(listAdd).notifyDataSetChanged();
    }

    @Optional
    @OnClick(R.id.btnRx)
    public void rxJavaGetData(){
        presenter.loadDataRx();
    }

    @Override
    public void loadAddressManager(List<Address> list) {
        if (list != null) {
            listAdd.clear();
            listAdd.addAll(list);
            AddressAdapter.getInstance(listAdd).notifyDataSetChanged();
            showToastOk("Get Data Complete");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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
        if (!isNetworkAvailable(this)) {
            RealmResults<Address> addresses = realm.where(Address.class).findAll();
            if (addresses.size() == 0) {
                showToastError("Null");
            } else {
                listAdd.clear();
                listAdd.addAll(addresses);
                AddressAdapter.getInstance(listAdd).notifyDataSetChanged();
                showToastInfo("Realm: " + addresses.size());
            }
        } else {
            presenter.loadDataRetrofit();
        }
    }

}
