package Classes;


import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Operation {
    private long id;
    private LocalDate dateOfOperation;
    private String accCode;
    private long accIn;
    private long accOut;
    private BigDecimal amount;
    private BigDecimal amountBefore;
    private BigDecimal amountAfter;

    public Operation(LocalDate dateOfOperation, String accCode, long accIn, long accOut,
                     BigDecimal amount, BigDecimal amountBefore, BigDecimal amountAfter) {
        this.dateOfOperation = dateOfOperation;
        this.accCode = accCode;
        this.accIn = accIn;
        this.accOut = accOut;
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }

    public Operation(long id, LocalDate dateOfOperation, String accCode, long accIn, long accOut,
                     BigDecimal amount, BigDecimal amountBefore, BigDecimal amountAfter) {
        this.id = id;
        this.dateOfOperation = dateOfOperation;
        this.accCode = accCode;
        this.accIn = accIn;
        this.accOut = accOut;
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(LocalDate dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public long getAccIn() {
        return accIn;
    }

    public void setAccIn(long accIn) {
        this.accIn = accIn;
    }

    public long getAccOut() {
        return accOut;
    }

    public void setAccOut(long accOut) {
        this.accOut = accOut;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountBefore() {
        return amountBefore;
    }

    public void setAmountBefore(BigDecimal amountBefore) {
        this.amountBefore = amountBefore;
    }

    public BigDecimal getAmountAfter() {
        return amountAfter;
    }

    public void setAmountAfter(BigDecimal amountAfter) {
        this.amountAfter = amountAfter;
    }
}
