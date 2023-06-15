import java.util.*;
import java.util.stream.LongStream;


public class Bank {

    private Set<Account> accounts = new HashSet<>();

    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return random.nextBoolean();
    }
    public void addAccount(Account c){
       accounts.add(c);
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account from = findAccount(fromAccountNum);
        Account to = findAccount(toAccountNum);
        if (from.isBlocked || to.isBlocked ){
            System.err.println("Один из участников транзакции заблокирован");
        }else {
            if (amount > 50000){
                if (isFraud(fromAccountNum, toAccountNum,amount)){
                    System.err.printf("Операция заблокирована. Счет %s и %s заблокированы",fromAccountNum, toAccountNum);
                    from.block();
                    to.block();
                }else {
                    doTransfer(from, to , amount);
                }
            }else{
                doTransfer(from, to , amount);
            }
        }
    }
    private Account findAccount(String number){
        return accounts.stream()
                .filter(account -> account.getAccNumber().equals(number))
                .findFirst().get();
    }
    private synchronized void doTransfer(Account from, Account to, long amount){
        long newBalance=from.getMoney()-amount;
        from.setMoney(newBalance);
        newBalance=to.getMoney()+amount;
        to.setMoney(newBalance);
        System.out.println("Успешный перевод");
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
            return findAccount(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        return accounts.stream()
                .flatMapToLong(a-> LongStream.of(a.getMoney()))
                .sum();
    }
}
