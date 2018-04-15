package lyhoangvinh.com.mymvp.base.response;

import lyhoangvinh.com.mymvp.Constant.ConstantsApi;


public class BaseResponse<T> extends BaseEntity<T> {

    @Override
    public boolean isSuccess() {
        return this.getStatus() != null
                && (this.getStatus().equalsIgnoreCase(ConstantsApi.STATUS_SUCCESS));
    }
}