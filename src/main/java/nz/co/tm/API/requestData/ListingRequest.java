package nz.co.tm.API.requestData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class ListingRequest {

        @JsonProperty("Category")
        private String category;
        @JsonProperty("Title")
        private String title;
        @JsonProperty("Description")
        private ArrayList<String> description;
        @JsonProperty("StartPrice")
        private String startPrice;
        @JsonProperty("BuyNowPrice")
        private String buyNowPrice;
        @JsonProperty("Duration")
        private int duration;
        @JsonProperty("Pickup")
        private int pickup;
        @JsonProperty("IsBrandNew")
        private String isBrandNew;
        @JsonProperty("ShippingOptions")
        private ArrayList<ShippingOption> shippingOptions;
        @JsonProperty("PaymentMethods")
        private ArrayList<Integer> paymentMethods;

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public ArrayList<String> getDescription() {
                return description;
        }

        public void setDescription(ArrayList<String> description) {
                this.description = description;
        }

        public String getStartPrice() {
                return startPrice;
        }

        public void setStartPrice(String startPrice) {
                this.startPrice = startPrice;
        }

        public String getBuyNowPrice() {
                return buyNowPrice;
        }

        public void setBuyNowPrice(String buyNowPrice) {
                this.buyNowPrice = buyNowPrice;
        }

        public int getDuration() {
                return duration;
        }

        public void setDuration(int duration) {
                this.duration = duration;
        }

        public int getPickup() {
                return pickup;
        }

        public void setPickup(int pickup) {
                this.pickup = pickup;
        }

        public String getIsBrandNew() {
                return isBrandNew;
        }

        public void setIsBrandNew(String isBrandNew) {
                this.isBrandNew = isBrandNew;
        }

        public ArrayList<ShippingOption> getShippingOptions() {
                return shippingOptions;
        }

        public void setShippingOptions(ArrayList<ShippingOption> shippingOptions) {
                this.shippingOptions = shippingOptions;
        }

        public ArrayList<Integer> getPaymentMethods() {
                return paymentMethods;
        }

        public void setPaymentMethods(ArrayList<Integer> paymentMethods) {
                this.paymentMethods = paymentMethods;
        }


}
