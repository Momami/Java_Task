package Classes;

import java.math.BigDecimal;

public class Account {
    private long id = -1;
    private long idUser;
    private BigDecimal amount = new BigDecimal(0);
    private String accCode;
    private boolean defAccount = false;

    public Account(long idUser, String accCode, boolean defAccount) {
        this.idUser = idUser;
        this.accCode = accCode;
        this.defAccount = defAccount;
    }

    public Account(long id, long idUser, String accCode, boolean defAccount) {
        this.id = id;
        new Account(idUser, accCode, defAccount);
    }

    public Account(long id, long idUser, BigDecimal amount, String accCode, boolean defAccount) {
        this.id = id;
        this.idUser = idUser;
        this.amount = amount;
        this.accCode = accCode;
        this.defAccount = defAccount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public boolean isDefAccount() {
        return defAccount;
    }

    public void setDefAccount(boolean defAccount) {
        this.defAccount = defAccount;
    }
}
