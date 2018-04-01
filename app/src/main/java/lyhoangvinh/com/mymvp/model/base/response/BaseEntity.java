package lyhoangvinh.com.mymvp.model.base.response;

public abstract class BaseEntity<T> {

    String status;

    String message;

    T data;

    public abstract boolean isSuccess();


    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}