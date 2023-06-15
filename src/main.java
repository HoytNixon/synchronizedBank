public class main {
    public static void main(String[] args) {
        Account a =new Account();
        a.setMoney(24);
        a.setAccNumber("1");
        Account a1 =new Account();
        a1.setMoney(26);
        a1.setAccNumber("2");
        Bank b = new Bank();
        b.addAccount(a);
        b.addAccount(a1);
        System.out.println(b.getSumAllAccounts());
    }
}
