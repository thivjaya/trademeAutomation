package nz.co.tm.API.requestData;

import com.fasterxml.jackson.annotation.JsonProperty;
public class ShippingOption {
    @JsonProperty("Type")
    public int type;
    @JsonProperty("Price")
    public double price;
    @JsonProperty("Method")
    public String method;
    @JsonProperty("ShippingId")
    public int shippingId;
}
