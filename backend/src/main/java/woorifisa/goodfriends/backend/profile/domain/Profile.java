package woorifisa.goodfriends.backend.profile.domain;

import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import woorifisa.goodfriends.backend.global.common.BaseCreateTimeEntity;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.*;

@SuperBuilder
@Table(name = "profiles")
@Entity
public class Profile extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "mobile_phone")
    private String mobileNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "account_number")
    private String accountNumber;

    protected Profile() {
    }

    public Profile(User user, String mobileNumber, String address, AccountType accountType, String accountNumber) {
        this.user = user;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }
    public Long getId() {
        return id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void updateMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public void updateAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
