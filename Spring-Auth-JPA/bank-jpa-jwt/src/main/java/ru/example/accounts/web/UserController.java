package ru.example.accounts.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.example.accounts.backend.dao.OperationRepository;
import ru.example.accounts.backend.dao.UserRepository;
import ru.example.accounts.backend.model.*;
import ru.example.accounts.backend.security.JwtToken;
import ru.example.accounts.backend.service.AccountService;
import ru.example.accounts.backend.service.JwtUserDetailsService;
import ru.example.accounts.backend.dao.AccountRepository;
import ru.example.accounts.backend.service.UserService;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

/**
 * Account controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtToken jwtToken;
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    private static Map<String, Float> converter;


    private static void initConverter(){
        converter = new HashMap<>();
        converter.put("RUB", 1f);
        converter.put("USD", 65.21f);
        converter.put("EUR", 70.95f);
    }

    @Autowired
    public UserController(UserService service, AuthenticationManager authenticationManager,
                          UserRepository userRepository, JwtUserDetailsService jwtUserDetailsService,
                          JwtToken jwtToken, AccountRepository accountRepository,
                          OperationRepository operationRepository) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtToken = jwtToken;
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
        initConverter();
    }


    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getLogin());
        final String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/signup")
    public Boolean create(@RequestBody User body) throws  ValidationException {
        if (userRepository.existsUserByLogin(body.getLogin()) ||
            userRepository.existsUserByPhone(body.getPhone())) {
            throw new ValidationException("Username already existed (login or phone)");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(body.getPassword());
        userRepository.save(new User(body.getPhone(), body.getLogin(), encodedPassword, body.getAddress()));
        return true;
    }

    private static boolean checkCurrency(String accCode) {
        List<String> currency = new ArrayList<String>();
        currency.add("RUB");
        currency.add("USD");
        currency.add("EUR");
        return currency.contains(accCode);
    }

    @PostMapping("/createAccount")
    @ApiOperation("Create account for user")
    public Boolean createAccount(BigDecimal amount,
                              @RequestParam(name = "accCode", required = false, defaultValue = "RUB") String accCode,
                              boolean defAccount) throws  ValidationException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        if (!checkCurrency(accCode)) {
            throw new ValidationException("Currency not found");
        }

        User user = userRepository.findByLogin(username);
        if(user.checkAccCodeInAccountList(accCode)){
            throw new ValidationException("Account with this currency already exists by user");
        }
        Account account = new Account(amount, accCode, defAccount);
        if (user.getAccountList().isEmpty()){
            account.setDefAccount(true);
        }
        if (account.isDefAccount()) {
            for (Account accountUser : user.getAccountList()) {
                if (accountUser.isDefAccount()) {
                    accountUser.setDefAccount(false);
                }
            }
        }
        accountRepository.save(account);
        user.setAccountList(account);
        userRepository.save(user);
        return true;
    }

    @PostMapping("/addMoneyToAccount")
    @ApiOperation("Add money")
    public boolean addMoney(BigDecimal money, String accCode) throws ValidationException{
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = service.getThroughName(username);
        Account inAccount = user.getAccountWithAccCode(accCode);
        if (inAccount == null){
            throw new ValidationException("Account not found");
        }
        if (money.doubleValue() < 0){
            throw new ValidationException("Money must be >= 0");
        }
        inAccount.setAmount(new BigDecimal(inAccount.getAmount().doubleValue() + money.doubleValue()));
        accountRepository.save(inAccount);
        return true;
    }

    @PostMapping("/transferMoney")
    @ApiOperation("Transfer money other user")
    public boolean addMoney(String accCode, BigDecimal money, String phone) throws ValidationException, ParseException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = service.getThroughName(username);
        if (user.getAccountList().isEmpty()){
            throw new ValidationException("User have not accounts");
        }
        Account account = null;
        if (user.checkAccCodeInAccountList(accCode)){
            account = user.getAccountWithAccCode(accCode);
        }
        else{
            account = user.getDefaultAccount();
        }
        String currentCurrency = account.getAccCode();
        if (!checkCurrency(accCode)){
            throw new ValidationException("Currency not found");
        }
        BigDecimal amountRub = new BigDecimal(money.doubleValue() * converter.get(currentCurrency));
        // Рассчитать остаток на счете
        BigDecimal amountBefore = account.getAmount();
        BigDecimal amountBalance = new BigDecimal(account.getAmount().doubleValue()
                * converter.get(account.getAccCode())
                - amountRub.doubleValue());
        if (amountBalance.doubleValue() < 0){
            throw new ValidationException("No money for transfer");
        }

        User userOut = service.get(phone);
        if (userOut == null){
            throw new ValidationException("No user with this phone");
        }

        Account accOut = userOut.getDefaultAccount();
        // Если нет у получателя нет дефолтного акка
        if (accOut == null){
            throw new ValidationException("User 2 have not accounts");
        }
        // Получить дату
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        LocalDate time = date.toInstant().atZone(ZoneOffset.ofHours(4)).toLocalDate();
        // Обновить счет отправителя
        account.setAmount(new BigDecimal(amountBalance.doubleValue() / converter.get(account.getAccCode())));
        // Обновить счет получателя
        double tmp = accOut.getAmount().doubleValue() * converter.get(accOut.getAccCode());
        BigDecimal addBalance = new BigDecimal((tmp + amountRub.doubleValue())
                / converter.get(accOut.getAccCode()));
        accOut.setAmount(addBalance);
        accountRepository.save(accOut);
        // Добавление в журнал операций
        Operation operation = new Operation(userOut.getPhone(), money,
                amountBefore, account.getAmount(), time, accCode);
        account.setOperationList(operation);
        accountRepository.save(account);
        operationRepository.save(operation);
        return true;
    }
    /**
     * Gets account operation.
     *
     * @param phone the phone
     * @return the account
     */
    @GetMapping("/info/{phone}")
    @ApiOperation("get user")
    public User getUser(@PathVariable("phone") String phone) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        System.out.println(username);
        return service.get(phone);
    }

    /**
     * Delete account operation.
     *
     * @param phone the phone
     * @return the account
     */
    @DeleteMapping("/delete/{phone}")
    @ApiOperation("delete user")
    public void deleteAccount(@PathVariable("phone") String phone) {

        service.delete(phone);
    }


    /**
     * Gets account list operation.
     *
     * @return the account
     */
    @GetMapping("/info/all")
    @ApiOperation("get all user")
    public List<User> getUser() {
        return service.getAll();
    }

    @GetMapping("/operations/all")
    @ApiOperation("Get all user's operations")
    public List<Operation> getOperations() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        System.out.println(username);
        User user = service.getThroughName(username);
        Set<Account> accounts = user.getAccountList();
        List<Operation> operations = new ArrayList<>();
        for (Account acc: accounts) {
            operations.addAll(acc.getOperationList());
        }
        return operations;
    }

    @GetMapping("/accounts/all")
    @ApiOperation("Get all user's accounts")
    public Set<Account> getAccounts() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        System.out.println(username);
        User user = service.getThroughName(username);
        return user.getAccountList();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

}
