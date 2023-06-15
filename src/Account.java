public class Account {

    private volatile long money;
    private String accNumber;
    public volatile boolean isBlocked = false;

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }
    public void block(){
        isBlocked = true;
    }
}
