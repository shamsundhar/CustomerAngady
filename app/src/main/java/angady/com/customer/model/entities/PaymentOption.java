package angady.com.customer.model.entities;

/**
 * Created by shyam on 3/6/2018.
 */

public class PaymentOption {
    public static enum PAYMENT_OPTION {CARD_ON_DELIVERY, CASH_ON_DELIVERY, PAYTM_ON_DELIVERY};
    private PAYMENT_OPTION selectedPaymentOption;

    public PAYMENT_OPTION getSelectedPaymentOption() {
        return selectedPaymentOption;
    }

    public void setSelectedPaymentOption(PAYMENT_OPTION selectedPaymentOption) {
        this.selectedPaymentOption = selectedPaymentOption;
    }

}
