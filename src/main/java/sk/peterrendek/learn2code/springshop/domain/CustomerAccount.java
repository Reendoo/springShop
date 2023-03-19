package sk.peterrendek.learn2code.springshop.domain;

import java.util.Objects;

public class CustomerAccount {
    private int customerId;
    private double money;

    public CustomerAccount() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAccount that = (CustomerAccount) o;
        return customerId == that.customerId && Double.compare(that.money, money) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, money);
    }
}
