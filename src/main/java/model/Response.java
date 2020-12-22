package model;

public class Response <D> {
    private boolean status;
    private String message;
    private D productDetails;

    public Response(boolean status, String message, D productDetails) {
        this.status = status;
        this.message = message;
        this.productDetails = productDetails;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(D productDetails) {
        this.productDetails = productDetails;
    }
}
